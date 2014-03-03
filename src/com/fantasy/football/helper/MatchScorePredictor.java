/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.helper;

import com.fantasy.football.model.Match;

/**
 *
 * @author Abdu
 */
public interface MatchScorePredictor {

    public static final int MAX_SCORE_PER_MATCH = 8;
    

    public int[] getMatchScore(Match match);
    
    
}
