package Avaj.simulator.planesCrafts;

import Avaj.simulator.Simulator;
import Avaj.simulator.WeatherTower;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String weather = weatherTower.getWeather(this.coordinates);
        HashMap<String, String> messages = new HashMap<String, String>() {{
            put("SUN", "Where is the A / C ?");
            put("RAIN", "It's raining.");
            put("FOG", "yoh! Thembalethu be careful.");
            put("SNOW", "I am freezing, my hands are cold.");
        }};

        if (weather.equals("SUN"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 10,
                    coordinates.getHeight() + 2
            );
        else if (weather.equals("RAIN"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 5,
                    coordinates.getHeight() + 0
            );
        else if (weather.equals("FOG"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 1,
                    coordinates.getHeight() + 0
            );
        else if (weather.equals("SNOW"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 7
            );
        Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + "): " + messages.get(weather));
        if (this.coordinates.getHeight() == 0)
        {
            Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

}
