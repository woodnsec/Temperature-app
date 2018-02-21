package com.woodnbottle.android.temperatureconverter;

/**
 * Created by woonat01 on 2/20/2018.
 */

public class ConverterUtil {
    //converts to celsius
    public static float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    //converts to fahrenheit
    public static float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5 + 32);
    }
}
