package com.timo.tictactoe.model;

import com.timo.tictactoe.utilities.StringUtility;

public class Cell {
    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || StringUtility.isNullOrEmpty(player.value);
    }
}
