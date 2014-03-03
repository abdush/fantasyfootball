/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.Match;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdu
 */
public class CompisiteScoreStrategy implements MatchScorePredictor {

    private Match match;
    private int homeTeamScore;
    private int awayTeamScore;
    private int[] matchScore = new int[2];

    private List<MatchScorePredictor> matchScoreStrategy;

    public CompisiteScoreStrategy() {
        matchScoreStrategy = new ArrayList<>();
        matchScoreStrategy.add(new AverageGoalStrategy());
        matchScoreStrategy.add(new DirectMatchStrategy());

    }

    public List<MatchScorePredictor> getMatchScoreStrategy() {
        return matchScoreStrategy;
    }

    public void setMatchScoreStrategy(List<MatchScorePredictor> matchScoreStrategy) {
        this.matchScoreStrategy = matchScoreStrategy;
    }

    @Override
    public int[] getMatchScore(Match match) {
        this.match = match;
        predicteScore();
        return matchScore;
    }

    /*
     @Override
     public int getHomeTeamScore() {
     return homeTeamScore;
     }

     @Override
     public int getAwayTeamScore() {
     return awayTeamScore;
     }
     */
    private void predicteScore() {
        homeTeamScore = 0;
        awayTeamScore = 0;
        matchScore[0] = 0;
        matchScore[1] = 0;
        
        //Change Home win, away win , draw probability based on the two teams rating
        for (MatchScorePredictor predictor : matchScoreStrategy) {
            int[] score = predictor.getMatchScore(match);
            homeTeamScore += score[0];
            awayTeamScore += score[1];
        }
        
        matchScore[0] = Math.round(homeTeamScore / matchScoreStrategy.size());
        matchScore[1] = Math.round(awayTeamScore / matchScoreStrategy.size());

    }

}
