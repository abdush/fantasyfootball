/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fantasy.football.helper;

import com.fantasy.football.model.MatchEvent;

/**
 *
 * @author Abdu
 */
public interface MatchEventListener {
    
    public void MatchEventOccured(MatchEvent matchEvent);
    
}
