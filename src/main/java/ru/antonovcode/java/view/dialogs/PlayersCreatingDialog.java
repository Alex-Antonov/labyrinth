package ru.antonovcode.java.view.dialogs;


import ru.antonovcode.java.util.options.Strings;
import ru.antonovcode.java.view.interfaces.CodeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alex on 01.08.2014.
 */
public class PlayersCreatingDialog extends JDialog {

    private SpringLayout springLayout = new SpringLayout();
    private JButton createButton;

    private JLabel playerLabel;
    private JTextField playerNameField;

    private int cntOfPlayers = 0;
    private int counter = 0;



    private final int X = 250;
    private final int Y = 150;

    private String[] order = Strings.CREATINGPLAYERSDIALOG_ORDER;
    private String[] nicks;

    public PlayersCreatingDialog(final CodeWindow window){
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);


        createButton = new JButton(Strings.CREATINGPLAYERSDIALOG_ADD);
        playerLabel = new JLabel();
        playerNameField = new JTextField(15);
        //playerNameField.setPreferredSize(new Dimension(100,50));

        this.setSize(X, Y);
        //this.setPreferredSize(new Dimension(X, Y));
        this.setLocation(550, 250);






        setLayout(springLayout);


        add(createButton);
        add(playerLabel);
        add(playerNameField);


        springLayout.putConstraint(SpringLayout.NORTH, playerLabel, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, playerLabel, 5, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, playerNameField, 5, SpringLayout.SOUTH, playerLabel);
        springLayout.putConstraint(SpringLayout.WEST, playerNameField, 5, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, createButton, 5, SpringLayout.SOUTH, playerNameField);
        springLayout.putConstraint(SpringLayout.WEST, createButton, 5, SpringLayout.WEST, this);

        this.setTitle(Strings.CREATINGPLAYERSDIALOG_TITLE);
        this.setModal(true);
        playerLabel.setText(Strings.CREATINGPLAYERSDIALOG_ENTER + " "+ order[counter]+Strings.CREATINGPLAYERSDIALOG_PLAYER);
        playerNameField.requestFocus();

        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPlayer();
                if(counter == cntOfPlayers){
                    window.createPlayersPanels(nicks);
                    playerNameField.requestFocus();
                    setVisible(false);
                }
            }
        });

        playerNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_ENTER:
                        addPlayer();
                        if(counter == cntOfPlayers){
                            window.createPlayersPanels(nicks);
                            playerNameField.requestFocus();
                            setVisible(false);
                        }
                        break;
                }
            }
        });

    }

    public void setCountOfPlayers(final int cntOfPlayers){
        counter = 0;
        playerLabel.setText(Strings.CREATINGPLAYERSDIALOG_ENTER + order[counter]+Strings.CREATINGPLAYERSDIALOG_PLAYER);
        this.cntOfPlayers = cntOfPlayers;
        nicks = new String[cntOfPlayers];
    }

    private void addPlayer(){
        String name = playerNameField.getText();
        if(name.equals("")){
            playerLabel.setText(Strings.CREATINGPLAYERSDIALOG_ENTER +order[counter]+Strings.CREATINGPLAYERSDIALOG_PLAYER + Strings.CREATINGPLAYERSDIALOG_PLEASE);
            return;
        }
        nicks[counter] = name;
        playerNameField.setText("");
        counter++;
        if(counter < cntOfPlayers) {
            playerLabel.setText(Strings.CREATINGPLAYERSDIALOG_ENTER + order[counter] + Strings.CREATINGPLAYERSDIALOG_PLAYER);
            playerNameField.requestFocus();
        }
    }

    public void updateLanguage(){
        this.setTitle(Strings.CREATINGPLAYERSDIALOG_TITLE);
        order = Strings.CREATINGPLAYERSDIALOG_ORDER;
        createButton.setText(Strings.CREATINGPLAYERSDIALOG_ADD);
    }



}
