package com.tss.domain;

public class PlayerBuilder {
    private String name;
    private int score;

    public PlayerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder withScore(int score) {
        this.score = score;
        return this;
    }

    public IPlayer build() {
        return new Player(name, score);
    }
}
