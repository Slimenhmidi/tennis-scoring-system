package com.tss.service;

import com.tss.domain.IPlayer;
import com.tss.domain.Players;
import com.tss.factory.PlayerFactory;

public class ScoreUpdater {

    public Players updateScore(Players players, char winner) {
        if (winner == 'A') {
            IPlayer playerA = PlayerFactory.createPlayerWithScore(players.getPlayerA().getName(), players.getPlayerA().getScore() + 1);
            return new Players(
                    playerA,
                    players.getPlayerB()
            );
        } else {
            IPlayer playerB = PlayerFactory.createPlayerWithScore(players.getPlayerB().getName(), players.getPlayerB().getScore() + 1);
            return new Players(
                    players.getPlayerA(),
                    playerB
            );
        }
    }
}
