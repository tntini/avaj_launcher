package Avaj.simulator;
import Avaj.simulator.planesCrafts.AircraftFactory;
import Avaj.weather.WeatherProvider;

import java.io.*;

public class Simulator {

    public static int cycles;
    public static PrintWriter writer;

    public static void main(String[] args)
    {
        if (args.length < 1)
            return;
        // scenario filename
        String filename = args[0];

        File simulationFile = new File("simulation.txt");
        try {
            writer = new PrintWriter(simulationFile);
        } catch (FileNotFoundException fne) {
            System.out.println("Error: " + fne.getMessage());
            return;
        }
        if (simulationFile.exists() && !simulationFile.isDirectory())
            writer.print("");

        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
        try {
            FileInputStream fstream = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int line = 1;
            String[] splitted;

            while ((strLine = br.readLine()) != null)
            {
                // Checking the first line that must be only 1 parameter as an integer.
                if (line == 1)
                    try {
                        cycles = Integer.parseInt(strLine);
                        if (cycles < 0)
                        {
                            System.out.println("Error: first line of scenario file must be a POSITIVE integer.");
                            return;
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: first line of scenario file must be an integer.");
                        return;
                    }
                else
                {
                    splitted = strLine.split(" ");
                    if (splitted.length == 1 && splitted[0].isEmpty())
                        continue;
                    if (splitted.length != 5)
                        throw new Exception("Error: line " + line + ": there must be 5 parameters.");

                    try {
                        aircraftFactory.newAircraft(
                                splitted[0],
                                splitted[1],
                                Integer.parseInt(splitted[2]),
                                Integer.parseInt(splitted[3]),
                                Integer.parseInt(splitted[4])
                        ).registerTower(weatherTower);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: line " + line + ": parameter 3 to 5 must be integers.");
                        return;
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        return;
                    }
                }
//                System.out.println(strLine);
                line++;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        while (cycles > 0)
        {
            weatherTower.changeWeather();
            cycles--;
        }

        writer.close();
    }
}
