/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fantasy.football.helper;

import com.fantasy.football.model.Formation;
import com.fantasy.football.model.Player;
import com.fantasy.football.model.Position;
import com.fantasy.football.model.Team;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Abdu
 */
public class BestTeamTactic extends TeamSelectionStrategy {

    public BestTeamTactic() {
        super();
    }

    public BestTeamTactic(Formation tactic) {
        super(tactic);
        //makeSquadSelection();
    }
    /*
     public BestTeamTactic(Team team, Formation tactic) {
     super(team, tactic);
     //makeSquadSelection();
     }

     */

    @Override
    public List<Player> getSquad(Team team) {
        if (tactic == null) {
            this.tactic = team.getFormation();
        }
        this.team = team;
        makeSquadSelection();
        return squad;
    }
    /*
     @Override
     public List<Player> getStartingEleven() {
     if (startingEleven == null) {
     makeSquadSelection();
     }
     return startingEleven;
     }

     @Override
     public List<Player> getSubstitutions() {
     if (subPlayers == null) {
     makeSquadSelection();
     }
     return subPlayers;
     }
     */

    private void makeSquadSelection() {

        Random rand = new Random();
        Set<Player> squad = new LinkedHashSet();

        if (tactic == null) {
            tactic = team.getFormation();
        }

        List<Player> goalkeepers = team.getAvailablePlayersByPosition(Position.GoalKeeper);
        List<Player> defenders = team.getAvailablePlayersByPosition(Position.Defender);
        List<Player> midfielders = team.getAvailablePlayersByPosition(Position.Midfielder);
        List<Player> forwarders = team.getAvailablePlayersByPosition(Position.Forward);
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.addAll(defenders);
        allPlayers.addAll(midfielders);
        allPlayers.addAll(forwarders);

        squad.add(goalkeepers.get(0));
        //goalkeepers.remove(0);

        for (int i = 0; i < tactic.getDefenders(); i++) {
            squad.add(defenders.get(i));
            //System.out.println(i + " " + defenders.get(i));
            //defenders.remove(i);

        }

        for (int i = 0; i < tactic.getMidfielders(); i++) {
            squad.add(midfielders.get(i));
            //midfielders.remove(i);
        }

        for (int i = 0; i < tactic.getForwarders(); i++) {
            squad.add(forwarders.get(i));
            //forwarders.remove(i);
        }

        if (squad.size() != NUM_OF_STARTING_PLAYERS) {
            for (int i = squad.size(); i < NUM_OF_STARTING_PLAYERS; i++) {
                int selected = rand.nextInt(midfielders.size());
                squad.add(midfielders.get(tactic.getMidfielders() + i));
                //midfielders.remove(selected);
            }
        }

        int numOfTotalPlayers = NUM_OF_STARTING_PLAYERS + NUM_OF_SUBSTITUTE_PLAYERS;
        squad.add(goalkeepers.get(1));

        int defSub = defenders.size() - tactic.getDefenders();
        int midSub = midfielders.size() - tactic.getMidfielders();
        int fwdSub = forwarders.size() - tactic.getForwarders();

        int defSelected = 0;
        for (int i = tactic.getDefenders(); i < 2 + tactic.getDefenders() && defSub > 0; i++, defSub--) {
            squad.add(defenders.get(i));
            defSelected++;
        }

        int midSelected = 0;
        for (int i = tactic.getMidfielders(); i < 2 + tactic.getMidfielders() && midSub > 0; i++, midSub--) {
            squad.add(midfielders.get(i));
            midSelected++;
        }

        int fwdSelected = 0;
        for (int i = tactic.getForwarders(); i < 2 + tactic.getForwarders() && fwdSub > 0; i++, fwdSub--) {
            squad.add(forwarders.get(i));
            fwdSelected++;
        }

        if (squad.size() != numOfTotalPlayers && (defSub + midSub + fwdSub) > NUM_OF_SUBSTITUTE_PLAYERS - 1) {
            for (int i = squad.size(); i < numOfTotalPlayers; i++) {
                int selected = rand.nextInt(midfielders.size());
                squad.add(allPlayers.get(selected));
                //midfielders.remove(selected);
            }
        }
        /*
         for (int i = 0; i < 2; i++) {
         int selected = tactic.getDefenders() + i;
         squad.add(defenders.get(selected));
         selected = tactic.getMidfielders() + i;
         squad.add(midfielders.get(selected));
         selected = tactic.getForwarders() + i;
         squad.add(forwarders.get(selected));
         }
         */
        this.squad = new ArrayList(squad);
        startingEleven = this.squad.subList(0, NUM_OF_STARTING_PLAYERS);
        subPlayers = this.squad.subList(NUM_OF_STARTING_PLAYERS, squad.size());
        teamSquad.add(0, startingEleven);
        teamSquad.add(1, subPlayers);
    }

}
