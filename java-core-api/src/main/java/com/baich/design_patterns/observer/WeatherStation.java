package com.baich.design_patterns.observer;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-06-27
 * Time : 11:02
 * Description:
 * Modified By:
 * version : v1.0
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
