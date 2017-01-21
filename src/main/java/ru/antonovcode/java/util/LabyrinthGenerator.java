package ru.antonovcode.java.util;

import ru.antonovcode.java.model.ammunition.defammunition.IMapIdenfires;
import ru.antonovcode.java.model.cell.direction.IDirection;
import ru.antonovcode.java.model.cell.Cell;
import ru.antonovcode.java.model.hospital.HospitalSystem;
import ru.antonovcode.java.util.options.GameOptions;
import ru.antonovcode.java.util.options.MapOptions;

/**
 * Created by alex on 29.07.2014.
 */
public class LabyrinthGenerator implements IDirection, IMapIdenfires {

    private final int ONE_HUNDRED = 100;

    private HospitalSystem hospitalSystem;
    private int X;
    private int Y;
    private int size;
    private Cell labyrinth[][];
    private ValueGenerator valGen;
    private int run = 0;


    public LabyrinthGenerator(final int size) {
        valGen = new ValueGenerator();
        this.size = size;
        this.labyrinth = new Cell[size][size];
        //generateLabyrinth();
    }


    public Cell[][] getCurrentLabyrinth() {
        if (run == 0) {
            this.labyrinth = new Cell[size][size];
            generateNewLabyrinth();
            //generateLabyrinth();
        }
        run++;
        return labyrinth;
    }

    public Cell[][] newLabyrinth(final int size) {

        this.size = size;
        this.labyrinth = new Cell[size][size];
        generateNewLabyrinth();
        //generateLabyrinth();

        return labyrinth;
    }

    public int getLabyrinthSize() {
        return size;
    }


    //----------------------------------------------second algorithm

    private void generateNewLabyrinth() {
        initCells();
        this.X = valGen.nextInt(size);
        this.Y = valGen.nextInt(size);
        boolean end = false;

        while (!end) {
            dig(this.X, this.Y);
            end = scan();
            if (!end)
                fixCell(this.X, this.Y);
        }


        setMapObjects();
        //to add loops
        //selLoopsIntoLabyrinth();
    }

    private void fixCell(final int X, final int Y) {
        boolean done = false;
        int dir = 0;

        while (!done) {
            dir = valGen.nextInt(4) + 1;

            switch (dir) {
                case DNORTH: {
                    if (Y != 0) {
                        if (labyrinth[Y - 1][X].getKey() == 1) {
                            labyrinth[Y][X].delWall(DNORTH);
                            labyrinth[Y - 1][X].delWall(DSOUTH);
                            done = true;
                        }
                        break;
                    }
                }
                case DEAST: {
                    if (X != (size - 1)) {
                        if (labyrinth[Y][X + 1].getKey() == 1) {
                            labyrinth[Y][X].delWall(DEAST);
                            labyrinth[Y][X + 1].delWall(DWEST);
                            done = true;
                        }
                        break;
                    }
                }
                case DSOUTH: {
                    if (Y != (size - 1)) {
                        if (labyrinth[Y + 1][X].getKey() == 1) {
                            labyrinth[Y][X].delWall(DSOUTH);
                            labyrinth[Y + 1][X].delWall(DNORTH);
                            done = true;
                        }
                        break;
                    }
                }
                case DWEST: {
                    if (X != 0) {
                        if (labyrinth[Y][X - 1].getKey() == 1) {
                            labyrinth[Y][X].delWall(DWEST);
                            labyrinth[Y][X - 1].delWall(DEAST);
                            done = true;
                        }
                        break;
                    }
                }
            }
        }
    }

