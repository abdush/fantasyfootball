/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

import com.fantasy.football.helper.MatchEventListener;
import com.fantasy.football.helper.MatchStatistics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abdu
 */
public class Match {
    
    
    private int matchID;
    private int roundID;
    private Team homeTeam;
    private Team awayTeam;
    private Date date;

    private int homeScore;
    private int awayScore;
    
    private List<Player> homeTeamSquad;
    private List<Player> awayTeamSquad;
    
    private List<Player> homeTeamStartingEleven;
    private List<Player> awayTeamStartingEleven;
    
    private List<Player> homeTeamSubs;
    private List<Player> awayTeamSubs;
    
    private List<MatchEvent> matchEvents;
    private List<MatchEventListener> matchEventListeners;
    
    private MatchStatistics matchStats;
    
    private boolean matchPlayed = false;
    
    public Match(int matchID, Team homeTeam, Team awayTeam) {
        this.matchID = matchID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        matchEventListeners = new ArrayList<>();
        homeTeamSquad = new ArrayList<>();
        awayTeamSquad = new ArrayList<>();
                
    }

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }
    
    

    public int getMatchID() {
        return matchID;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Date getDate() {
        return date;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public List<Player> getHomeTeamSquad() {
        return homeTeamSquad;
    }

    public List<Player> getAwayTeamSquad() {
        return awayTeamSquad;
    }

    public List<MatchEvent> getMatchEvents() {
        return matchEvents;
    }

    public void setHomeTeamSquad(List<Player> homeTeamSquad) {
        this.homeTeamSquad = homeTeamSquad;
        
    }

    public void setAwayTeamSquad(List<Player> awayTeamSquad) {
        this.awayTeamSquad = awayTeamSquad;
    }
    
    public void setHomeTeamSquad(List<Player> homeTeamStartingEleven, List<Player> homeTeamSubs) {
        this.homeTeamStartingEleven = homeTeamStartingEleven;
        this.homeTeamSubs = homeTeamSubs;
        homeTeamSquad.clear();
        homeTeamSquad.addAll(homeTeamStartingEleven);
        homeTeamSquad.addAll(homeTeamSubs);
        
    }

    public void setAwayTeamSquad(List<Player> awayTeamStartingEleven, List<Player> awayTeamSubs) {
        this.awayTeamStartingEleven = awayTeamStartingEleven;
        this.awayTeamSubs = awayTeamSubs;
        awayTeamSquad.clear();
        awayTeamSquad.addAll(awayTeamStartingEleven);
        awayTeamSquad.addAll(awayTeamSubs);
    }

    
    public List<Player> getHomeTeamStartingEleven() {
        return homeTeamStartingEleven;
    }

    public List<Player> getAwayTeamStartingEleven() {
        return awayTeamStartingEleven;
    }

    public List<Player> getHomeTeamSubs() {
        return homeTeamSubs;
    }

    public List<Player> getAwayTeamSubs() {
        return awayTeamSubs;
    }

    public void setHomeTeamStartingEleven(List<Player> homeTeamStartingEleven) {
        this.homeTeamStartingEleven = homeTeamStartingEleven;
        //homeTeamSquad.addAll(homeTeamStartingEleven);
    }

    public void setAwayTeamStartingEleven(List<Player> awayTeamStartingEleven) {
        this.awayTeamStartingEleven = awayTeamStartingEleven;
        //awayTeamSquad.addAll(awayTeamStartingEleven);
    }

    public void setHomeTeamSubs(List<Player> homeTeamSubs) {
        this.homeTeamSubs = homeTeamSubs;
        //homeTeamSquad.addAll(homeTeamSubs);
    }

    public void setAwayTeamSubs(List<Player> awayTeamSubs) {
        this.awayTeamSubs = awayTeamSubs;
        //awayTeamSquad.addAll(awayTeamSubs);
    }

    
    public void setMatchEvents(List<MatchEvent> matchEvents) {
        //this.matchEvents = matchEvents;
        for(MatchEvent matchEvent: matchEvents)
            addMatchEvent(matchEvent);
        
    }
    
    public void addMatchEvent(MatchEvent matchEvent) {
        if(matchEvents == null)
            matchEvents = new ArrayList();
        matchEvents.add(matchEvent);
        matchEvent.setMatch(this);
        notifyListeners(matchEvent);
        
    }
    
    public void notifyListeners(MatchEvent matchEvent) {
        for(MatchEventListener matchEventListener: matchEventListeners) {
        if(matchEventListener != null)
            matchEventListener.MatchEventOccured(matchEvent);
        }
    }
    
    public void addMatchEventListener(MatchEventListener matchEventListener) {
        matchEventListeners.add(matchEventListener);
    }
    
    public void removeMatchEventListener(MatchEventListener matchEventListener) {
        matchEventListeners.remove(matchEventListener);
    }

    public MatchStatistics getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(MatchStatistics matchStats) {
        this.matchStats = matchStats;
    }

    public void setMatchPlayed(boolean matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    public boolean isMatchPlayed() {
        return matchPlayed;
    }
    
    public String previewMatchStart() {
        String matchDetails = "\n";
        matchDetails += matchID + ": " + homeTeam + "  vs " + awayTeam + "\n";
        matchDetails += "Home Squad: \n"; 
        matchDetails += homeTeamSquad + "\n";
        matchDetails += "Away Squad: \n"; 
        matchDetails += awayTeamSquad + "\n";
        return matchDetails;
    }
    
    public String previewMatch() {
        String matchDetails = "\n";
        //matchDetails += matchID + ": " + homeTeam + " " + homeScore + " vs " + awayScore + " " + awayTeam + "\n";
        //matchDetails += homeTeamSquad + "\n";
        //matchDetails += awayTeamSquad + "\n";
        if (matchEvents != null) {
          //Collections.sort(matchEvents, new MatchEventSorter());
            //matchDetails += matchEvents.toString();
            for(MatchEvent event: matchEvents)
                matchDetails += event;
        }
        return matchDetails;
    }
    
   @Override
    public String toString() {
        return matchID + ": " + homeTeam + " " + homeScore + " vs " + awayScore + " " + awayTeam;
    } 
}
