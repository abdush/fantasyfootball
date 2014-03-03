/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author Abdu
 */

public class Team {
    
    private int teamNo;
    private String teamName;
    private String Staduim;
    private Formation formation;
    private int teamRating;
    private double avgHomeGoals;
    private double avgAwayGoals;
    private List<Player> players;

     

    public Team() {
        
    }

    @XmlAttribute(name ="name")
    public String getTeamName() {
        return teamName;
    }

    @XmlAttribute(name ="id")
    public int getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(int teamNo) {
        this.teamNo = teamNo;
    }

    public String getStaduim() {
        return Staduim;
    }

    @XmlElementWrapper(name = "players")
    @XmlElement(name = "player", type = Player.class)
    public List<Player> getPlayers() {
        return players;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setStaduim(String Staduim) {
        this.Staduim = Staduim;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        for(Player player: players)
            player.setTeam(this);
    }

    @XmlAttribute(name="teamRating")
    public int getTeamRating() {
        return teamRating;
    }

    @XmlAttribute(name="AVGHG")
    public double getAvgHomeGoals() {
        return avgHomeGoals;
    }

    @XmlAttribute(name="AVGAG")
    public double getAvgAwayGoals() {
        return avgAwayGoals;
    }

    public void setTeamRating(int teamRating) {
        this.teamRating = teamRating;
    }

    public void setAvgHomeGoals(double avgHomeGoals) {
        this.avgHomeGoals = avgHomeGoals;
    }

    public void setAvgAwayGoals(double avgAwayGoals) {
        this.avgAwayGoals = avgAwayGoals;
    }

    @XmlAttribute(name="formation")
    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
    
    public List<Player> getPlayersByPosition(Position position) {
        List<Player> players = new ArrayList<>();
        for (Player player: this.players) {
            if (player.getPositions().contains(position))
                players.add(player);
        }
        Collections.sort(players, new PlayerFormSorter());
        return players;
    }
    
    public List<Player> getAvailablePlayers() {
        List<Player> players = new ArrayList<>();
        for (Player player: this.players) {
            if (player.isAvailable())
                players.add(player);
        }
        return players;
    }
     
    public List<Player> getAvailablePlayersByPosition(Position position) {
        List<Player> players = new ArrayList<>();
        for (Player player: this.players) {
            if (player.isAvailable() && (player.getPositions().contains(position)))
                players.add(player);
        }
        Collections.sort(players, new PlayerFormSorter());
        return players;
    }
    
    public static List<Player> getPlayersByPosition(List<Player> players, Position position) {
        List<Player> playersInPosition = new ArrayList<>();
        for (Player player: players) {
            if (player.getPositions().contains(position))
                playersInPosition.add(player);
        }
        //Collections.sort(players, new PlayerFormSorter());
        return playersInPosition;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.teamNo;
        hash = 23 * hash + Objects.hashCode(this.teamName);
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
        final Team other = (Team) obj;
        if (this.teamNo != other.teamNo) {
            return false;
        }
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        return true;
    }
        
    
    @Override
    public String toString() {
        return teamName ;
    }
    
}
