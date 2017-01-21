package ru.antonovcode.java.view.dialogs;

import ru.antonovcode.java.util.options.GameOptions;
import ru.antonovcode.java.util.options.MapOptions;
import ru.antonovcode.java.util.options.Strings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alex on 08.08.2014.
 */
public class OptionDialog extends JDialog {


    private SpringLayout springLayout = new SpringLayout();

    private JLabel movingOptionLabel;

    private JLabel percentOfPortalsLabel;
    private JLabel percentOfAmmunitionLabel;
    private JLabel percentOfFakeTreasureLabel;
    private JLabel factorOfTrapsLabel;
    private JLabel hospitalsPercentLabel;

    private JTextField percentOfPortalsField;
    private JTextField percentOfAmmunitionField;
    private JTextField percentOfFakeTreasureField;
    private JTextField factorOfTrapsField;
    private JTextField hospitalsPercentField;
    //private JLabel hospitalModeLabel;
    //private JLabel exitModeLabel;

    //----------------------------------moves--------------------------------------
    private JRadioButton firstWallMove;
    private JRadioButton nextCellMove;
    private ButtonGroup mainMoveGroup = new ButtonGroup();


    private ButtonGroup fwmGroup = new ButtonGroup();

    private ButtonGroup ncmGroup = new ButtonGroup();

    //----------------------------------hospital------------------------------------

    private JCheckBox hospitalMode;

    //----------------------------------exit----------------------------------------
    private JCheckBox exitMode;
    private JCheckBox fakeTreasureMode;
    private JCheckBox mapAmmunition;
    private JCheckBox dinamitAndMineOnly;
    private JCheckBox langRus;



    private JButton accept;
    private JButton defaultCustoms;
    private ClickAdapter clickAdapter = new ClickAdapter();

