/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.Match;
import com.fantasy.football.model.Player;
import java.util.List;

/**
 *
 * @author Abdu
 */
public class MatchSimulator {

    private TeamSelectionStrategy homeSquadSelector;
    private TeamSelectionStrategy awaySquadSelector;
    private MatchScorePredictor scoreGenerator;
    private Match match;

    public MatchSimulator() {
        homeSquadSelector = new BestTeamTactic();
        awaySquadSelector = new BestTeamTactic();
        scoreGenerator = new CompisiteScoreStrategy();

    }

    public Match getMatch() {
        return match;
    }

    public void setHomeSquadSelector(TeamSelectionStrategy homeSquadSelector) {
        this.homeSquadSelector = homeSquadSelector;
    }

    public void setAwaySquadSelector(TeamSelectionStrategy awaySquadSelector) {
        this.awaySquadSelector = awaySquadSelector;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public MatchScorePredictor getScoreGenerator() {
        return scoreGenerator;
    }

    public void setScoreGenerator(MatchScorePredictor scoreGenerator) {
        this.scoreGenerator = scoreGenerator;
    }

    /*
    * Simulates a single match.
    * Selects the Home and Away team squad based on the tactic and strategy.
    * Predictes the score for the match.
    * Generate random events for the match.
    * It also add a Match event listener.
    */
    public void simulateMatch(Match match) {

        //Make Home and Away Team Selection: 
        List<Player> homeSquad = homeSquadSelector.getSquad(match.getHomeTeam());
        match.setHomeTeamSquad(homeSquad.subList(0, 11), homeSquad.subList(11, homeSquad.size()));
        List<Player> awaySquad = awaySquadSelector.getSquad(match.getAwayTeam());
        match.setAwayTeamSquad(awaySquad.subList(0, 11), awaySquad.subList(11, awaySquad.size()));

        //System.out.println(match.previewMatchStart());

        //Predicte Match score result:
        int[] score = scoreGenerator.getMatchScore(match);
        match.setHomeScore(score[0]);
        match.setAwayScore(score[1]);

        //Add a match event listener to in order to listen for events and update players stats
        match.addMatchEventListener(new MatchEventHandler());

        //Generate Match Events: 
        //Goals, Assists, Cards, Own goals, injuries, penalties, substitutions; 
        MatchEventFactory.generateMatchEvents(match);
    }

}
