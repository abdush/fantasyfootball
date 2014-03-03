/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football;

import com.fantasy.football.controller.EPLController;
import com.fantasy.football.controller.FantasyTeamController;
import com.fantasy.football.helper.FixtureGenerator;
import com.fantasy.football.dao.XMLParser;
import com.fantasy.football.model.FantasyLeague;
import com.fantasy.football.model.FantasyTeam;
import com.fantasy.football.model.League;
import com.fantasy.football.view.MainFrame;

/**
 *
 * @author Abdu
 */
public class MainApp {
    
    
    /**
     * @param args the command line arguments
     * MVC Pattern
     * Loads the League (team and players) from XML File (Model)
     * Generate a random fixture.
     * Creates a new Fantasy League and Fantasy team for the user (Model)
     * Loads the GUI (view)
     * creates the controllers for handling EPL simulation and Fantasy team selection
     */
    public static void main(String[] args) {
        
        League league = XMLParser.loadFromXmlFile();
        league.setGameWeeks(FixtureGenerator.generateFixture(league));
        league.initializeLeagueTable();
        
        FantasyLeague fantasyFootball = new FantasyLeague();
        FantasyTeam fantasyTeam = new FantasyTeam("MY_TEAM");
        fantasyFootball.addTeam(fantasyTeam);
        
        MainFrame mainView = new MainFrame(league, fantasyTeam);
        
        EPLController controller = new EPLController(league, fantasyFootball, fantasyTeam);
        controller.setMainView(mainView);
        
        FantasyTeamController fantasyController = new FantasyTeamController(league, fantasyFootball, fantasyTeam);
        fantasyController.setMainView(mainView);
                
    }
    
}