    private void dig(int X, int Y) {
        labyrinth[Y][X].setKey(1);
        int dir = 0;

        if (!checkDirs(X, Y)) {
            return;
        } else {
            boolean got = false;
            while (!got) {
                dir = valGen.nextInt(4) + 1;

                switch (dir) {
                    case DNORTH: {
                        if (Y != 0) {
                            if (labyrinth[Y - 1][X].getKey() != 1) {
                                labyrinth[Y][X].delWall(DNORTH);
                                labyrinth[Y - 1][X].delWall(DSOUTH);
                                Y--;
                                got = true;
                                dig(X, Y);
                            }
                            break;
                        }
                    }
                    case DEAST: {
                        if (X != (size - 1)) {
                            if (labyrinth[Y][X + 1].getKey() != 1) {
                                labyrinth[Y][X].delWall(DEAST);
                                labyrinth[Y][X + 1].delWall(DWEST);
                                X++;
                                got = true;
                                dig(X, Y);
                            }
                            break;
                        }
                    }
                    case DSOUTH: {
                        if (Y != (size - 1)) {
                            if (labyrinth[Y + 1][X].getKey() != 1) {
                                labyrinth[Y][X].delWall(DSOUTH);
                                labyrinth[Y + 1][X].delWall(DNORTH);
                                Y++;
                                got = true;
                                dig(X, Y);
                            }
                            break;
                        }
                    }
                    case DWEST: {
                        if (X != 0) {
                            if (labyrinth[Y][X - 1].getKey() != 1) {
                                labyrinth[Y][X].delWall(DWEST);
                                labyrinth[Y][X - 1].delWall(DEAST);
                                X--;
                                got = true;
                                dig(X, Y);
                            }
                            break;
                        }
                    }
                }
            }

        }
    }

