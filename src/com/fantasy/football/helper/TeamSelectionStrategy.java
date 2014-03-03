/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.helper;

import com.fantasy.football.model.Formation;
import com.fantasy.football.model.Player;
import com.fantasy.football.model.Team;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdu
 */
public abstract class TeamSelectionStrategy {
    
    public static final int NUM_OF_STARTING_PLAYERS = 11;
    public static final int NUM_OF_SUBSTITUTE_PLAYERS = 7;
    
    protected Team team;
    protected List<Player> startingEleven;
    protected List<Player> subPlayers;
    protected List<Player> squad;
    protected Formation tactic;
    protected List<List<Player>> teamSquad = new ArrayList(2);

    public TeamSelectionStrategy() {
    }

    public TeamSelectionStrategy(Formation tactic) {
        this.tactic = tactic;
    }
/*
    public TeamSelectionStrategy(Team team, Formation tactic) {
        this.team = team;
        this.tactic = tactic;
    }

    public void setTeam(Team team) {
        this.team = team;
    }*/

    public void setTactic(Formation tactic) {
        this.tactic = tactic;
    }
    
    
    public abstract List<Player> getSquad(Team team);
    
/*    public abstract List<List<Player>> getSquad(Team team);

    public abstract List<Player> getStartingEleven();

    public abstract List<Player> getSubstitutions();
*/    
}
