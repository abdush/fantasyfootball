/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Abdu
 */
public class GameWeek {
    
    private static int instanceCreated = 0;
    private int  roundID;
    private Date date;
    private List<Match> matches;

    public GameWeek(Date date, List<Match> matches) {
        this.roundID = ++instanceCreated;
        this.date = date;
        this.matches = matches;
    }

    
    public GameWeek(int roundID, Date date, List<Match> matches) {
        this.roundID = roundID;
        this.date = date;
        this.matches = matches;
        for(Match match: matches)
            match.setRoundID(roundID);
        
    }

    public int getRoundID() {
        return roundID;
    }

    public Date getDate() {
        return date;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
        for(Match match: matches)
            match.setRoundID(roundID);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
        for(Match match: matches)
            match.setRoundID(roundID);
    }

    public void updateMatchRoundId() {
        for(Match match: matches)
            match.setRoundID(roundID);
    }
    @Override
    public String toString() {
        String output = "GameWeek " + roundID + ": " + date + "\n";
        for (Match match: matches)
            output += match.toString() + " \n";
        return output;
    }
    
}
