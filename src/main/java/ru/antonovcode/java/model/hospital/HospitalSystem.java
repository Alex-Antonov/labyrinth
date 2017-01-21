package ru.antonovcode.java.model.hospital;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by alex on 16.09.2014.
 */
public class HospitalSystem {
    private ArrayList<Hospital> hospitalList;

    public HospitalSystem(){
        hospitalList = new ArrayList<Hospital>();
    }

    public void addHospital(int X, int Y){
        hospitalList.add(new Hospital(X, Y));
    }

    public boolean checkPatientAt(int X, int Y, String nick){
        boolean total = false;
        for(Hospital h : hospitalList){
            if(h.getX() == X && h.getY() == Y){
                if(h.cure(nick) == null){
                    h.add(nick);
                    for(Hospital h1 : hospitalList)
                        if(h1 != h){
                            h1.del(nick);
                        }

                    total = false;
                } else{
                    total = true;
                }
                break;
            }
        }
        return total;
    }
}
