package com.timo.tictactoe.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timo.tictactoe.R;
import com.timo.tictactoe.databinding.ActivityGameBinding;
import com.timo.tictactoe.model.Player;
import com.timo.tictactoe.viewmodel.GameViewModel;

import static com.timo.tictactoe.utilities.StringUtility.isNullOrEmpty;

public class GameActivity extends AppCompatActivity {

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_dialog_tag";
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this);
        dialog.show(getSupportFragmentManager(),GAME_BEGIN_DIALOG_TAG);
    }

    public void onPlayerSet(String player1, String player2) {
        initDataBinding(player1,player2);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityGameBinding activityGameBinding = DataBindingUtil
                .setContentView(this,R.layout.activity_game);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.init(player1,player2);
        activityGameBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this,this::onGameWinnerChanged);
    }

    @VisibleForTesting
    public void onGameWinnerChanged(Player winner) {
        String noWinner = getApplication().getString(R.string.no_winner);
        String winnerName = winner == null || isNullOrEmpty(winner.name) ? noWinner : winner.name;
        GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }
}
