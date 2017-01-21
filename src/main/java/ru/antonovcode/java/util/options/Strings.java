package ru.antonovcode.java.util.options;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by alex on 12.09.2014.
 */
public class Strings {
    private static final String MAP_SETTINGS_FILE = "settings/lang-settings.txt";

    public static String WINDOW_MAIN;
    public static String WINDOW_MAIN_NEWGAME;
    public static String WINDOW_TITLE;
    public static String WINDOW_EXIT;
    public static String WINDOW_OPTION;
    public static String WINDOW_CUSTOMS;
    public static String WINDOW_EDITMESSAGE;
    public static String WINDOW_NEXTMOVE;
    public static String WINDOW_WINNER;

    public static String LABPANEL_EDITBUTTON;

    public static String NEWGAMEDIALOG_TITLE;
    public static String NEWGAMEDIALOG_PLAYERS;
    public static String NEWGAMEDIALOG_AREASIZE;
    public static String NEWGAMEDIALOG_OPTIONS;
    public static String NEWGAMEDIALOG_CREATE;

    public static String OPTIONDIALOG_TITLE;
    public static String OPTIONDIALOG_ENDOFMOVING;
    public static String OPTIONDIALOG_FIRSTWALL;
    public static String OPTIONDIALOG_NEXTCELL;
    public static String OPTIONDIALOG_HOSPITALMODE;
    public static String OPTIONDIALOG_EXITMODE;
    public static String OPTIONDIALOG_FAKETREASUREMODE;
    public static String OPTIONDIALOG_MAPAMMUNITION;
    public static String OPTIONDIALOG_ONLYMINESANDDINAS;
    public static String OPTIONDIALOG_RUSSIANINTERFACE;
    public static String OPTIONDIALOG_PERCENTOFPORTALS;
    public static String OPTIONDIALOG_PERCENTOFAMMUNITION;
    public static String OPTIONDIALOG_PERCENTOFFAKES;
    public static String OPTIONDIALOG_HOSPITALPERCENTOFDEADLOCKS;
    public static String OPTIONDIALOG_FACTORTRAPS;
    public static String OPTIONDIALOG_SETDEFAULTCUSTOMS;
    public static String OPTIONDIALOG_ACCEPT;

    public static String CREATINGPLAYERSDIALOG_TITLE;
    public static String CREATINGPLAYERSDIALOG_ENTER;
    public static String CREATINGPLAYERSDIALOG_PLAYER;
    public static String CREATINGPLAYERSDIALOG_PLEASE;
    public static String[] CREATINGPLAYERSDIALOG_ORDER;
    public static String CREATINGPLAYERSDIALOG_ADD;

    public static String PLAYERPANEL_LOCATION;
    public static String PLAYERPANEL_INSTOCK;
    public static String PLAYERPANEL_COUNTOFHURTS;
    public static String PLAYERPANEL_USE;

    public static String AMMUNITION_GUN;
    public static String AMMUNITION_MINE;
    public static String AMMUNITION_DINAMIT;

    static {
        updateFields();
    }

