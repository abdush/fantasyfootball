/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.Fixture;
import com.fantasy.football.model.GameWeek;
import com.fantasy.football.model.League;
import com.fantasy.football.model.Match;
import com.fantasy.football.model.Team;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Abdu
 */
public class FixtureGenerator {

    
    
    private List<Team> teams;
    private static Fixture fixture;
    private static GameWeek gameWeek;
    private static List<GameWeek> gameWeeks;
    private static List<Match> matches;

    private static Calendar calendar;

    public FixtureGenerator(List<Team> teams) {
        this.teams = teams;
    }

    public static List<GameWeek> generateFixture(League league) {

        matches = new ArrayList();
        gameWeeks = new ArrayList();
        List<Team> teams = league.getTeams();
        // Generate the fixtures using  RR cyclic algorithm.
        int totalRounds = teams.size() - 1;
        int matchesPerRound = teams.size() / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (totalRounds);
                int away = (totalRounds - match + round) % (totalRounds);
                
                // Last team stays in the same place while the others rotate around it.
                //Swap last team between home and away every 2 weeks
                if (match == 0) {
                    away = teams.size() - 1;
                    if (round % 2 == 1) {
                        int swap = home;
                        home = away;
                        away = swap;
                    }
                }

                // Add one so teams are number 1 to teams not 0 to teams - 1
                rounds[round][match] = (home + 1) + " v " + (away + 1);
                matches.add(new Match(match + 1, teams.get(home), teams.get(away)));

            }
            //System.out.println(matches);
            gameWeeks.add(new GameWeek(round + 1, Calendar.getInstance().getTime(), matches));
            //System.out.println(gameWeek);
            matches = new ArrayList();

        }
        Collections.shuffle(gameWeeks);
        calendar = new GregorianCalendar(2013, 7, 1);
        calendar.set(GregorianCalendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendar.set(GregorianCalendar.DAY_OF_WEEK_IN_MONTH, 1);
        Calendar calendar2 = new GregorianCalendar();
        //System.out.println( calendar.getTime());

        for (int round = 0; round < totalRounds; round++) {
            calendar.add(Calendar.DATE, 7);
            //System.out.println(calendar.getTime());
            gameWeeks.get(round).setDate(calendar.getTime());
            gameWeeks.get(round).setRoundID(round + 1);

        }
        for (int round = 0; round < totalRounds; round++) {
            calendar.add(Calendar.DATE, 7);
            gameWeeks.add(new GameWeek(round + totalRounds + 1, calendar.getTime(), swapHomeAway(gameWeeks.get(round).getMatches())));
        }
        fixture = new Fixture(gameWeeks);
        
        return gameWeeks;
    }

    private static List<Match> swapHomeAway(List<Match> matches) {
        List<Match> round2 = new ArrayList();
        for (Match match : matches) {
            round2.add(new Match(match.getMatchID(), match.getAwayTeam(), match.getHomeTeam()));
        }
        return round2;
    }

}
