package com.timo.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.timo.tictactoe.model.Game;
import com.timo.tictactoe.model.Player;
import com.timo.tictactoe.view.GameActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class GameEndDialogShould {

    @Rule
    public ActivityTestRule<GameActivity> activityRule
            = new ActivityTestRule<>(GameActivity.class,true,false);

    private Context context = InstrumentationRegistry.getTargetContext();

    @Test
    public void displayWinnerWhenGameEnds() {
        givenGameActivityLaunched();
        givenGameEnded();

        Espresso.onView(withId(R.id.tv_winner))
                .check(matches(isDisplayed()));
    }

    @Test
    public void displayBeginDialogWhenDoneClicked() {
        givenGameActivityLaunched();
        givenGameEnded();

        Espresso.onView(withId(android.R.id.button1)).perform((click()));

        Espresso.onView(withId(R.id.tv_winner))
                .check(doesNotExist());
        Espresso.onView(withId(R.id.et_player1))
                .check(matches(isDisplayed()));
    }


    private void givenGameActivityLaunched() {
        Intent i = new Intent(context, GameActivity.class);
        activityRule.launchActivity(i);
    }

    private void givenGameEnded() {
        GameActivity activity = activityRule.getActivity();
        activity.onGameWinnerChanged(new Player("Maire","w"));
    }

}
