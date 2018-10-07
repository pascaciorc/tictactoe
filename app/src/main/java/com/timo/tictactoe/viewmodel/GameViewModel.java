package com.timo.tictactoe.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

import com.timo.tictactoe.model.Cell;
import com.timo.tictactoe.model.Game;
import com.timo.tictactoe.model.Player;

import static com.timo.tictactoe.utilities.StringUtility.stringFromNumbers;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String,String> cells;
    private Game game;

    public void init(String p1, String p2) {
        game = new Game(p1, p2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row,column),game.currentPlayer.value);
            if (game.hasGameEnded()) {
                game.reset();
            } else {
                game.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}
