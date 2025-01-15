package com.tss.domain;

public class GameResult {
    private final Players players;
    private final String score;
    private final GameState gameState;
    
    public GameResult(Players players, String score, GameState gameState) {
        this.players = players;
        this.score = score;
        this.gameState = gameState;
    }
    
    public Players getPlayers() {
        return players;
    }
    
    public String getScore() {
        return score;
    }
    
    public GameState getGameState() {
        return gameState;
    }
}