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
public abstract class MatchEvent {
    
    protected int minute;
    protected Team team;
    protected Player player;

    protected Match match;
    
    public MatchEvent(int minute) {
        
    }
    
    public MatchEvent(int minute, Team team) {
        this.minute = minute;
        this.team = team;
    }

    
    public MatchEvent(int minute, Team team, Player player) {
        this.minute = minute;
        this.team = team;
        this.player = player;
    }

    public int getMinute() {
        return minute;
    }

    public Team getTeam() {
        return team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setTime(int minute) {
        this.minute = minute;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    
    @Override
    public String toString() {
        return   "min: " + minute + ", team=" + team + ", player:" + player + "\n";
    }
    
    
    
}
