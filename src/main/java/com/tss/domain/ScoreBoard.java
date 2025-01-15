package com.tss.domain;

public class ScoreBoard {
    private static final int[] POINT_SYSTEM = {0, 15, 30, 40};
    
    public String getCurrentScore(Players players, GameState gameState) {
        IPlayer playerA = players.getPlayerA();
        IPlayer playerB = players.getPlayerB();

        if (gameState == GameState.PLAYER_A_WINS) {
            return "Player A wins the game";
        }
        if (gameState == GameState.PLAYER_B_WINS) {
            return "Player B wins the game";
        }
        
        if (isDeuce(playerA, playerB)) {
            return "Deuce";
        }
        
        if (isAdvantage(playerA, playerB)) {
            return getAdvantageString(playerA, playerB);
        }
        
        return getRegularScore(playerA, playerB);
    }
    
    private boolean isDeuce(IPlayer playerA, IPlayer playerB) {
        return playerA.getScore() >= 3 && playerB.getScore() >= 3 
            && playerA.getScore() == playerB.getScore();
    }
    
    private boolean isAdvantage(IPlayer playerA, IPlayer playerB) {
        return playerA.getScore() >= 3 && playerB.getScore() >= 3 
            && Math.abs(playerA.getScore() - playerB.getScore()) == 1;
    }
    
    private String getAdvantageString(IPlayer playerA, IPlayer playerB) {
        return "Advantage Player " + 
            (playerA.getScore() > playerB.getScore() ? "A" : "B");
    }
    
    private String getRegularScore(IPlayer playerA, IPlayer playerB) {
        String scoreA = translateScore(playerA.getScore());
        String scoreB = translateScore(playerB.getScore());
        return String.format("Player A : %s / Player B : %s", scoreA, scoreB);
    }
    
    private String translateScore(int score) {
        return score < 4 ? String.valueOf(POINT_SYSTEM[score]) : "40";
    }
}