package ru.antonovcode.java.controller;

import ru.antonovcode.java.model.Model;
import ru.antonovcode.java.model.ammunition.defammunition.IAmmunitionID;
import ru.antonovcode.java.view.interfaces.CodeWindow;
import ru.antonovcode.java.view.panels.LabyrinthPanel;

import java.awt.*;

/**
 * Created by alex on 28.08.2014.
 */
public class Controller implements IAmmunitionID{

    private Model model = null;

    public Controller(final CodeWindow window){

        window.setCodeHandler(new CodeHandler() {

            @Override
            public void createNewGame(final int labyrinthSize, String[] nicks) {
                model = new Model(labyrinthSize);
                model.createNewPlayers(nicks);
                window.setPlayersToPlayerPanels(model.getPlayers());
                window.repaintLabyrinth(model.createNewLabyrinth(labyrinthSize), labyrinthSize, model.getPlayers());

               // if(!window.getColoritOfLabyrinth())
                   // model.drawPlayers(window.getLabyrinthGraphics(), LabyrinthPanel.LEFTHIGHCORNER, LabyrinthPanel.DIF);


                //window.updatePlayersPanel(coordinates, ammunitionIm, model.getAmmunitionList(selectedNick), countOfHurts);
            }




        });


    }

    public interface CodeHandler{
        void createNewGame(final int labyrinthSize, String[] nicks);

    }
}
