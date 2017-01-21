package ru.antonovcode.java.view.panels;

import ru.antonovcode.java.model.ammunition.defammunition.IMapIdenfires;
import ru.antonovcode.java.model.cell.Cell;
import ru.antonovcode.java.model.cell.direction.IDirection;
import ru.antonovcode.java.model.hospital.HospitalSystem;
import ru.antonovcode.java.model.player.Player;
import ru.antonovcode.java.util.options.Strings;
import ru.antonovcode.java.view.dialogs.NewGameDialog;
import ru.antonovcode.java.view.interfaces.CodeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alex on 28.08.2014.
 */
public class LabyrinthPanel extends JPanel implements IDirection, IMapIdenfires {

    private Cell[][] labyrinthModel;
    private int labyrinthSize;
    public static final int LEFTHIGHCORNER = 25;
    public static final int DIF = 25;
    private Player players[] = null;
    public boolean colorit = false;
    private final int bigside = 36;
    private final int smallside = 12;
    private final int midside = 24;


    private static boolean editMode = true;
    private JButton doneEditLabyrinthButton;

    private javax.swing.Timer timer = new Timer(100, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //System.out.println("WOW!"); //To change body of generated methods, choose Tools | Templates.
            //model.update(cur_time);
            repaint();
            //model.repaint();
            //cur_time += 0.5;
        }
    });
    private static String portalPath = "/images/gif_map_portal.gif";
    //private static String portalPath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "map_portal.png";
    private static String minePath = "/images/map_mine.png";
    private static String treasurePath = "/images/map_treasure.png";
    private static String exitPath = "/images/map_exit.png";
    private static String trapPath = "/images/map_trap.png";
    private static String hospitalPath = "/images/map_hospital.png";
    private static String faketreasurePath = "/images/map_faketreasure.png";

    private static String wallsPath = "/images/map_smallwall.png";
    private static String wallbhPath = "/images/map_bigwall_h.png";
    private static String wallbvPath = "/images/map_bigwall_v.png";
    private static String nowallhPath = "/images/map_nowall_h.png";
    private static String nowallvPath = "/images/map_nowall_v.png";

    private static String gunAmoPath = "/images/map_gun_amo.png";
    private static String mineAmoPath = "/images/map_mine_amo.png";
    private static String dinamitAmoPath = "/images/map_dinamit_amo.png";

    private static Image portalIm = null;
    private static Image mineIm = null;
    private static Image treasureIm = null;
    private static Image exitIm = null;
    private static Image trapIm = null;
    private static Image hospitalIm = null;
    private static Image faketreasureIm = null;

    private static Image smallwallIm = null;
    private static Image bigwallhIm = null;
    private static Image bigwallvIm = null;
    private static Image nowallhIm = null;
    private static Image nowallvIm = null;

    private static Image mapGunAmoIm = null;
    private static Image mapMineAmoIm = null;
    private static Image mapDinamitAmoIm = null;

    {
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            //portalIm = tk.getDefaultToolkit().getImage(getClass().getResource(exitPath));
            portalIm = tk.getDefaultToolkit().getImage(getClass().getResource(portalPath));
            mineIm = tk.getDefaultToolkit().getImage(getClass().getResource(minePath));
            treasureIm = tk.getDefaultToolkit().getImage(getClass().getResource(treasurePath));
            exitIm = tk.getDefaultToolkit().getImage(getClass().getResource(exitPath));
            trapIm = tk.getDefaultToolkit().getImage(getClass().getResource(trapPath));
            hospitalIm = tk.getDefaultToolkit().getImage(getClass().getResource(hospitalPath));
            faketreasureIm = tk.getDefaultToolkit().getImage(getClass().getResource(faketreasurePath));
            smallwallIm = tk.getDefaultToolkit().getImage(getClass().getResource(wallsPath));
            bigwallhIm = tk.getDefaultToolkit().getImage(getClass().getResource(wallbhPath));
            bigwallvIm = tk.getDefaultToolkit().getImage(getClass().getResource(wallbvPath));
            nowallhIm = tk.getDefaultToolkit().getImage(getClass().getResource(nowallhPath));
            nowallvIm = tk.getDefaultToolkit().getImage(getClass().getResource(nowallvPath));

            mapGunAmoIm = tk.getDefaultToolkit().getImage(getClass().getResource(gunAmoPath));
            mapMineAmoIm = tk.getDefaultToolkit().getImage(getClass().getResource(mineAmoPath));
            mapDinamitAmoIm = tk.getDefaultToolkit().getImage(getClass().getResource(dinamitAmoPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LabyrinthPanel(final CodeWindow window) {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (editMode) {
                    int x = e.getX();
                    int y = e.getY();
                    if (x == y)
                        return;
                    if(((x - DIF) / DIF >= labyrinthSize || ((y - DIF) / DIF) >= labyrinthSize))
                        return;
                    if (!colorit) {
                        int X = ((x - DIF) / DIF);
                        int Y = ((y - DIF) / DIF);

                        int xo = x % DIF;
                        int yo = y % DIF;
                        System.out.println("origin x;y = " + x + ";" + y + "\nmath X;Y = " + X + ";" + Y + "\n%x;y = " + xo + ";" + yo);
                        if (xo > DIF / 2) {
                            if (yo > DIF / 2) {
                                //if (X < labyrinthSize && Y < labyrinthSize)
                                if (yo > xo) {
                                    if (Y < labyrinthSize - 1)
                                        if (labyrinthModel[Y][X].getWall(DSOUTH)) {
                                            labyrinthModel[Y][X].delWall(DSOUTH);
                                            labyrinthModel[Y + 1][X].delWall(DNORTH);
                                        } else {
                                            labyrinthModel[Y][X].addWall(DSOUTH);
                                            labyrinthModel[Y + 1][X].addWall(DNORTH);
                                        }

                                } else {
                                    if (X < labyrinthSize - 1)
                                        if (labyrinthModel[Y][X].getWall(DEAST)) {
                                            labyrinthModel[Y][X].delWall(DEAST);
                                            labyrinthModel[Y][X + 1].delWall(DWEST);
                                        } else {
                                            labyrinthModel[Y][X].addWall(DEAST);
                                            labyrinthModel[Y][X + 1].addWall(DWEST);
                                        }

                                }

                            } else {
                                //if (X < labyrinthSize && Y > 0)
                                if (yo < (DIF - xo)) {
                                    if (Y > 0)
                                        if (labyrinthModel[Y][X].getWall(DNORTH)) {
                                            labyrinthModel[Y][X].delWall(DNORTH);
                                            labyrinthModel[Y - 1][X].delWall(DSOUTH);
                                        } else {
                                            labyrinthModel[Y][X].addWall(DNORTH);
                                            labyrinthModel[Y - 1][X].addWall(DSOUTH);
                                        }
                                } else {
                                    if (X < labyrinthSize - 1)
                                        if (labyrinthModel[Y][X].getWall(DEAST)) {
                                            labyrinthModel[Y][X].delWall(DEAST);
                                            labyrinthModel[Y][X + 1].delWall(DWEST);
                                        } else {
                                            labyrinthModel[Y][X].addWall(DEAST);
                                            labyrinthModel[Y][X + 1].addWall(DWEST);
                                        }

                                }
                            }

                        } else {
                            //if (X > 0 && Y < labyrinthSize)
                            if (yo > DIF / 2) {
                                if (DIF - yo < xo) {
                                    if (Y < labyrinthSize - 1)
                                        if (labyrinthModel[Y][X].getWall(DSOUTH)) {
                                            labyrinthModel[Y][X].delWall(DSOUTH);
                                            labyrinthModel[Y + 1][X].delWall(DNORTH);
                                        } else {
                                            labyrinthModel[Y][X].addWall(DSOUTH);
                                            labyrinthModel[Y + 1][X].addWall(DNORTH);
                                        }
                                } else {
                                    if (X > 0)
                                        if (labyrinthModel[Y][X].getWall(DWEST)) {
                                            labyrinthModel[Y][X].delWall(DWEST);
                                            labyrinthModel[Y][X - 1].delWall(DEAST);
                                        } else {
                                            labyrinthModel[Y][X].addWall(DWEST);
                                            labyrinthModel[Y][X - 1].addWall(DEAST);
                                        }
                                }

                            } else {
                                //if (X > 0 && Y > 0)
                                if (yo < xo) {
                                    if (Y > 0)
                                        if (labyrinthModel[Y][X].getWall(DNORTH)) {
                                            labyrinthModel[Y][X].delWall(DNORTH);
                                            labyrinthModel[Y - 1][X].delWall(DSOUTH);
                                        } else {
                                            labyrinthModel[Y][X].addWall(DNORTH);
                                            labyrinthModel[Y - 1][X].addWall(DSOUTH);
                                        }
                                } else {
                                    if (X > 0)
                                        if (labyrinthModel[Y][X].getWall(DWEST)) {
                                            labyrinthModel[Y][X].delWall(DWEST);
                                            labyrinthModel[Y][X - 1].delWall(DEAST);
                                        } else {
                                            labyrinthModel[Y][X].addWall(DWEST);
                                            labyrinthModel[Y][X - 1].addWall(DEAST);
                                        }
                                }
                            }

                        }

                        //----------------------if colorit==true-------------------
                    } else {

                    }
                    repaint();
                } else {
                    //newGameDialog.setVisible(true);
                    //editMode = true;
                }

            }
        });
        doneEditLabyrinthButton = new JButton(Strings.LABPANEL_EDITBUTTON);
        this.setLayout(new SpringLayout());
        add(doneEditLabyrinthButton);
        doneEditLabyrinthButton.setEnabled(false);
        doneEditLabyrinthButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(doneEditLabyrinthButton.isEnabled()){
                    doneEditLabyrinthButton.setVisible(false);
                    editMode = false;
                    window.startGame();
                }

            }
        });
    }

    public void setLabyrinthModel(Cell[][] labyrinthModel, final int labyrinthSize) {
        doneEditLabyrinthButton.setVisible(true);
        doneEditLabyrinthButton.setEnabled(true);
        editMode = true;
        this.labyrinthModel = labyrinthModel;
        this.labyrinthSize = labyrinthSize;
        timer.start();
    }

    public void setPlayers(Player[] players) {
        this.players = players;
        this.repaint();
    }

    public static boolean getEditMode(){
        return editMode;
    }

    public static void setEditMode(){
        editMode = true;
    }

    @Override
    public void paint(Graphics gr) {

        Graphics2D g = (Graphics2D)gr;
        BasicStroke pen1 = new BasicStroke(3);
        g.setStroke(pen1);
        g.setFont(new Font("TimesRoman", Font.BOLD, 14));
        doneEditLabyrinthButton.repaint();

        if (colorit)
            drawLab(g);
        else {
            int x = LEFTHIGHCORNER;
            int y = LEFTHIGHCORNER;

            g.setColor(Color.GRAY);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.BLACK);

            if (labyrinthModel != null) {
                int num = 1;
                for (int i = 0; i < labyrinthSize; i++) {
                    char sign = 'A';
                    g.drawString(Integer.toString(num), x - 15, y + 20);
                    g.drawString(Integer.toString(num), DIF + labyrinthSize * DIF + 2, y + 20);
                    num++;
                    for (int j = 0; j < labyrinthSize; j++) {
                        if (i == 0) {
                            g.drawString(Character.toString(sign), x + 10, y - 3);
                            sign++;
                        }
                        if (i == (labyrinthSize - 1)) {
                            g.drawString(Character.toString(sign), x + 10, y + 2 * DIF - 10);
                            sign++;
                        }

                        int mapObject = labyrinthModel[i][j].getKindOfObject();
                        switch (mapObject) {
                            case MAP_PLANTED_MINE:
                                g.drawImage(mineIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_PORTAL:
                                g.drawImage(portalIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_TREASURE:
                                g.drawImage(treasureIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_EXIT:
                                g.drawImage(exitIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_HOSPITAL:
                                g.drawImage(hospitalIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_TRAP:
                                g.drawImage(trapIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_FAKE_TREASURE:
                                g.drawImage(faketreasureIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_GUN:
                                g.drawImage(mapGunAmoIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_MINE:
                                g.drawImage(mapMineAmoIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                            case MAP_DINAMIT:
                                g.drawImage(mapDinamitAmoIm, DIF + DIF * j, DIF + DIF * i, null);
                                break;
                        }

                        //System.out.print(labyrinthModel[i][j].getKey() + " ");
                        for (int k = 0; k < 4; k++) {
                            switch (k) {
                                case 0:
                                    if (labyrinthModel[i][j].getWall(DNORTH))
                                        g.drawLine(x, y, (x + DIF), y);
                                    break;
                                case 1:
                                    if (labyrinthModel[i][j].getWall(DEAST))
                                        g.drawLine((x + DIF), y, (x + DIF), (y + DIF));
                                    break;
                                case 2:
                                    if (labyrinthModel[i][j].getWall(DSOUTH))
                                        g.drawLine(x, (y + DIF), (x + DIF), (y + DIF));
                                    break;
                                case 3:
                                    if (labyrinthModel[i][j].getWall(DWEST))
                                        g.drawLine(x, y, x, (y + DIF));
                                    break;
                            }
                        }

                        x += DIF;
                    }
                    //System.out.println();
                    x = LEFTHIGHCORNER;
                    y += DIF;
                }
            }

            Player iniciativePlayer = null;
            if (players != null) {
                for (final Player p : players) {
                    if(p.getIniciative())
                        iniciativePlayer = p;
                    else
                        p.paint(g, LEFTHIGHCORNER, DIF);
                }
                if(iniciativePlayer != null)
                    iniciativePlayer.paint(g, LEFTHIGHCORNER, DIF);
            }
            //System.out.println("------------------");
        }


    }

    private void drawLab(Graphics g) {

        int x = LEFTHIGHCORNER;
        int y = LEFTHIGHCORNER;

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(Color.BLACK);


        if (labyrinthModel != null) {
            int startpoint = labyrinthSize * (smallside + bigside) + x;
            x = startpoint;

            boolean firstloop = true;
            for (int i = 0; i < labyrinthSize; i++) {
                if (i > 0)
                    x = startpoint + smallside * (i * 3) + smallside;
                if (i == 0)
                    for (int j = labyrinthSize - 1; j >= 0; j--) {

                        g.drawImage(smallwallIm, x, y, null);
                        g.drawImage(bigwallhIm, x - bigside, y, null);
                        if (j == 0) {
                            g.drawImage(smallwallIm, x - bigside - smallside, y, null);
                            y += smallside;
                            x = startpoint + smallside;

                        } else
                            x -= (bigside + smallside);
                    }

                for (int j = labyrinthSize - 1; j >= 0; j--) {
                    int mapObject = labyrinthModel[i][j].getKindOfObject();
                    switch (mapObject) {
                        case MAP_PLANTED_MINE:
                            g.drawImage(mineIm, x - midside, y, null);
                            break;
                        case MAP_PORTAL:
                            g.drawImage(portalIm, x - midside, y, null);
                            break;
                        case MAP_TREASURE:
                            g.drawImage(treasureIm, x - midside, y, null);
                            break;
                        case MAP_EXIT:
                            g.drawImage(exitIm, x - midside, y, null);
                            break;
                        case MAP_HOSPITAL:
                            g.drawImage(hospitalIm, x - midside, y, null);
                            break;
                        case MAP_TRAP:
                            g.drawImage(trapIm, x - midside, y, null);
                            break;
                        case MAP_FAKE_TREASURE:
                            g.drawImage(faketreasureIm, x - midside, y, null);
                            break;
                        case MAP_GUN:
                            g.drawImage(mapGunAmoIm, x - midside, y, null);
                            break;
                        case MAP_MINE:
                            g.drawImage(mapMineAmoIm, x - midside, y, null);
                            break;
                        case MAP_DINAMIT:
                            g.drawImage(mapDinamitAmoIm, x - midside, y, null);
                            break;
                    }

                    Player iniciativePlayer = null;

                    if (players != null) {
                        for (Player p : players) {
                            if (p.getX() == j && p.getY() == i) {
                                if(p.getIniciative())
                                    iniciativePlayer = p;
                                else
                                    p.paint(g, x - midside, y, DIF);
                            }
                        }
                        if(iniciativePlayer != null)
                            if (iniciativePlayer.getX() == j && iniciativePlayer.getY() == i)
                                iniciativePlayer.paint(g, x - midside, y, DIF);

                    }


                    for (int k = 1; k < 4; k++) {
                        switch (k) {
                            case 1:
                                if (labyrinthModel[i][j].getWall(DEAST)) {
                                    g.drawImage(bigwallvIm, x, y, null);
                                } else {
                                    g.drawImage(nowallvIm, x, y, null);
                                }
                                break;
                            case 2:
                                if (labyrinthModel[i][j].getWall(DWEST)) {
                                    g.drawImage(bigwallvIm, x - smallside - bigside, y, null);
                                } else {
                                    g.drawImage(nowallvIm, x - smallside - bigside, y, null);
                                }
                                break;
                            case 3:
                                g.drawImage(smallwallIm, x + midside, y + midside, null);
                                if (labyrinthModel[i][j].getWall(DSOUTH)) {
                                    g.drawImage(bigwallhIm, x - smallside, y + midside, null);
                                } else {
                                    g.drawImage(nowallhIm, x - smallside, y + midside, null);
                                }
                                g.drawImage(smallwallIm, x - midside, y + midside, null);
                                break;
                        }
                    }

                    x -= (smallside + bigside);
                }


                y += (bigside);
            }
        }

    }

    public boolean getColorit() {
        return colorit;
    }

    public void changeColorit() {
        colorit = !colorit;
        repaint();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void updateLanguage(){
        doneEditLabyrinthButton.setText(Strings.LABPANEL_EDITBUTTON);
    }
}