package com.tss.domain;

/**
 * Immutable interface to define a player. A player have a name and current score
 */
public interface IPlayer {
    int getScore();
    String getName();
}
