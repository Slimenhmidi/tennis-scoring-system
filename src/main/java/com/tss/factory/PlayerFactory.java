package com.tss.factory;

import com.tss.domain.IPlayer;
import com.tss.domain.PlayerBuilder;

public class PlayerFactory {

    public static IPlayer createPlayer(String name) {
        //Design Patterns used here : Builder and Delegate
        return new PlayerBuilder()
                .withName(name)
                .withScore(0)
                .build();
    }

    public static IPlayer createPlayerWithScore(String name, int newScore) {
        return new PlayerBuilder()
                .withName(name)
                .withScore(newScore)
                .build();
    }
}
