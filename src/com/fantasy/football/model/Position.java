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
public enum Position {
    
    GoalKeeper("GK"),
    Defender("Def"),
    Midfielder("Mid"),
    Forward("Fwd");
    
    private String positionName;

    private Position(String positionName) {
        this.positionName = positionName;
    }
    
   
    
}
