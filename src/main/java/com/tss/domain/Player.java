package com.tss.domain;

/**
 * Internal implementation of the player interface
 */
class Player implements IPlayer {
    private final String name;
    private final int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
