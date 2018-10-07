package com.timo.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.timo.tictactoe.view.GameActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class GameBeginDialogShould {

    @Rule
    public ActivityTestRule<GameActivity> activityRule
            = new ActivityTestRule<>(GameActivity.class,true,false);

    private Context context = InstrumentationRegistry.getTargetContext();

    @Test
    public void display_empty_names_messages_if_names_empty() {
        givenGameActivityLaunched();

        Espresso.onView(withId(R.id.et_player1)).perform(typeText(""));
        Espresso.onView(withId(R.id.et_player2)).perform(typeText(""));

        Espresso.onView(withId(android.R.id.button1)).perform((click()));

        Espresso.onView(withId(R.id.layout_player1))
                .check(matches(hasTextInputLayoutErrorText(context.getString(R.string.game_dialog_empty_name))));
    }

    @Test
    public void display_same_names_messages_if_names_same() {
        givenGameActivityLaunched();

        Espresso.onView(withId(R.id.et_player1)).perform(typeText("Mario"));
        Espresso.onView(withId(R.id.et_player2)).perform(typeText("Mario"));

        Espresso.onView(withId(android.R.id.button1)).perform(click());

        Espresso.onView(withId(R.id.layout_player1))
                .check(matches(hasTextInputLayoutErrorText(context.getString(R.string.game_dialog_same_name))));
    }

    @Test
    public void display_empty_name_message_if_one_name_empty() {
        givenGameActivityLaunched();

        Espresso.onView(withId(R.id.et_player1)).perform(typeText("sailorFag"));

        Espresso.onView(withId(android.R.id.button1)).perform(click());

        Espresso.onView(withId(R.id.layout_player2))
                .check(matches(hasTextInputLayoutErrorText(context.getString(R.string.game_dialog_empty_name))));
    }

    @Test
    public void close_dialog_after_names_valid() {
        givenGameActivityLaunched();

        Espresso.onView(withId(R.id.et_player1)).perform(typeText("Maire Wink"));
        Espresso.onView(withId(R.id.et_player2)).perform(typeText("Sailor Fag"));

        Espresso.onView(withId(android.R.id.button1)).perform(click());

        Espresso.onView(withId(R.id.layout_player1))
                .check(doesNotExist());
    }

    private void givenGameActivityLaunched() {
        Intent intent = new Intent(context, GameActivity.class);
        activityRule.launchActivity(intent);
    }

    private static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
