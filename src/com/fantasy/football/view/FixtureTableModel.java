/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.view;

import com.fantasy.football.model.GameWeek;
import com.fantasy.football.model.Match;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abdu
 */
public class FixtureTableModel extends AbstractTableModel {

    
    private GameWeek gameWeek;

    public FixtureTableModel(GameWeek gameWeek) {
        this.gameWeek = gameWeek;
    }

    public void setGameWeek(GameWeek gameWeek) {
        this.gameWeek = gameWeek;
        //fireTableDataChanged();
        fireTableStructureChanged();
    }

    public GameWeek getGameWeek() {
        return gameWeek;
    }
    @Override
    public int getRowCount() {
        return gameWeek.getMatches().size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Match match = gameWeek.getMatches().get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = gameWeek.getDate();
                break;
            case 1:
                value = match.getHomeTeam();
                break;
            case 2:
                if (match.isMatchPlayed()) {
                    value = match.getHomeScore();
                }
                break;
            case 3:
                value = "vs";
                break;
            case 4:
                if (match.isMatchPlayed()) {
                    value = match.getAwayScore();
                }
                break;
            case 5:
                value = match.getAwayTeam();
                break;
            default:
                throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }

        return value;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String[] colNames = new String[] {"GameWeek" + gameWeek.getRoundID() , "Home", "", "", "", "Away"};
        return colNames[columnIndex];
    }
    
    public Match getMatchAt(int row) {
        return gameWeek.getMatches().get(row);

    }
}
