package Avaj.simulator.planesCrafts;

import Avaj.simulator.WeatherTower;


public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
