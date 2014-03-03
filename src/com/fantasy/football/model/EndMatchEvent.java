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
public class EndMatchEvent extends MatchEvent {

    public EndMatchEvent(int minute) {
        super(minute);
    }

    @Override
    public String toString() {
        return "[ Final Whistle ...]\n";
    }
    
    
}
