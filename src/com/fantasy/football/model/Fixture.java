/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

import java.util.List;

/**
 *
 * @author Abdu
 */
public class Fixture {
    
    private List<GameWeek> gameWeeks;

    public Fixture(List<GameWeek> fixture) {
        this.gameWeeks = fixture;
    }

    public List<GameWeek> getGameWeeks() {
        return gameWeeks;
    }

    public void setGameWeeks(List<GameWeek> fixture) {
        this.gameWeeks = fixture;
    }
    
    public String toString() {
        String output = "";
        for (GameWeek gameweek: gameWeeks)
            output += gameweek.toString() + "\n";
        return output;
    }
    
}
