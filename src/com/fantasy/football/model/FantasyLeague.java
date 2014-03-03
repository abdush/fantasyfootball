/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdu
 */
public class FantasyLeague {
    
    private String leagueName;
    private List<FantasyTeam> teams;
    private List<GameWeek> gameWeeks;

    public FantasyLeague() {
    }

    public String getLeagueName() {
        return leagueName;
    }

    public List<FantasyTeam> getTeams() {
        return teams;
    }

    public List<GameWeek> getGameWeeks() {
        return gameWeeks;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setTeams(List<FantasyTeam> teams) {
        this.teams = teams;
    }

    public void setGameWeeks(List<GameWeek> gameWeeks) {
        this.gameWeeks = gameWeeks;
    }
    
    public void addTeam(FantasyTeam team) {
        if(teams == null)
            teams = new ArrayList<>();
        teams.add(team);
    }
}
