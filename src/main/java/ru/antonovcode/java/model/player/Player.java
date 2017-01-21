package ru.antonovcode.java.model.player;


import ru.antonovcode.java.model.ammunition.Ammunition;
import ru.antonovcode.java.model.ammunition.defammunition.IAmmunition;
import ru.antonovcode.java.model.ammunition.defammunition.IAmmunitionID;
import ru.antonovcode.java.model.ammunition.defammunition.IMapIdenfires;
import ru.antonovcode.java.model.ammunition.impl.*;
import ru.antonovcode.java.model.cell.Cell;
import ru.antonovcode.java.model.cell.direction.IDirection;
import ru.antonovcode.java.model.codemodel.CodeModel;
import ru.antonovcode.java.model.hospital.HospitalSystem;
import ru.antonovcode.java.util.ValueGenerator;
import ru.antonovcode.java.util.options.GameOptions;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by alex on 01.08.2014.
 */
public class Player implements IAmmunition, IDirection, IAmmunitionID, IMapIdenfires {

    private String nick;
    private int posX;
    private int posY;

    private boolean gotTreasure = false;
    private boolean gotFakeTreasure = false;
    private boolean alive = true;
    private boolean iniciative = false;
    private boolean winner = false;

    private int cntOfHurt = 0;
    private CodeModel codeModel;
    private int[] actions = {0, 0};

    //used by hospital mode
    private ArrayList<Ammunition> ammunitions;
    private ValueGenerator valGen = new ValueGenerator();

    private static String greenPath = "/images/map_greensmile.png";
    private static String redPath = "/images/map_redsmile.png";
    private static String blackPath = "/images/map_blacksmile.png";

    private static Image greenIm = null;
    private static Image redIm = null;
    private static Image blackIm = null;

