package com.tss.domain;

public record Player(String name, int score){

    /**
     * Factory method that creates a new player having the given name with a score 0
     * @param name the player name
     * @return a new player having the given name with a score 0
     */
    public static Player newPlayer(String name) {
        return new Player(name, 0);
    }
}
