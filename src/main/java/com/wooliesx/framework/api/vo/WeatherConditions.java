package com.wooliesx.framework.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherConditions {

    @JsonProperty("list")

    private List<WeatherConditions> weatherConditionsList;
    private String moonset_ts;



    public List<WeatherConditions> getWeatherConditionsList() {
        return weatherConditionsList;
    }

    public void setWeatherConditionsList(List<WeatherConditions> weatherConditionsList) {
        this.weatherConditionsList = weatherConditionsList;}
    private Temperature temperature;
    private String dt_txt; // e.g. "2020-08-22 06:00:00"

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }



    public void setmoonset_ts(String moonset_ts) {
        this.moonset_ts = moonset_ts;
    }

    public String getmoonset_ts() {
        return moonset_ts;
    }




}
