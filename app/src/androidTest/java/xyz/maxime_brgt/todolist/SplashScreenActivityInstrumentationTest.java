package xyz.maxime_brgt.todolist;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

/**
 * Created by maxim on 29/01/2017.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SplashScreenActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityRule = new ActivityTestRule<>(
            SplashScreenActivity.class);

    @Test
    public void onCreate() throws Exception {
        // check the text at bottom
        onView(withId(R.id.made_by)).inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(withText("Made by Maxime Bruguet")));
    }
}