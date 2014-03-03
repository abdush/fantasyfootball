/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.view;

import com.fantasy.football.model.GameWeek;
import com.fantasy.football.model.League;
import com.fantasy.football.model.Match;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Abdu
 */
public class FixturePane extends JPanel {

    private League league;
    private FixtureTableModel fixtureTableModel;
    private JTable fixtureTable;
    private JPanel gameweekPane;
    private JScrollPane scrollPane;
    private JPanel navigation;
    private JButton next;
    private JButton previous;
    private JButton simulate;

    private int roundId = 1;

    public FixturePane(League league) {
        this.league = league;
        initialize();
    }

    private void initialize() {
        gameweekPane = new JPanel(new BorderLayout(3, 3));
        fixtureTableModel = new FixtureTableModel(league.getNextRound());
        fixtureTable = new JTable(fixtureTableModel);

        scrollPane = new JScrollPane(fixtureTable);
        //Dimension d = fixtureTable.getPreferredSize();
        //scrollPane.setPreferredSize(new Dimension(d.width, fixtureTable.getRowHeight() * rows));

        navigation = new JPanel(new FlowLayout(FlowLayout.CENTER));
        next = new JButton(">");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int nxtRnd = ++roundId;
                if (nxtRnd > league.getNumberOfRounds()) {
                    nxtRnd = league.getNumberOfRounds();
                    roundId = league.getNumberOfRounds();
                }
                GameWeek gw = league.getGameWeek(fixtureTableModel.getGameWeek().getRoundID() + 1);
                if (gw != null) {
                    fixtureTableModel.setGameWeek(gw);
                }
            }
        });

        previous = new JButton("<");
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int nxtRnd = --roundId;
                if (nxtRnd < 1) {
                    nxtRnd = 1;
                    roundId = 1;
                }
                GameWeek gw = league.getGameWeek(fixtureTableModel.getGameWeek().getRoundID() - 1);
                if (gw != null) {
                    fixtureTableModel.setGameWeek(gw);
                }
            }
        });

        simulate = new JButton("Simulte next Round");

        navigation.add(previous);
        navigation.add(next);
        navigation.add(simulate);

        gameweekPane.add(scrollPane, BorderLayout.CENTER);
        gameweekPane.add(navigation, BorderLayout.SOUTH);
        add(gameweekPane);

        ///Listener for match double click
        fixtureTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = fixtureTable.getSelectedRow();
                    //int modelSelectedRow = playersTable.convertRowIndexToModel(selectedRow);
                    Match selectedMatch = fixtureTableModel.getMatchAt(selectedRow);
                    if (selectedMatch.isMatchPlayed()) {
                        showMatchDialog(selectedMatch);
                    }
                }
            }
        });
    }

    public void showMatchDialog(Match match) {
        JDialog matchDialog = new JDialog(new JFrame(), "Match Events", true);
        //JLabel label = new JLabel("<html>" + match.previewMatch() + "</html>");
        //label.setHorizontalAlignment(JLabel.CENTER);
        //label.setPreferredSize(new Dimension(400, 400));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setCursor(null);
        textArea.setOpaque(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(600, 400));
        textArea.setText(match.previewMatch());
        matchDialog.add(textArea);
        matchDialog.pack();
        if (matchDialog.isShowing()) {
            matchDialog.toFront();
        } else {
            matchDialog.setVisible(true);
        }
    }

    public void addFixtureListener(ActionListener fixtureListener) {
        //next.addActionListener(fixtureListener);
        //previous.addActionListener(fixtureListener);
        simulate.addActionListener(fixtureListener);
    }

    public void setFixtureModel(GameWeek gameWeek) {
        fixtureTableModel.setGameWeek(gameWeek);
    }

    public GameWeek getFixtureModel() {
        return fixtureTableModel.getGameWeek();
    }

}
