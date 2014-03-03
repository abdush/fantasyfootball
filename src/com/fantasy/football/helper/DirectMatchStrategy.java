/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.Match;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Abdu
 */
public class DirectMatchStrategy implements MatchScorePredictor {

    
    private static final int SCORE_MEAN_DIV = 2;
    private Match match;
    private int homeTeamScore;
    private int awayTeamScore;
    private int[] matchScore = new int[2];
    
    public DirectMatchStrategy() {
    
    }

    
    @Override
    public int[] getMatchScore(Match match){
        this.match = match;
        generateMatchScore(60,40,0);
        return matchScore;
    }
    //This should be a different algorithm that predictes score based on face to face matches between the two teams
    private void generateMatchScore(int homePercentage, int awayPercentage, int drawPercentage) {
        
        Random rand = new Random();
        int perc = rand.nextInt(100);
        if (perc < homePercentage) {
            homeTeamScore = rand.nextInt(Math.round((float) match.getHomeTeam().getAvgHomeGoals()) + SCORE_MEAN_DIV) + 1;
            awayTeamScore = (homeTeamScore > 1 ? rand.nextInt(homeTeamScore) : 0);
        
        } else if (perc >= homePercentage && perc < homePercentage + awayPercentage) {
            awayTeamScore = rand.nextInt(Math.round((float) match.getAwayTeam().getAvgAwayGoals()) + SCORE_MEAN_DIV) + 1;
            homeTeamScore = (awayTeamScore > 1 ? rand.nextInt(awayTeamScore) : 0);
        
        } else {
            awayTeamScore = rand.nextInt(Math.round((float) match.getAwayTeam().getAvgAwayGoals()) + 1);
            homeTeamScore = awayTeamScore;
        
        }
        
        matchScore[0] = homeTeamScore;
        matchScore[1] = awayTeamScore;
        
    }
}
