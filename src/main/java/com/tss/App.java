package com.tss;

import com.tss.service.TennisGame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        TennisGame tennisGame = new TennisGame();
        //System.out.println(tennisGame.processGame("AAAA"));
        //System.out.println(tennisGame.processGame("ABABABAB"));
        //System.out.println(tennisGame.processGame("ABABABAA"));
        System.out.println(tennisGame.processGame("ABABAA"));


    }
}
