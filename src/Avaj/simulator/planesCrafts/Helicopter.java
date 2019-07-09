package Avaj.simulator.planesCrafts;

import Avaj.simulator.Simulator;
import Avaj.simulator.WeatherTower;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        HashMap<String, String> messages = new HashMap<String, String>() {{
            put("SUN", "This is hot.");
            put("RAIN", "Ehhh, I knew I should have to fix these wipers...");
            put("FOG", "please be careful.");
            put("SNOW", "My hands are freezing!");
        }};

        if (weather.equals("SUN"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 10,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 2
            );
        else if (weather.equals("RAIN"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 5,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0
            );
        else if (weather.equals("FOG"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 1,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0
            );
        else if (weather.equals("SNOW"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 12
            );
        Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + messages.get(weather));
        if (this.coordinates.getHeight() == 0) {
            Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
            weatherTower.unregister(this);
            Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}

