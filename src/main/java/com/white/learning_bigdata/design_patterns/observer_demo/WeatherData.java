package com.white.learning_bigdata.design_patterns.observer_demo;

import java.util.Observable;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-08-10
 * Time : 10:24
 * Description:
 * Modified By:
 * version : v1.0
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
    }

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
