package Avaj.simulator;

import Avaj.weather.WeatherProvider;
import Avaj.simulator.planesCrafts.Coordinates;



public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates)
    {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather()
    {
        this.conditionChanged();
    }

}
