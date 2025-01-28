package com.tss.domain;

/**
 * data class to define a game state
 * @param playerA
 * @param playerB
 * @param status
 */
public record GameState(
    Player playerA, 
    Player playerB, 
    GameStatus status
) {
    /**
     * Create initial GameState when the game starts
     * @param playerAName the first player name
     * @param playerBName the opponent name
     * @return the initial GameState object with 2 players with score 0 each and status IN_PROGRESS
     */
    public static GameState initial(String playerAName, String playerBName) {
        return new GameState(
            Player.newPlayer(playerAName),
            Player.newPlayer(playerBName),
            GameStatus.IN_PROGRESS
        );
    }
}