package com.tss.application.services;

import com.tss.application.ports.input.TennisScoreProcessor;
import com.tss.application.ports.output.GameStateRepository;
import com.tss.application.ports.output.ScoreFormatter;
import com.tss.domain.GameState;
import com.tss.domain.GameStatus;
import com.tss.domain.Player;

public class TennisGameService implements TennisScoreProcessor {
    private final ScoreFormatter scoreFormatter;
    private final GameStateRepository gameStateRepository;
    private GameState currentGameState;
    private final String playerAName;
    private final String playerBName;

    public TennisGameService(
        ScoreFormatter scoreFormatter,
        GameStateRepository gameStateRepository,
        String playerAName,
        String playerBName
    ) {
        this.scoreFormatter = scoreFormatter;
        this.gameStateRepository = gameStateRepository;
        this.currentGameState = GameState.initial(playerAName, playerBName);
        this.playerAName = playerAName;
        this.playerBName = playerBName;
    }

    @Override
    public void processPoint(String winner) {
        validateInput(winner);
        currentGameState = calculateNewGameState(winner);
        gameStateRepository.save(currentGameState);
    }

    @Override
    public String getScore() {
        return scoreFormatter.formatScore(currentGameState);
    }

    private void validateInput(String winner) {
        if (!playerAName.equals(winner) && !playerBName.equals(winner)) {
            throw new IllegalArgumentException(String.format("Invalid input: must be '%s' or '%s'", playerAName, playerBName));
        }
    }

    private GameState calculateNewGameState(String winner) {
        var playerA = currentGameState.playerA();
        var playerB = currentGameState.playerB();

        if (playerAName.equals(winner)) {
            playerA = new Player(playerA.name(), playerA.score() + 1);
        } else {
            playerB = new Player(playerB.name(), playerB.score() + 1);
        }
        var status = determineGameStatus(playerA, playerB);
        return new GameState(playerA, playerB, status);
    }

    private GameStatus determineGameStatus(Player playerA, Player playerB) {
        if (hasPlayerWon(playerA, playerB)) {
            return GameStatus.PLAYER_A_WINS;
        } else if (hasPlayerWon(playerB, playerA)) {
            return GameStatus.PLAYER_B_WINS;
        }
        return GameStatus.IN_PROGRESS;
    }

    private boolean hasPlayerWon(Player player, Player opponent) {
        return player.score() >= 4 && player.score() >= opponent.score() + 2;
    }
}