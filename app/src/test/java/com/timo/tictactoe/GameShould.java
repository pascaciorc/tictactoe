package com.timo.tictactoe;


import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.timo.tictactoe.model.Cell;
import com.timo.tictactoe.model.Game;
import com.timo.tictactoe.model.Player;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameShould {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private Game game;
    private Player player;

    @Before
    public void setup() {
        game = new Game("Pablo","Marcoc");
        player = game.player1;
    }

    @Test
    public void endGameIfHasThreeHorizontalCells() {
        Cell cell = new Cell(player);
        game.cells[1][0] = cell;
        game.cells[1][1] = cell;
        game.cells[1][2] = cell;

        boolean hasGameEnded = game.hasGameEnded();

        assertEquals(hasGameEnded,true);
    }

    @Test
    public void endGameIfHasThreeVerticalCells() {
        Cell cell = new Cell(player);
        game.cells[0][1] = cell;
        game.cells[1][1] = cell;
        game.cells[2][1] = cell;

        boolean hasGameEnded = game.hasGameEnded();

        assertEquals(hasGameEnded,true);
    }

    @Test
    public void endGameIfHasThreeDiagonalCells() {
        Cell cell = new Cell(player);
        game.cells[0][2] = cell;
        game.cells[1][1] = cell;
        game.cells[2][0] = cell;

        boolean hasGameEnded = game.hasGameEnded();

        assertEquals(hasGameEnded,true);
    }

    @Test
    public void endGameIfBoardIsFull() {
        Player p1 = new Player("A","x");
        Player p2 = new Player("B","o");

        Cell c1 = new Cell(p1);
        Cell c2 = new Cell(p2);
        Cell c3 = new Cell(p1);
        Cell c4 = new Cell(p2);
        Cell c5 = new Cell(p1);
        Cell c6 = new Cell(p2);
        Cell c7 = new Cell(p1);
        Cell c8 = new Cell(p2);
        Cell c9 = new Cell(p1);

        game.cells[0][0] = c1;
        game.cells[0][1] = c2;
        game.cells[0][2] = c3;
        game.cells[1][0] = c4;
        game.cells[1][1] = c5;
        game.cells[1][2] = c6;
        game.cells[2][0] = c7;
        game.cells[2][1] = c8;
        game.cells[2][2] = c9;

        boolean boardIsFull = game.isBoardFull();

        assertEquals(boardIsFull,true);
    }

    @Test
    public void switchFromPlayer1ToPlayer2() {
        game.currentPlayer = game.player1;
        game.switchPlayer();

        assertEquals(game.currentPlayer,game.player2);
    }
}
