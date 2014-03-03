/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.model;

import com.fantasy.football.helper.LeagueTableSorter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abdu
 */
@XmlRootElement
public class League {

    private String leagueName;
    private List<Team> teams;
    private List<GameWeek> gameWeeks;
    private int currentGameWeek = 1;
    private Map leaguetable;

    public League() {
    }

    public League(String leagueName, List<Team> teams) {
        this.leagueName = leagueName;
        this.teams = teams;
    }

    @XmlAttribute(name = "name")
    public String getLeagueName() {
        return leagueName;
    }

    @XmlElement(name = "team")
    public List<Team> getTeams() {
        return teams;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Team getTeam(int teamNo) {
        for (Team team : teams) {
            if (team.getTeamNo() == teamNo) {
                return team;
            }
        }
        return null;
    }

    public int getNumberOfTeams() {
        return teams.size();
    }

    public int getNumberOfRounds() {
        return (gameWeeks.size());
    }

    public List<GameWeek> getGameWeeks() {
        return gameWeeks;
    }

    public void setGameWeeks(List<GameWeek> gameWeeks) {
        this.gameWeeks = gameWeeks;
    }

    public GameWeek getGameWeek(int roundId) {
        if (roundId > gameWeeks.size()) {
            roundId = gameWeeks.size();
        }
        if (roundId < 1) {
            roundId = 1;
        }
        for (GameWeek gw : gameWeeks) {
            if (gw.getRoundID() == roundId) {
                return gw;
            }
        }

        return null;

    }

    public Map getLeaguetable() {
        return leaguetable;
    }

    public GameWeek getNextRound() {
        // int RoundId = currentGameWeek - 1;
        return getGameWeek(currentGameWeek);
    }

    public void updateNextRound() {
        currentGameWeek += 1;
    }

    public int getCurrentGameWeek() {
        return currentGameWeek;
    }

    public void setCurrentGameWeek(int currentGameWeek) {
        this.currentGameWeek = currentGameWeek;
    }

    public void initializeLeagueTable() {
        leaguetable = new HashMap();
        Integer standing;
        List<Integer> standings;
        for (int i = 0; i < teams.size(); i++) {
            standing = 0;
            standings = new ArrayList();
            standings.add(0, 0);
            standings.add(1, 0);
            leaguetable.put(teams.get(i), standing);
        }
    }

    public void updateLeagueTable(Match match) {
        int hScore = match.getHomeScore();
        int aScore = match.getAwayScore();
        Integer hPoints = (Integer) leaguetable.get(match.getHomeTeam());
        Integer aPoints = (Integer) leaguetable.get(match.getAwayTeam());
        
        if (hScore == aScore) {
            hPoints += 1;
            aPoints += 1;
        
        } else if (hScore > aScore) {
            hPoints += 3;
        
        } else {
            aPoints += 3;
        
        }

        leaguetable.put(match.getHomeTeam(), hPoints);
        leaguetable.put(match.getAwayTeam(), aPoints);

    }

    public Map getSortedLeagueTable() {
        //HashMap<String,Double> map = new HashMap<String,Double>();
        LeagueTableSorter sorter = new LeagueTableSorter(leaguetable);
        TreeMap<Team, Integer> sorted_map = new TreeMap(sorter);
        sorted_map.putAll(leaguetable);
        return sorted_map;
    }

    @Override
    public String toString() {
        return leagueName;
    }
}
