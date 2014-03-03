/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.controller;

import com.fantasy.football.model.FantasyLeague;
import com.fantasy.football.model.FantasyTeam;
import com.fantasy.football.model.League;
import com.fantasy.football.view.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdu
 */
public class FantasyTeamController {

    private League league;
    private FantasyLeague fantasyFootball;
    private FantasyTeam fantasyTeam;
    private MainFrame mainView;

    public FantasyTeamController(League league, FantasyLeague fantasyFootball, FantasyTeam fantasyTeam) {
        this.league = league;
        this.fantasyFootball = fantasyFootball;
        this.fantasyTeam = fantasyTeam;
    }
    
    
    
    public void setMainView(MainFrame mainView) {
        this.mainView = mainView;
        this.mainView.addFantasyTeamListener(new FantasyTeamListener());
    }


    public void addFantasyTeam(FantasyTeam team) {
        fantasyFootball.addTeam(team);
    }

    public void updateFantasyTeam(FantasyTeam team) {
        
    }

    private class FantasyTeamListener implements ActionListener {

        public FantasyTeamListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            fantasyTeam = mainView.getFantasyTeamModel();
            if(fantasyTeam.getPlayers().size() != FantasyTeam.NUM_OF_PLAYERS) {
                mainView.displayErrorMessage("Please Pickup all the 15 players for your team!!");
                return;
            }
            
            boolean validSquad = fantasyTeam.setSquad(fantasyTeam.getPlayers().subList(0, 11), fantasyTeam.getPlayers().subList(11, 15));
            if (!validSquad)
            {
                mainView.displayErrorMessage("Please Make sure you follow the rules for your team selection!!");
                return;
            }
            //mainView.setFixtureModel(league.getNextRound());            
            mainView.displayInfoMessage("Team Selected!!");
        }
    }

}
