package ru.antonovcode.java.model.ammunition;

import ru.antonovcode.java.model.cell.direction.IDirection;

import java.awt.*;

/**
 * Created by alex on 01.08.2014.
 */
public abstract class Ammunition {

    protected String name;

    protected int posX = 10;
    protected int posY = 10;

    protected int timesToUseLeft = -1;

    //use to put on the labyrinth map
    public Ammunition(final int posX, final int posY){
        this.posX = posX;
        this.posY = posY;
    }

    //use to put into players bags
    public Ammunition(final int timesToUseLeft){
        this.timesToUseLeft = timesToUseLeft;
    }

    public void addOne(){
        timesToUseLeft++;
    }

    public boolean use(){
        if(timesToUseLeft > 0){
            timesToUseLeft--;
            return true;
        } else {
            return false;
        }

    }

    public abstract void paint(Graphics g);
    public abstract Image getImage();
    public abstract void updateLanguage();

    public int getTimesToUseLeft(){
        return this.timesToUseLeft;
    }

    public String getAmmunitionName(){
        return name;
    }

}
