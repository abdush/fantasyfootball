/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

import java.util.Comparator;

/**
 *
 * @author Abdu
 */
public class MatchEventSorter implements Comparator<MatchEvent> {

    @Override
    /*
    * Sorts Match events based on the time (minute)
    */
    public int compare(MatchEvent m1, MatchEvent m2) {
        int returnValue = 0;
        if(m1.getMinute() > m2.getMinute())
            returnValue = 1;
        else if (m1.getMinute() < m2.getMinute())
            returnValue = -1;
        return returnValue;
    }
    
}
