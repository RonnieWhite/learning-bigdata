package com.baich.bigdata.design_patterns.observer_demo;

import java.util.Observable;
import java.util.Observer;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-10
 * Time : 10:28
 * Description:
 * Modified By:
 * version : v1.0
 */
public class CurrentConditionsDisplay implements Observer {
    Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        weatherData.setMeasurements(20f, 30f, 101.1f);
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        currentConditionsDisplay.update(weatherData, 1);
    }
}
