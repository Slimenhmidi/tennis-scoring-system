package com.tss.adapters.output;

import com.tss.application.ports.output.GameStateRepository;
import com.tss.domain.GameState;

public class InMemoryGameStateRepository implements GameStateRepository {
    private GameState gameState;

    @Override
    public void save(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public GameState load() {
        return gameState;
    }
}