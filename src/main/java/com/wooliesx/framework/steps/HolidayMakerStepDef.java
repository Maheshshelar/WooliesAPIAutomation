package com.wooliesx.framework.steps;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooliesx.framework.api.OpenWeatherMapApi;
import com.wooliesx.framework.api.vo.ForcastModel;
import com.wooliesx.framework.api.vo.Temperature;
import com.wooliesx.framework.api.vo.WeatherConditions;
import com.wooliesx.framework.util.DateUtil;
import com.wooliesx.framework.util.TemperatureUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HolidayMakerStepDef<JSONObject> {

    private static ForcastModel forcastModel;
    private static List<WeatherConditions> MonFriWeatherConditions;
    private static List<WeatherConditions> TempRangeWeatherConditions;
    private static List<WeatherConditions> WindSpeedWeatherConditions;
    private static List<WeatherConditions> UvIndexWeatherConditions;
    @Given("^I like to surf in any of 2 beaches (\\d+) of Sydney$")


    public void iLikeToSurfInAnyOf2BeachesOfSydney(int top) throws IOException {
        OpenWeatherMapApi openWeatherMapApi = new OpenWeatherMapApi();
        Response response = openWeatherMapApi.getForcastByCity("Sydney", "AU");
        String responseString = response.getBody().asString();

        BufferedWriter writer = new BufferedWriter(new FileWriter("SydneyWeatherCondition.json", true));
        writer.append(responseString);
        writer.close();
        System.out.println(responseString);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            forcastModel = objectMapper.readValue(responseString, ForcastModel.class);
            System.out.println(forcastModel.getcityname());

            Assert.assertTrue(forcastModel.getcityname().equalsIgnoreCase("Sydney"), "API response contain weather condition for sydney beached");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @And("^I only like to surf on \"([^\"]*)\" & \"([^\"]*)\" in next 3 months$")
    public void iOnlyLikeToSurfOnMONFRIDAY(String surfDays1, String surfDay2) throws Throwable {
        List<WeatherConditions> weatherConditionsList = forcastModel.getWeatherConditionsList();
        MonFriWeatherConditions = new ArrayList<>();

        for (WeatherConditions weatherCondition : weatherConditionsList) {
            String dateAsString = weatherCondition.getDt_txt(); // e.g. format "2020-08-22 06:00:00"

            boolean isSurfDay = DateUtil.isSurfDay(dateAsString, surfDays1, surfDay2);
            if (isSurfDay) {
                MonFriWeatherConditions.add(weatherCondition);
            }
        }
    }


    @Then("^I check to if see the temperature is between <(\\d+) and (\\d+)>$")
    public void iCheckToIfSeeTheTemperatureIsBetweenCAndC(int minTem, int maxTem) {
        List<WeatherConditions> weatherConditionsList = forcastModel.getWeatherConditionsList();

        boolean isTempFavourable = true;
        TempRangeWeatherConditions = new ArrayList<>();
        for (WeatherConditions weatherConditions : MonFriWeatherConditions) {
            Temperature temperature = weatherConditions.getTemperature();
            float tempInKelvin = temperature.getTemp();
            float tempInCelsius = TemperatureUtil.convertToCelsius(tempInKelvin);
            if(tempInCelsius>minTem && tempInCelsius<maxTem)
            {
                isTempFavourable = false;
                break;
            }
            if (!isTempFavourable) {
                System.out.println("Temperature not between min and max temp");
            }
            Assert.assertTrue(isTempFavourable, "Temp is  between "+ minTem + "and" +maxTem);
            TempRangeWeatherConditions.add(weatherConditions);
        }
    }

    @And("^I check wind speed to be between (\\d+) and (\\d+)$")
    public void iCheckWindSpeedToBeBetweenAnd(int windSpeedMin, int windSpeedMax) {
        WindSpeedWeatherConditions= new ArrayList<>();
        boolean isWindSpeedFavourable = true;
        for (WeatherConditions weatherConditions : TempRangeWeatherConditions) {
            Temperature temperature = weatherConditions.getTemperature();
            float windSpeed = temperature.getWind_spd();

            if (windSpeed > windSpeedMin && windSpeed < windSpeedMax) {
                isWindSpeedFavourable = false;
                break;
            }
            if (!isWindSpeedFavourable) {
                System.out.println("WindSpeed not between min and max temp");
            }
            Assert.assertTrue(isWindSpeedFavourable, "WindSpeed is  between " + windSpeedMin + "and" + windSpeedMax);
            WindSpeedWeatherConditions.add(weatherConditions);
        }

    }

    @And("^I check to see if UV index is <= (\\d+)$")
    public void iCheckToSeeIfUVIndexIs(int uvIndexExpected) {
        boolean isUvIndexFavourable = true;
        UvIndexWeatherConditions= new ArrayList<>();
        for (WeatherConditions weatherConditions : WindSpeedWeatherConditions) {
            Temperature temperature = weatherConditions.getTemperature();
            float uv_Index = temperature.getUv_Index();

            if (uv_Index <= uvIndexExpected) {
                isUvIndexFavourable = false;
                break;
            }
            if (!isUvIndexFavourable) {
                System.out.println("Uv index is not less then or equal to uvIndexExpected");
            }
            Assert.assertTrue(isUvIndexFavourable, "Uv index is  less then or equal to" + uvIndexExpected);
            UvIndexWeatherConditions.add(weatherConditions);

        }
    }



 @When("^I look up the the weather forecast for the next 3 month with POSTAL CODES (\\d+)")
    public void ILookUpTheWeatherForecastForTheNext3MonthWithPOSTALCODES(int postalCode) throws IOException {
        OpenWeatherMapApi openWeatherMapApi = new OpenWeatherMapApi();
        Response response = openWeatherMapApi.getForcastByPostalCode(postalCode,"AU");
        String responseString = response.getBody().asString();
     BufferedWriter writer = new BufferedWriter(new FileWriter("WeatherConditionForPostalCode.json", true));
     writer.append(responseString);
     writer.close();
        System.out.print(responseString);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            forcastModel = objectMapper.readValue(responseString, ForcastModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @And("I Pick best suitable spot out of top two spots, based upon suitable weather forecast for the day")
    public void iPickBestSuitableSpotOutOfTopTwoSpotsBasedUponSuitableWeatherForecastForTheDay() {



    }
}