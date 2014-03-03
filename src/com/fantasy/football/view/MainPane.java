/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.view;

import com.fantasy.football.model.FantasyTeam;
import com.fantasy.football.model.League;
import com.fantasy.football.model.Player;
import com.fantasy.football.model.Team;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Abdu
 */
public class MainPane extends JPanel {

    private JPanel topPanel;
    private JTabbedPane tabbedPane;
    private FantasyTeamPane fantasyTeamPane;
    private FixturePane fixturePane;
    private LeagueStandingPane leagueStandingPane;
    private League league;
    private FantasyTeam team;
    private JLabel teamName;
    private JLabel gameWeekPoints;
    private JLabel overallPoints;

    public MainPane(League league, FantasyTeam team) {
        this.league = league;
        this.team = team;
        initComponents();
    }

    public FantasyTeamPane getFantasyTeamPane() {
        return fantasyTeamPane;
    }

    public FixturePane getFixturePane() {
        return fixturePane;
    }

    public LeagueStandingPane getLeagueStandingPane() {
        return leagueStandingPane;
    }

    
    private void initComponents() {

        List<Player> allPlayers = new ArrayList<>();
        for (Team team : league.getTeams()) {
            allPlayers.addAll(team.getPlayers());
        }
        fantasyTeamPane = new FantasyTeamPane(allPlayers, team);

        topPanel = new JPanel(new FlowLayout());
        teamName = new JLabel("Fantasy Team: " + team.getTeamName());
        //gameWeekPoints = new JLabel("Game Week Points: " + team.getGameWeekPoints().get(league.getCurrentGameWeek()));
        overallPoints = new JLabel("Overall Points: " + team.getOverallPoints());
        
        topPanel.add(teamName);
        //topPanel.add(gameWeekPoints);
        topPanel.add(overallPoints);
        fixturePane = new FixturePane(league);
        leagueStandingPane = new LeagueStandingPane(league);
        
        
        tabbedPane = new JTabbedPane();
        tabbedPane.add("FIXTURES", fixturePane);
        tabbedPane.add("LEAGUE TABLE", leagueStandingPane);
        tabbedPane.add("PICK YOUR TEAM", fantasyTeamPane);
        //tabbedPane.add("PLAYERS STATS", img);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public void updateCalculatedPoints() {
        //gameWeekPoints.setText("Game Week Points: " + team.getGameWeekPoints().get(league.getCurrentGameWeek()));
        overallPoints.setText("Overall Points: " + team.getOverallPoints());
    }
}
