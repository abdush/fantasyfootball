/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.view;

import com.fantasy.football.model.Player;
import com.fantasy.football.model.Position;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abdu
 */
public class PlayerTableModel extends AbstractTableModel {

    private List<Player> allPlayers;
    private String[] colNames = new String[] {"Pos", "Name", "Team", "Value", "TPoints"};
    
    public PlayerTableModel(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
        fireTableDataChanged();
    }

    
    @Override
    public int getRowCount() {
        return allPlayers.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        Player player = allPlayers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = player.getPositions().get(0);
                break;
            case 1:
                value = player.getPlayerName();
                break;
            case 2:
                value = player.getTeam().getTeamName();
                break;
            case 3:
                value = player.getPlayerValue();
                break;
            case 4:
                value = player.getTotalPoints();
                break;
            default:
                throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }

        return value;

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
        /*Class<?> className;
        switch (columnIndex) {
            case 0:
                className = Position.class;
                break;
            case 1:
            case 2:
                className = String.class;
                break;
            case 3:
                className = Double.class;
                break;
            case 4:
                className = Integer.class;
                break;
            default:
                throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }
        return className;*/
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }
    
    /**
     * This will return the user at the specified row...
     *
     * @param row
     * @return
     */
    public Player getPlayerAt(int row) {
        return allPlayers.get(row);
    }

}
