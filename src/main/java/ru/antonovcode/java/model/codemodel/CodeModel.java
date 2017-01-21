package ru.antonovcode.java.model.codemodel;

import ru.antonovcode.java.model.cell.Cell;
import ru.antonovcode.java.model.hospital.HospitalSystem;
import ru.antonovcode.java.model.player.Player;

/**
 * Created by alex on 13.09.2014.
 */
public interface CodeModel {
    Cell[][] getCurrentLabyrinthModel();
    Player isHerePlayer(int X, int Y);
    HospitalSystem getHospitalSystem();
}
