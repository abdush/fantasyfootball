/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.model;

/**
 *
 * @author Abdu
 */
public class PlayerStatistics {

    private Player player;
    private int roundId;
    private int matchId;
    private int minuteIn;
    private int minuteOut;
    private int minutesPlayed;
    private int goalsScored;
    private int assists;
    private int goalsConceded;
    private int cleanSheets;
    private int yellowCards;
    private int redCards;
    private int points;

    public PlayerStatistics(Player player, int roundId, int matchId) {
        this.player = player;
        this.roundId = roundId;
        this.matchId = matchId;
        this.minuteIn = 0;
        this.minuteOut = 0;
        minutesPlayed = minuteOut - minuteIn;
    }

    public Player getPlayer() {
        return player;
    }

    public int getRoundId() {
        return roundId;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public int getCleanSheets() {
        return cleanSheets;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public int getPoints() {
        return points;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public void setGoalsScored() {
        goalsScored++;
    }

    public void setAssists() {
        assists++;
    }

    public int getMinuteIn() {
        return minuteIn;
    }

    public int getMinuteOut() {
        return minuteOut;
    }

    public void setMinuteIn(int minuteIn) {
        this.minuteIn = minuteIn;
        minutesPlayed = this.minuteOut - this.minuteIn;
    }

    public void setMinuteOut(int minuteOut) {
        this.minuteOut = minuteOut;
        minutesPlayed = this.minuteOut - this.minuteIn;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public void setCleanSheets(int cleanSheets) {
        this.cleanSheets = cleanSheets;
    }

    public void setYellowCards() {
        yellowCards++;
    }

    public void setRedCards() {
        redCards++;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addConcededGoal() {
        goalsConceded++;
    }

    public void updatePoints() {
        points = 0;
        if (minutesPlayed > 0) {
            //For playing up to 60 minutes	1 point, 60 min or more 2 points
            if (minutesPlayed < 60) {
                points += 1;
            } else if (minutesPlayed >= 60) {
                points += 2;
                if (goalsConceded == 0) {
                    if (player.getPositions().get(0).equals(Position.GoalKeeper) || player.getPositions().get(0).equals(Position.Defender)) {
                        points += 4;
                    } else if (player.getPositions().get(0).equals(Position.Midfielder)) {
                        points += 1;
                    }
                }
            }

            //for each goal scored 6 points if GK or DEF, 5 if MID, 4 if FWD 
            if (goalsScored > 0) {
                if (player.getPositions().get(0).equals(Position.GoalKeeper) || player.getPositions().get(0).equals(Position.Defender)) {
                    points += goalsScored * 6;
                } else if (player.getPositions().get(0).equals(Position.Midfielder)) {
                    points += goalsScored * 5;
                } else {
                    points += goalsScored * 4;
                }
            }
            //3 points for each assist
            points += assists * 3;

            //-1 point for every 2 goals conceded
            //difficult....
            points += (goalsConceded / 2) * -1;

            //-1 for every yellow card
            points += yellowCards * -1;

            //-3 for red card
            redCards += redCards * -3;
        }
        //add the game points to the totalpoints
        player.addPoints(points);

        player.addPlayerStats(this);

    }

    @Override
    public String toString() {
        return "PlayerStatistics{" + "player=" + player + ", roundId=" + roundId + ", matchId=" + matchId + ", minuteIn=" + minuteIn + ", minuteOut=" + minuteOut + ", minutesPlayed=" + minutesPlayed + ", goalsScored=" + goalsScored + ", assists=" + assists + ", goalsConceded=" + goalsConceded + ", cleanSheets=" + cleanSheets + ", yellowCards=" + yellowCards + ", redCards=" + redCards + ", points=" + points + ", TP=" + player.getTotalPoints() + '}';
    }

}
