package ru.antonovcode.java.view;

import ru.antonovcode.java.controller.Controller;
import ru.antonovcode.java.model.ammunition.Ammunition;
import ru.antonovcode.java.model.cell.Cell;
import ru.antonovcode.java.model.hospital.HospitalSystem;
import ru.antonovcode.java.model.player.Player;
import ru.antonovcode.java.util.options.GameOptions;
import ru.antonovcode.java.util.options.Strings;
import ru.antonovcode.java.view.dialogs.InfoDialog;
import ru.antonovcode.java.view.dialogs.NewGameDialog;
import ru.antonovcode.java.view.dialogs.PlayersCreatingDialog;
import ru.antonovcode.java.view.interfaces.CodeWindow;
import ru.antonovcode.java.view.panels.LabyrinthPanel;
import ru.antonovcode.java.view.panels.PlayersPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by alex on 29.07.2014.
 */
public class Window extends JFrame implements CodeWindow {

    private boolean sessionEnd = false;


    private NewGameDialog newGameDialog = new NewGameDialog(this);
    private PlayersCreatingDialog playersCreatingDialog = new PlayersCreatingDialog(this);
    public InfoDialog infoDialog = new InfoDialog();

    private LabyrinthPanel labyrinthPanel;
    private JScrollPane labyrinthScroll;
    private PlayersPanel[] playerPanels;
    //----------for playersPanels-----------

    //--------------------------------------

    private JTabbedPane playersPane;
    private SpringLayout springLayout = new SpringLayout();

    private JMenuBar mainMenu;
    private JMenu fileMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem exitMenuItem;

    private JMenu optionsMenu;
    private JMenuItem customizationMenuItem;
    private JCheckBoxMenuItem draw3DMenuItem;


    private final int X = 550;
    private final int Y = 550;

    private int labyrinthSize = 0;


