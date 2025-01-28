package com.tss.application.ports.output;

import com.tss.domain.GameState;

public interface ScoreFormatter {
    String formatScore(GameState gameState);
}
