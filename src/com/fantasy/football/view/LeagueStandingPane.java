/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.view;

import com.fantasy.football.model.GameWeek;
import com.fantasy.football.model.League;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Abdu
 */
public class LeagueStandingPane extends JPanel {

    private League league;
    private LeagueTableModel leagueTableModel;
    private JTable leagueTable;
    private JPanel leagueStandingPane;
    private JScrollPane scrollPane;
    private JPanel navigation;
    private JButton next;
    private JButton previous;
    private JButton simulate;

    private int roundId = 1;

    public LeagueStandingPane(League league) {
        this.league = league;
        initialize();
    }

    private void initialize() {
        leagueStandingPane = new JPanel(new BorderLayout(3, 3));
        leagueTableModel = new LeagueTableModel(league.getSortedLeagueTable());
        leagueTable = new JTable(leagueTableModel);

        scrollPane = new JScrollPane(leagueTable);
        //Dimension d = leagueTable.getPreferredSize();
        //scrollPane.setPreferredSize(new Dimension(d.width, leagueTable.getRowHeight() * rows));

        leagueStandingPane.add(scrollPane, BorderLayout.CENTER);
//        leagueStandingPane.add(navigation, BorderLayout.SOUTH);
        add(leagueStandingPane);

    }

   public void addLeagueStandingListener(ActionListener fixtureListener) {
        //next.addActionListener(fixtureListener);
        //previous.addActionListener(fixtureListener);
        //simulate.addActionListener(fixtureListener);
    }

    public void setLeagueStandingModel(Map leagueTable) {
        leagueTableModel.setLeagueTable(leagueTable);
    }

    public Map getLeagueStandingModel() {
        return leagueTableModel.getLeagueTable();
    }

}
