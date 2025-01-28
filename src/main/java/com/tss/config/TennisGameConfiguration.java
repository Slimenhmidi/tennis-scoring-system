package com.tss.config;

import com.tss.adapters.output.DefaultScoreFormatter;
import com.tss.adapters.output.InMemoryGameStateRepository;
import com.tss.application.ports.input.TennisScoreProcessor;
import com.tss.application.ports.output.GameStateRepository;
import com.tss.application.ports.output.ScoreFormatter;
import com.tss.application.services.TennisGameService;

public class TennisGameConfiguration {

    public static TennisScoreProcessor createGame(String playerAName, String playerBName) {
        ScoreFormatter scoreFormatter = new DefaultScoreFormatter();
        GameStateRepository repository = new InMemoryGameStateRepository();
        return new TennisGameService(scoreFormatter, repository, playerAName, playerBName);
    }


}