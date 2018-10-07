package com.timo.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.timo.tictactoe.model.Player;
import com.timo.tictactoe.view.GameActivity;

import org.junit.Rule;
import org.junit.Test;

public class GameActivityShould {

    @Rule
    public ActivityTestRule<GameActivity> activityRule
            = new ActivityTestRule<>(GameActivity.class,true,false);

    private Context context = InstrumentationRegistry.getTargetContext();

    private Player p1 = new Player("Lalo","w");
    private Player p2 = new Player("Maria", "q");

    @Test
    public void endGameWhenOnePlayerWins() {
        givenGameActivityLaunched();

        Espresso.onView(withId(R.id.et_player1)).perform(typeText(p1.name));
        Espresso.onView(withId(R.id.et_player2)).perform(typeText(p2.name));

        Espresso.onView(withId(android.R.id.button1)).perform((click()));

        Espresso.onView(withId(R.id.cell_02)).perform(click());
        Espresso.onView(withId(R.id.cell_11)).perform(click());
        Espresso.onView(withId(R.id.cell_01)).perform(click());
        Espresso.onView(withId(R.id.cell_10)).perform(click());
        Espresso.onView(withId(R.id.cell_00)).perform(click());

        Espresso.onView(withId(R.id.tv_winner)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.tv_winner)).check(matches(withText(p1.name)));
    }

    @Test
    public void endGameWhenPlayerTwoWins() {
        givenGameActivityLaunched();

        Espresso.onView(withId(R.id.et_player1)).perform(typeText(p1.name));
        Espresso.onView(withId(R.id.et_player2)).perform(typeText(p2.name));

        Espresso.onView(withId(android.R.id.button1)).perform((click()));

        Espresso.onView(withId(R.id.cell_00)).perform(click());
        Espresso.onView(withId(R.id.cell_02)).perform(click());
        Espresso.onView(withId(R.id.cell_22)).perform(click());
        Espresso.onView(withId(R.id.cell_11)).perform(click());
        Espresso.onView(withId(R.id.cell_10)).perform(click());
        Espresso.onView(withId(R.id.cell_20)).perform(click());

        Espresso.onView(withId(R.id.tv_winner)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.tv_winner)).check(matches(withText(p2.name)));
    }

    @Test
    public void noOneWins() {
        givenGameActivityLaunched();

        Espresso.onView(withId(R.id.et_player1)).perform(typeText(p1.name));
        Espresso.onView(withId(R.id.et_player2)).perform(typeText(p2.name));

        Espresso.onView(withId(android.R.id.button1)).perform((click()));

        Espresso.onView(withId(R.id.cell_00)).perform(click());
        Espresso.onView(withId(R.id.cell_02)).perform(click());
        Espresso.onView(withId(R.id.cell_22)).perform(click());
        Espresso.onView(withId(R.id.cell_11)).perform(click());
        Espresso.onView(withId(R.id.cell_10)).perform(click());

        Espresso.onView(withId(R.id.tv_winner)).check(doesNotExist());
    }

    @Test
    public void boardIsFull() {
        givenGameActivityLaunched();

        Espresso.onView(withId(R.id.et_player1)).perform(typeText(p1.name));
        Espresso.onView(withId(R.id.et_player2)).perform(typeText(p2.name));

        Espresso.onView(withId(android.R.id.button1)).perform((click()));

        Espresso.onView(withId(R.id.cell_00)).perform(click());
        Espresso.onView(withId(R.id.cell_22)).perform(click());
        Espresso.onView(withId(R.id.cell_20)).perform(click());
        Espresso.onView(withId(R.id.cell_02)).perform(click());
        Espresso.onView(withId(R.id.cell_11)).perform(click());
        Espresso.onView(withId(R.id.cell_01)).perform(click());
        Espresso.onView(withId(R.id.cell_12)).perform(click());
        Espresso.onView(withId(R.id.cell_10)).perform(click());
        Espresso.onView(withId(R.id.cell_21)).perform(click());

        Espresso.onView(withId(R.id.tv_winner)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.tv_winner)).check(matches(withText(R.string.no_winner)));
    }

    private void givenGameActivityLaunched() {
        Intent intent = new Intent(context, GameActivity.class);
        activityRule.launchActivity(intent);
    }
}
