/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.view;

import com.fantasy.football.model.FantasyLeague;
import com.fantasy.football.model.FantasyTeam;
import com.fantasy.football.model.GameWeek;
import com.fantasy.football.model.League;
import com.fantasy.football.model.Player;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdu
 */
public class MainFrame extends JFrame {

    //private EPLController controller;
    private MainPane mainPane;
    private FantasyTeamPane fantasyTeamPane;
    private FixturePane fixturePane;
    private LeagueStandingPane leagueStandingPane;
    private League league;
    private FantasyLeague fantasyLeague;
    private FantasyTeam team;

    public MainFrame(League league, FantasyTeam team) {
        super("EPL Fantasy Football");
        this.league = league;
        this.team = team;
        initComponents();
    }

    public void displayGUI() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                initComponents();
            }
        });

    }

    private void initComponents() {
        mainPane = new MainPane(league, team);
        getContentPane().add(mainPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        fixturePane = mainPane.getFixturePane();
        fantasyTeamPane = mainPane.getFantasyTeamPane();
        leagueStandingPane = mainPane.getLeagueStandingPane();
//         controller.setMainView(this);       

    }

    /*   public void setController(EPLController controller) {
     //        this.controller = controller;
     //controller.setMainView(this);
     }
     */
    public void addFixtureListener(ActionListener fixtureListener) {
        fixturePane.addFixtureListener(fixtureListener);
    }

    public void setFixtureModel(GameWeek model) {
        fixturePane.setFixtureModel(model);
    }

    public GameWeek getFixtureModel() {
        return fixturePane.getFixtureModel();
    }

    public void addFantasyTeamListener(ActionListener fantasyTeamListener) {
        fantasyTeamPane.addFantasyTeamListener(fantasyTeamListener);
    }

    public void setFantasyTeamModel(FantasyTeam model) {
        fantasyTeamPane.setFantasyTeamModel(model);
        mainPane.updateCalculatedPoints();
    }

    public void setPlayerModel(List<Player> model) {
        fantasyTeamPane.setPlayerModel(model);
    }

    public FantasyTeam getFantasyTeamModel() {
        return fantasyTeamPane.getFantasyTeamModel();
    }

    public List<Player> getPlayerModel() {
        return fantasyTeamPane.getPlayersModel();
    }
    
    public void setLeagueStandingModel(Map leagueStanding) {
        leagueStandingPane.setLeagueStandingModel(leagueStanding);
    }
    public Map getLeagueStandingModel() {
        return leagueStandingPane.getLeagueStandingModel();
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void displayInfoMessage(String infoMessage) {
        JOptionPane.showMessageDialog(this, infoMessage);
    }

}
