package com.fantasy.football.view;

import com.fantasy.football.model.Team;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static javax.management.Query.match;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abdu
 */
public class LeagueTableModel extends AbstractTableModel {

    
    private Map leagueTable;

    public LeagueTableModel(Map leagueTable) {
        this.leagueTable = leagueTable;
    }

    public void setLeagueTable(Map leagueTable) {
        this.leagueTable = leagueTable;
        //fireTableDataChanged();
        fireTableStructureChanged();
    }

    public Map getLeagueTable() {
        return leagueTable;
    }

    
    @Override
    public int getRowCount() {
        return leagueTable.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "";
        Team team = (Team) leagueTable.keySet().toArray()[rowIndex];
        Integer standing = (Integer) leagueTable.get(team);
//        Integer standing = (Integer) leagueTable.entrySet().toArray()[rowIndex];
       
        switch (columnIndex) {
            case 0:
                value = team.getTeamName();
                break;
            case 1:
                value = standing;
                break;
            
            default:
                throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }

        return value;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String[] colNames = new String[] {"Team" , "Points"};
        return colNames[columnIndex];
    }
    /*
    public Match getMatchAt(int row) {
        return gameWeek.getMatches().get(row);

    }*/
}
