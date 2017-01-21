package ru.antonovcode.java.model.hospital;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by alex on 15.09.2014.
 */
public class Hospital {

    private Map<String, String> patients;
    private int X;
    private int Y;

    public Hospital(int X, int Y){
        this.X = X;
        this.Y = Y;
        patients = new HashMap();
    }

    public void add(String patient){
        patients.put(patient, patient);
    }

    public String cure(String patient){
        String first = null;
        if(!patients.isEmpty()){
            first = patients.get(patient);
            if(first != null){
                if(first.equals(patient)){
                    return first;
                }
                else{
                    patients.put(patient, patient);
                    return null;
                }
            } else{
                patients.put(patient, patient);
                return null;
            }

        } else{
            patients.put(patient, patient);
            return null;
        }
    }

    public void del(String nick){
        patients.remove(nick);
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }
}
