package ru.antonovcode.java.model.ammunition.impl;

import ru.antonovcode.java.model.ammunition.Ammunition;
import ru.antonovcode.java.util.options.Strings;

import java.awt.*;
import java.io.File;

/**
 * Created by alex on 01.08.2014.
 */
public class Gun extends Ammunition {
    private static String fileName = "/images/gun.png";
    private static Image image = null;

    {
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            image = tk.getDefaultToolkit().getImage(getClass().getResource(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Gun(final int posX, final int posY){

        super(posX, posY);
        name = Strings.AMMUNITION_GUN;
    }

    public Gun(final int timesToUseLeft){

        super(timesToUseLeft);
        name = Strings.AMMUNITION_GUN;
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
        name = Strings.AMMUNITION_GUN;
    }
}
