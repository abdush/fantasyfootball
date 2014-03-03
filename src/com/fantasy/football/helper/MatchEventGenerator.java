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
import com.fantasy.football.model.Player;
import com.fantasy.football.model.Position;
import com.fantasy.football.model.RedCardEvent;
import com.fantasy.football.model.StartMatchEvent;
import com.fantasy.football.model.SubstitutionEvent;
import com.fantasy.football.model.Team;
import com.fantasy.football.model.YellowCardEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Abdu
 */
public class MatchEventGenerator {

    private static final int MAX_REDCARDS_PER_GAME = 3;
    private static final int MAX_YELLOWCARDS_PER_GAME = 8;
    private static final int MAX_SUB_PER_TEAM = 3;

    private Match match;
    private MatchStatistics matchFacts;
    
    public MatchEventGenerator(Match match) {
        this.match = match;
        matchFacts = new MatchStatistics(match);
    }

    private Team getRandomTeam() {
        Random rand = new Random();

        if (rand.nextInt(2) == 0) {
            return match.getHomeTeam();
        } else {
            return match.getAwayTeam();
        }
    }

    private Player getRandomPlayerOnPitch(Team team) {

        List<Player> playersOnPitch = matchFacts.getPlayersOnPitch(team);

        List<Player> goalKeeperOnPitch = Team.getPlayersByPosition(playersOnPitch, Position.GoalKeeper);
        List<Player> forwardsOnPitch = Team.getPlayersByPosition(playersOnPitch, Position.Forward);
        List<Player> midfieldersOnPitch = Team.getPlayersByPosition(playersOnPitch, Position.Midfielder);
        List<Player> defendersOnPitch = Team.getPlayersByPosition(playersOnPitch, Position.Defender);
        List<Player> allFrontPlayersOnPitch = new ArrayList();
        allFrontPlayersOnPitch.addAll(forwardsOnPitch);
        allFrontPlayersOnPitch.addAll(midfieldersOnPitch);
        allFrontPlayersOnPitch.addAll(defendersOnPitch);
        Random rand = new Random();
        int selectedPlayer = rand.nextInt(allFrontPlayersOnPitch.size());
        return allFrontPlayersOnPitch.get(selectedPlayer);

    }

    private Player getRandomPlayerOnPitchByPosition(Team team, Position position) {
        List<Player> playersOnPitch = matchFacts.getPlayersOnPitch(team);
        List<Player> playersOnPosition = Team.getPlayersByPosition(playersOnPitch, position);

        Random rand = new Random();
        int selectedPlayer = rand.nextInt(playersOnPosition.size());
        return playersOnPosition.get(selectedPlayer);
    }

    void generateGoalEvent(int minute, Team team) {

        Random rand = new Random();
        int goalScorer;
        GoalEvent goalEvent;
        Player scorerPlayer;
        List<Player> playersOnPitch = matchFacts.getPlayersOnPitch(team);

        List<Player> goalKeeperOnPitch = Team.getPlayersByPosition(playersOnPitch, Position.GoalKeeper);
        List<Player> forwardsOnPitch = Team.getPlayersByPosition(playersOnPitch, Position.Forward);
        List<Player> midfieldersOnPitch = Team.getPlayersByPosition(playersOnPitch, Position.Midfielder);
        List<Player> defendersOnPitch = Team.getPlayersByPosition(playersOnPitch, Position.Defender);

        if (forwardsOnPitch.isEmpty()) {
            forwardsOnPitch.addAll(midfieldersOnPitch);
        }

        int scorerPosition = rand.nextInt(100);
        if (scorerPosition <= GoalEvent.CHANCE_FORWARDS) {
            goalScorer = rand.nextInt(forwardsOnPitch.size());
            scorerPlayer = forwardsOnPitch.get(goalScorer);

        } else if (scorerPosition > GoalEvent.CHANCE_FORWARDS && scorerPosition < GoalEvent.CHANCE_FORWARDS + GoalEvent.CHANCE_MIDFIELDER) {
            goalScorer = rand.nextInt(midfieldersOnPitch.size());
            scorerPlayer = midfieldersOnPitch.get(goalScorer);
            goalEvent = new GoalEvent(minute, team, midfieldersOnPitch.get(goalScorer));
        } else {
            goalScorer = rand.nextInt(defendersOnPitch.size());
            scorerPlayer = defendersOnPitch.get(goalScorer);

        }
        //match.addMatchEvent(goalEvent);
        matchFacts.addGoalScorer(team, scorerPlayer);
        generateAssistEvent(minute, team, scorerPlayer);
        match.addMatchEvent(new GoalEvent(minute, team, scorerPlayer));
        //notify Listener
    }

    void generateAssistEvent(int minute, Team team, Player scorer) {

        Player assistPlayer;
        do {
            assistPlayer = getRandomPlayerOnPitch(team);

        } while (assistPlayer.equals(scorer));

        match.addMatchEvent(new AssistEvent(minute, team, assistPlayer));
        //notify Listener

    }

