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
public class PlayerFormSorter implements Comparator<Player> {

    @Override
    /*
     * Sort Players based on their form (Total points) 
     */
     public int compare(Player p1, Player p2) {
        return Integer.compare(p2.getTotalPoints(), p1.getTotalPoints());
      }
   /* public int compare(Player p1, Player p2) {
        int returnValue = 0;
        if(p1.getTotalPoints() > p2.getTotalPoints())
            returnValue = -1;
        else if (p1.getTotalPoints() < p2.getTotalPoints())
            returnValue = 1;
        return returnValue;
    }*/
    
}
