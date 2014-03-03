/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.controller;

import com.fantasy.football.helper.BestTeamTactic;
import com.fantasy.football.helper.MatchSimulator;
import com.fantasy.football.helper.ReserveTeamTactic;
import com.fantasy.football.helper.TeamSelectionStrategy;
import com.fantasy.football.model.FantasyLeague;
import com.fantasy.football.model.FantasyTeam;
import com.fantasy.football.model.Formation;
import com.fantasy.football.model.GameWeek;
import com.fantasy.football.model.League;
import com.fantasy.football.model.Match;
import com.fantasy.football.model.Player;
import com.fantasy.football.model.Team;
import com.fantasy.football.view.FixturePane;
import com.fantasy.football.view.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Abdu
 */
public class EPLController {

    private League league;
    private FantasyLeague fantasyFootball;
    private FantasyTeam fantasyTeam;
    private MatchSimulator simulator;
    private TeamSelectionStrategy homeSquadSelector;
    private TeamSelectionStrategy awaySquadSelector;
    private FixturePane fixtureView;
    private MainFrame mainView;

    public EPLController(League league, FantasyLeague fantasyFootball, FantasyTeam fantasyTeam) {
        this.league = league;
        this.fantasyFootball = fantasyFootball;
        this.fantasyTeam = fantasyTeam;
        simulator = new MatchSimulator();
    }

    public void setMainView(MainFrame mainView) {
        this.mainView = mainView;
        this.mainView.addFixtureListener(new FixtureListener());
    }

    public League getLeague() {
        return league;
    }

    public FantasyLeague getFantasyFootball() {
        return fantasyFootball;
    }

    public FantasyTeam getFantasyTeam() {
        return fantasyTeam;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void setFantasyFootball(FantasyLeague fantasyFootball) {
        this.fantasyFootball = fantasyFootball;
    }

    public void setFantasyTeam(FantasyTeam fantasyTeam) {
        this.fantasyTeam = fantasyTeam;
    }

    public int getCurrentRound() {
        return league.getCurrentGameWeek();
    }

    //This method handles the request to simulate all game week matches 
    public void simulateNxtRound() {

        GameWeek nxtRnd = league.getNextRound();
        for (Match match : nxtRnd.getMatches()) {
            //Simulate a change of Tactic if the Home team is much stronger than the Away Team
            if (match.getHomeTeam().getTeamRating() - match.getAwayTeam().getTeamRating() > 2) {
                simulator.setHomeSquadSelector(new ReserveTeamTactic());
                simulator.setAwaySquadSelector(new BestTeamTactic(Formation.FiveFourOne));
            } else {
                simulator.setHomeSquadSelector(new BestTeamTactic());
                simulator.setAwaySquadSelector(new BestTeamTactic());
            }
            simulator.simulateMatch(match);
            league.updateLeagueTable(match);
            
        }
        System.out.println(league.getSortedLeagueTable());
        //TreeMap map = (TreeMap)league.getSortedLeagueTable();
        //Integer standing = (Integer) map.get(map.get(0));
        //System.out.println(standing);
    }

    public void simulateGameWeek(int roundId) {

    }

    //Updates All Fantasy team game week points after the simulation of the round matches 
    public void updateGameWeekPoints() {
        for (FantasyTeam team : fantasyFootball.getTeams()) {
            team.updateGameWeekPoints(league.getCurrentGameWeek());
        }
        /*for (FantasyTeam team : fantasyFootball.getTeams()) {
            System.out.println(team.getOverallPoints());
        }*/
    }

    public void updateFantasyTeamView() {

        mainView.setFixtureModel(league.getNextRound());
        mainView.setLeagueStandingModel(league.getSortedLeagueTable());
        mainView.setFantasyTeamModel(fantasyTeam);
        List<Player> allPlayers = new ArrayList<>();

        for (Team team : league.getTeams()) {
            allPlayers.addAll(team.getPlayers());
        }
        mainView.setPlayerModel(allPlayers);
    }

    
    private class FixtureListener implements ActionListener {

        public FixtureListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            simulateNxtRound();
            updateGameWeekPoints();
            updateFantasyTeamView();
            league.updateNextRound();
            mainView.displayErrorMessage("Simulated!!");
        }
    }
}
