package ru.antonovcode.java.model.ammunition.impl;

import ru.antonovcode.java.model.ammunition.Ammunition;
import ru.antonovcode.java.util.options.Strings;

import java.awt.*;
import java.io.File;

/**
 * Created by alex on 02.08.2014.
 */
public class Mine extends Ammunition {
    private static String fileName = "/images/mine.png";
    private static Image image = null;

    {
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            image = tk.getDefaultToolkit().getImage(getClass().getResource(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Mine(int posX, int posY) {
        super(posX, posY);
        name = Strings.AMMUNITION_MINE;
    }

    public Mine(int timesToUseLeft) {
        super(timesToUseLeft);
        name = Strings.AMMUNITION_MINE;
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
        name = Strings.AMMUNITION_MINE;
    }


}
