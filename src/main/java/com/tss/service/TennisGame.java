package com.tss.service;

import com.tss.domain.*;
import com.tss.factory.PlayerFactory;

public class TennisGame {
    private final IPlayer playerA;
    private final IPlayer playerB;
    private GameState gameState;
    private final ScoreBoard scoreBoard;
    private final ScoreUpdater scoreUpdater;
    
    public TennisGame() {
        this.playerA = PlayerFactory.createPlayer("A");
        this.playerB = PlayerFactory.createPlayer("B");
        this.scoreBoard = new ScoreBoard();
        this.scoreUpdater = new ScoreUpdater();

    }
    
    public String processGame(String points) {
        StringBuilder gameProgress = new StringBuilder();
        Players currentPlayers = new Players(playerA, playerB);
        //initialize the game as in progress
        this.gameState = GameState.IN_PROGRESS;
        for (char point : points.toCharArray()) {
            GameResult result = processPoint(point, currentPlayers);
            currentPlayers = result.getPlayers();
            gameProgress.append(result.getScore()).append("\n");
            
            if (result.getGameState() != GameState.IN_PROGRESS) {
                gameState = result.getGameState();
                break;
            }
        }
        
        return gameProgress.toString().trim();
    }
    
    private GameResult processPoint(char winner, Players currentPlayers) {
        validateInput(winner);
        
        Players updatedPlayers = scoreUpdater.updateScore(currentPlayers, winner);
        GameState newGameState = calculateGameState(updatedPlayers);
        String score = scoreBoard.getCurrentScore(updatedPlayers,
                                                newGameState);
        
        return new GameResult(updatedPlayers, score, newGameState);
    }
    
    private void validateInput(char winner) {
        if (winner != 'A' && winner != 'B') {
            throw new IllegalArgumentException("Invalid input: must be 'A' or 'B'");
        }
    }
    
    private GameState calculateGameState(Players players) {
        if (hasPlayerWon(players.getPlayerA(), players.getPlayerB())) {
            return GameState.PLAYER_A_WINS;
        } else if (hasPlayerWon(players.getPlayerB(), players.getPlayerA())) {
            return GameState.PLAYER_B_WINS;
        }
        return GameState.IN_PROGRESS;
    }
    
    private boolean hasPlayerWon(IPlayer player, IPlayer opponent) {
        return player.getScore() >= 4 && player.getScore() >= opponent.getScore() + 2;
    }
    
    public GameState getGameState() {
        return gameState;
    }
}