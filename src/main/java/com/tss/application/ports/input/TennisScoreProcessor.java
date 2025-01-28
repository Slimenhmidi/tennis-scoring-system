package com.tss.application.ports.input;

/**
 * Processes the case when a given player wins a ball and compute his/her score
 */
public interface TennisScoreProcessor {
    void processPoint(String winner);
    String getScore();
}