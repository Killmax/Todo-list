package xyz.maxime_brgt.todolist;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by maxim on 29/01/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("xyz.maxime_brgt.todolist", appContext.getPackageName());
    }

    @Test
    public void displayAlertDialogOnClick() throws Exception {
        // Check if AlertDialog is displayed
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withText(R.string.title_add_dial)).check(matches(isDisplayed()));
    }

    @Test
    public void clickCancelOnAlertDialog() throws Exception {
        // Click on Cancel button
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withText(R.string.cancel_add_dial)).perform(click());
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withText(R.string.title_add_dial)).check(matches(isDisplayed()));
        onView(withText(R.string.cancel_add_dial)).perform(click());
    }

    @Test
    public void clickAddEmptyOnAlertDialog() throws Exception {
        // Click on Add if fields are empty
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withText(R.string.save_add_dial)).perform(click());
        onView(withText(R.string.error_add_dial)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

}