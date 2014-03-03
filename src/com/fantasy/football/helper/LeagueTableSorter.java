/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.helper;

import com.fantasy.football.model.Team;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Abdu
 */
/*public class LeagueTableSorter {
    

    public static void main(String[] args) {

        HashMap<String,Double> map = new HashMap<String,Double>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);

        map.put("A",99.5);
        map.put("B",67.4);
        map.put("C",67.4);
        map.put("D",67.3);

        System.out.println("unsorted map: "+map);

        sorted_map.putAll(map);

        System.out.println("results: "+sorted_map);
    }
}*/

public class LeagueTableSorter implements Comparator<Team> {

    Map<Team, Integer> base;
    
    public LeagueTableSorter(Map<Team, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(Team a, Team b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}