    public OptionDialog(){
        this.setTitle(Strings.OPTIONDIALOG_TITLE);
        this.setModal(true);
        this.setVisible(false);
        this.setSize(410, 530);
        //this.setPreferredSize(new Dimension(X, Y));
        this.setLocation(400, 50);

        movingOptionLabel = new JLabel(Strings.OPTIONDIALOG_ENDOFMOVING);
        percentOfPortalsLabel = new JLabel(Strings.OPTIONDIALOG_PERCENTOFPORTALS);
        percentOfAmmunitionLabel  = new JLabel(Strings.OPTIONDIALOG_PERCENTOFAMMUNITION);
        percentOfFakeTreasureLabel = new JLabel(Strings.OPTIONDIALOG_PERCENTOFFAKES);
        factorOfTrapsLabel = new JLabel(Strings.OPTIONDIALOG_FACTORTRAPS);
        hospitalsPercentLabel = new JLabel(Strings.OPTIONDIALOG_HOSPITALPERCENTOFDEADLOCKS);

        percentOfPortalsField = new JTextField(3);
        percentOfAmmunitionField = new JTextField(3);
        percentOfFakeTreasureField = new JTextField(3);
        factorOfTrapsField = new JTextField(3);
        hospitalsPercentField = new JTextField(3);


        firstWallMove = new JRadioButton(Strings.OPTIONDIALOG_FIRSTWALL);
        nextCellMove = new JRadioButton(Strings.OPTIONDIALOG_NEXTCELL);
        mainMoveGroup.add(firstWallMove);
        mainMoveGroup.add(nextCellMove);

        hospitalMode = new JCheckBox(Strings.OPTIONDIALOG_HOSPITALMODE);

        exitMode = new JCheckBox(Strings.OPTIONDIALOG_EXITMODE);
        fakeTreasureMode = new JCheckBox(Strings.OPTIONDIALOG_FAKETREASUREMODE);
        mapAmmunition = new JCheckBox(Strings.OPTIONDIALOG_MAPAMMUNITION);
        dinamitAndMineOnly = new JCheckBox(Strings.OPTIONDIALOG_ONLYMINESANDDINAS);
        langRus = new JCheckBox(Strings.OPTIONDIALOG_RUSSIANINTERFACE);


        accept = new JButton(Strings.OPTIONDIALOG_ACCEPT);
        defaultCustoms = new JButton(Strings.OPTIONDIALOG_SETDEFAULTCUSTOMS);

        setLayout(springLayout);


        exitMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!exitMode.isSelected()) {
                    fakeTreasureMode.setEnabled(false);
                    fakeTreasureMode.setSelected(false);
                    percentOfFakeTreasureField.setEnabled(false);
                    percentOfFakeTreasureLabel.setEnabled(false);
                }
                else{
                    fakeTreasureMode.setEnabled(true);
                }

            }
        });

        mapAmmunition.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!mapAmmunition.isSelected()) {
                    dinamitAndMineOnly.setEnabled(false);
                    dinamitAndMineOnly.setSelected(false);
                    percentOfAmmunitionField.setEnabled(false);
                    percentOfAmmunitionLabel.setEnabled(false);
                }
                else {
                    dinamitAndMineOnly.setEnabled(true);
                    percentOfAmmunitionField.setEnabled(true);
                    percentOfAmmunitionLabel.setEnabled(true);
                }
            }
        });

        fakeTreasureMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!fakeTreasureMode.isSelected()) {
                    percentOfFakeTreasureField.setEnabled(false);
                    percentOfFakeTreasureLabel.setEnabled(false);
                }
                else {
                    percentOfFakeTreasureField.setEnabled(true);
                    percentOfFakeTreasureLabel.setEnabled(true);
                }
            }
        });

        hospitalMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!hospitalMode.isSelected()) {
                    hospitalsPercentField.setEnabled(false);
                    factorOfTrapsField.setEnabled(false);
                    hospitalsPercentLabel.setEnabled(false);
                    factorOfTrapsLabel.setEnabled(false);
                }
                else {
                    hospitalsPercentField.setEnabled(true);
                    factorOfTrapsField.setEnabled(true);
                    hospitalsPercentLabel.setEnabled(true);
                    factorOfTrapsLabel.setEnabled(true);
                }
            }
        });


        updateInterface();


        add(movingOptionLabel);

        add(firstWallMove);
        add(nextCellMove);


        add(hospitalMode);
        add(exitMode);
        add(fakeTreasureMode);

        add(mapAmmunition);
        add(dinamitAndMineOnly);
        add(langRus);

        add(percentOfPortalsLabel);
        add(percentOfAmmunitionLabel);
        add(percentOfFakeTreasureLabel);
        add(hospitalsPercentLabel);
        add(factorOfTrapsLabel);


        add(percentOfPortalsField);
        add(percentOfAmmunitionField);
        add(percentOfFakeTreasureField);
        add(hospitalsPercentField);
        add(factorOfTrapsField);


        add(accept);
        add(defaultCustoms);

        springLayout.putConstraint(SpringLayout.NORTH, movingOptionLabel, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, movingOptionLabel, 15, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, firstWallMove, 5, SpringLayout.SOUTH, movingOptionLabel);
        springLayout.putConstraint(SpringLayout.WEST, firstWallMove, 40, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, nextCellMove, 0, SpringLayout.SOUTH, firstWallMove);
        springLayout.putConstraint(SpringLayout.WEST, nextCellMove, 40, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, hospitalMode, 15, SpringLayout.SOUTH, nextCellMove);
        springLayout.putConstraint(SpringLayout.WEST, hospitalMode, 15, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, exitMode, 10, SpringLayout.SOUTH, hospitalMode);
        springLayout.putConstraint(SpringLayout.WEST, exitMode, 15, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, fakeTreasureMode, 5, SpringLayout.SOUTH, exitMode);
        springLayout.putConstraint(SpringLayout.WEST, fakeTreasureMode, 40, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, mapAmmunition, 5, SpringLayout.SOUTH, fakeTreasureMode);
        springLayout.putConstraint(SpringLayout.WEST, mapAmmunition, 15, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, dinamitAndMineOnly, 5, SpringLayout.SOUTH, mapAmmunition);
        springLayout.putConstraint(SpringLayout.WEST, dinamitAndMineOnly, 40, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, langRus, 5, SpringLayout.SOUTH, dinamitAndMineOnly);
        springLayout.putConstraint(SpringLayout.WEST, langRus, 15, SpringLayout.WEST, this);



        springLayout.putConstraint(SpringLayout.NORTH, percentOfPortalsField, 15, SpringLayout.SOUTH, langRus);
        springLayout.putConstraint(SpringLayout.WEST, percentOfPortalsField, 320, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, percentOfAmmunitionField, 11, SpringLayout.SOUTH, percentOfPortalsField);
        springLayout.putConstraint(SpringLayout.WEST, percentOfAmmunitionField, 320, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, percentOfFakeTreasureField, 11, SpringLayout.SOUTH, percentOfAmmunitionField);
        springLayout.putConstraint(SpringLayout.WEST, percentOfFakeTreasureField, 320, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, hospitalsPercentField, 11, SpringLayout.SOUTH, percentOfFakeTreasureField);
        springLayout.putConstraint(SpringLayout.WEST, hospitalsPercentField, 320, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, factorOfTrapsField, 11, SpringLayout.SOUTH, hospitalsPercentField);
        springLayout.putConstraint(SpringLayout.WEST, factorOfTrapsField, 320, SpringLayout.WEST, this);




        springLayout.putConstraint(SpringLayout.NORTH, percentOfPortalsLabel, 15, SpringLayout.SOUTH, langRus);
        springLayout.putConstraint(SpringLayout.EAST, percentOfPortalsLabel, -5, SpringLayout.WEST, percentOfPortalsField);

        springLayout.putConstraint(SpringLayout.NORTH, percentOfAmmunitionLabel, 15, SpringLayout.SOUTH, percentOfPortalsLabel);
        springLayout.putConstraint(SpringLayout.EAST, percentOfAmmunitionLabel, -5, SpringLayout.WEST, percentOfAmmunitionField);

        springLayout.putConstraint(SpringLayout.NORTH, percentOfFakeTreasureLabel, 15, SpringLayout.SOUTH, percentOfAmmunitionLabel);
        springLayout.putConstraint(SpringLayout.EAST, percentOfFakeTreasureLabel, -5, SpringLayout.WEST, percentOfFakeTreasureField);

        springLayout.putConstraint(SpringLayout.NORTH, hospitalsPercentLabel, 15, SpringLayout.SOUTH, percentOfFakeTreasureLabel);
        springLayout.putConstraint(SpringLayout.EAST, hospitalsPercentLabel, -5, SpringLayout.WEST, hospitalsPercentField);

        springLayout.putConstraint(SpringLayout.NORTH, factorOfTrapsLabel, 15, SpringLayout.SOUTH, hospitalsPercentLabel);
        springLayout.putConstraint(SpringLayout.EAST, factorOfTrapsLabel, -5, SpringLayout.WEST, factorOfTrapsField);



        springLayout.putConstraint(SpringLayout.NORTH, accept, 5, SpringLayout.SOUTH, factorOfTrapsField);
        springLayout.putConstraint(SpringLayout.EAST, accept, 0, SpringLayout.EAST, factorOfTrapsField);

        springLayout.putConstraint(SpringLayout.NORTH, defaultCustoms, 5, SpringLayout.SOUTH, factorOfTrapsField);
        springLayout.putConstraint(SpringLayout.EAST, defaultCustoms, -10, SpringLayout.WEST, accept);

        accept.addMouseListener(clickAdapter);

        defaultCustoms.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameOptions.reloadOptions();
                MapOptions.reloadOptions();
                updateInterface();
                //repaint();
            }
        });

    }


    class ClickAdapter extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            setNewCustonms();
            setVisible(false);
        }
    }

    public void updateInterface(){
        firstWallMove.setSelected(GameOptions.END_OF_MOVE_BY_FIRST_WALL);
        nextCellMove.setSelected(!GameOptions.END_OF_MOVE_BY_FIRST_WALL);
        hospitalMode.setSelected(GameOptions.HOSPITAL_MODE_ON);
        exitMode.setSelected(GameOptions.EXIT_MODE_ON);
        fakeTreasureMode.setSelected(GameOptions.FAKE_TREASURE_MODE_ON);
        mapAmmunition.setSelected(GameOptions.MAP_AMMUNITION_ON);
        dinamitAndMineOnly.setSelected(GameOptions.DINAMIT_AND_MINE_ONLY);
        langRus.setSelected(GameOptions.RUS_LANG);

        percentOfPortalsField.setText(Integer.toString(MapOptions.PERCENT_OF_PORTALS));
        percentOfAmmunitionField.setText(Integer.toString(MapOptions.PERCENT_OF_AMMUNITION));
        percentOfFakeTreasureField.setText(Integer.toString(MapOptions.PERCENT_OF_FAKE_TREASURE));
        factorOfTrapsField.setText(Float.toString(MapOptions.TRAPS_FACTOR_OF_HOSPITALS));
        hospitalsPercentField.setText(Integer.toString(MapOptions.HOSPITALS_PERCENT_OF_DEADLOCKS));


        if(!exitMode.isSelected()) {
            fakeTreasureMode.setEnabled(false);
            fakeTreasureMode.setSelected(false);
            percentOfFakeTreasureField.setEnabled(false);
            percentOfFakeTreasureLabel.setEnabled(false);
        } else {
            fakeTreasureMode.setEnabled(true);
        }
        if(!fakeTreasureMode.isSelected()){
            percentOfFakeTreasureField.setEnabled(false);
            percentOfFakeTreasureLabel.setEnabled(false);
        } else {
            percentOfFakeTreasureField.setEnabled(true);
            percentOfFakeTreasureLabel.setEnabled(true);
        }
        if(!mapAmmunition.isSelected()) {
            dinamitAndMineOnly.setEnabled(false);
            percentOfAmmunitionField.setEnabled(false);
            percentOfAmmunitionLabel.setEnabled(false);
        } else {
            dinamitAndMineOnly.setEnabled(true);
            percentOfAmmunitionField.setEnabled(true);
            percentOfAmmunitionLabel.setEnabled(true);
        }
        if(!hospitalMode.isSelected()){
            hospitalsPercentField.setEnabled(false);
            factorOfTrapsField.setEnabled(false);
            hospitalsPercentLabel.setEnabled(false);
            factorOfTrapsLabel.setEnabled(false);
        } else {
            hospitalsPercentField.setEnabled(true);
            factorOfTrapsField.setEnabled(true);
            hospitalsPercentLabel.setEnabled(true);
            factorOfTrapsLabel.setEnabled(true);
        }
    }

    private void setNewCustonms(){
        GameOptions.DINAMIT_AND_MINE_ONLY = dinamitAndMineOnly.isSelected();
        GameOptions.MAP_AMMUNITION_ON = mapAmmunition.isSelected();
        GameOptions.FAKE_TREASURE_MODE_ON = fakeTreasureMode.isSelected();
        GameOptions.HOSPITAL_MODE_ON = hospitalMode.isSelected();
        GameOptions.END_OF_MOVE_BY_FIRST_WALL = firstWallMove.isSelected();
        GameOptions.EXIT_MODE_ON = exitMode.isSelected();
        GameOptions.RUS_LANG = langRus.isSelected();


        int percOfPortals = Integer.parseInt(percentOfPortalsField.getText());
        int hospitalPer = Integer.parseInt(hospitalsPercentField.getText());
        int percOfAmmunition = Integer.parseInt(percentOfAmmunitionField.getText());
        int percOfFake = Integer.parseInt(percentOfFakeTreasureField.getText());
        float trapFactor = Float.parseFloat(factorOfTrapsField.getText());

        if(percOfPortals <= 5 && percOfPortals >=0)
            MapOptions.PERCENT_OF_PORTALS = percOfPortals;
        else{
            if(percOfPortals > 5)
                MapOptions.PERCENT_OF_PORTALS = 5;
            else
                MapOptions.PERCENT_OF_PORTALS = 0;
        }


        if(hospitalsPercentField.isEnabled()){
            if(hospitalPer >=0 && hospitalPer <= 50)
                MapOptions.HOSPITALS_PERCENT_OF_DEADLOCKS = hospitalPer;
            else {
                if(hospitalPer > 50)
                    MapOptions.HOSPITALS_PERCENT_OF_DEADLOCKS = 50;
                else
                    MapOptions.HOSPITALS_PERCENT_OF_DEADLOCKS = 0;
            }
        }

        if(percentOfAmmunitionField.isEnabled()){
            if(percOfAmmunition >= 0 && percOfAmmunition <=10)
                MapOptions.PERCENT_OF_AMMUNITION = percOfAmmunition;
            else {
                if(percOfAmmunition > 10)
                    MapOptions.PERCENT_OF_AMMUNITION = 10;
                else
                    MapOptions.PERCENT_OF_AMMUNITION = 0;
            }
        }

        if(percentOfFakeTreasureField.isEnabled()){
            if(percOfFake >= 0 && percOfFake <= 5)
                MapOptions.PERCENT_OF_FAKE_TREASURE = percOfFake;
            else {
                if(percOfFake > 5)
                    MapOptions.PERCENT_OF_FAKE_TREASURE = 5;
                else
                    MapOptions.PERCENT_OF_FAKE_TREASURE = 0;
            }
        }

        if(factorOfTrapsField.isEnabled()){
            if(trapFactor >= 0.0f && trapFactor <= 2.0f)
                MapOptions.TRAPS_FACTOR_OF_HOSPITALS = trapFactor;
            else{
                if(trapFactor > 2.0f)
                    MapOptions.TRAPS_FACTOR_OF_HOSPITALS = 2.0f;
                else
                    MapOptions.TRAPS_FACTOR_OF_HOSPITALS = 0.0f;
            }
        }

    }

    public void updateLanguage(){
        this.setTitle(Strings.OPTIONDIALOG_TITLE);


        movingOptionLabel.setText(Strings.OPTIONDIALOG_ENDOFMOVING);
        percentOfPortalsLabel.setText(Strings.OPTIONDIALOG_PERCENTOFPORTALS);
        percentOfAmmunitionLabel.setText(Strings.OPTIONDIALOG_PERCENTOFAMMUNITION);
        percentOfFakeTreasureLabel.setText(Strings.OPTIONDIALOG_PERCENTOFFAKES);
        factorOfTrapsLabel.setText(Strings.OPTIONDIALOG_FACTORTRAPS);
        hospitalsPercentLabel.setText(Strings.OPTIONDIALOG_HOSPITALPERCENTOFDEADLOCKS);


        firstWallMove.setText(Strings.OPTIONDIALOG_FIRSTWALL);
        nextCellMove.setText(Strings.OPTIONDIALOG_NEXTCELL);


        hospitalMode.setText(Strings.OPTIONDIALOG_HOSPITALMODE);

        exitMode.setText(Strings.OPTIONDIALOG_EXITMODE);
        fakeTreasureMode.setText(Strings.OPTIONDIALOG_FAKETREASUREMODE);
        mapAmmunition.setText(Strings.OPTIONDIALOG_MAPAMMUNITION);
        dinamitAndMineOnly.setText(Strings.OPTIONDIALOG_ONLYMINESANDDINAS);
        langRus.setText(Strings.OPTIONDIALOG_RUSSIANINTERFACE);


        accept.setText(Strings.OPTIONDIALOG_ACCEPT);
        defaultCustoms.setText(Strings.OPTIONDIALOG_SETDEFAULTCUSTOMS);
        updateInterface();
    }
}
