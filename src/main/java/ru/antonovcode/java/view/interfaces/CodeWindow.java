package ru.antonovcode.java.view.interfaces;

import ru.antonovcode.java.controller.Controller;
import ru.antonovcode.java.model.ammunition.Ammunition;
import ru.antonovcode.java.model.cell.Cell;
import ru.antonovcode.java.model.player.Player;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alex on 28.08.2014.
 */
public interface CodeWindow {

    void newGame(int size, int numOfPlayers);
    void createPlayersPanels(String[] players);
    void setPlayersToPlayerPanels(Player[] player);
    void repaintLabyrinth(final Cell[][] labyrinth, final int size, Player[] players);
    String selectNextPlayer();
    void setCodeHandler(Controller.CodeHandler handler);
    void updateLanguage();
    void startGame();


}
