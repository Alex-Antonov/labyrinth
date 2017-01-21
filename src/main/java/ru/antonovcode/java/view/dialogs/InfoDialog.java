package ru.antonovcode.java.view.dialogs;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by alex on 12.09.2014.
 */
public class InfoDialog extends JDialog {

    private JLabel infoLabel;

    public InfoDialog(){
        setSize(300, 50);
        setLocation(500, 250);
        setModal(true);
        setVisible(false);

        SpringLayout spr = new SpringLayout();
        setLayout(new SpringLayout());

        infoLabel = new JLabel();

        add(infoLabel);

        spr.putConstraint(SpringLayout.NORTH, infoLabel, 40, SpringLayout.NORTH, this);
        spr.putConstraint(SpringLayout.WEST, infoLabel, 5, SpringLayout.WEST, this);



    }

    public void setMessage(String message){
        setTitle(message);
        infoLabel.setText(message);
    }
}
