package ru.antonovcode.java.model;

import ru.antonovcode.java.model.ammunition.Ammunition;
import ru.antonovcode.java.model.cell.Cell;
import ru.antonovcode.java.model.cell.direction.IDirection;
import ru.antonovcode.java.model.codemodel.CodeModel;
import ru.antonovcode.java.model.hospital.HospitalSystem;
import ru.antonovcode.java.model.player.Player;
import ru.antonovcode.java.util.LabyrinthGenerator;
import ru.antonovcode.java.util.ValueGenerator;
import ru.antonovcode.java.util.options.GameOptions;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alex on 07.09.2014.
 */
public class Model implements CodeModel, IDirection {

    private Player[] players;
    private Cell[][] labyrinth;
    private LabyrinthGenerator labyrinthGenerator;
    private ValueGenerator valGen;
    private int labyrinthSize;

    public Model(final int labyrinthSize) {
        this.labyrinthSize = labyrinthSize;
        valGen = new ValueGenerator();
        labyrinthGenerator = new LabyrinthGenerator(labyrinthSize);
        labyrinth = labyrinthGenerator.getCurrentLabyrinth();
    }

    @Override
    public Cell[][] getCurrentLabyrinthModel() {
        return this.labyrinth;
    }

    @Override
    public Player isHerePlayer(int X, int Y) {
        for(Player p : players)
            if(p.getX() == X && p.getY() == Y)
                return p;
        return null;
    }

    public Player[] createNewPlayers(final String[] nicks) {
        players = new Player[nicks.length];
        for (int i = 0; i < nicks.length; i++) {
            players[i] = new Player(nicks[i], valGen.nextInt(labyrinthSize), valGen.nextInt(labyrinthSize), this);
            if(i == 0)
                players[i].setIniciative(true);
        }
        return this.players;
    }

    public Cell[][] createNewLabyrinth(final int size) {
        this.labyrinthSize = size;
        labyrinth = labyrinthGenerator.newLabyrinth(size);
        return this.labyrinth;
    }

    public boolean movePlayer(final int direction, final String nick) {
        int idx = 0;
        boolean total = false;
        for (Player p : players) {
            if (p.getNick().equals(nick))
                break;
            idx++;
        }

        switch (direction) {
            case DNORTH:
                total = players[idx].moveUp();
                break;
            case DSOUTH:
                total = players[idx].moveDown();
                break;
            case DEAST:
                total = players[idx].moveRight();
                break;
            case DWEST:
                total = players[idx].moveLeft();
                break;
        }

        return total;
    }

    public String getPlayersCoordinates(String nick) {
        String coordinates = null;
        for (Player p : players) {
            if (p.getNick().equals(nick)) {
                coordinates = p.getCoordinates();
                break;
            }
        }
        return coordinates;
    }

    public Image getAmmunitionImage(final String nick, final int id) {
        Image im = null;
        for (Player p : players) {
            if (p.getNick().equals(nick)) {
                im = ((Ammunition) p.getAmmunitionList().get(id)).getImage();
                break;
            }
        }
        return im;
    }


    public ArrayList<Ammunition> getAmmunitionList(final String nick) {
        ArrayList<Ammunition> am = null;
        for (Player p : players) {
            if (p.getNick().equals(nick)) {
                 am = p.getAmmunitionList();
                break;
            }
        }
        return am;
    }

    public int getHurtsCounter(final String nick) {
        int cnt = 0;
        for (Player p : players) {
            if (p.getNick().equals(nick)) {
                cnt = p.getCountOfHurt();
                break;
            }
        }
        return cnt;
    }

    public void drawPlayers(Graphics g, int LHC, int dif) {
        for (Player p : players) {
            p.paint(g, LHC, dif);
        }
    }


    public Player[] getPlayers(){
        return players;
    }


    public void changeColorit() {

    }

    @Override
    public HospitalSystem getHospitalSystem() {
        if(GameOptions.HOSPITAL_MODE_ON)
            return labyrinthGenerator.getHospitalSystem();
        else
            return null;
    }
}
