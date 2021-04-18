package com.wooliesx.framework.api;

import com.wooliesx.framework.util.ConfigurationLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;

public class OpenWeatherMapApi {

    private static String apiKey;
    private static String baseUrl;
    private Properties properties;

    public OpenWeatherMapApi() {

        properties = ConfigurationLoader.loadProperties();
        apiKey = properties.getProperty("openweathermap.apikey");
        baseUrl = properties.getProperty("openweathermap.base.url");
    }


    public Response getForcastByCity(String city,String country) {
        String endpoint = properties.getProperty("openweathermap.forecast.endpoint");
        // prepare full api url
        // e.g. https://api.weatherbit.io/v2.0/forecast/daily?city=Raleigh,NC&key=API_KEY
        String apiUrl = baseUrl + endpoint + "?city=" + city + "&country="  + country + "&key=" + apiKey;
        System.out.println(apiUrl);
        Response sixteenDaysForcast = RestAssured.get(apiUrl);
        return sixteenDaysForcast;
    }

    public Response getForcastByPostalCode(int postalCode, String country) {
        String endpoint = properties.getProperty("openweathermap.forecast.endpoint");
        // prepare full api url
        // e.g. https://api.weatherbit.io/v2.0/forecast/daily?postal_code=2000,NC&key=API_KEY
        String apiUrl = baseUrl + endpoint + "?postal_code=" + postalCode + "&country="  + country + "&key=" + apiKey;
        System.out.println(apiUrl);
        Response Forcast = RestAssured.get(apiUrl);
        return Forcast;
    }
}
