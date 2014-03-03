/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.Match;
import com.fantasy.football.model.Player;
import com.fantasy.football.model.Position;
import com.fantasy.football.model.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Abdu
 * This class acts a live match statistics.. 
 * It is used by the MatchEventGenerator in order to get available players on pitch and
 * other match facts occurred.
 * 
 */


public class MatchStatistics {

    private Match match;
    
    List<Player> awayStartingPlayers;
    List<Player> homeStartingPlayers;

    List<Player> awaySubPlayers;
    List<Player> homeSubPlayers;

    List<Player> allHomeFrontPlayersOnPitch;
    List<Player> allAwayFrontPlayersOnPitch;

    List<Player> allHomeFrontPlayersOnBench;
    List<Player> allAwayFrontPlayersOnBench;

    HashMap homePlayersOnFieldAtTime;
    HashMap awayPlayersOnFieldAtTime;

    List<Player> homeScoreSheet;
    List<Player> awayScoreSheet;

    List<Player> homePlayersIn;
    List<Player> homePlayersOut;
    List<Player> awayPlayersIn;
    List<Player> awayPlayersOut;

    List<Player> homeYellowCardedPlayers;
    List<Player> homeRedCardedPlayers;
    List<Player> awayYellowCardedPlayers;
    List<Player> awayRedCardedPlayers;

    public MatchStatistics(Match match) {
        this.match = match;
        initialize();
    }

    public void initialize() {

        /////Home Team
        homeStartingPlayers = match.getHomeTeamStartingEleven();
        homeSubPlayers = match.getHomeTeamSubs();

        List<Player> homeGoalKeeperOnPitch = Team.getPlayersByPosition(homeStartingPlayers, Position.GoalKeeper);
        List<Player> homeForwardsOnPitch = Team.getPlayersByPosition(homeStartingPlayers, Position.Forward);
        List<Player> homeMidfieldersOnPitch = Team.getPlayersByPosition(homeStartingPlayers, Position.Midfielder);
        List<Player> homeDefendersOnPitch = Team.getPlayersByPosition(homeStartingPlayers, Position.Defender);
        allHomeFrontPlayersOnPitch = new ArrayList(homeDefendersOnPitch);
        allHomeFrontPlayersOnPitch.addAll(homeMidfieldersOnPitch);
        allHomeFrontPlayersOnPitch.addAll(homeForwardsOnPitch);

        List<Player> homeGoalKeeperOnBench = Team.getPlayersByPosition(homeSubPlayers, Position.GoalKeeper);
        List<Player> homeForwardsOnBench = Team.getPlayersByPosition(homeSubPlayers, Position.Forward);
        List<Player> homeMidfieldersOnBench = Team.getPlayersByPosition(homeSubPlayers, Position.Midfielder);
        List<Player> homeDefendersOnBench = Team.getPlayersByPosition(homeSubPlayers, Position.Defender);
        allHomeFrontPlayersOnBench = new ArrayList(homeDefendersOnBench);
        allHomeFrontPlayersOnBench.addAll(homeMidfieldersOnBench);
        allHomeFrontPlayersOnBench.addAll(homeForwardsOnBench);

        //////Away Team
        awayStartingPlayers = match.getAwayTeamStartingEleven();
        awaySubPlayers = match.getAwayTeamSubs();

        List<Player> awayGoalKeeperOnPitch = Team.getPlayersByPosition(awayStartingPlayers, Position.GoalKeeper);
        List<Player> awayForwardsOnPitch = Team.getPlayersByPosition(awayStartingPlayers, Position.Forward);
        List<Player> awayMidfieldersOnPitch = Team.getPlayersByPosition(awayStartingPlayers, Position.Midfielder);
        List<Player> awayDefendersOnPitch = Team.getPlayersByPosition(awayStartingPlayers, Position.Defender);
        allAwayFrontPlayersOnPitch = new ArrayList(awayDefendersOnPitch);
        allAwayFrontPlayersOnPitch.addAll(awayMidfieldersOnPitch);
        allAwayFrontPlayersOnPitch.addAll(awayForwardsOnPitch);

        List<Player> awayGoalKeeperOnBench = Team.getPlayersByPosition(awaySubPlayers, Position.GoalKeeper);
        List<Player> awayForwardsOnBench = Team.getPlayersByPosition(awaySubPlayers, Position.Forward);
        List<Player> awayMidfieldersOnBench = Team.getPlayersByPosition(awaySubPlayers, Position.Midfielder);
        List<Player> awayDefendersOnBench = Team.getPlayersByPosition(awaySubPlayers, Position.Defender);
        allAwayFrontPlayersOnBench = new ArrayList(awayDefendersOnBench);
        allAwayFrontPlayersOnBench.addAll(awayMidfieldersOnBench);
        allAwayFrontPlayersOnBench.addAll(awayForwardsOnBench);

        /////Event Player/////////////
        homeScoreSheet = new ArrayList();
        awayScoreSheet = new ArrayList();

        homePlayersIn = new ArrayList();
        homePlayersOut = new ArrayList();
        awayPlayersIn = new ArrayList();
        awayPlayersOut = new ArrayList();

        homeYellowCardedPlayers = new ArrayList();
        homeRedCardedPlayers = new ArrayList();
        awayYellowCardedPlayers = new ArrayList();
        awayRedCardedPlayers = new ArrayList();
    }

    public List<Player> getPlayersOnPitch(Team team) {
        if (team == match.getHomeTeam()) {
            return getHomePlayersOnPitch();
        }
        if (team == match.getAwayTeam()) {
            return getAwayPlayersOnPitch();
        }
        return null;
    }

