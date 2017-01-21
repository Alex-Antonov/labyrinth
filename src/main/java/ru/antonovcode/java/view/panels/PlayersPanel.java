package ru.antonovcode.java.view.panels;

import ru.antonovcode.java.model.ammunition.Ammunition;
import ru.antonovcode.java.model.cell.direction.IDirection;
import ru.antonovcode.java.model.player.Player;
import ru.antonovcode.java.util.options.GameOptions;
import ru.antonovcode.java.util.options.Strings;
import ru.antonovcode.java.view.interfaces.CodeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by alex on 28.08.2014.
 */
public class PlayersPanel extends JPanel implements IDirection{

    private boolean killed;
    private Player player;
    private static CodeWindow codeWindow;
    //private ArrayList<Ammunition> ammunitionList = null;

    private JCheckBox use;

    private JButton upButton = new JButton();
    private JButton downButton = new JButton();
    private JButton leftButton = new JButton();
    private JButton rightButton = new JButton();

    private static final String up = "/images/up.png";
    private static final String down = "/images/down.png";
    private static final String right = "/images/right.png";
    private static final String left = "/images/left.png";

    private static final String treasurePath = "/images/treasure.png";
    private static final String faketreasurePath = "/images/faketreasure.png";

    private JLabel imgLabel;
    private JLabel treasureImLabel;
    private JLabel faketreasureImLabel;
    private JLabel countOfAmmunition;
    private JLabel coordinates;
    private JLabel hurtCounter;

    private SmallDrawPanel smallDrawPanel;
    private JScrollPane smallDrawScroll;

    private SpringLayout springLayout = new SpringLayout();

    private JComboBox ammunitionComboBox;

