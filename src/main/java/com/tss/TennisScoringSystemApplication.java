package com.tss;

import com.tss.application.ports.input.TennisScoreProcessor;
import com.tss.config.TennisGameConfiguration;

import java.util.Arrays;

public class TennisScoringSystemApplication {
    public static void main(String[] args) {
        TennisScoreProcessor game = TennisGameConfiguration.createGame("A", "B");
        
        // Example usage
        String points = "ABABAA";

        for (char point : points.toCharArray()) {
            game.processPoint(point+"");
            System.out.println(game.getScore());
        }
    }
}