    public Window() {

        mainMenu = new JMenuBar();
        fileMenu = new JMenu(Strings.WINDOW_MAIN);
        newGameMenuItem = new JMenuItem(Strings.WINDOW_MAIN_NEWGAME);
        draw3DMenuItem = new JCheckBoxMenuItem("3D");

        optionsMenu = new JMenu(Strings.WINDOW_OPTION);
        customizationMenuItem = new JMenuItem(Strings.WINDOW_CUSTOMS);
        exitMenuItem = new JMenuItem(Strings.WINDOW_EXIT);

        setTitle(Strings.WINDOW_TITLE);

        fileMenu.add(newGameMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        optionsMenu.add(customizationMenuItem);
        optionsMenu.add(draw3DMenuItem);

        mainMenu.add(fileMenu);
        mainMenu.add(optionsMenu);
        this.setJMenuBar(mainMenu);

        labyrinthPanel = new LabyrinthPanel(this);
        labyrinthScroll = new JScrollPane(labyrinthPanel);

        playersPane = new JTabbedPane();

        setSize(1365, 730);


        labyrinthPanel.setBackground(Color.RED);
        labyrinthPanel.setVisible(true);


        //doneEditLabyrinthButton.setLocation(700, 100);

        add(labyrinthScroll);
        add(playersPane);

        labyrinthScroll.setPreferredSize(new Dimension(2 * X - X / 3, Y));
        labyrinthPanel.setPreferredSize(new Dimension(3 * X + X / 2, Y + Y / 2));
        playersPane.setPreferredSize(new Dimension((X - X / 4) + 20, Y));


        setLayout(springLayout);
        setResizable(false);

        springLayout.putConstraint(SpringLayout.NORTH, labyrinthScroll, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, labyrinthScroll, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, labyrinthScroll, 600, SpringLayout.SOUTH, this);

        springLayout.putConstraint(SpringLayout.NORTH, playersPane, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, playersPane, 2 * X - X / 3 + 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, playersPane, 600, SpringLayout.SOUTH, this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    @Override
    public void newGame(int size, int numOfPlayers) {
        sessionEnd = false;
        LabyrinthPanel.setEditMode();
        labyrinthSize = size;
        playersCreatingDialog.setCountOfPlayers(numOfPlayers);
        playersCreatingDialog.setVisible(true);
    }

    @Override
    public void createPlayersPanels(String[] players) {
        playersPane.removeAll();
        playerPanels = new PlayersPanel[players.length];
        for (int i = 0; i < players.length; i++) {
            playerPanels[i] = new PlayersPanel(players[i], this);
//            if(i == 0){
//                playerPanels[i].panelOn();
//            }
            playersPane.add(playerPanels[i]);
        }
        playersPane.setSelectedIndex(0);
        //playersPane.setEnabled(false);

    }

    @Override
    public void setPlayersToPlayerPanels(Player[] player) {
        int countOfPanels = playersPane.getTabCount();
        for (int i = 0; i < countOfPanels; i++)
            ((PlayersPanel) playersPane.getComponentAt(i)).setPlayer(player[i]);
    }


    @Override
    public void repaintLabyrinth(Cell[][] labyrinth, final int size, Player[] players) {
        labyrinthPanel.setLabyrinthModel(labyrinth, size);
        labyrinthPanel.setPlayers(players);
        labyrinthPanel.repaint();
        //infoDialog.setMessage(Strings.WINDOW_EDITMESSAGE);
        //infoDialog.setVisible(true);
        JOptionPane.showMessageDialog(null, Strings.WINDOW_EDITMESSAGE);
    }


    private void showWinner() {
        JOptionPane.showMessageDialog(null, Strings.WINDOW_WINNER + playersPane.getSelectedComponent().getName());
    }


    @Override
    public String selectNextPlayer() {
        ((PlayersPanel) playersPane.getSelectedComponent()).panelOff();
        if (((PlayersPanel) playersPane.getSelectedComponent()).isWinner()) {
            sessionEnd = true;
            showWinner();
            return null;
        }
        int tabCnt = playersPane.getTabCount();
        int currentIndex = playersPane.getSelectedIndex();
        int countOfKilled = 0;
        for (int i = 0; i < tabCnt; i++) {
            playersPane.setSelectedIndex(i);
            if (((PlayersPanel) playersPane.getSelectedComponent()).isKilled())
                countOfKilled++;
        }

        if (countOfKilled == tabCnt - 1) {
            //playersPane.setSelectedIndex(currentIndex);
            for (int i = 0; i < tabCnt; i++) {
                playersPane.setSelectedIndex(i);
                if (!((PlayersPanel) playersPane.getSelectedComponent()).isKilled())
                    break;
            }
            sessionEnd = true;
            showWinner();
            return null;
        }

        while (true) {
            if (currentIndex == tabCnt - 1)
                currentIndex = 0;
            else
                currentIndex++;
            playersPane.setSelectedIndex(currentIndex);
            if (!((PlayersPanel) playersPane.getSelectedComponent()).isKilled()) {
                ((PlayersPanel) playersPane.getSelectedComponent()).panelOn();
                break;
            }

        }

        return playersPane.getSelectedComponent().getName();

    }

    @Override
    public void setCodeHandler(final Controller.CodeHandler handler) {
        newGameMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                newGameDialog.setVisible(true);
            }
        });

        draw3DMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                labyrinthPanel.changeColorit();
            }
        });

        playersCreatingDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (playerPanels != null) {
                    String str[] = new String[playerPanels.length];
                    for (int i = 0; i < playerPanels.length; i++) {
                        str[i] = playerPanels[i].getName();
                    }
                    //System.out.println("===============================+++++++++++++++++++====================");
                    handler.createNewGame(labyrinthSize, str);
                }
            }
        });

        customizationMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                newGameDialog.openOptionDialog();
            }
        });
        exitMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //dispose();
                System.exit(0);
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (!sessionEnd && isActive()) {
                    if (e.getID() != KeyEvent.KEY_PRESSED) {
                        return false;
                    }
                    if (playersPane.getSelectedComponent() != null) {
                        String nextPlayer = null;
                        int keyCode = e.getKeyCode();
                        switch (keyCode) {
                            case KeyEvent.VK_W:
                                //upButton.doClick();
                                if (!((PlayersPanel) playersPane.getSelectedComponent()).UP()) {
                                    nextPlayer = selectNextPlayer();
                                }
                                labyrinthPanel.repaint();
                                break;
                            case KeyEvent.VK_S:
                                //downButton.doClick();
                                if (!((PlayersPanel) playersPane.getSelectedComponent()).DOWN()) {
                                    nextPlayer = selectNextPlayer();
                                }
                                labyrinthPanel.repaint();
                                break;
                            case KeyEvent.VK_A:
                                //leftButton.doClick();
                                if (!((PlayersPanel) playersPane.getSelectedComponent()).LEFT()) {
                                    nextPlayer = selectNextPlayer();
                                }

                                labyrinthPanel.repaint();
                                break;
                            case KeyEvent.VK_D:
                                //rightButton.doClick();
                                if (!((PlayersPanel) playersPane.getSelectedComponent()).RIGHT()) {
                                    nextPlayer = selectNextPlayer();
                                }

                                labyrinthPanel.repaint();
                                break;
                            case KeyEvent.VK_F:
                                //rightButton.doClick();
                                ((PlayersPanel) playersPane.getSelectedComponent()).setUseCheckBox();
                                break;
                            case KeyEvent.VK_E:
                                //rightButton.doClick();
                                ((PlayersPanel) playersPane.getSelectedComponent()).listAmmunitionDown();
                                break;
                            case KeyEvent.VK_Q:
                                //rightButton.doClick();
                                ((PlayersPanel) playersPane.getSelectedComponent()).listAmmunitionUp();
                                break;
                            default:

                        }
                        if (nextPlayer != null)
                            JOptionPane.showMessageDialog(null, Strings.WINDOW_NEXTMOVE + nextPlayer);


                    }
                    return false;
                }
                return false;
            }

        });
    }

    @Override
    public void updateLanguage() {
        Strings.updateFields();
        setTitle(Strings.WINDOW_TITLE);
        fileMenu.setText(Strings.WINDOW_MAIN);
        newGameMenuItem.setText(Strings.WINDOW_MAIN_NEWGAME);
        optionsMenu.setText(Strings.WINDOW_OPTION);
        customizationMenuItem.setText(Strings.WINDOW_CUSTOMS);
        exitMenuItem.setText(Strings.WINDOW_EXIT);

        labyrinthPanel.updateLanguage();
        newGameDialog.updateLanguage();
        playersCreatingDialog.updateLanguage();
        if (playerPanels != null)
            for (PlayersPanel p : playerPanels)
                p.updateLanguage();
    }

    @Override
    public void startGame() {
        playerPanels[0].panelOn();
    }


}
