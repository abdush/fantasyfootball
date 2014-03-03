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
public class GoalEvent extends MatchEvent{
    
    public static final double  CHANCE_FORWARDS = 60; 
    public static final double CHANCE_MIDFIELDER = 25; 
    public static final double CHANCE_DEFENDER = 15; 

    public GoalEvent(int minute, Team team, Player player) {
        super(minute, team, player);
    }

    @Override
    public String toString() {
        return "[Goooaal: " + minute + " ] " + player + " [" + team + "]\n";
    }
    
    
}
