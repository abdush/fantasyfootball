/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Abdu
 */
public class TimeGenerator {
    
    private List<Integer> fullTimeMinutes;
    private List<Integer> fisrtHalfMinutes;
    private List<Integer> secondHalfMinutes;
    private List<Integer> selectedMinutes;
    
    private static final int FULL_TIME = 90;
    private static final int HALF_TIME = 45;
    

    public TimeGenerator() {
        initialize();
    }
    
    public void initialize() {
        fullTimeMinutes = new ArrayList();
        fisrtHalfMinutes = new ArrayList();
        secondHalfMinutes = new ArrayList();
        selectedMinutes = new ArrayList();
        
        for(int i= 1; i<=FULL_TIME; i++)
            fullTimeMinutes.add(i);
        fisrtHalfMinutes = fullTimeMinutes.subList(0, HALF_TIME);
        secondHalfMinutes = fullTimeMinutes.subList(HALF_TIME, FULL_TIME);
    
    }
    
    
    public int getMinute() {
        
        Random rand = new Random();
        int selected = 0;
        do {
        selected = rand.nextInt(fullTimeMinutes.size());
        } while (selectedMinutes.contains(selected));
        selectedMinutes.add(selected);
        int minute = fullTimeMinutes.get(selected);
        return minute;
               
    }
    
    public int getFirstHalfMinute() {
        
        Random rand = new Random();
        int selected = 0;
        do {
        selected = rand.nextInt(fisrtHalfMinutes.size());
        } while (selectedMinutes.contains(selected));
        selectedMinutes.add(selected);
        int minute = fisrtHalfMinutes.get(selected);
        return minute;
               
    }
    
    public int getSecondHalfMinute() {
        
        Random rand = new Random();
        int selected = 0;
        do {
        selected = rand.nextInt(secondHalfMinutes.size());
        } while (selectedMinutes.contains(selected));
        selectedMinutes.add(selected);
        int minute = secondHalfMinutes.get(selected);
        return minute;
               
    }
    
}
