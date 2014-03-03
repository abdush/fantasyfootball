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
public class ReserveTeamTactic extends TeamSelectionStrategy {

    public ReserveTeamTactic() {
        super();
    }

    public ReserveTeamTactic(Formation tactic) {
        super(tactic);
        //makeSquadSelection();
    }

    /*    public ReserveTeamTactic(Team team, Formation tactic) {
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

     @Override
     public void setTeam(Team team) {
     this.team = team;
     makeSquadSelection();
     }
     */

    private void makeSquadSelection() {

        Random rand = new Random();
        Set<Player> squadSet = new LinkedHashSet();

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

        squadSet.add(goalkeepers.get(1));
        //goalkeepers.remove(0);

        for (int i = 1; i <= tactic.getDefenders(); i++) {
            squadSet.add(defenders.get(defenders.size() - i));
            //System.out.println(i + " " + defenders.get(i))s;
            //defenders.remove(i);

        }

        for (int i = 1; i <= tactic.getMidfielders(); i++) {
            squadSet.add(midfielders.get(midfielders.size() - i));
            //midfielders.remove(i);
        }

        for (int i = 1; i <= tactic.getForwarders(); i++) {
            squadSet.add(forwarders.get(forwarders.size() - i));
            //forwarders.remove(i);
        }

        int j = 1;
        while (squadSet.size() != NUM_OF_STARTING_PLAYERS) {
            int selected = rand.nextInt(midfielders.size());
            squadSet.add(midfielders.get(midfielders.size() - j - tactic.getMidfielders()));
            j++;
        }

        if (squadSet.size() != NUM_OF_STARTING_PLAYERS) {
            for (int i = squadSet.size(); i < NUM_OF_STARTING_PLAYERS; i++) {
                int selected = rand.nextInt(midfielders.size());
                squadSet.add(midfielders.get(midfielders.size() - i - tactic.getMidfielders()));
                //midfielders.remove(selected);
            }
        }

        int numOfTotalPlayers = NUM_OF_STARTING_PLAYERS + NUM_OF_SUBSTITUTE_PLAYERS;

        squadSet.add(goalkeepers.get(0));

        int defSub = defenders.size() - tactic.getDefenders();
        int midSub = midfielders.size() - tactic.getMidfielders();
        int fwdSub = forwarders.size() - tactic.getForwarders();

        int defSelected = 0;
        for (int i = 0; i < 2 && defSub > 0; i++, defSub--) {
            squadSet.add(defenders.get(i));
            defSelected++;
        }

        int midSelected = 0;
        for (int i = 0; i < 2 && midSub > 0; i++, midSub--) {
            squadSet.add(midfielders.get(i));
            midSelected++;
        }

        int fwdSelected = 0;
        for (int i = 0; i < 2 && fwdSub > 0; i++, fwdSub--) {
            squadSet.add(forwarders.get(i));
            fwdSelected++;
        }

        if (squadSet.size() != numOfTotalPlayers && (defSub + midSub + fwdSub) > NUM_OF_SUBSTITUTE_PLAYERS - 1) {
            while (squadSet.size() < numOfTotalPlayers) {
                int selected = rand.nextInt(allPlayers.size());
                squadSet.add(allPlayers.get(selected));
                //midfielders.remove(selected);
            }
        }

        this.squad = new ArrayList(squadSet);
        startingEleven = this.squad.subList(0, NUM_OF_STARTING_PLAYERS);
        subPlayers = this.squad.subList(NUM_OF_STARTING_PLAYERS, squad.size());
        teamSquad.add(0, startingEleven);
        teamSquad.add(1, subPlayers);
    }
}
