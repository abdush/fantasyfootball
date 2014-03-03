/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.view;

import com.fantasy.football.model.FantasyTeam;
import com.fantasy.football.model.Player;
import com.fantasy.football.model.PlayerStatistics;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abdu
 */
public class FantasyTeamTableModel extends AbstractTableModel implements Reorderable {

    private FantasyTeam team;

    public FantasyTeamTableModel() {
    }

    public FantasyTeamTableModel(FantasyTeam team) {
        this.team = team;
    }

    public FantasyTeam getTeam() {
        return team;
    }

    public void setTeam(FantasyTeam team) {
        this.team = team;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return 15;
        //return team.getPlayers().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        if (team == null) {
            return value;
        }
        if (team.getPlayers().isEmpty()) {
            return value;
        }
        if (rowIndex >= team.getPlayers().size()) {
            return value;
        }

        Player player = team.getPlayers().get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = player.getPositions().get(0);
                break;
            case 1:
                value = player.getPlayerName();
                break;
            case 2:
                value = player.getLastWeekPoints();//gameweek points
                break;
            case 3:
                value = player.getTotalPoints();
                break;
            case 4:
                value = player.getTeam(); //next match
                break;
            default:
                throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }

        return value;

    }

    @Override
    public String getColumnName(int columnIndex) {
        String[] colNames = new String[]{"Pos", "Name", "WPoints", "TPoints", "NxtMatch"};
        return colNames[columnIndex];
    }

    public void addRow(Player data) {
        team.addPlayer(data);
            //this.fireTableDataChanged();
        //this.fireTableStructureChanged();
        fireTableRowsInserted(team.getPlayers().size() - 1, team.getPlayers().size() - 1);
    }

    public void removeRowAt(int row) {
        team.getPlayers().remove(row);
        //this.fireTableDataChanged();
        fireTableRowsDeleted(row - 1, row - 1);
    }

    /**
     * This will return the user at the specified row...
     *
     * @param row
     * @return
     */
    public Player getPlayerAt(int row) {
        if (row < team.getPlayers().size()) {
            return team.getPlayers().get(row);
        } else {
            return null;
        }
    }

    @Override
    public void reorder(int fromIndex, int toIndex) {
        /*    Object o = getDataVector().remove(from);
         getDataVector().add(to, o);
         fireTableDataChanged();
         }*/
        Player fromPlayer = team.getPlayers().get(fromIndex);
        Player toPlayer = team.getPlayers().get(toIndex);
        //if( fromIndex < toIndex ) { toIndex --; } // We now have one row less!
        team.getPlayers().set(toIndex, fromPlayer);
        team.getPlayers().set(fromIndex, toPlayer);
        fireTableDataChanged();

    }
}
