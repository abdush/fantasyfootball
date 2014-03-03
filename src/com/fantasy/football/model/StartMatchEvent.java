/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.model;

/**
 *
 * @author Abdu
 */
public class StartMatchEvent extends MatchEvent{

    public StartMatchEvent(int minute) {
        super(minute);
    }

    @Override
    public String toString() {
        return "[ Match Kick off ...]\n";
    }

    
    
    
    
}
