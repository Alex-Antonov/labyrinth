package ru.antonovcode.java.util.options;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Created by alex on 11.09.2014.
 */
public class GameOptions {

    private static final String GAME_SETTINGS_FILE = "settings/game-settings.txt";
    //private static File file = new File(GAME_SETTINGS_FILE);

    public static boolean END_OF_MOVE_BY_FIRST_WALL;
    public static boolean HOSPITAL_MODE_ON;
    public static boolean EXIT_MODE_ON;
    public static boolean FAKE_TREASURE_MODE_ON;
    public static boolean MAP_AMMUNITION_ON;
    public static boolean DINAMIT_AND_MINE_ONLY;
    public static boolean RUS_LANG;


    //public static final GameOptions go = new GameOptions();


    static{
        reloadOptions();
    }

    public static void reloadOptions(){
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = GameOptions.class.getClassLoader().getResourceAsStream(GAME_SETTINGS_FILE);
            properties.load(inputStream);
            //Here it is readed
            END_OF_MOVE_BY_FIRST_WALL = Boolean.parseBoolean(properties.getProperty("END_OF_MOVE_BY_FIRST_WALL"));
            HOSPITAL_MODE_ON = Boolean.parseBoolean(properties.getProperty("HOSPITAL_MODE_ON"));
            EXIT_MODE_ON = Boolean.parseBoolean(properties.getProperty("EXIT_MODE_ON"));
            FAKE_TREASURE_MODE_ON = Boolean.parseBoolean(properties.getProperty("FAKE_TREASURE_MODE_ON"));
            MAP_AMMUNITION_ON = Boolean.parseBoolean(properties.getProperty("MAP_AMMUNITION_ON"));
            DINAMIT_AND_MINE_ONLY = Boolean.parseBoolean(properties.getProperty("DINAMIT_AND_MINE_ONLY"));
            RUS_LANG = Boolean.parseBoolean(properties.getProperty("RUS_LANG"));


        } catch (FileNotFoundException ex) {
            System.err.println("Properties config file not found");
        } catch (IOException ex) {
            System.err.println("Error while reading file");
        } finally {
            try {
                if(inputStream != null)
                    inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
