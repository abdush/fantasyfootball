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
public enum Formation {
    
    FourFourTwo(4,4,2),
    FourFiveOne(4,5,1),
    FiveFourOne(5,4,1);
    
    private final int defenders;
    private final int midfielders;
    private final int forwarders;

    private Formation(int defenders, int midfielders, int forwarders) {
        this.defenders = defenders;
        this.midfielders = midfielders;
        this.forwarders = forwarders;
    }

    public static Formation getFourFourTwo() {
        return FourFourTwo;
    }

    public static Formation getFourFiveOne() {
        return FourFiveOne;
    }

    public static Formation getFiveFourOne() {
        return FiveFourOne;
    }

    
    public int getDefenders() {
        return defenders;
    }

    public int getMidfielders() {
        return midfielders;
    }

    public int getForwarders() {
        return forwarders;
    }
    
    
    
    
}
