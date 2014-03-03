/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.Match;
import java.util.Random;

/**
 *
 * @author Abdu
 */
public class AverageGoalStrategy implements MatchScorePredictor {

    private static final int HOME_WIN_CHANCE = 40;
    private static final int AWAY_WIN_CHANCE = 30;
    private static final int DRAW_CHANCE = 30;
    private static final int SCORE_MEAN_DIV = 2;

    private Match match;
    private int homeTeamScore;
    private int awayTeamScore;
    private int[] matchScore = new int[2];

    public AverageGoalStrategy() {

    }

    @Override
    public int[] getMatchScore(Match match) {
        this.match = match;
        predicteScore();
        return matchScore;
    }
    /*@Override
     public int getHomeTeamScore() {
     return homeTeamScore;
     }

     @Override
     public int getAwayTeamScore() {
     return awayTeamScore;
     }*/

    private void predicteScore() {

        //Change Home win, away win , draw probability based on the two teams rating
        int teamRateDiff = match.getHomeTeam().getTeamRating()
                - match.getAwayTeam().getTeamRating();

        switch (teamRateDiff) {
            case 0:
                generateMatchScore(HOME_WIN_CHANCE, AWAY_WIN_CHANCE, DRAW_CHANCE);
                break;
            case 1:
                generateMatchScore(HOME_WIN_CHANCE + 10, AWAY_WIN_CHANCE - 10, DRAW_CHANCE);
                break;
            case 2:
                generateMatchScore(HOME_WIN_CHANCE + 20, AWAY_WIN_CHANCE - 10, DRAW_CHANCE - 10);
                break;
            case 3:
                generateMatchScore(HOME_WIN_CHANCE + 30, AWAY_WIN_CHANCE - 10, DRAW_CHANCE - 20);
                break;
            case -1:
                generateMatchScore(HOME_WIN_CHANCE - 5, AWAY_WIN_CHANCE + 5, DRAW_CHANCE);
                break;
            case -2:
                generateMatchScore(HOME_WIN_CHANCE - 10, AWAY_WIN_CHANCE + 30, DRAW_CHANCE - 20);
                break;
            case -3:
                generateMatchScore(HOME_WIN_CHANCE - 20, AWAY_WIN_CHANCE + 40, DRAW_CHANCE - 20);
                break;
            default:
                generateMatchScore(HOME_WIN_CHANCE + 10, AWAY_WIN_CHANCE + 20, DRAW_CHANCE - 30);
        }

    }

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
