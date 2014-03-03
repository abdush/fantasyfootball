/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

import java.sql.Time;

/**
 *
 * @author Abdu
 */
public class YellowCardEvent extends MatchEvent{
    
  private static final double AVG_YELLOW_CARDS_PER_GAME = 3.4;

    public YellowCardEvent(int minute, Team team, Player player) {
        super(minute, team, player);
    }
  
    @Override
    public String toString() {
        return "[Yellow Card: " + minute + " ] " + player + " [" + team + "]\n";
    }

    
}