    {
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image im = tk.getDefaultToolkit().getImage(getClass().getResource(treasurePath));
            treasureImLabel = new JLabel(new ImageIcon(im));
            treasureImLabel.setEnabled(false);

            im = tk.getDefaultToolkit().getImage(getClass().getResource(faketreasurePath));
            faketreasureImLabel = new JLabel(new ImageIcon(im));
            faketreasureImLabel.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PlayersPanel(final String nick, CodeWindow cw) {

        codeWindow = cw;
        this.killed = false;
        this.setName(nick);
        this.setBackground(Color.RED);

        coordinates = new JLabel(Strings.PLAYERPANEL_LOCATION);
        countOfAmmunition = new JLabel();

        hurtCounter = new JLabel(Strings.PLAYERPANEL_COUNTOFHURTS);

        use = new JCheckBox();

        initMoveButtons();


        ammunitionComboBox = new JComboBox();
        //setAmmunitionList(player.getAmmunitionList());
        //ammunitionComboBox.setSelectedIndex(0);
        use.setText(Strings.PLAYERPANEL_USE);


        //Icon icon = new ImageIcon(im);
        imgLabel = new JLabel();
        //imgLabel.setIcon(new ImageIcon(im));
        ///////////////////////////////////
        countOfAmmunition = new JLabel();
        //int cnt = ((Ammunition) (player.getAmmunitions().get(ammunitionList.getSelectedIndex()))).getTimesToUseLeft();
        countOfAmmunition.setText(Strings.PLAYERPANEL_INSTOCK);
        ///////////////////////////////////
        ammunitionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(player != null && ammunitionComboBox.getItemCount() > 0){
                    Image im = ((Ammunition)player.getAmmunitionList().get(ammunitionComboBox.getSelectedIndex())).getImage();
                    imgLabel.setIcon(new ImageIcon(im));
                    int cnt = ((Ammunition)player.getAmmunitionList().get(ammunitionComboBox.getSelectedIndex())).getTimesToUseLeft();
                    setCountOfAmmunition(cnt);
                    use.setText(Strings.PLAYERPANEL_USE + ammunitionComboBox.getSelectedItem());
                    if(cnt > 0){
                        use.setEnabled(true);
                    } else {
                        use.setEnabled(false);
                    }
                }
                use.setBackground(getBackground());
                repaint();
            }
        });

        smallDrawPanel = new SmallDrawPanel();
        smallDrawScroll = new JScrollPane(smallDrawPanel);

        smallDrawPanel.setPreferredSize(new Dimension(1000, 1000));
        smallDrawScroll.setPreferredSize(new Dimension(410, 360));



        setLayout(springLayout);

        add(ammunitionComboBox);
        add(imgLabel);
        add(countOfAmmunition);

        add(upButton);
        add(downButton);
        add(leftButton);
        add(rightButton);
        add(use);
        add(coordinates);


        add(hurtCounter);
        add(treasureImLabel);
        add(faketreasureImLabel);
        add(smallDrawScroll);


        springLayout.putConstraint(SpringLayout.NORTH, countOfAmmunition, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, countOfAmmunition, -15, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.NORTH, imgLabel, 40, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, imgLabel, -5, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.NORTH, ammunitionComboBox, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, ammunitionComboBox, 0, SpringLayout.WEST, imgLabel);

        springLayout.putConstraint(SpringLayout.NORTH, coordinates, 15, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, coordinates, 5, SpringLayout.WEST, this);


        springLayout.putConstraint(SpringLayout.NORTH, upButton, 130, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, upButton, 160, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, leftButton, 2, SpringLayout.SOUTH, upButton);
        springLayout.putConstraint(SpringLayout.EAST, leftButton, -2, SpringLayout.WEST, upButton);

        springLayout.putConstraint(SpringLayout.NORTH, rightButton, 2, SpringLayout.SOUTH, upButton);
        springLayout.putConstraint(SpringLayout.WEST, rightButton, 2, SpringLayout.EAST, upButton);

        springLayout.putConstraint(SpringLayout.NORTH, downButton, 2, SpringLayout.SOUTH, upButton);
        springLayout.putConstraint(SpringLayout.WEST, downButton, 0, SpringLayout.WEST, upButton);

        springLayout.putConstraint(SpringLayout.NORTH, smallDrawScroll, 10, SpringLayout.SOUTH, downButton);
        springLayout.putConstraint(SpringLayout.WEST, smallDrawScroll, 10, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.SOUTH, use, -5, SpringLayout.NORTH, rightButton);
        springLayout.putConstraint(SpringLayout.WEST, use, 0, SpringLayout.EAST, upButton);

        springLayout.putConstraint(SpringLayout.NORTH, hurtCounter, 15, SpringLayout.SOUTH, coordinates);
        springLayout.putConstraint(SpringLayout.WEST, hurtCounter, 5, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, treasureImLabel, 15, SpringLayout.SOUTH, hurtCounter);
        springLayout.putConstraint(SpringLayout.WEST, treasureImLabel, 2, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, faketreasureImLabel, 15, SpringLayout.SOUTH, hurtCounter);
        springLayout.putConstraint(SpringLayout.WEST, faketreasureImLabel, 2, SpringLayout.WEST, this);

        //KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
    }

    public void setPlayer(Player player){
        this.player = player;
        updatePanel();

    }


    public void panelOn() {
        upButton.setEnabled(true);
        downButton.setEnabled(true);
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);
        this.setBackground(Color.GREEN);
        ammunitionComboBox.setSelectedIndex(ammunitionComboBox.getSelectedIndex());
        use.setBackground(Color.GREEN);
        //use.setEnabled(true);
        ammunitionComboBox.setEnabled(true);
        imgLabel.setEnabled(true);
        smallDrawScroll.setEnabled(true);
        player.setIniciative(true);
    }

    public void panelOff() {
        upButton.setEnabled(false);
        downButton.setEnabled(false);
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        this.setBackground(Color.RED);
        use.setBackground(Color.RED);
        use.setEnabled(false);
        ammunitionComboBox.setEnabled(false);
        imgLabel.setEnabled(false);
        smallDrawScroll.setEnabled(false);
    }

    public void panelKill() {
        upButton.setEnabled(false);
        downButton.setEnabled(false);
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        this.setBackground(Color.BLACK);
        use.setBackground(Color.BLACK);
        use.setEnabled(false);
        ammunitionComboBox.setEnabled(false);
        imgLabel.setEnabled(false);
        this.killed = true;
        smallDrawScroll.setEnabled(false);
    }

    public boolean isKilled() {
        killed = !player.isAlive();
        if(killed)
            panelKill();
        return killed;
    }


    private void initMoveButtons() {
        //panelKill();
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image image = tk.getDefaultToolkit().getImage(getClass().getResource(up));
            upButton.setIcon(new ImageIcon(image));

            upButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(GameOptions.END_OF_MOVE_BY_FIRST_WALL){
                        if(!UP())
                            codeWindow.selectNextPlayer();
                    } else {

                    }
                }


            });

            image = tk.getDefaultToolkit().getImage(getClass().getResource(down));
            downButton.setIcon(new ImageIcon(image));

            downButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(GameOptions.END_OF_MOVE_BY_FIRST_WALL){
                        if(!DOWN())
                            codeWindow.selectNextPlayer();
                    } else {

                    }

                }
            });

            image = tk.getDefaultToolkit().getImage(getClass().getResource(left));
            leftButton.setIcon(new ImageIcon(image));

            leftButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(GameOptions.END_OF_MOVE_BY_FIRST_WALL){
                        if(!LEFT())
                            codeWindow.selectNextPlayer();
                    } else {

                    }

                }
            });

            image = tk.getDefaultToolkit().getImage(getClass().getResource(right));
            rightButton.setIcon(new ImageIcon(image));

            rightButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(GameOptions.END_OF_MOVE_BY_FIRST_WALL) {
                        if (!RIGHT()) {
                            codeWindow.selectNextPlayer();
                        }
                    } else {

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUseCheckBox(){
        use.setSelected(!use.isSelected());
    }

    public void listAmmunitionDown(){
        if(!ammunitionComboBox.isEnabled())
            return;
        int count = ammunitionComboBox.getItemCount();
        if(ammunitionComboBox.getSelectedIndex() + 1 == count)
            ammunitionComboBox.setSelectedIndex(0);
        else
            ammunitionComboBox.setSelectedIndex(ammunitionComboBox.getSelectedIndex() + 1);
    }

    public void listAmmunitionUp(){
        if(!ammunitionComboBox.isEnabled())
            return;
        int count = ammunitionComboBox.getItemCount();
        if(ammunitionComboBox.getSelectedIndex() == 0)
            ammunitionComboBox.setSelectedIndex(count - 1);
        else
            ammunitionComboBox.setSelectedIndex(ammunitionComboBox.getSelectedIndex() - 1);
    }

    public void updatePanel(){
        setCoordinates(player.getCoordinates());
        setAmmunitionList(player.getAmmunitionList());
        setAmmunitionImage(((Ammunition)player.getAmmunitionList().get(ammunitionComboBox.getSelectedIndex())).getImage());
        setCountOfAmmunition(((Ammunition)player.getAmmunitionList().get(ammunitionComboBox.getSelectedIndex())).getTimesToUseLeft());
        if(player.getGotTreasure())
            setTreasureImLabel();
        else{
            if(player.getGotFakeTreasure())
                setFaketreasureImLabel();
            else
                disableTreasureImLabel();
        }
        setHurtCounter(player.getCountOfHurt());
        if(player.isAlive()){
            if(player.getIniciative() && !LabyrinthPanel.getEditMode())
                panelOn();
            else
                panelOff();
        } else
            panelKill();

    }

    private void setCoordinates(final String coordinates) {
        this.coordinates.setText(Strings.PLAYERPANEL_LOCATION + coordinates);
    }

    private void setAmmunitionImage(final Image image) {
        imgLabel.setIcon(new ImageIcon(image));
    }

    private void setCountOfAmmunition(final int count) {
        if(count == 0)
            use.setEnabled(false);
        this.countOfAmmunition.setText(Strings.PLAYERPANEL_INSTOCK + count);
    }

    private void setTreasureImLabel() {
        treasureImLabel.setEnabled(true);
        treasureImLabel.setVisible(true);
        faketreasureImLabel.setEnabled(false);
        faketreasureImLabel.setVisible(false);
    }

    private void setFaketreasureImLabel() {
        faketreasureImLabel.setVisible(true);
        faketreasureImLabel.setEnabled(true);
        treasureImLabel.setVisible(false);
        treasureImLabel.setEnabled(false);
    }

    private void disableTreasureImLabel() {
        faketreasureImLabel.setVisible(false);
        faketreasureImLabel.setEnabled(false);
        treasureImLabel.setVisible(true);
        treasureImLabel.setEnabled(false);
    }

    private void setHurtCounter(final int count) {
        hurtCounter.setText(Strings.PLAYERPANEL_COUNTOFHURTS + count);
    }

    private void setAmmunitionList(ArrayList<Ammunition> ammunitionList) {
        int id = 0;
        if(ammunitionComboBox.getItemCount() > 0)
            id = ammunitionComboBox.getSelectedIndex();
        ammunitionComboBox.removeAllItems();
        for(Ammunition am : ammunitionList){
            ammunitionComboBox.addItem(am.getAmmunitionName());
        }
        ammunitionComboBox.setSelectedIndex(id);
        setCountOfAmmunition(ammunitionList.get(id).getTimesToUseLeft());
    }


    public void updateLanguage(){
        //coordinates = new JLabel(Strings.PLAYERPANEL_LOCATION);
        //hurtCounter = new JLabel(Strings.PLAYERPANEL_COUNTOFHURTS);
        //use.setText(Strings.PLAYERPANEL_USE);
        //countOfAmmunition.setText(Strings.PLAYERPANEL_INSTOCK);

        int size = player.getAmmunitionList().size();

        for(int i = 0; i < size; i++)
            ((Ammunition)player.getAmmunitionList().get(i)).updateLanguage();

        updatePanel();
    }

    public boolean UP(){
        if (upButton.isEnabled()) {
            if (use.isSelected()) {
                player.useAmmunition(ammunitionComboBox.getSelectedIndex(), DNORTH);
                use.setSelected(false);
                int cnt = ((Ammunition) (player.getAmmunitionList().get(ammunitionComboBox.getSelectedIndex()))).getTimesToUseLeft();
                countOfAmmunition.setText(Strings.PLAYERPANEL_INSTOCK + Integer.toString(cnt));
                //selectNextPlayer();
                updatePanel();
                return false;
            } else {
                if (!player.moveUp()){
                    //selectNextPlayer();
                    updatePanel();
                    return false;
                } else {
                    coordinates.setText(Strings.PLAYERPANEL_LOCATION + player.getX() + "-" + player.getY());
                    hurtCounter.setText(Strings.PLAYERPANEL_COUNTOFHURTS + player.getCountOfHurt());
                    if (player.getGotTreasure()) {
                        treasureImLabel.setVisible(true);
                        treasureImLabel.setEnabled(true);
                        faketreasureImLabel.setVisible(false);
                        faketreasureImLabel.setEnabled(false);
                    } else {
                        if (player.getGotFakeTreasure()) {
                            treasureImLabel.setVisible(false);
                            treasureImLabel.setEnabled(false);
                            faketreasureImLabel.setVisible(true);
                            faketreasureImLabel.setEnabled(true);
                        } else {

                            treasureImLabel.setVisible(true);
                            treasureImLabel.setEnabled(false);
                            faketreasureImLabel.setVisible(false);
                            faketreasureImLabel.setEnabled(false);

                        }
                    }
                    ammunitionComboBox.setSelectedIndex(ammunitionComboBox.getSelectedIndex());
                    updatePanel();
                    return true;
                }
            }
        }
        updatePanel();
        return true;
    }

    public boolean DOWN(){
        if (upButton.isEnabled()) {
            if (use.isSelected()) {
                player.useAmmunition(ammunitionComboBox.getSelectedIndex(), DSOUTH);
                use.setSelected(false);
                int cnt = ((Ammunition) (player.getAmmunitionList().get(ammunitionComboBox.getSelectedIndex()))).getTimesToUseLeft();
                countOfAmmunition.setText(Strings.PLAYERPANEL_INSTOCK + Integer.toString(cnt));
                //selectNextPlayer();
                updatePanel();
                return false;
            } else {
                if (!player.moveDown()){
                    //selectNextPlayer();
                    updatePanel();
                    return false;
                } else {
                    coordinates.setText(Strings.PLAYERPANEL_LOCATION + player.getX() + "-" + player.getY());
                    hurtCounter.setText(Strings.PLAYERPANEL_COUNTOFHURTS + player.getCountOfHurt());
                    if (player.getGotTreasure()) {
                        treasureImLabel.setVisible(true);
                        treasureImLabel.setEnabled(true);
                        faketreasureImLabel.setVisible(false);
                        faketreasureImLabel.setEnabled(false);
                    } else {
                        if (player.getGotFakeTreasure()) {
                            treasureImLabel.setVisible(false);
                            treasureImLabel.setEnabled(false);
                            faketreasureImLabel.setVisible(true);
                            faketreasureImLabel.setEnabled(true);
                        } else {

                            treasureImLabel.setVisible(true);
                            treasureImLabel.setEnabled(false);
                            faketreasureImLabel.setVisible(false);
                            faketreasureImLabel.setEnabled(false);

                        }
                    }
                    ammunitionComboBox.setSelectedIndex(ammunitionComboBox.getSelectedIndex());
                    updatePanel();
                    return true;
                }
            }
        }
        updatePanel();
        return true;
    }

    public boolean LEFT(){
        if (upButton.isEnabled()) {
            if (use.isSelected()) {
                player.useAmmunition(ammunitionComboBox.getSelectedIndex(), DWEST);
                use.setSelected(false);
                int cnt = ((Ammunition) (player.getAmmunitionList().get(ammunitionComboBox.getSelectedIndex()))).getTimesToUseLeft();
                countOfAmmunition.setText(Strings.PLAYERPANEL_INSTOCK + Integer.toString(cnt));
                //selectNextPlayer();
                updatePanel();
                return false;
            } else {
                if (!player.moveLeft()){
                    //selectNextPlayer();
                    updatePanel();
                    return false;
                } else {
                    coordinates.setText(Strings.PLAYERPANEL_LOCATION + player.getX() + "-" + player.getY());
                    hurtCounter.setText(Strings.PLAYERPANEL_COUNTOFHURTS + player.getCountOfHurt());
                    if (player.getGotTreasure()) {
                        treasureImLabel.setVisible(true);
                        treasureImLabel.setEnabled(true);
                        faketreasureImLabel.setVisible(false);
                        faketreasureImLabel.setEnabled(false);
                    } else {
                        if (player.getGotFakeTreasure()) {
                            treasureImLabel.setVisible(false);
                            treasureImLabel.setEnabled(false);
                            faketreasureImLabel.setVisible(true);
                            faketreasureImLabel.setEnabled(true);
                        } else {

                            treasureImLabel.setVisible(true);
                            treasureImLabel.setEnabled(false);
                            faketreasureImLabel.setVisible(false);
                            faketreasureImLabel.setEnabled(false);

                        }
                    }
                    ammunitionComboBox.setSelectedIndex(ammunitionComboBox.getSelectedIndex());
                    updatePanel();
                    return true;
                }
            }
        }
        updatePanel();
        return true;
    }

    public boolean RIGHT(){
        if (upButton.isEnabled()) {
            if (use.isSelected()) {
                player.useAmmunition(ammunitionComboBox.getSelectedIndex(), DEAST);
                use.setSelected(false);
                int cnt = ((Ammunition) (player.getAmmunitionList().get(ammunitionComboBox.getSelectedIndex()))).getTimesToUseLeft();
                countOfAmmunition.setText(Strings.PLAYERPANEL_INSTOCK + Integer.toString(cnt));
                //selectNextPlayer();
                updatePanel();
                return false;
            } else {
                if (!player.moveRight()){
                    //selectNextPlayer();
                    updatePanel();
                    return false;
                } else {
                    coordinates.setText(Strings.PLAYERPANEL_LOCATION + player.getX() + "-" + player.getY());
                    hurtCounter.setText(Strings.PLAYERPANEL_COUNTOFHURTS + player.getCountOfHurt());
                    if (player.getGotTreasure()) {
                        treasureImLabel.setVisible(true);
                        treasureImLabel.setEnabled(true);
                        faketreasureImLabel.setVisible(false);
                        faketreasureImLabel.setEnabled(false);
                    } else {
                        if (player.getGotFakeTreasure()) {
                            treasureImLabel.setVisible(false);
                            treasureImLabel.setEnabled(false);
                            faketreasureImLabel.setVisible(true);
                            faketreasureImLabel.setEnabled(true);
                        } else {

                            treasureImLabel.setVisible(true);
                            treasureImLabel.setEnabled(false);
                            faketreasureImLabel.setVisible(false);
                            faketreasureImLabel.setEnabled(false);

                        }
                    }
                    ammunitionComboBox.setSelectedIndex(ammunitionComboBox.getSelectedIndex());
                    updatePanel();
                    return true;
                }
            }
        }
        updatePanel();
        return true;
    }

    public boolean isWinner() {
        return player.isWinner();
    }
}
