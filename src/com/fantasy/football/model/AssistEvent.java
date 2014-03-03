/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

/**
 *
 * @author Abdu
 */
public class AssistEvent extends MatchEvent {

    public AssistEvent(int minute, Team team, Player player) {
        super(minute, team, player);
    }
    
    @Override
    public String toString() {
        return "[Assist: " + minute + " ] " + player + " [" + team + "]\n";
    }
    
}
