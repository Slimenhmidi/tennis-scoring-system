package com.tss.application.ports.output;

import com.tss.domain.GameState;

public interface GameStateRepository {
    void save(GameState gameState);
    GameState load();
}