package com.tss;

import com.tss.domain.GameState;
import com.tss.service.TennisGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class TennisGameTests {
    private TennisGame game;

    @BeforeEach
    void setUp() {
        game = new TennisGame();
    }

    @Test
    @DisplayName("Should handle basic scoring sequence")
    void testBasicScoring() {
        String result = game.processGame("ABABAA");
        String expected = String.join("\n",
                "Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 40 / Player B : 30",
                "Player A wins the game"
        );
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle deuce scenario")
    void testDeuce() {
        String result = game.processGame("ABABAB");
        assertTrue(result.contains("Deuce"));
    }

    @Test
    @DisplayName("Should handle advantage scenario")
    void testAdvantage() {
        String result = game.processGame("ABABABA");
        assertTrue(result.contains("Advantage Player A"));
    }

    @Test
    @DisplayName("Should handle advantage switching back to deuce")
    void testAdvantageToDeuce() {
        String result = game.processGame("ABABABAB");
        String[] scores = result.split("\n");
        assertEquals("Deuce", scores[scores.length - 1]);
    }

    @Test
    @DisplayName("Should handle winning from advantage")
    void testWinFromAdvantage() {
        String result = game.processGame("ABABABAA");
        assertTrue(result.contains("Player A wins the game"));
    }

    @Test
    @DisplayName("Should reject invalid input")
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.processGame("ABCDE");
        });
    }

    @Test
    @DisplayName("Should handle empty input")
    void testEmptyInput() {
        String result = game.processGame("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Should handle quick win scenario")
    void testQuickWin() {
        String result = game.processGame("AAAA");
        assertTrue(result.contains("Player A wins the game"));
    }

    @Test
    @DisplayName("Should maintain correct game state")
    void testGameState() {
        game.processGame("AAA");
        assertEquals(GameState.IN_PROGRESS, game.getGameState());

        game.processGame("A");
        assertEquals(GameState.IN_PROGRESS, game.getGameState());

        game.processGame("BBBB");
        assertEquals(GameState.PLAYER_B_WINS, game.getGameState());

        game.processGame("B");
        assertEquals(GameState.IN_PROGRESS, game.getGameState());

        game.processGame("BBB");
        assertEquals(GameState.IN_PROGRESS, game.getGameState());

        game.processGame("ABABABAA");
        assertEquals(GameState.PLAYER_A_WINS, game.getGameState());

        game.processGame("ABABABAB");
        assertEquals(GameState.IN_PROGRESS, game.getGameState());

    }
}