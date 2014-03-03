/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.AssistEvent;
import com.fantasy.football.model.EndMatchEvent;
import com.fantasy.football.model.GoalEvent;
import com.fantasy.football.model.Match;
import com.fantasy.football.model.MatchEvent;
import com.fantasy.football.model.Player;
import com.fantasy.football.model.PlayerStatistics;
import com.fantasy.football.model.RedCardEvent;
import com.fantasy.football.model.StartMatchEvent;
import com.fantasy.football.model.SubstitutionEvent;
import com.fantasy.football.model.Team;
import com.fantasy.football.model.YellowCardEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdu
 */
public class MatchEventHandler implements MatchEventListener {

    private List<PlayerStatistics> playersStats;
    private Match match;

    @Override
    /**
     * This method is notified when a match event has occurred and added to
     * a match. It checks the type of the match event and updates player statistics.
     */
    public void MatchEventOccured(MatchEvent matchEvent) {
          
        //System.out.println(matchEvent);
        if (matchEvent instanceof StartMatchEvent) {            
            this.match = matchEvent.getMatch();
            initialize();
        }
        if (matchEvent instanceof SubstitutionEvent) {            
            SubstitutionEvent subEvent = (SubstitutionEvent) matchEvent;
            addSubs(subEvent);
        }
        if (matchEvent instanceof YellowCardEvent) {            
            addYellowCard(matchEvent);
        }
        if (matchEvent instanceof RedCardEvent) {            
            addRedCard(matchEvent);
        }
        if (matchEvent instanceof GoalEvent) {            
            addGoal(matchEvent);
        }
        if (matchEvent instanceof AssistEvent) {            
            addAssist(matchEvent);
        }
        if (matchEvent instanceof EndMatchEvent) {            
            updateStats();
            //dumpStats();
        }

    }

    private void initialize() {
        playersStats = new ArrayList<>();
        for (Player player : match.getHomeTeam().getPlayers()) {
            playersStats.add(new PlayerStatistics(player, match.getRoundID(), match.getMatchID()));
        }
        for (Player player : match.getAwayTeam().getPlayers()) {
            playersStats.add(new PlayerStatistics(player, match.getRoundID(), match.getMatchID()));
        }
        for (Player player : match.getHomeTeamStartingEleven()) {
            getPlayerStats(player).setMinuteOut(90);
            
        }
        for (Player player : match.getAwayTeamStartingEleven()) {
            getPlayerStats(player).setMinuteOut(90);
        }
    }

    private void addSubs(SubstitutionEvent subEvent) {
        playersStats.add(new PlayerStatistics(subEvent.getPlayerIn(), match.getRoundID(), match.getMatchID()));
        getPlayerStats(subEvent.getPlayerIn()).setMinuteIn(subEvent.getMinute());
        getPlayerStats(subEvent.getPlayerIn()).setMinuteOut(90);
        PlayerStatistics playerOut = getPlayerStats(subEvent.getPlayerOut());
        playerOut.setMinuteOut(subEvent.getMinute());
        playerOut.updatePoints();
        playersStats.remove(playerOut);
        
    }

    private void addRedCard(MatchEvent redCardEvent) {

        getPlayerStats(redCardEvent.getPlayer()).setMinuteOut(redCardEvent.getMinute());
        getPlayerStats(redCardEvent.getPlayer()).setRedCards();
        
    }

    private void addYellowCard(MatchEvent yellowCardEvent) {

        getPlayerStats(yellowCardEvent.getPlayer()).setYellowCards();
        
    }

    private void addGoal(MatchEvent goalEvent) {

        getPlayerStats(goalEvent.getPlayer()).setGoalsScored();
        for (PlayerStatistics playerStats: getOtherTeamPlayerStats(goalEvent.getTeam()))
            playerStats.addConcededGoal();
    }

    private void addAssist(MatchEvent assistEvent) {

        getPlayerStats(assistEvent.getPlayer()).setAssists();
        
    }

    private void updateStats() {
        for(PlayerStatistics playerStas: playersStats)
            playerStas.updatePoints();
        match.setMatchPlayed(true);
    }

    public void dumpStats() {
        
        for(PlayerStatistics playerStas: playersStats)
            System.out.println(playerStas);

    }

    private PlayerStatistics getPlayerStats(Player player) {
        for (PlayerStatistics playerStats : playersStats) {
            if (playerStats.getPlayer().equals(player)) {
                return playerStats;
            }
        }
        return null;
    }
    
    private List<PlayerStatistics> getOtherTeamPlayerStats(Team team) {
        List<PlayerStatistics> OtherPlayersStats = new ArrayList<>();
        for (PlayerStatistics playerStats : playersStats) {
            if (!playerStats.getPlayer().getTeam().equals(team)) {
                OtherPlayersStats.add(playerStats);
            }
        }
        return OtherPlayersStats;
    }
}
