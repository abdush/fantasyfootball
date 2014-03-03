/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Abdu
 */
public class Player {
    
    private String playerName;
    private int playerNumber;
    private List<Position> positions;
    private Position role;
    private double playerValue;
    private int totalPoints;
    private boolean isAvailable = true;
    private Team team;
    private Map playerStats;

    public Player() {
    }

    @XmlAttribute(name="name")
    public String getPlayerName() {
        return playerName;
    }

    @XmlAttribute(name="number")
    public int getPlayerNumber() {
        return playerNumber;
    }

    @XmlAttribute(name="position")
    public List<Position> getPositions() {
        return positions;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @XmlAttribute(name="value")
    public double getPlayerValue() {
        return playerValue;
    }

    @XmlAttribute(name="totalPoints")
    public int getTotalPoints() {
        return totalPoints;
    }

    public void setPlayerValue(double playerValue) {
        this.playerValue = playerValue;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void addPoints(int addedPoints) {
        totalPoints += addedPoints;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.playerName);
        hash = 97 * hash + this.playerNumber;
        hash = 97 * hash + Objects.hashCode(this.team);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.playerName, other.playerName)) {
            return false;
        }
        if (this.playerNumber != other.playerNumber) {
            return false;
        }
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        return true;
    }

    public Map getPlayerStats() {
        return playerStats;
    }

    public void addPlayerStats(PlayerStatistics ps) {
        if(playerStats == null)
            playerStats = new TreeMap();
        playerStats.put(ps.getRoundId(), ps);
    }
    
    public PlayerStatistics getPlayerStats(int roundId) {
        return (PlayerStatistics)playerStats.get(roundId);
    }
    public int getLastWeekPoints() {
        if(playerStats != null) {
            PlayerStatistics ps = (PlayerStatistics)playerStats.get(playerStats.size());
            if(ps != null) {
                return ps.getPoints();
            }
        }
        return 0;
        
    }
    
    
    @Override
    public String toString() {
        return playerName + " " + positions;
    }
}
