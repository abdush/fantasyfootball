/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.Match;
import com.fantasy.football.model.Team;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Abdu
 */
public class MatchEventFactory {

    public static final double YELLOW_CARD_FH_PROB = 0.03;
    public static final double RED_CARD_FH_PROB = 0.001;
    public static final double SUB_FH_PROB = 0.01;
    public static final double YELLOW_CARD_SH_PROB = 0.03;
    public static final double RED_CARD_SH_PROB = 0.001;
    public static final double SUB_SH_PROB = 0.05;

    public static void generateMatchEvents(Match match) {
        
        MatchEventGenerator matchEventFactory = new MatchEventGenerator(match);
        HashMap goalEvents = new HashMap();
        TimeGenerator timer = new TimeGenerator();

        ///////////////////////Goooooooals (score already generated before)////////////////////////////////
        for (int i = 0; i < match.getHomeScore(); i++) {
            goalEvents.put(timer.getMinute(), match.getHomeTeam());
        }

        for (int i = 0; i < match.getAwayScore(); i++) {
            goalEvents.put(timer.getMinute(), match.getAwayTeam());
        }

        ////////////////FIRST HALF/////////////////////////////////////////////////
        matchEventFactory.generateStartMatchEvent();
        Random rand = new Random();
        double randomNumber = rand.nextDouble();
        for (int i = 1; i <= 45; i++) {
            if (goalEvents.containsKey(i)) {
                matchEventFactory.generateGoalEvent(i, (Team) goalEvents.get(i));
                continue;
            }
            if (rand.nextDouble() <= RED_CARD_FH_PROB) {
                matchEventFactory.generateRedCardEvent(i);
                continue;
            }
            if (rand.nextDouble() <= SUB_FH_PROB) {
                matchEventFactory.generateSubEvent(i);
                 continue;
            }
            if (rand.nextDouble() <= YELLOW_CARD_FH_PROB ) {
                matchEventFactory.generateYellowCardEvent(i);
                continue;
            }

        }

        //////////SECOND HALF////////////////////////////
        for (int i = 46; i <= 90; i++) {
            if (goalEvents.containsKey(i)) {
                matchEventFactory.generateGoalEvent(i, (Team) goalEvents.get(i));
                continue;
            }

            if (rand.nextDouble() <= RED_CARD_SH_PROB) {
                matchEventFactory.generateRedCardEvent(i);
                continue;
            }
            if (rand.nextDouble() <= YELLOW_CARD_SH_PROB) {
                matchEventFactory.generateYellowCardEvent(i);
                continue;
            }
            if (rand.nextDouble() <= SUB_SH_PROB) {
                matchEventFactory.generateSubEvent(i);
                continue;
            }
        }
        
        matchEventFactory.generateEndMatchEvent();
    }
}
