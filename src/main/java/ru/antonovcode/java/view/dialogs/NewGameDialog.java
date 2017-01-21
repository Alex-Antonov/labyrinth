package ru.antonovcode.java.view.dialogs;

import ru.antonovcode.java.util.options.Strings;
import ru.antonovcode.java.view.Window;
import ru.antonovcode.java.view.interfaces.CodeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by alex on 31.07.2014.
 */
public class NewGameDialog extends JDialog {

    private OptionDialog optionDialog = new OptionDialog();
    private SpringLayout springLayout = new SpringLayout();

    private JButton createButton;
    private JButton showOptionDialog;
    private JComboBox sizeBox;
    private JComboBox playerBox;
    private JLabel playerLabel;
    private JLabel sizeLabel;

    private final int X = 200;
    private final int Y = 150;

    public NewGameDialog(final CodeWindow window){

        this.setVisible(false);

        createButton = new JButton(Strings.NEWGAMEDIALOG_CREATE);
        showOptionDialog = new JButton(Strings.NEWGAMEDIALOG_OPTIONS);

        playerLabel = new JLabel(Strings.NEWGAMEDIALOG_PLAYERS);
        sizeLabel = new JLabel(Strings.NEWGAMEDIALOG_AREASIZE);

        this.setSize(X, Y);
        //this.setPreferredSize(new Dimension(X, Y));
        this.setLocation(550, 250);

        sizeBox = new JComboBox();
        playerBox = new JComboBox();

        for (int i = 8; i <= 20; i++) {
            sizeBox.addItem(Integer.toString(i));
        }
        sizeBox.setSelectedIndex(2);

        for (int i = 2; i <= 4; i++) {
            playerBox.addItem(Integer.toString(i));
        }
        playerBox.setSelectedIndex(0);


        setLayout(springLayout);

        add(sizeBox);
        add(playerBox);
        add(createButton);
        add(playerLabel);
        add(sizeLabel);
        add(showOptionDialog);

        springLayout.putConstraint(SpringLayout.NORTH, playerLabel, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, playerLabel, 5, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, playerBox, 5, SpringLayout.SOUTH, playerLabel);
        springLayout.putConstraint(SpringLayout.WEST, playerBox, 10, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, sizeLabel, 10, SpringLayout.SOUTH, playerBox);
        springLayout.putConstraint(SpringLayout.WEST, sizeLabel, 5, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, sizeBox, 5, SpringLayout.SOUTH, sizeLabel);
        springLayout.putConstraint(SpringLayout.WEST, sizeBox, 10, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, createButton, 80, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, createButton, 100, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, showOptionDialog, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, showOptionDialog, 0, SpringLayout.EAST, createButton);

        this.setTitle(Strings.NEWGAMEDIALOG_TITLE);

        this.setModal(true);

        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                window.newGame(Integer.parseInt((String) sizeBox.getSelectedItem()), Integer.parseInt((String) playerBox.getSelectedItem()));
                setVisible(false);
            }
        });

        showOptionDialog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                optionDialog.updateInterface();
                optionDialog.setVisible(true);
            }
        });

        optionDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                window.updateLanguage();
            }
        });
    }

    public void updateLanguage(){
        this.setTitle(Strings.NEWGAMEDIALOG_TITLE);
        createButton.setText(Strings.NEWGAMEDIALOG_CREATE);
        showOptionDialog.setText(Strings.NEWGAMEDIALOG_OPTIONS);

        playerLabel.setText(Strings.NEWGAMEDIALOG_PLAYERS);
        sizeLabel.setText(Strings.NEWGAMEDIALOG_AREASIZE);
        optionDialog.updateLanguage();
        optionDialog.updateInterface();

    }

    public void openOptionDialog(){
        optionDialog.setVisible(true);
    }



}
