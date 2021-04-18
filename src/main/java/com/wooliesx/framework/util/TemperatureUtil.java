package com.wooliesx.framework.util;

public class TemperatureUtil {

    public static float convertToCelsius(float tempInKelvin) {
        return tempInKelvin - 273.15F;
    }
}