    {
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();

            greenIm = tk.getDefaultToolkit().getImage(getClass().getResource(greenPath));
            redIm = tk.getDefaultToolkit().getImage(getClass().getResource(redPath));
            blackIm = tk.getDefaultToolkit().getImage(getClass().getResource(blackPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    0 - gun
    1 - mina
    2 - dinamit
     */
    private void initStartAmmunition() {
        //3 guns, 3 dynamits and 2 mines.
        if (!GameOptions.MAP_AMMUNITION_ON) {
            ammunitions.add(new Gun(GUN));
            ammunitions.add(new Mine(MINE));
            ammunitions.add(new Dinamit(DINAMIT));
        } else {
            ammunitions.add(new Gun(0));
            ammunitions.add(new Mine(0));
            ammunitions.add(new Dinamit(0));
        }


    }

    public Player(final String nick, final int posX, final int posY, final CodeModel codeModel) {
        this.codeModel = codeModel;
        this.nick = nick;
        this.posX = posX;
        this.posY = posY;
        ammunitions = new ArrayList<Ammunition>();
        initStartAmmunition();
    }

    public boolean isWinner(){
        return winner;
    }

    public void setIniciative(final boolean iniciative) {
        this.iniciative = iniciative;
    }

    public boolean getIniciative() {
        return iniciative;
    }

    public int addHurt() {
        cntOfHurt++;
        return cntOfHurt;
    }

    public int delHurt() {
        if (cntOfHurt > 0)
            cntOfHurt--;
        return cntOfHurt;
    }

    public int getCountOfHurt() {
        return cntOfHurt;
    }

    public boolean setGotTreasure() {
        if (gotTreasure || gotFakeTreasure)
            return false;
        else {
            gotTreasure = true;
            return true;
        }
    }

    public boolean setGotFakeTreasure() {
        if (gotTreasure || gotFakeTreasure)
            return false;
        else {
            gotFakeTreasure = true;
            return true;
        }
    }

    public boolean getGotTreasure() {
        return gotTreasure;
    }

    public boolean getGotFakeTreasure() {
        return gotFakeTreasure;
    }

    public String getNick() {
        return this.nick;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void kill() {
        this.alive = false;
    }

    public ArrayList getAmmunitionList() {
        return this.ammunitions;
    }


    public boolean isHerePlayer(final int X, final int Y) {
        if (X == posX && Y == posY)
            return true;
        else
            return false;
    }

    public String getCoordinates() {
        char x = 'A';
        x += posX;
        int y = 1 + posY;
        return (Character.toString(x) + "-" + Integer.toString(y));
    }


    public void paint(Graphics g, final int LHC, final int dif) {
        if (alive) {
            if (iniciative)
                g.setColor(Color.green);
            else
                g.setColor(Color.red);
        } else {
            g.setColor(Color.black);
        }

        g.fillOval(LHC + 2 + dif * posX, LHC + 2 + dif * posY, dif - 4, dif - 4);
        g.setColor(Color.black);
        Graphics2D g1 = (Graphics2D) g;
        BasicStroke penl = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 30);
        g1.setStroke(penl);
        g.drawOval(LHC + 2 + dif * posX, LHC + 2 + dif * posY, dif - 4, dif - 4);
    }

    public void paint(Graphics g, final int X, final int Y, final int dif) {
        if (alive) {
            if (iniciative) {
                //g.setColor(Color.green);
                g.drawImage(greenIm, X, Y, null);
            } else {
                //g.setColor(Color.red);
                g.drawImage(redIm, X, Y, null);
            }
        } else {
            //g.setColor(Color.black);
            g.drawImage(blackIm, X, Y, null);
        }
        //System.out.println("recurse");
        //controller.repaintLabyrinth();
        //g.fillOval(X, Y, dif - 4, dif - 4);
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public boolean useAmmunition(final int ammunitionId, final int DIRECTION) {
        boolean total = false;
        if(!ammunitions.get(ammunitionId).use()){
            iniciative = false;
            //codeModel..repaintLabyrinth();
            return total;
        }

        switch (ammunitionId) {
            case GUN_ID:
                total = useGun(DIRECTION);
                break;
            case MINE_ID:
                total = useMine(DIRECTION);
                break;
            case DINAMIT_ID:
                total = useDinamit(DIRECTION);
                break;
        }
        actions[1] = 1;
        if(GameOptions.END_OF_MOVE_BY_FIRST_WALL)
            iniciative = false;
        else{
            if(actions[0] != 0){
                iniciative = false;
                resetActionFlags();
            }
        }
        //controller.repaintLabyrinth();

        return total;
    }
    //==================================================================================================================
    private boolean useGun(final int DIR) {
        boolean total = false;
        Player killed = null;
        Cell[][] labyrinth = codeModel.getCurrentLabyrinthModel();

        switch (DIR) {
            case DNORTH: {
                for (int i = posY; i > 0; i--) {
                    if (labyrinth[i][posX].getWall(DIR)) {
                        break;
                    } else {
                        if ((killed = codeModel.isHerePlayer(posX, i - 1)) != null) {
                            if(killed.isAlive())
                                break;
                            else
                                killed = null;
                        }
                    }
                }
            }
            break;
            case DEAST: {
                for (int i = posX; i < labyrinth.length; i++) {
                    if (labyrinth[posY][i].getWall(DIR)) {
                        break;
                    } else {
                        if ((killed = codeModel.isHerePlayer(i + 1, posY)) != null) {
                            if(killed.isAlive())
                                break;
                            else
                                killed = null;
                        }
                    }
                }
            }
            break;
            case DSOUTH: {
                for (int i = posY; i < labyrinth.length; i++) {
                    if (labyrinth[i][posX].getWall(DIR)) {
                        break;
                    } else {
                        if ((killed = codeModel.isHerePlayer(posX, i + 1)) != null) {
                            if(killed.isAlive())
                                break;
                            else
                                killed = null;
                        }
                    }
                }
            }
            break;
            case DWEST: {
                for (int i = posX; i > 0; i--) {
                    if (labyrinth[posY][i].getWall(DIR)) {
                        break;
                    } else {
                        if ((killed = codeModel.isHerePlayer(i - 1, posY)) != null) {
                            if(killed.isAlive())
                                break;
                            else
                                killed = null;
                        }
                    }
                }
            }
            break;
        }

        if(killed != null){
            if(killed.isAlive()){
                total = true;
                killed.kill();
                if(killed.getGotTreasure()) {
                    labyrinth[killed.getY()][killed.getX()].setKindOfObject(MAP_TREASURE);

                } else
                if(killed.getGotFakeTreasure()){
                    labyrinth[killed.getY()][killed.getX()].setKindOfObject(MAP_FAKE_TREASURE);
                }
            }


        }
        return total;
    }



    private boolean useMine(final int DIR) {
        Cell[][] labyrinth = codeModel.getCurrentLabyrinthModel();
        labyrinth[posY][posX].setKindOfObject(MAP_PLANTED_MINE);
        return true;
    }

    private boolean useDinamit(final int DIR) {
        boolean total = false;
        Cell[][] labyrinth = codeModel.getCurrentLabyrinthModel();

        switch (DIR){
            case DNORTH:{
                if(posY != 0){
                    labyrinth[posY][posX].delWall(DIR);
                    labyrinth[posY - 1][posX].delWall(DSOUTH);
                    total = true;
                }
            }break;

            case DEAST:{
                if(posX != (labyrinth.length - 1)){
                    labyrinth[posY][posX].delWall(DIR);
                    labyrinth[posY][posX + 1].delWall(DWEST);
                    total = true;
                }
            }break;

            case DSOUTH:{
                if(posY != (labyrinth.length - 1)){
                    labyrinth[posY][posX].delWall(DIR);
                    labyrinth[posY + 1][posX].delWall(DNORTH);
                    total = true;
                }
            }break;

            case DWEST:{
                if(posX != 0){
                    labyrinth[posY][posX].delWall(DIR);
                    labyrinth[posY][posX - 1].delWall(DEAST);
                    total = true;
                }
            }break;

        }

        return total;
    }
    //==================================================================================================================

    public boolean moveUp() {
        boolean total = false;
        Cell[][] labyrinth = codeModel.getCurrentLabyrinthModel();
        if(GameOptions.END_OF_MOVE_BY_FIRST_WALL){
            if (labyrinth != null) {
                if (labyrinth[posY][posX].getWall(DNORTH)) {
                    iniciative = false;
                    total = false;
                } else {
                    posY -= 1;
                    if (checkCell())
                        total = true;

                }
                //codeModel.repaintLabyrinth();
            }
            return total;
        } else {
            if(labyrinth != null){
                if(actions[0] == 0){
                    if (labyrinth[posY][posX].getWall(DNORTH)) {
                        total = true;
                    } else {
                        posY -= 1;
                        total = checkCell();
                        actions[0] = 1;
                    }
                } else {
                    resetActionFlags();
                    iniciative = false;
                    total = false;
                }
            }
            return total;
        }
    }

    private void resetActionFlags() {
        actions[0] = 0;
        actions[1] = 0;
    }

    public boolean moveDown() {
        boolean total = false;
        Cell[][] labyrinth = codeModel.getCurrentLabyrinthModel();
        if(GameOptions.END_OF_MOVE_BY_FIRST_WALL){

            if (labyrinth != null) {
                if (labyrinth[posY][posX].getWall(DSOUTH)) {
                    iniciative = false;
                    total = false;
                } else {
                    posY += 1;
                    if (checkCell())
                        total = true;

                }
                //codeModel.repaintLabyrinth();
            }
            return total;
        } else {
            if(labyrinth != null){
                if(actions[0] == 0){
                    if (labyrinth[posY][posX].getWall(DSOUTH)) {
                        total = true;
                    } else {
                        posY += 1;
                        total = checkCell();
                        actions[0] = 1;
                    }
                } else {
                    resetActionFlags();
                    iniciative = false;
                    total = false;
                }
            }
            return total;
        }

    }

    public boolean moveLeft() {
        boolean total = false;
        Cell[][] labyrinth = codeModel.getCurrentLabyrinthModel();
        if(GameOptions.END_OF_MOVE_BY_FIRST_WALL){
            if (labyrinth != null) {
                if (labyrinth[posY][posX].getWall(DWEST)) {
                    iniciative = false;
                    total = false;
                } else {
                    posX -= 1;
                    if (checkCell())
                        total = true;

                }
                //codeModel.repaintLabyrinth();
            }
            return total;
        } else {
            if(labyrinth != null){
                if(actions[0] == 0){
                    if (labyrinth[posY][posX].getWall(DWEST)) {
                        total = true;
                    } else {
                        posX -= 1;
                        total = checkCell();
                        actions[0] = 1;
                    }
                } else {
                    resetActionFlags();
                    iniciative = false;
                    total = false;
                }
            }
            return total;
        }
    }

    public boolean moveRight() {
        boolean total = false;
        Cell[][] labyrinth = codeModel.getCurrentLabyrinthModel();
        if(GameOptions.END_OF_MOVE_BY_FIRST_WALL){
            if (labyrinth != null) {
                if (labyrinth[posY][posX].getWall(DEAST)) {
                    iniciative = false;
                    total = false;
                } else {
                    posX += 1;
                    if (checkCell())
                        total = true;

                }
                //codeModel.repaintLabyrinth();
            }
            return total;
        } else {
            if(labyrinth != null){
                if(actions[0] == 0){
                    if (labyrinth[posY][posX].getWall(DEAST)) {
                        total = true;
                    } else {
                        posX += 1;
                        total = checkCell();
                        actions[0] = 1;
                    }
                } else {
                    resetActionFlags();
                    iniciative = false;
                    total = false;
                }
            }
            return total;
        }
    }

    private boolean checkCell() {
        boolean total = false;
        Cell[][] labyrinth = codeModel.getCurrentLabyrinthModel();
        if (labyrinth != null) {
            int kindOfCell = labyrinth[posY][posX].getKindOfObject();

            switch (kindOfCell) {
                case MAP_PLANTED_MINE:
                    iniciative = false;
                    if (GameOptions.HOSPITAL_MODE_ON)
                        addHurt();//alive = false;-----------------------------in settings
                    else
                        kill();
                    total = false;
                    labyrinth[posY][posX].setKindOfObject(-1);
                    if (gotTreasure) {
                        gotTreasure = false;
                        labyrinth[posY][posX].setKindOfObject(MAP_TREASURE);
                    } else {
                        if (gotFakeTreasure) {
                            gotFakeTreasure = false;
                            labyrinth[posY][posX].setKindOfObject(MAP_FAKE_TREASURE);
                        }
                    }
                    break;
                case MAP_TRAP:
                    iniciative = false;
                    addHurt();//alive = false;-----------------------------in settings
                    total = false;
                    labyrinth[posY][posX].setKindOfObject(-1);
                    if (gotTreasure) {
                        gotTreasure = false;
                        labyrinth[posY][posX].setKindOfObject(MAP_TREASURE);
                    } else {
                        if (gotFakeTreasure) {
                            gotFakeTreasure = false;
                            labyrinth[posY][posX].setKindOfObject(MAP_FAKE_TREASURE);
                        }
                    }
                    break;

                case MAP_PORTAL:
                    iniciative = false;
                    while (true) {
                        posX = valGen.nextInt(labyrinth.length);
                        posY = valGen.nextInt(labyrinth.length);
                        if (labyrinth[posY][posX].getKindOfObject() == -1)
                            break;
                    }
                    total = false;
                    break;
                case MAP_TREASURE:
                    if (this.getCountOfHurt() == 0 && !gotFakeTreasure) {
                        gotTreasure = true;
                        labyrinth[posY][posX].setKindOfObject(-1);
                        iniciative = false;
                        total = false;
                        if(!GameOptions.EXIT_MODE_ON)
                            winner = true;
                    } else {
                        total = true;
                    }
                    break;

                case MAP_FAKE_TREASURE:
                    if (this.getCountOfHurt() == 0 && !gotFakeTreasure && !gotTreasure) {
                        gotFakeTreasure = true;
                        labyrinth[posY][posX].setKindOfObject(-1);
                        iniciative = false;
                        total = false;
                    } else {
                        total = true;
                    }
                    break;

                case MAP_HOSPITAL:
                    if (this.getCountOfHurt() == 0)
                        total = true;
                    else {
                        HospitalSystem hospitalSystem = codeModel.getHospitalSystem();
                        if(!hospitalSystem.checkPatientAt(posX, posY, nick)){
                            this.delHurt();
                            total = false;
                            iniciative = false;
                        } else
                            total = true;
                    }
                    break;

                case MAP_EXIT:
                    if (gotFakeTreasure) {
                        gotFakeTreasure = false;
                        total = false;
                        iniciative = false;
                    } else {
                        if (gotTreasure) {
                            winner = true;//controller.setWinner(this);
                            total = false;
                            iniciative = false;
                        } else {
                            total = true;
                        }
                    }
                    break;

                case MAP_GUN:
                    ammunitions.get(GUN_ID).addOne();
                    labyrinth[posY][posX].setKindOfObject(-1);
                    total = true;
                    break;

                case MAP_MINE:
                    ammunitions.get(MINE_ID).addOne();
                    if(GameOptions.DINAMIT_AND_MINE_ONLY)
                        ammunitions.get(DINAMIT_ID).addOne();
                    labyrinth[posY][posX].setKindOfObject(-1);
                    total = true;
                    break;

                case MAP_DINAMIT:
                    ammunitions.get(DINAMIT_ID).addOne();
                    if(GameOptions.DINAMIT_AND_MINE_ONLY)
                        ammunitions.get(MINE_ID).addOne();
                    labyrinth[posY][posX].setKindOfObject(-1);
                    total = true;
                    break;

                case -1:
                    total = true;
                    break;
            }

        }
        return total;


    }

}
