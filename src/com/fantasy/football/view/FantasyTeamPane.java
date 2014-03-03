/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.view;

import com.fantasy.football.model.FantasyTeam;
import com.fantasy.football.model.Player;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Abdu
 */
public class FantasyTeamPane extends JPanel {

    private List<Player> allPlayers;
    private FantasyTeam team;
    private PlayerTableModel playerModel;
    private FantasyTeamTableModel teamModel;
    private JTable playersTable;
    private JTable teamTable;
    private final int rows = 31;
    private JPanel playersPane;
    private JPanel teamPane;
    private JScrollPane playersScrollPane;
    private JButton confirm;

    public FantasyTeamPane(List<Player> allPlayers, FantasyTeam team) {
        this.allPlayers = allPlayers;
        this.team = team;
        initialize();
    }

    private void initialize() {
        playersPane = new JPanel(new BorderLayout(3, 3));
        playerModel = new PlayerTableModel(allPlayers);
        playersTable = new JTable(playerModel);
        TableRowSorter sorter
                = new TableRowSorter(playersTable.getModel());
//                sorter.setComparator(4,new PlayerFormSorter());
        playersTable.setRowSorter(sorter);
        playersScrollPane = new JScrollPane(playersTable, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Dimension d = playersTable.getPreferredSize();
        playersScrollPane.setPreferredSize(new Dimension(d.width, playersTable.getRowHeight() * rows));

        JPanel navigation = new JPanel(
                new FlowLayout(FlowLayout.CENTER));
        JButton next = new JButton(">");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int height = playersTable.getRowHeight() * (rows - 1);
                JScrollBar bar = playersScrollPane.getVerticalScrollBar();
                bar.setValue(bar.getValue() + height);
            }
        });
        JButton previous = new JButton("<");
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int height = playersTable.getRowHeight() * (rows - 1);
                JScrollBar bar = playersScrollPane.getVerticalScrollBar();
                bar.setValue(bar.getValue() - height);
            }
        });

        navigation.add(previous);
        navigation.add(next);
        playersPane.add(playersScrollPane, BorderLayout.CENTER);
        playersPane.add(navigation, BorderLayout.SOUTH);
        teamPane = new JPanel();
        teamModel = new FantasyTeamTableModel(team);
        teamTable = new JTable(teamModel);
        JScrollPane sp = new JScrollPane(teamTable);
        teamPane.add(sp);
        confirm = new JButton("Confirm Team");
        teamPane.add(confirm);
        setLayout(new BorderLayout());
        add(teamPane, BorderLayout.CENTER);
        add(playersPane, BorderLayout.EAST);

        playersTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = playersTable.getSelectedRow();
                    int modelSelectedRow = playersTable.convertRowIndexToModel(selectedRow);
                    int viewColumn = playersTable.getSelectedColumn();
                    int modelColumn = playersTable.convertColumnIndexToModel(viewColumn);
                    teamModel.addRow(playerModel.getPlayerAt(modelSelectedRow));
                }
            }
        });

        ////////////////
        teamTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = teamTable.getSelectedRow();
                if (e.getClickCount() == 2 && selectedRow < teamModel.getTeam().getPlayers().size()) {
                    teamModel.removeRowAt(selectedRow);
                }
            }
        });

        ///DnD
        teamTable.setDragEnabled(true);
        teamTable.setDropMode(DropMode.ON_OR_INSERT);
        teamTable.setTransferHandler(new TeamTableRowHandler(teamTable));
    }

    public void setAllPlayers(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public void addFantasyTeamListener(ActionListener fantasyTeamListener) {
        //next.addActionListener(fixtureListener);
        //previous.addActionListener(fixtureListener);
        confirm.addActionListener(fantasyTeamListener);
    }

    public void setFantasyTeamModel(FantasyTeam team) {
        teamModel.setTeam(team);
    }

    public FantasyTeam getFantasyTeamModel() {
        return teamModel.getTeam();
    }

    public void setPlayerModel(List<Player> allPlayer) {
        playerModel.setAllPlayers(allPlayers);
    }

    public List<Player> getPlayersModel() {
        return playerModel.getAllPlayers();
    }

}