    private List<Player> getHomePlayersOnPitch() {
        List<Player> players = new ArrayList();

        for (Player player : homeStartingPlayers) {
            if (!(homePlayersOut.contains(player)) && !(homeRedCardedPlayers.contains(player))) {
                players.add(player);
            }
        }
        for (Player player : homePlayersIn) {
            if (!(homePlayersOut.contains(player)) && !(homeRedCardedPlayers.contains(player))) {
                players.add(player);
            }
        }
        return players;

    }

    private List<Player> getAwayPlayersOnPitch() {
        List<Player> players = new ArrayList();

        for (Player player : awayStartingPlayers) {
            if (!(awayPlayersOut.contains(player)) && !(awayRedCardedPlayers.contains(player))) {
                players.add(player);
            }
        }
        for (Player player : awayPlayersIn) {
            if (!(awayPlayersOut.contains(player)) && !(awayRedCardedPlayers.contains(player))) {
                players.add(player);
            }
        }

        return players;

    }

    public List<Player> getPlayersOnPitchByPosition(Team team, Position position) {
        if (team == match.getHomeTeam()) {
            return getHomePlayersOnPitchByPosition(position);
        }
        if (team == match.getAwayTeam()) {
            return getAwayPlayersOnPitchByPosition(position);
        }
        return null;
    }

    public List<Player> getHomePlayersOnPitchByPosition(Position position) {
        return Team.getPlayersByPosition(getHomePlayersOnPitch(), position);
    }

    public List<Player> getAwayPlayersOnPitchByPosition(Position position) {
        return Team.getPlayersByPosition(getAwayPlayersOnPitch(), position);
    }

    public List<Player> getPlayersOnBench(Team team) {
        if (team == match.getHomeTeam()) {
            return getHomePlayersOnBench();
        }
        if (team == match.getAwayTeam()) {
            return getAwayPlayersOnBench();
        }
        return null;
    }

    public List<Player> getHomePlayersOnBench() {
        List<Player> players = new ArrayList();

        for (Player player : homeSubPlayers) {
            if (!(homePlayersIn.contains(player))) {
                players.add(player);
            }
        }

        return players;
    }

    public List<Player> getAwayPlayersOnBench() {
        List<Player> players = new ArrayList();

        for (Player player : awaySubPlayers) {
            if (!(awayPlayersIn.contains(player))) {
                players.add(player);
            }
        }

        return players;
    }

    public List<Player> getPlayersOnBenchByPosition(Team team, Position position) {
        if (team == match.getHomeTeam()) {
            return getHomePlayersOnBenchByPosition(position);
        }
        if (team == match.getAwayTeam()) {
            return getAwayPlayersOnBenchByPosition(position);
        }
        return null;
    }

    public List<Player> getHomePlayersOnBenchByPosition(Position position) {
        return Team.getPlayersByPosition(getHomePlayersOnBench(), position);
    }

    public List<Player> getAwayPlayersOnBenchByPosition(Position position) {
        return Team.getPlayersByPosition(getAwayPlayersOnBench(), position);
    }

    public List<Player> getGoalScorers(Team team) {
        if (team == match.getHomeTeam()) {
            return homeScoreSheet;
        }
        if (team == match.getAwayTeam()) {
            return awayScoreSheet;
        }
        return null;
    }

    public void addGoalScorer(Team team, Player player) {
        if (team == match.getHomeTeam()) {
            homeScoreSheet.add(player);
        }
        if (team == match.getAwayTeam()) {
            awayScoreSheet.add(player);
        }
    }

    public List<Player> getYellowCardedPlayers(Team team) {
        if (team == match.getHomeTeam()) {
            return homeYellowCardedPlayers;
        }
        if (team == match.getAwayTeam()) {
            return awayYellowCardedPlayers;
        }
        return null;
    }

    public void addYellowCardPlayer(Team team, Player player) {
        if (team == match.getHomeTeam()) {
            homeYellowCardedPlayers.add(player);
        }
        if (team == match.getAwayTeam()) {
            awayYellowCardedPlayers.add(player);
        }
    }

    public List<Player> getRedCardedPlayers(Team team) {
        if (team == match.getHomeTeam()) {
            return homeRedCardedPlayers;
        }
        if (team == match.getAwayTeam()) {
            return awayRedCardedPlayers;
        }
        return null;
    }

    public void addRedCardPlayer(Team team, Player player) {
        if (team == match.getHomeTeam()) {
            homeRedCardedPlayers.add(player);
        }
        if (team == match.getAwayTeam()) {
            awayRedCardedPlayers.add(player);
        }
    }

    public List<Player> getSubsInPlayers(Team team) {
        if (team == match.getHomeTeam()) {
            return homePlayersIn;
        }
        if (team == match.getAwayTeam()) {
            return awayPlayersIn;
        }
        return null;
    }

    public void addSubInPlayer(Team team, Player player) {
        if (team == match.getHomeTeam()) {
            homePlayersIn.add(player);
        }
        if (team == match.getAwayTeam()) {
            awayPlayersIn.add(player);
        }
    }

    public List<Player> getSubsOutPlayers(Team team) {
        if (team == match.getHomeTeam()) {
            return homePlayersOut;
        }
        if (team == match.getAwayTeam()) {
            return awayPlayersOut;
        }
        return null;
    }

    public void addSubOutPlayer(Team team, Player player) {
        if (team == match.getHomeTeam()) {
            homePlayersOut.add(player);
        }
        if (team == match.getAwayTeam()) {
            awayPlayersOut.add(player);
        }
    }
}
