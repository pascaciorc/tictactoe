package com.timo.tictactoe;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.timo.tictactoe.model.Cell;
import com.timo.tictactoe.model.Game;
import com.timo.tictactoe.model.Player;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class GameHorizontalCellShould {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private Game game;
    private Player player;

    @Before
    public void setup() {
        game = new Game("Pablo","x");
        player = game.player1;
    }

    @Test
    public void returnTrueIfHasThreeSameHorizontalCellsAtRow1() {
        Cell cell = new Cell(player);

        game.cells[0][0] = cell;
        game.cells[0][1] = cell;
        game.cells[0][2] = cell;

        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();

        assertEquals(hasThreeSameHorizontalCells,true);
    }

    @Test
    public void returnTrueIfHasThreeSameHorizontalCellsAtRow2() {
        Cell cell = new Cell(player);

        game.cells[1][0] = cell;
        game.cells[1][1] = cell;
        game.cells[1][2] = cell;

        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();

        assertEquals(hasThreeSameHorizontalCells,true);
    }

    @Test
    public void returnTrueIfHasThreeSameHorizontalCellsAtRow3() {
        Cell cell = new Cell(player);

        game.cells[2][0] = cell;
        game.cells[2][1] = cell;
        game.cells[2][2] = cell;

        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();

        assertEquals(hasThreeSameHorizontalCells,true);
    }

    @Test
    public void returnFalseIfDoesNotHaveThreeSameHorizontalCells() {
        Cell cell1 = new Cell(player);
        Cell cell2 = new Cell(new Player("Maire","q"));

        game.cells[2][0] = cell1;
        game.cells[2][1] = cell2;
        game.cells[2][2] = cell1;

        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();

        assertEquals(hasThreeSameHorizontalCells,false);
    }
}