    void generateYellowCardEvent(int minute) {

        int yellowCards = matchFacts.getYellowCardedPlayers(match.getHomeTeam()).size()
                + matchFacts.getYellowCardedPlayers(match.getAwayTeam()).size();

        if (yellowCards < MAX_YELLOWCARDS_PER_GAME) {
            Team team = getRandomTeam();
            Player cardedPlayer = getRandomPlayerOnPitch(team);
            match.addMatchEvent(new YellowCardEvent(minute, team, cardedPlayer));
            if (matchFacts.getYellowCardedPlayers(team).contains(cardedPlayer)) {
                generateRedCardEvent(minute, team, cardedPlayer);
            }
            matchFacts.addYellowCardPlayer(team, cardedPlayer);
        }
    }

    void generateRedCardEvent(int minute) {

        int redCards = matchFacts.getRedCardedPlayers(match.getHomeTeam()).size()
                + matchFacts.getRedCardedPlayers(match.getAwayTeam()).size();

        if (redCards < MAX_REDCARDS_PER_GAME) {
            Team team = getRandomTeam();
            Player cardedPlayer = getRandomPlayerOnPitch(team);
            generateRedCardEvent(minute, team, cardedPlayer);
        }

    }

    void generateRedCardEvent(int minute, Team team, Player carderPlayer) {

        //Player carderPlayer = matchFacts.getYellowCardedPlayers(team).get(matchFacts.getYellowCardedPlayers(team).size() - 1);
        matchFacts.addRedCardPlayer(team, carderPlayer);
        match.addMatchEvent(new RedCardEvent(minute, team, carderPlayer));
        if (carderPlayer.getPositions().contains(Position.GoalKeeper)) {
            generateSubEvent(minute, team, getRandomPlayerOnPitchByPosition(team, Position.Midfielder), matchFacts.getPlayersOnBenchByPosition(team, Position.GoalKeeper).get(0));
        }
    }

    void generateSubEvent(int minute) {
        List<Team> teams = new ArrayList<>();

        if (matchFacts.getSubsInPlayers(match.getHomeTeam()).size() < MAX_SUB_PER_TEAM) {
            teams.add(match.getHomeTeam());
        }
        if (matchFacts.getSubsInPlayers(match.getAwayTeam()).size() < MAX_SUB_PER_TEAM) {
            teams.add(match.getAwayTeam());
        }
        if (teams.isEmpty()) {
            return;
        }
        Random rand = new Random();

        Team team = teams.get(rand.nextInt(teams.size()));
        Player playerOut = getRandomPlayerOnPitch(team);
        List<Player> subs = matchFacts.getPlayersOnBenchByPosition(team, playerOut.getPositions().get(0));
        if (subs.isEmpty()) {
            subs = matchFacts.getPlayersOnBench(team);
        }
        int selectedIn = rand.nextInt(subs.size());
        Player playerIn = subs.get(selectedIn);

        matchFacts.addSubOutPlayer(team, playerOut);
        matchFacts.addSubInPlayer(team, playerIn);
        match.addMatchEvent(new SubstitutionEvent(minute, team, playerIn, playerOut));
    }

    void generateSubEvent(int minute, Team team, Player playerOut) {

        Random rand = new Random();
        List<Team> teams = new ArrayList<>();

        if (matchFacts.getSubsInPlayers(match.getHomeTeam()).size() < MAX_SUB_PER_TEAM) {
            teams.add(match.getHomeTeam());
        }
        if (matchFacts.getSubsInPlayers(match.getAwayTeam()).size() < MAX_SUB_PER_TEAM) {
            teams.add(match.getAwayTeam());
        }
        if (teams.isEmpty()) {
            return;
        }
        List<Player> subs = matchFacts.getPlayersOnBenchByPosition(team, playerOut.getPositions().get(0));
        if (subs.isEmpty()) {
            subs = matchFacts.getPlayersOnBench(team);
        }
        int selectedIn = rand.nextInt(subs.size());
        Player playerIn = subs.get(selectedIn);

        matchFacts.addSubOutPlayer(team, playerOut);
        matchFacts.addSubInPlayer(team, playerIn);
        match.addMatchEvent(new SubstitutionEvent(minute, team, playerIn, playerOut));
    }

    void generateSubEvent(int minute, Team team, Player playerOut, Player playerIn) {

        if (matchFacts.getSubsInPlayers(team).size() < MAX_SUB_PER_TEAM) {

            matchFacts.addSubOutPlayer(team, playerOut);
            matchFacts.addSubInPlayer(team, playerIn);
            match.addMatchEvent(new SubstitutionEvent(minute, team, playerIn, playerOut));
        }
    }

    void generateStartMatchEvent() {
        match.addMatchEvent(new StartMatchEvent(0));
    }

    void generateEndMatchEvent() {
        match.addMatchEvent(new EndMatchEvent(90));
    }
}
