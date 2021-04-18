package com.wooliesx.framework.api.vo;

public class Temperature {

    private float temp;
    private float feels_like;
    private float temp_min;
    private float temp_max;
    private int pressure;
    private int sea_level;
    private float Wind_spd;
    private float Uv_Index;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(float feels_like) {
        this.feels_like = feels_like;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getSea_level() {
        return sea_level;
    }

    public void setSea_level(int sea_level) {
        this.sea_level = sea_level;
    }

    public float getWind_spd() {
        return Wind_spd;
    }
    public void setWind_spd(float Wind_spd) {
        this.Wind_spd = Wind_spd;
    }

    public float getUv_Index() {return Uv_Index; }

    public void setUv_Index(float Uv_Index) { this.Uv_Index = Uv_Index;
        }

    }

