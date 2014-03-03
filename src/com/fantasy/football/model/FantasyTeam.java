/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Abdu
 */
public class FantasyTeam {

    private String teamName;
    private List<Player> players;
    private List<Player> startingEleven;
    private List<Player> subPlayers;
    private Player captain;
    private Player vCaptain;
    private double teamValue;
    private double availableMoney;
    private HashMap gameWeekPoints;
    private int overallPoints;
    private int roundId;
    private HashMap gameWeekTransfers;

    private static final int INITIAL_BUDGET = 100;
    public static final int NUM_OF_PLAYERS = 15;
    private static final int NUM_OF_STARTING_PLAYERS = 11;
    private static final int NUM_OF_SUB_PLAYERS = 4;
    private static final int NUM_OF_GK_PLAYERS = 2;
    private static final int NUM_OF_DEF_PLAYERS = 5;
    private static final int NUM_OF_MID_PLAYERS = 5;
    private static final int NUM_OF_FWD_PLAYERS = 3;
    private static final int MIN_STARTING_GK = 1;
    private static final int MIN_STARTING_DEF = 3;
    private static final int MIN_STARTING_FWD = 1;
    public static final int MAX_PLAYERS_PER_EPL = 3;

    public FantasyTeam(String teamName) {
        this.teamName = teamName;
        players = new ArrayList<>(NUM_OF_PLAYERS);
        startingEleven = new ArrayList<>(NUM_OF_STARTING_PLAYERS);
        subPlayers = new ArrayList<>(NUM_OF_SUB_PLAYERS);
        gameWeekPoints = new HashMap();
        gameWeekTransfers = new HashMap();
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public double getTeamValue() {
        return teamValue;
    }

    public double getAvailableMoney() {
        return availableMoney;
    }

    public HashMap getGameWeekPoints() {
        return gameWeekPoints;
    }

    public int getOverallPoints() {
        return overallPoints;
    }

    public int getRoundId() {
        return roundId;
    }

    public HashMap getGameWeekTransfers() {
        return gameWeekTransfers;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setTeamValue(double teamValue) {
        this.teamValue = teamValue;
    }

    public void setAvailableMoney(double availableMoney) {
        this.availableMoney = availableMoney;
    }

    public void setGameWeekPoints(HashMap gameWeekPoints) {
        this.gameWeekPoints = gameWeekPoints;
    }

    public void setOverallPoints(int overallPoints) {
        this.overallPoints = overallPoints;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public void setGameWeekTransfers(HashMap gameWeekTransfers) {
        this.gameWeekTransfers = gameWeekTransfers;
    }

    public void addPlayer(Player player) {
        if (players.contains(player)) {
            return;
        }
        players.add(player);
        //   if (!checkPlayersInEPL() || !checkTeamSquad())
        //     players.remove(player);

    }

    public List<Player> getStartingEleven() {
        return startingEleven;
    }

    public List<Player> getSubPlayers() {
        return subPlayers;
    }

    public Player getCaptain() {
        return captain;
    }

    public Player getvCaptain() {
        return vCaptain;
    }

    public boolean setSquad(List<Player> startingEleven, List<Player> subPlayers) {
        List<Player> tempStarting = this.startingEleven;
        List<Player> tempSub = this.subPlayers;
        setStartingEleven(startingEleven);
        setSubPlayers(subPlayers);
        if (checkPlayersInEPL() && checkTeamSquad() && checkStartingSquad()) {
            tempStarting = null;
            tempSub = null;
            return true;
        } else {
            this.startingEleven = tempStarting;
            this.subPlayers = tempSub;
            return false;
        }

    }

    public void setStartingEleven(List<Player> startingEleven) {

        this.startingEleven = startingEleven;

    }

    public void setSubPlayers(List<Player> subPlayers) {

        this.subPlayers = subPlayers;

    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public void setvCaptain(Player vCaptain) {
        this.vCaptain = vCaptain;
    }

    public boolean checkTeamSquad() {
        if (Team.getPlayersByPosition(players, Position.GoalKeeper).size() != NUM_OF_GK_PLAYERS) {
            return false;
        }
        if (Team.getPlayersByPosition(players, Position.Defender).size() != NUM_OF_DEF_PLAYERS) {
            return false;
        }
        if (Team.getPlayersByPosition(players, Position.Midfielder).size() != NUM_OF_MID_PLAYERS) {
            return false;
        }
        if (Team.getPlayersByPosition(players, Position.Forward).size() != NUM_OF_FWD_PLAYERS) {
            return false;
        }

        return true;
    }

    public boolean checkStartingSquad() {
        if (startingEleven.size() != NUM_OF_STARTING_PLAYERS) {
            return false;
        }
        if (Team.getPlayersByPosition(startingEleven, Position.GoalKeeper).size() != MIN_STARTING_GK) {
            return false;
        }
        if (Team.getPlayersByPosition(startingEleven, Position.Defender).size() < MIN_STARTING_DEF) {
            return false;
        }
        if (Team.getPlayersByPosition(startingEleven, Position.Forward).size() < MIN_STARTING_FWD) {
            return false;
        }
        if (subPlayers.size() != NUM_OF_SUB_PLAYERS) {
            return false;
        }
        return true;
    }

    public boolean checkPlayersInEPL() {
        for (Player player : players) {
            if (countPlayerinEPLTeam(player.getTeam()) > MAX_PLAYERS_PER_EPL) {
                return false;
            }
        }
        return true;
    }

    public int countPlayerinEPLTeam(Team team) {
        int count = 0;
        for (Player player : players) {
            if (player.getTeam().equals(team)) {
                count++;
            }
        }
        return count;
    }

    public void updateGameWeekPoints(int roundId) {
        if (!startingEleven.isEmpty()) {
            int GWPoints = 0;
            for (Player player : startingEleven) {
                PlayerStatistics ps = player.getPlayerStats(roundId);
                GWPoints += ps.getPoints();
            }
            overallPoints += GWPoints;
            this.gameWeekPoints.put(roundId, GWPoints);
        }
    }

    @Override
    public String toString() {
        return "FantasyTeam{" + "teamName=" + teamName + ", players=" + players + ", startingEleven=" + startingEleven + ", subPlayers=" + subPlayers + ", captain=" + captain + ", vCaptain=" + vCaptain + ", teamValue=" + teamValue + ", availableMoney=" + availableMoney + ", gameWeekPoints=" + gameWeekPoints + ", overallPoints=" + overallPoints + ", roundId=" + roundId + ", gameWeekTransfers=" + gameWeekTransfers + '}';
    }

}
