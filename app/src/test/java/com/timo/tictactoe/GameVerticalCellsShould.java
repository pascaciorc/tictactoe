package com.timo.tictactoe;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.timo.tictactoe.model.Cell;
import com.timo.tictactoe.model.Game;
import com.timo.tictactoe.model.Player;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameVerticalCellsShould {
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
    public void returnTrueIfHasThreeSameVerticalCellsAtRow1() {
        Cell cell = new Cell(player);

        game.cells[0][0] = cell;
        game.cells[1][0] = cell;
        game.cells[2][0] = cell;

        boolean hasThreeSameVerticalCells = game.hasThreeSameVerticalCells();

        assertEquals(hasThreeSameVerticalCells,true);
    }

    @Test
    public void returnTrueIfHasThreeSameVerticalCellsAtRow2() {
        Cell cell = new Cell(player);

        game.cells[0][1] = cell;
        game.cells[1][1] = cell;
        game.cells[2][1] = cell;

        boolean hasThreeSameVerticalCells = game.hasThreeSameVerticalCells();

        assertEquals(hasThreeSameVerticalCells,true);
    }

    @Test
    public void returnTrueIfHasThreeSameVerticalCellsAtRow3() {
        Cell cell = new Cell(player);

        game.cells[0][2] = cell;
        game.cells[1][2] = cell;
        game.cells[2][2] = cell;

        boolean hasThreeSameVerticalCells = game.hasThreeSameVerticalCells();

        assertEquals(hasThreeSameVerticalCells,true);
    }



    @Test
    public void returnFalseIfDoesNotHaveThreeSameVerticalCells() {
        Cell cell1 = new Cell(player);
        Cell cell2 = new Cell(new Player("Maire","q"));

        game.cells[0][1] = cell1;
        game.cells[1][1] = cell2;
        game.cells[2][1] = cell1;

        boolean hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells();

        assertEquals(hasThreeSameHorizontalCells,false);
    }
}
