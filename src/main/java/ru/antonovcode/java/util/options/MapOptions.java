package ru.antonovcode.java.util.options;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by alex on 11.09.2014.
 */
public class MapOptions {

    private static final String MAP_SETTINGS_FILE = "settings/map-settings.txt";

    public static final int PERCENT_OF_EXIT = 1;
    public static final int PERCENT_OF_TREASURE = 1;
    public static int PERCENT_OF_PORTALS;
    public static int PERCENT_OF_AMMUNITION;
    public static int PERCENT_OF_FAKE_TREASURE;

    public static float TRAPS_FACTOR_OF_HOSPITALS;

    public static int HOSPITALS_PERCENT_OF_DEADLOCKS;

    //public final static MapOptions mo = new MapOptions();

    static {
        reloadOptions();
    }

    public static void reloadOptions(){
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = MapOptions.class.getClassLoader().getResourceAsStream(MAP_SETTINGS_FILE);
            properties.load(inputStream);
            //Here it is readed
            PERCENT_OF_PORTALS = Integer.parseInt(properties.getProperty("PERCENT_OF_PORTALS"));
            PERCENT_OF_AMMUNITION = Integer.parseInt(properties.getProperty("PERCENT_OF_AMMUNITION"));
            TRAPS_FACTOR_OF_HOSPITALS = Float.parseFloat(properties.getProperty("TRAPS_FACTOR_OF_HOSPITALS"));
            PERCENT_OF_FAKE_TREASURE = Integer.parseInt(properties.getProperty("PERCENT_OF_FAKE_TREASURE"));
            HOSPITALS_PERCENT_OF_DEADLOCKS = Integer.parseInt(properties.getProperty("HOSPITALS_PERCENT_OF_DEADLOCKS"));


        } catch (FileNotFoundException ex) {
            System.err.println("Properties config file not found");
        } catch (IOException ex) {
            System.err.println("Error while reading file");
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
