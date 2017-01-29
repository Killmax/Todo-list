package xyz.maxime_brgt.todolist;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;
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
    public void displayDialogOnAddItem() throws Exception {
        // Check if Dialog is displayed
        Log.d("TEST", "CLICK ON ADD ITEM BUTTON");
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withText(R.string.title_add_dial)).check(matches(isDisplayed()));
        Log.d("TEST", "FINISHED");
    }

    @Test
    public void clickCancelOnNewDialog() throws Exception {
        // Click on Cancel button
        Log.d("TEST", "CLICK ON CANCEL IN NEW TASK DIALOG");
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withText(R.string.cancel_add_dial)).perform(click());
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withText(R.string.cancel_add_dial)).perform(click());
        Log.d("TEST", "FINISHED");
    }

    @Test
    public void clickAddEmptyOnNewDialog() throws Exception {
        // Click on Add if fields are empty
        Log.d("TEST", "CLICK ON ADD WHEN EMPTY IN NEW TASK DIALOG");
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withText(R.string.save_add_dial)).perform(click());
        onView(withText(R.string.error_add_dial)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        Log.d("TEST", "FINISHED");
    }

    @Test
    public void clickAddOnNewDialog() throws Exception {
        // Click on Add if fields are OK
        Log.d("TEST", "ADD NEW TASK");
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withId(R.id.addTitle)).perform(typeText("Test title"));
        onView(withId(R.id.addContent)).perform(typeText("Test content"));
        onView(withText(R.string.save_add_dial)).perform(click());
        onView(withText(R.string.success_add_dial)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
        onView(withText(R.string.done_edit_dial)).perform(click());
        Log.d("TEST", "FINISHED");
    }

    @Test
    public void addAndDeleteTask() throws Exception {
        Log.d("TEST", "ADD NEW TASK AND DELETE IT");
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withId(R.id.addTitle)).perform(typeText("Test title Click"));
        onView(withId(R.id.addContent)).perform(typeText("Test content Click"));
        onView(withText(R.string.save_add_dial)).perform(click());
        onView(withText(R.string.success_add_dial)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
        onView(withText(R.string.done_edit_dial)).perform(click());
        Log.d("TEST", "FINISHED");
    }

    @Test
    public void editAndSaveTask() throws Exception {
        // Edit task and save it
        Log.d("TEST", "EDIT A CREATED TASK AND SAVE IT");
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withId(R.id.addTitle)).perform(typeText("Test title Edit"));
        onView(withId(R.id.addContent)).perform(typeText("Test content Edit"));
        onView(withText(R.string.save_add_dial)).perform(click());
        onView(withText(R.string.success_add_dial)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
        onView(withId(R.id.editTitle)).perform(clearText(), typeText("Test title Edit After"));
        onView(withId(R.id.editContent)).perform(clearText(), typeText("Test content Edit After"));
        onView(withText(R.string.save_edit_dial)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
        onView(withText(R.string.done_edit_dial)).perform(click());
        Log.d("TEST", "FINISHED");
    }

    @Test
    public void clickCancelEditDialog() throws Exception {
        Log.d("TEST", "CLICK CANCEL ON EDIT DIALOG");
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withId(R.id.addTitle)).perform(typeText("Test title Cancel"));
        onView(withId(R.id.addContent)).perform(typeText("Test content Cancel"));
        onView(withText(R.string.save_add_dial)).perform(click());
        onView(withText(R.string.success_add_dial)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
        onView(withText(R.string.cancel_add_dial)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
        onView(withText(R.string.done_edit_dial)).perform(click());
        Log.d("TEST", "FINISHED");
    }

    @Test
    public void saveEmptyEditDialog() throws Exception {
        Log.d("TEST", "TRY TO SAVE AN EDITED TASK WITH EMPTY FIELDS");
        // Try to save a edited task when field are empty
        onView(withId(R.id.btnAddItem)).perform(click());
        onView(withId(R.id.addTitle)).perform(typeText("Test title Save Empty"));
        onView(withId(R.id.addContent)).perform(typeText("Test content Save Empty"));
        onView(withText(R.string.save_add_dial)).perform(click());
        onView(withText(R.string.success_add_dial)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        // Sleep because Toaster is too long
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
        onView(withId(R.id.editTitle)).perform(clearText());
        onView(withId(R.id.editContent)).perform(clearText());
        onView(withText(R.string.save_edit_dial)).perform(click());
        onView(withText(R.string.error_add_dial)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
        onView(withText(R.string.done_edit_dial)).perform(click());
        Log.d("TEST", "FINISH");
    }

}