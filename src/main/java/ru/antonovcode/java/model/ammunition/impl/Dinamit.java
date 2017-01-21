package ru.antonovcode.java.model.ammunition.impl;

import ru.antonovcode.java.model.ammunition.Ammunition;
import ru.antonovcode.java.util.options.Strings;

import java.awt.*;

/**
 * Created by alex on 02.08.2014.
 */
public class Dinamit extends Ammunition {

    private static String fileName = "/images/dinamit.png";
    private static Image image = null;

    {
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            image = tk.getDefaultToolkit().getImage(getClass().getResource(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dinamit(int posX, int posY) {
        super(posX, posY);
        name = Strings.AMMUNITION_DINAMIT;
    }

    public Dinamit(int timesToUseLeft) {
        super(timesToUseLeft);
        name = Strings.AMMUNITION_DINAMIT;
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(image, posX, posY, null);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void updateLanguage() {
        name = Strings.AMMUNITION_DINAMIT;
    }
}
