package com.tss.adapters.output;

import com.tss.application.ports.output.ScoreFormatter;
import com.tss.domain.GameState;
import com.tss.domain.GameStatus;
import com.tss.domain.Player;
import com.tss.domain.ScoreType;

public class DefaultScoreFormatter implements ScoreFormatter {
    private static final int[] POINT_SYSTEM = {0, 15, 30, 40};

    @Override
    public String formatScore(GameState gameState) {
        ScoreType scoreType = determineScoreType(gameState);
        
        return switch (scoreType) {
            case GAME_OVER -> formatGameOver(gameState);
            case DEUCE -> "Deuce";
            case ADVANTAGE -> formatAdvantage(gameState);
            case REGULAR -> formatRegularScore(gameState);
        };
    }

    private ScoreType determineScoreType(GameState gameState) {
        if (gameState.status() != GameStatus.IN_PROGRESS) {
            return ScoreType.GAME_OVER;
        }
        
        Player playerA = gameState.playerA();
        Player playerB = gameState.playerB();

        if (isDeuce(playerA, playerB)) {
            return ScoreType.DEUCE;
        }
        if (isAdvantage(playerA, playerB)) {
            return ScoreType.ADVANTAGE;
        }
        return ScoreType.REGULAR;
    }

    private boolean isDeuce(Player playerA, Player playerB) {
        return playerA.score() >= 3 && playerB.score() >= 3 
            && playerA.score() == playerB.score();
    }

    private boolean isAdvantage(Player playerA, Player playerB) {
        return playerA.score() >= 3 && playerB.score() >= 3 
            && Math.abs(playerA.score() - playerB.score()) == 1;
    }

    private String formatGameOver(GameState gameState) {
        return gameState.status() == GameStatus.PLAYER_A_WINS 
            ? "Player A wins the game" 
            : "Player B wins the game";
    }

    private String formatAdvantage(GameState gameState) {
        return "Advantage Player " + 
            (gameState.playerA().score() > gameState.playerB().score() ? "A" : "B");
    }

    private String formatRegularScore(GameState gameState) {
        String scoreA = translateScore(gameState.playerA().score());
        String scoreB = translateScore(gameState.playerB().score());
        return String.format("Player A : %s / Player B : %s", scoreA, scoreB);
    }

    private String translateScore(int score) {
        return score < 4 ? String.valueOf(POINT_SYSTEM[score]) : "40";
    }
}