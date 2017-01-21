package ru.antonovcode.java.model.cell;


import ru.antonovcode.java.model.ammunition.defammunition.IAmmunitionID;
import ru.antonovcode.java.model.cell.direction.IDirection;

/**
 * Created by alex on 29.07.2014.
 */
public class Cell implements IDirection, Cloneable, IAmmunitionID {


    private boolean[] sides = new boolean[4];
    private double key;
    private int wallCounter = 0;

    private int kindOfObject = -1;


    public Cell(final boolean NORTH, final boolean EAST, final boolean SOUTH, final boolean WEST){
        sides[0] = NORTH;
        sides[1] = EAST;
        sides[2] = SOUTH;
        sides[3] = WEST;
        key = 0;
        for(boolean a : sides)
            if(a)
                wallCounter++;

    }

    public void setKindOfObject(final int kind){
        this.kindOfObject = kind;
    }

    public int getKindOfObject(){
        return this.kindOfObject;
    }

    public int getWallCounter(){
        return wallCounter;
    }

    public Object clone(){
        Cell clone = new Cell(false, false, false, false);

        for(int i = 0; i < this.sides.length; i++){
            clone.sides[i] = this.sides[i];
            clone.setKey(this.key);
        }
        return clone;
    }



    public boolean getWall(final int direction){
        switch(direction){
            case DNORTH:
                return getNorth();

            case DSOUTH:
                return getSouth();

            case DEAST:
                return getEast();

            case DWEST:
                return getWest();

            default:
            //    throw Ooops!
                return false;
        }
    }

    public boolean addWall(final int direction){
        switch(direction){
            case DNORTH:
                return addNorth();

            case DSOUTH:
                return addSouth();

            case DEAST:
                return addEast();

            case DWEST:
                return addWest();

            default:
                //    throw
                return false;
        }


    }

    public boolean delWall(final int direction){
        switch(direction){
            case DNORTH:
                return delNorth();

            case DSOUTH:
                return delSouth();

            case DEAST:
                return delEast();

            case DWEST:
                return delWest();

            default:
                //    throw
                return false;
        }

    }

    public void setKey(final double key){
        this.key = key;
    }

    public double getKey(){
        return key;
    }

    //private section----------------
    //deleters-----------------------
    private boolean delEast(){
        if(sides[1]) {
            this.sides[1] = !sides[1];
            wallCounter--;
        }
        return this.sides[1];
    }

    private boolean delWest(){
        if(sides[3]) {
            this.sides[3] = !sides[3];
            wallCounter--;
        }
        return this.sides[3];
    }

    private boolean delNorth(){
        if(sides[0]) {
            this.sides[0] = !sides[0];
            wallCounter--;
        }
        return this.sides[0];
    }

    private boolean delSouth(){
        if(sides[2]) {
            this.sides[2] = !sides[2];
            wallCounter--;
        }
        return this.sides[2];
    }

    //adders--------------------------
    private boolean addEast(){
        if(!sides[1]) {
            this.sides[1] = !sides[1];
            wallCounter++;
        }
        return this.sides[1];
    }

    private boolean addWest(){
        if(!sides[3]) {
            this.sides[3] = !sides[3];
            wallCounter++;
        }
        return this.sides[3];
    }

    private boolean addNorth(){
        if(!sides[0]) {
            this.sides[0] = !sides[0];
            wallCounter++;
        }
        return this.sides[0];
    }

    private boolean addSouth(){
        if(!sides[2]) {
            this.sides[2] = !sides[2];
            wallCounter++;
        }
        return this.sides[2];
    }
    //getters-------------------------
    private boolean getNorth(){
        return this.sides[0];
    }

    private boolean getSouth(){
        return this.sides[2];
    }

    private boolean getEast(){
        return this.sides[1];
    }

    private boolean getWest(){
        return this.sides[3];
    }


}
