package com.tss.domain;

/**
 * Immutable class that defines the players presented in the game.
 *
 *
 */
public class Players {

    private final IPlayer playerA;
    private final IPlayer playerB;

    public Players(IPlayer playerA, IPlayer playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public IPlayer getPlayerA() {
        return playerA;
    }

    public IPlayer getPlayerB() {
        return playerB;
    }
}