    public static void updateFields(){
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = MapOptions.class.getClassLoader().getResourceAsStream(MAP_SETTINGS_FILE);
            properties.load(inputStream);
            //Here it is readed
            if(!GameOptions.RUS_LANG){
                WINDOW_MAIN = properties.getProperty("WINDOW_MAIN_EN");
                WINDOW_MAIN_NEWGAME = properties.getProperty("WINDOW_MAIN_NEWGAME_EN");
                WINDOW_TITLE = properties.getProperty("WINDOW_TITLE_EN");
                WINDOW_EXIT = properties.getProperty("WINDOW_EXIT_EN");
                WINDOW_CUSTOMS = properties.getProperty("WINDOW_CUSTOMS_EN");
                WINDOW_OPTION = properties.getProperty("WINDOW_OPTION_EN");
                WINDOW_EDITMESSAGE = properties.getProperty("WINDOW_EDITMESSAGE_EN");
                WINDOW_NEXTMOVE = properties.getProperty("WINDOW_NEXTMOVE_EN");
                WINDOW_WINNER = properties.getProperty("WINDOW_WINNER_EN");

                LABPANEL_EDITBUTTON = properties.getProperty("LABPANEL_EDITBUTTON_EN");

                NEWGAMEDIALOG_TITLE = properties.getProperty("NEWGAMEDIALOG_TITLE_EN");
                NEWGAMEDIALOG_PLAYERS = properties.getProperty("NEWGAMEDIALOG_PLAYERS_EN");
                NEWGAMEDIALOG_AREASIZE = properties.getProperty("NEWGAMEDIALOG_AREASIZE_EN");
                NEWGAMEDIALOG_OPTIONS = properties.getProperty("NEWGAMEDIALOG_OPTIONS_EN");
                NEWGAMEDIALOG_CREATE = properties.getProperty("NEWGAMEDIALOG_CREATE_EN");

                OPTIONDIALOG_TITLE = properties.getProperty("OPTIONDIALOG_TITLE_EN");
                OPTIONDIALOG_ENDOFMOVING = properties.getProperty("OPTIONDIALOG_ENDOFMOVING_EN");
                OPTIONDIALOG_FIRSTWALL = properties.getProperty("OPTIONDIALOG_FIRSTWALL_EN");
                OPTIONDIALOG_NEXTCELL = properties.getProperty("OPTIONDIALOG_NEXTCELL_EN");

                OPTIONDIALOG_HOSPITALMODE = properties.getProperty("OPTIONDIALOG_HOSPITALMODE_EN");
                OPTIONDIALOG_EXITMODE = properties.getProperty("OPTIONDIALOG_EXITMODE_EN");
                OPTIONDIALOG_FAKETREASUREMODE = properties.getProperty("OPTIONDIALOG_FAKETREASUREMODE_EN");
                OPTIONDIALOG_MAPAMMUNITION = properties.getProperty("OPTIONDIALOG_MAPAMMUNITION_EN");
                OPTIONDIALOG_ONLYMINESANDDINAS = properties.getProperty("OPTIONDIALOG_ONLYMINESANDDINAS_EN");
                OPTIONDIALOG_RUSSIANINTERFACE = properties.getProperty("OPTIONDIALOG_RUSSIANINTERFACE_EN");
                OPTIONDIALOG_PERCENTOFPORTALS = properties.getProperty("OPTIONDIALOG_PERCENTOFPORTALS_EN");
                OPTIONDIALOG_PERCENTOFAMMUNITION = properties.getProperty("OPTIONDIALOG_PERCENTOFAMMUNITION_EN");
                OPTIONDIALOG_PERCENTOFFAKES = properties.getProperty("OPTIONDIALOG_PERCENTOFFAKES_EN");
                OPTIONDIALOG_HOSPITALPERCENTOFDEADLOCKS = properties.getProperty("OPTIONDIALOG_HOSPITALPERCENTOFDEADLOCKS_EN");
                OPTIONDIALOG_FACTORTRAPS = properties.getProperty("OPTIONDIALOG_FACTORTRAPS_EN");
                OPTIONDIALOG_SETDEFAULTCUSTOMS = properties.getProperty("OPTIONDIALOG_SETDEFAULTCUSTOMS_EN");
                OPTIONDIALOG_ACCEPT = properties.getProperty("OPTIONDIALOG_ACCEPT_EN");

                CREATINGPLAYERSDIALOG_TITLE = properties.getProperty("CREATINGPLAYERSDIALOG_TITLE_EN");
                CREATINGPLAYERSDIALOG_ENTER = properties.getProperty("CREATINGPLAYERSDIALOG_ENTER_EN");
                CREATINGPLAYERSDIALOG_PLAYER = properties.getProperty("CREATINGPLAYERSDIALOG_PLAYER_EN");
                CREATINGPLAYERSDIALOG_PLEASE = properties.getProperty("CREATINGPLAYERSDIALOG_PLEASE_EN");
                CREATINGPLAYERSDIALOG_ORDER = properties.getProperty("CREATINGPLAYERSDIALOG_ORDER_EN").split(";");
                CREATINGPLAYERSDIALOG_ADD = properties.getProperty("CREATINGPLAYERSDIALOG_ADD_EN");

                PLAYERPANEL_LOCATION = properties.getProperty("PLAYERPANEL_LOCATION_EN");
                PLAYERPANEL_INSTOCK = properties.getProperty("PLAYERPANEL_INSTOCK_EN");
                PLAYERPANEL_COUNTOFHURTS = properties.getProperty("PLAYERPANEL_COUNTOFHURTS_EN");
                PLAYERPANEL_USE = properties.getProperty("PLAYERPANEL_USE_EN");

                AMMUNITION_GUN = properties.getProperty("AMMUNITION_GUN_EN");
                AMMUNITION_MINE = properties.getProperty("AMMUNITION_MINE_EN");
                AMMUNITION_DINAMIT = properties.getProperty("AMMUNITION_DINAMIT_EN");
            } else {
                WINDOW_MAIN = new String(properties.getProperty("WINDOW_MAIN_RUS").getBytes("ISO8859-1"), "Cp1251");
                WINDOW_MAIN_NEWGAME = new String(properties.getProperty("WINDOW_MAIN_NEWGAME_RUS").getBytes("ISO8859-1"), "Cp1251");
                WINDOW_TITLE = new String(properties.getProperty("WINDOW_TITLE_RUS").getBytes("ISO8859-1"), "Cp1251");
                WINDOW_EXIT = new String(properties.getProperty("WINDOW_EXIT_RUS").getBytes("ISO8859-1"), "Cp1251");
                WINDOW_CUSTOMS = new String(properties.getProperty("WINDOW_CUSTOMS_RUS").getBytes("ISO8859-1"), "Cp1251");
                WINDOW_OPTION = new String(properties.getProperty("WINDOW_OPTION_RUS").getBytes("ISO8859-1"), "Cp1251");
                WINDOW_EDITMESSAGE = new String(properties.getProperty("WINDOW_EDITMESSAGE_RUS").getBytes("ISO8859-1"), "Cp1251");
                WINDOW_NEXTMOVE = new String(properties.getProperty("WINDOW_NEXTMOVE_RUS").getBytes("ISO8859-1"), "Cp1251");
                WINDOW_WINNER = new String(properties.getProperty("WINDOW_WINNER_RUS").getBytes("ISO8859-1"), "Cp1251");

                LABPANEL_EDITBUTTON = new String(properties.getProperty("LABPANEL_EDITBUTTON_RUS").getBytes("ISO8859-1"), "Cp1251");

                NEWGAMEDIALOG_TITLE = new String(properties.getProperty("NEWGAMEDIALOG_TITLE_RUS").getBytes("ISO8859-1"), "Cp1251");
                NEWGAMEDIALOG_PLAYERS = new String(properties.getProperty("NEWGAMEDIALOG_PLAYERS_RUS").getBytes("ISO8859-1"), "Cp1251");
                NEWGAMEDIALOG_AREASIZE = new String(properties.getProperty("NEWGAMEDIALOG_AREASIZE_RUS").getBytes("ISO8859-1"), "Cp1251");
                NEWGAMEDIALOG_OPTIONS = new String(properties.getProperty("NEWGAMEDIALOG_OPTIONS_RUS").getBytes("ISO8859-1"), "Cp1251");
                NEWGAMEDIALOG_CREATE = new String(properties.getProperty("NEWGAMEDIALOG_CREATE_RUS").getBytes("ISO8859-1"), "Cp1251");

                OPTIONDIALOG_TITLE = new String(properties.getProperty("OPTIONDIALOG_TITLE_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_ENDOFMOVING = new String(properties.getProperty("OPTIONDIALOG_ENDOFMOVING_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_FIRSTWALL = new String(properties.getProperty("OPTIONDIALOG_FIRSTWALL_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_NEXTCELL = new String(properties.getProperty("OPTIONDIALOG_NEXTCELL_RUS").getBytes("ISO8859-1"), "Cp1251");

                OPTIONDIALOG_HOSPITALMODE = new String(properties.getProperty("OPTIONDIALOG_HOSPITALMODE_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_EXITMODE = new String(properties.getProperty("OPTIONDIALOG_EXITMODE_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_FAKETREASUREMODE = new String(properties.getProperty("OPTIONDIALOG_FAKETREASUREMODE_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_MAPAMMUNITION = new String(properties.getProperty("OPTIONDIALOG_MAPAMMUNITION_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_ONLYMINESANDDINAS = new String(properties.getProperty("OPTIONDIALOG_ONLYMINESANDDINAS_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_RUSSIANINTERFACE = new String(properties.getProperty("OPTIONDIALOG_RUSSIANINTERFACE_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_PERCENTOFPORTALS = new String(properties.getProperty("OPTIONDIALOG_PERCENTOFPORTALS_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_PERCENTOFAMMUNITION = new String(properties.getProperty("OPTIONDIALOG_PERCENTOFAMMUNITION_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_PERCENTOFFAKES = new String(properties.getProperty("OPTIONDIALOG_PERCENTOFFAKES_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_HOSPITALPERCENTOFDEADLOCKS = new String(properties.getProperty("OPTIONDIALOG_HOSPITALPERCENTOFDEADLOCKS_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_FACTORTRAPS = new String(properties.getProperty("OPTIONDIALOG_FACTORTRAPS_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_SETDEFAULTCUSTOMS = new String(properties.getProperty("OPTIONDIALOG_SETDEFAULTCUSTOMS_RUS").getBytes("ISO8859-1"), "Cp1251");
                OPTIONDIALOG_ACCEPT = new String(properties.getProperty("OPTIONDIALOG_ACCEPT_RUS").getBytes("ISO8859-1"), "Cp1251");

                CREATINGPLAYERSDIALOG_TITLE = new String(properties.getProperty("CREATINGPLAYERSDIALOG_TITLE_RUS").getBytes("ISO8859-1"), "Cp1251");
                CREATINGPLAYERSDIALOG_ENTER = new String(properties.getProperty("CREATINGPLAYERSDIALOG_ENTER_RUS").getBytes("ISO8859-1"), "Cp1251");
                CREATINGPLAYERSDIALOG_PLAYER = new String(properties.getProperty("CREATINGPLAYERSDIALOG_PLAYER_RUS").getBytes("ISO8859-1"), "Cp1251");
                CREATINGPLAYERSDIALOG_PLEASE = new String(properties.getProperty("CREATINGPLAYERSDIALOG_PLEASE_RUS").getBytes("ISO8859-1"), "Cp1251");

                CREATINGPLAYERSDIALOG_ORDER = properties.getProperty("CREATINGPLAYERSDIALOG_ORDER_RUS").split(";");
                for(int i = 0; i < CREATINGPLAYERSDIALOG_ORDER.length; i++)
                    CREATINGPLAYERSDIALOG_ORDER[i] = new String(CREATINGPLAYERSDIALOG_ORDER[i].getBytes("ISO8859-1"), "Cp1251");

                CREATINGPLAYERSDIALOG_ADD = new String(properties.getProperty("CREATINGPLAYERSDIALOG_ADD_RUS").getBytes("ISO8859-1"), "Cp1251");

                PLAYERPANEL_LOCATION = new String(properties.getProperty("PLAYERPANEL_LOCATION_RUS").getBytes("ISO8859-1"), "Cp1251");
                PLAYERPANEL_INSTOCK = new String(properties.getProperty("PLAYERPANEL_INSTOCK_RUS").getBytes("ISO8859-1"), "Cp1251");
                PLAYERPANEL_COUNTOFHURTS = new String(properties.getProperty("PLAYERPANEL_COUNTOFHURTS_RUS").getBytes("ISO8859-1"), "Cp1251");
                PLAYERPANEL_USE = new String(properties.getProperty("PLAYERPANEL_USE_RUS").getBytes("ISO8859-1"), "Cp1251");

                AMMUNITION_GUN = new String(properties.getProperty("AMMUNITION_GUN_RUS").getBytes("ISO8859-1"), "Cp1251");
                AMMUNITION_MINE = new String(properties.getProperty("AMMUNITION_MINE_RUS").getBytes("ISO8859-1"), "Cp1251");
                AMMUNITION_DINAMIT = new String(properties.getProperty("AMMUNITION_DINAMIT_RUS").getBytes("ISO8859-1"), "Cp1251");
            }



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
