package com.wooliesx.framework.api.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Model for response from weather forcast endpoint: http://api.weatherbit.io/v2.0/forecast/
 */
public class ForcastModel {


    private String city_name;


    @JsonProperty("main")
    private List<WeatherConditions> weatherConditionsList;


    public void setcity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getcityname() {

        return city_name;
    }

    public List<WeatherConditions> getWeatherConditionsList() {
        return weatherConditionsList;
    }

    public void setWeatherConditionsList(List<WeatherConditions> weatherConditionsList) {
        this.weatherConditionsList = weatherConditionsList;
    }

}

