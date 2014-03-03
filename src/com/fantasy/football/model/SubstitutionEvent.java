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
public class SubstitutionEvent extends MatchEvent {
    
    private static final int MAX_SUBS_PER_GAME = 3;
    
    //private Player playerIn;
    private Player playerOut;

    public SubstitutionEvent(int minute, Team team, Player player) {
        super(minute, team, player);
    }

    public SubstitutionEvent(int minute, Team team, Player playerIn, Player playerOut) {
        super(minute, team, playerIn);
        this.playerOut = playerOut;
    }
    
    public Player getPlayerIn() {
        return getPlayer();
    }

    public Player getPlayerOut() {
        return playerOut;
    }

    public void setPlayerIn(Player playerIn) {
        setPlayer(playerIn);
    }

    public void setPlayerOut(Player playerOut) {
        this.playerOut = playerOut;
    }
    
    
    @Override
    public String toString() {
        return "[Sub: " + minute + " ] " + " [IN: " + player + " ] [OUT: " + playerOut + " ] [" + team + "]\n";
        
    }
    
}