    private boolean checkDirs(final int X, final int Y) {
        if (Y == 0) {
            //left-high
            if (X == 0) {
                if (labyrinth[Y + 1][X].getKey() == 0 || labyrinth[Y][X + 1].getKey() == 0)
                    return true;
            } else {
                //right-high
                if (X == (size - 1)) {
                    if (labyrinth[Y + 1][X].getKey() == 0 || labyrinth[Y][X - 1].getKey() == 0)
                        return true;
                } else {//high
                    if (labyrinth[Y + 1][X].getKey() == 0 || labyrinth[Y][X - 1].getKey() == 0 || labyrinth[Y][X + 1].getKey() == 0)
                        return true;
                }
            }
        } else {
            if (Y == (size - 1)) {
                //left-low
                if (X == 0) {
                    if (labyrinth[Y - 1][X].getKey() == 0 || labyrinth[Y][X + 1].getKey() == 0)
                        return true;
                } else {
                    //right-low
                    if (X == (size - 1)) {
                        if (labyrinth[Y - 1][X].getKey() == 0 || labyrinth[Y][X - 1].getKey() == 0)
                            return true;
                    } else {//low
                        if (labyrinth[Y - 1][X].getKey() == 0 || labyrinth[Y][X - 1].getKey() == 0 || labyrinth[Y][X + 1].getKey() == 0)
                            return true;
                    }
                }

            } else {
                //left-middle
                if (X == 0) {
                    if (labyrinth[Y - 1][X].getKey() == 0 || labyrinth[Y][X + 1].getKey() == 0 || labyrinth[Y + 1][X].getKey() == 0)
                        return true;
                } else {
                    //right-middle
                    if (X == (size - 1)) {
                        if (labyrinth[Y - 1][X].getKey() == 0 || labyrinth[Y][X - 1].getKey() == 0 || labyrinth[Y + 1][X].getKey() == 0)
                            return true;
                    } else { //middle-middle
                        if (labyrinth[Y - 1][X].getKey() == 0 || labyrinth[Y][X + 1].getKey() == 0 || labyrinth[Y + 1][X].getKey() == 0 || labyrinth[Y][X - 1].getKey() == 0)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean scan() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (labyrinth[i][j].getKey() == 0) {
                    if (i == 0) {
                        if (j == 0) {
                            if (labyrinth[i][j + 1].getKey() == 1 || labyrinth[i + 1][j].getKey() == 1) {
                                this.X = j;
                                this.Y = i;
                                return false;
                            }
                        } else {
                            if (j == (size - 1)) {
                                if (labyrinth[i][j - 1].getKey() == 1 || labyrinth[i + 1][j].getKey() == 1) {
                                    this.X = j;
                                    this.Y = i;
                                    return false;
                                }
                            } else {
                                if (labyrinth[i][j - 1].getKey() == 1 || labyrinth[i][j + 1].getKey() == 1 || labyrinth[i + 1][j].getKey() == 1) {
                                    this.X = j;
                                    this.Y = i;
                                    return false;
                                }
                            }
                        }
                    } else {
                        if (i == (size - 1)) {
                            if (j == 0) {
                                if (labyrinth[i - 1][j].getKey() == 1 || labyrinth[i][j + 1].getKey() == 1) {
                                    this.X = j;
                                    this.Y = i;
                                    return false;
                                }
                            } else {
                                if (j == (size - 1)) {
                                    if (labyrinth[i - 1][j].getKey() == 1 || labyrinth[i][j - 1].getKey() == 1) {
                                        this.X = j;
                                        this.Y = i;
                                        return false;
                                    }
                                } else {
                                    if (labyrinth[i - 1][j].getKey() == 1 || labyrinth[i][j - 1].getKey() == 1 || labyrinth[i][j + 1].getKey() == 1) {
                                        this.X = j;
                                        this.Y = i;
                                        return false;
                                    }
                                }
                            }
                        } else {
                            if (j == (size - 1)) {
                                if (labyrinth[i + 1][j].getKey() == 1 || labyrinth[i - 1][j].getKey() == 1 || labyrinth[i][j - 1].getKey() == 1) {
                                    this.X = j;
                                    this.Y = i;
                                    return false;
                                }
                            } else {
                                if (j == 0) {
                                    if (labyrinth[i + 1][j].getKey() == 1 || labyrinth[i - 1][j].getKey() == 1 || labyrinth[i][j + 1].getKey() == 1) {
                                        this.X = j;
                                        this.Y = i;
                                        return false;
                                    }
                                } else {
                                    if (labyrinth[i + 1][j].getKey() == 1 || labyrinth[i - 1][j].getKey() == 1 || labyrinth[i][j - 1].getKey() == 1 || labyrinth[i][j + 1].getKey() == 1) {
                                        this.X = j;
                                        this.Y = i;
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private void initCells() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                labyrinth[i][j] = new Cell(true, true, true, true);
            }
        }
    }


    private void setMapObjects() {
        if(GameOptions.HOSPITAL_MODE_ON)
            hospitalSystem = new HospitalSystem();
        int deadlocks = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (labyrinth[i][j].getWallCounter() == 3)
                    deadlocks++;

        int portals = (int) ((float) MapOptions.PERCENT_OF_PORTALS * size * size / (float) ONE_HUNDRED);
        int treasure = MapOptions.PERCENT_OF_TREASURE;

        int hospitals = 0;
        int traps = 0;
        int exit = 0;
        int fakeTreasures = 0;
        int ammunitions = 0;

        if (GameOptions.HOSPITAL_MODE_ON) {
            hospitals = (int) ((float) MapOptions.HOSPITALS_PERCENT_OF_DEADLOCKS * deadlocks / (float) ONE_HUNDRED);
            traps = (int) (MapOptions.TRAPS_FACTOR_OF_HOSPITALS * (float) hospitals);
        }

        if (GameOptions.EXIT_MODE_ON) {
            exit = MapOptions.PERCENT_OF_EXIT;
            if (GameOptions.FAKE_TREASURE_MODE_ON)
                fakeTreasures = (int) ((float) MapOptions.PERCENT_OF_FAKE_TREASURE * size * size / (float) ONE_HUNDRED);
        }

        if (GameOptions.MAP_AMMUNITION_ON)
            ammunitions = (int) ((float) MapOptions.PERCENT_OF_AMMUNITION * size * size / (float) ONE_HUNDRED);

        int x = 0;
        int y = 0;

        System.out.println("size: " + size + "\n------------");
        System.out.println("portals: " + portals);
        System.out.println("hospitals: " + hospitals);
        System.out.println("traps: " + traps);
        System.out.println("treasure: " + treasure);
        System.out.println("exit: " + exit);
        System.out.println("fakeTreasures: " + fakeTreasures);
        System.out.println("ammunitions: " + ammunitions + "\n------------");
        System.out.println("deadlocks: " + deadlocks + "\n------------");

        while (portals > 0 || treasure > 0 || hospitals > 0 || traps > 0 || exit > 0 || fakeTreasures > 0 || ammunitions > 0) {
            x = valGen.nextInt(size);
            y = valGen.nextInt(size);

            if (labyrinth[y][x].getKindOfObject() == -1) {

                if (labyrinth[y][x].getWallCounter() == 3 && valGen.nextInt(ONE_HUNDRED) > 79 && hospitals > 0) {
                    labyrinth[y][x].setKindOfObject(MAP_HOSPITAL);
                    hospitalSystem.addHospital(x, y);
                    hospitals--;
                    continue;
                }

                if (valGen.nextInt(ONE_HUNDRED) > 97 && portals > 0) {
                    boolean got = false;
                    for (int i = 0; i < size; i++) {

                        if ((labyrinth[i][x].getKindOfObject() == MAP_PORTAL || labyrinth[y][i].getKindOfObject() == MAP_PORTAL) && valGen.nextBoolean()) {
                            got = true;
                            break;
                        }
                    }
                    if (!got) {
                        labyrinth[y][x].setKindOfObject(MAP_PORTAL);
                        portals--;
                    }
                    continue;
                }

                if (valGen.nextInt(ONE_HUNDRED) > 96 && fakeTreasures > 0) {
                    boolean got = false;
                    for (int i = 0; i < size; i++) {


                        if ((labyrinth[i][x].getKindOfObject() == MAP_FAKE_TREASURE || labyrinth[y][i].getKindOfObject() == MAP_FAKE_TREASURE) && valGen.nextBoolean()) {
                            got = true;
                            break;
                        }
                    }
                    if (!got) {
                        labyrinth[y][x].setKindOfObject(MAP_FAKE_TREASURE);
                        fakeTreasures--;
                    }

                    continue;
                }

                if (valGen.nextInt(ONE_HUNDRED) > 95 && ammunitions > 0) {
                    if (!GameOptions.DINAMIT_AND_MINE_ONLY)
                        labyrinth[y][x].setKindOfObject(valGen.nextInt(3));
                    else
                        labyrinth[y][x].setKindOfObject(valGen.nextInt(2) + 1);
                    ammunitions--;
                    continue;
                }

                if (valGen.nextInt(ONE_HUNDRED) > 90 && traps > 0) {
                    boolean got = false;
                    for (int i = 0; i < size; i++) {


                        if ((labyrinth[i][x].getKindOfObject() == MAP_TRAP || labyrinth[y][i].getKindOfObject() == MAP_TRAP) && valGen.nextBoolean()) {
                            got = true;
                            break;
                        }
                    }

                    if (!got) {
                        labyrinth[y][x].setKindOfObject(MAP_TRAP);
                        traps--;
                    }
                    continue;
                }

                if (valGen.nextInt(ONE_HUNDRED) > 98 && exit > 0) {
                    labyrinth[y][x].setKindOfObject(MAP_EXIT);
                    exit--;
                    continue;
                }

                if (valGen.nextInt(ONE_HUNDRED) > 98 && treasure > 0) {
                    boolean got = false;
                    for (int i = 0; i < size; i++) {
                        if ((labyrinth[i][x].getKindOfObject() == MAP_FAKE_TREASURE || labyrinth[y][i].getKindOfObject() == MAP_FAKE_TREASURE)  && valGen.nextBoolean()) {
                            got = true;
                            break;
                        }
                    }

                    if (!got) {
                        labyrinth[y][x].setKindOfObject(MAP_TREASURE);
                        treasure--;
                    }
                    continue;
                }
            }
        }

        System.out.println("size: " + size + "\n------------");
        System.out.println("portals: " + portals);
        System.out.println("hospitals: " + hospitals);
        System.out.println("traps: " + traps);
        System.out.println("treasure: " + treasure);
        System.out.println("exit: " + exit);
        System.out.println("fakeTreasures: " + fakeTreasures);
        System.out.println("ammunitions: " + ammunitions + "\n------------");
        System.out.println("deadlocks: " + deadlocks + "\n------------");


    }

    public HospitalSystem getHospitalSystem(){
        return hospitalSystem;
    }


}



/*
        int cntOfPortals = size - valGen.nextInt(size) + 1;
        int cntOfTraps = size;

        int cntOfHospitals = size/2;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(valGen.nextInt(100) > 96 && cntOfPortals > 0 && labyrinth[i][j].getWallCounter() == 3){
                    labyrinth[i][j].setPortal();
                    cntOfPortals--;
                } else {
                    if(valGen.nextInt(100) > 96 && cntOfTraps > 0 && labyrinth[i][j].getWallCounter() == 3){
                        labyrinth[i][j].setTrap();
                        cntOfTraps--;
                    } else {
                        if(valGen.nextInt(100) > 96 && cntOfHospitals > 0 && labyrinth[i][j].getWallCounter() == 3){
                            labyrinth[i][j].setHospital();
                            cntOfHospitals--;
                        }
                    }
                }
            }
        }

        int fakeTreasurCounter = size/3;
        boolean treasure = false;
        boolean exit = false;
        while(!treasure || !exit || fakeTreasurCounter > 0){

            int x = valGen.nextInt(size);
            int y = valGen.nextInt(size);
            if(!labyrinth[y][x].isPortal() && !labyrinth[y][x].isExit() && !labyrinth[y][x].isTrap() && !labyrinth[y][x].isHospital() && !treasure && !labyrinth[y][x].isFakeTreasure() && labyrinth[y][x].getWallCounter() == 3){
                labyrinth[y][x].setTreasure();
                treasure = true;
            }
            x = valGen.nextInt(size);
            y = valGen.nextInt(size);
            if(!labyrinth[y][x].isPortal() && !labyrinth[y][x].isTreasure() && !labyrinth[y][x].isTrap() && !labyrinth[y][x].isHospital() && !exit && !labyrinth[y][x].isFakeTreasure() && labyrinth[y][x].getWallCounter() == 3){
                labyrinth[y][x].setExit();
                exit = true;
            }

            x = valGen.nextInt(size);
            y = valGen.nextInt(size);
            if(!labyrinth[y][x].isPortal() && !labyrinth[y][x].isExit() && !labyrinth[y][x].isTreasure() && !labyrinth[y][x].isTrap() && !labyrinth[y][x].isHospital() && fakeTreasurCounter>0 && labyrinth[y][x].getWallCounter() == 3){
                labyrinth[y][x].setFakeTreasure();
                fakeTreasurCounter--;
            }
        }


        private void selLoopsIntoLabyrinth() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (valGen.nextInt(1000) > 850) {
                    int dir = valGen.nextInt(4) + 1;

                    switch (dir) {
                        case DNORTH:
                            if (i != 0 && (labyrinth[i][j].getWall(DWEST) || labyrinth[i][j].getWall(DEAST))) {
                                labyrinth[i][j].delWall(dir);
                                labyrinth[i - 1][j].delWall(DSOUTH);
                            }

                            break;
                        case DEAST:
                            if (j != (size - 1) && (labyrinth[i][j].getWall(DNORTH) || labyrinth[i][j].getWall(DSOUTH))) {
                                labyrinth[i][j].delWall(dir);
                                labyrinth[i][j + 1].delWall(DWEST);
                            }

                            break;
                        case DSOUTH:
                            if (i != (size - 1) && (labyrinth[i][j].getWall(DWEST) || labyrinth[i][j].getWall(DEAST))) {
                                labyrinth[i][j].delWall(dir);
                                labyrinth[i + 1][j].delWall(DNORTH);
                            }

                            break;
                        case DWEST:
                            if (j != 0 && (labyrinth[i][j].getWall(DNORTH) || labyrinth[i][j].getWall(DSOUTH))) {
                                labyrinth[i][j].delWall(dir);
                                labyrinth[i][j - 1].delWall(DEAST);
                            }

                            break;

                    }


                }
            }
        }
    }
        */

//    private void setMapObjects() {
//        float cntOfTreeWallsCells = 0;
//        float portals = (float) 0.3;
//        float hosps = (float) 0.3;
//        float amo = (float) 0.4;
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                if (labyrinth[i][j].getWallCounter() == 3)
//                    cntOfTreeWallsCells++;
//            }
//        }
//
//
//        int cntOfPortals = (int) (cntOfTreeWallsCells * portals);
//        int cntOfHospitals = (int) (cntOfTreeWallsCells * hosps) - 1;
//        int cntOfAmo = (int) (cntOfTreeWallsCells * amo);
//
//        int cntOfTraps = (int)((float)size * 0.7);
//
//        while (cntOfAmo > 0 || cntOfHospitals > 0 || cntOfPortals > 0 || cntOfTraps > 0) {
//            int x = valGen.nextInt(size);
//            int y = valGen.nextInt(size);
//
//            if (labyrinth[y][x].getKindOfObject() != -1 && labyrinth[y][x].getWallCounter() == 3 && cntOfHospitals > 0) {
//                labyrinth[y][x].setKindOfObject(MAP_HOSPITAL);
//                cntOfHospitals--;
//            }
//
//
//            x = valGen.nextInt(size);
//            y = valGen.nextInt(size);
//            if (labyrinth[y][x].getKindOfObject() != -1 && cntOfPortals > 0) {
//                labyrinth[y][x].setKindOfObject(MAP_PORTAL);
//                cntOfPortals--;
//            }
//            x = valGen.nextInt(size);
//            y = valGen.nextInt(size);
//            if (labyrinth[y][x].getKindOfObject() != -1 && labyrinth[y][x].getWallCounter() == 3 && cntOfAmo > 0) {
//                int id = valGen.nextInt(3);
//                labyrinth[y][x].setKindOfObject(id);
//                cntOfAmo--;
//            }
//            x = valGen.nextInt(size);
//            y = valGen.nextInt(size);
//            if (labyrinth[y][x].getKindOfObject() != -1 && cntOfTraps > 0) {
//                labyrinth[y][x].setKindOfObject(MAP_TRAP);
//                cntOfTraps--;
//            }
//
//        }
//
//
//        int fakeTreasurCounter = size / 3;
//        boolean treasure = false;
//        boolean exit = false;
//        while (!treasure || !exit || fakeTreasurCounter > 0) {
//
//            int x = valGen.nextInt(size);
//            int y = valGen.nextInt(size);
//            if (labyrinth[y][x].getKindOfObject() != -1 && !treasure) {
//                labyrinth[y][x].setKindOfObject(MAP_TREASURE);
//                treasure = true;
//            }
//            x = valGen.nextInt(size);
//            y = valGen.nextInt(size);
//            if (labyrinth[y][x].getKindOfObject() != -1 && !exit) {
//                labyrinth[y][x].setKindOfObject(MAP_EXIT);
//                exit = true;
//            }
//
//            x = valGen.nextInt(size);
//            y = valGen.nextInt(size);
//            if (labyrinth[y][x].getKindOfObject() != -1 && fakeTreasurCounter > 0) {
//                labyrinth[y][x].setKindOfObject(MAP_FAKE_TREASURE);
//                fakeTreasurCounter--;
//            }
//        }
//        //return labyrinth;
//
//    }
