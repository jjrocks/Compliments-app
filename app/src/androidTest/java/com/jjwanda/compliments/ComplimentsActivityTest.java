package com.jjwanda.compliments;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

/**
 * The basic test unit for android running.
 */
@RunWith(AndroidJUnit4.class)
public class ComplimentsActivityTest {

    private String currentCompliment;
    private String previousCompliment;

    @Rule
    public ActivityTestRule<ComplimentsActivity> complimentsActivityActivityRule =
            new ActivityTestRule<ComplimentsActivity>(ComplimentsActivity.class);

    @Test
    public void complimentIsDifferent() {
        // Check to first see if it is displayed
        onView(withId(R.id.compliments_relativeView)).check(matches(isDisplayed()));
        onView(withId(R.id.compliment_textview)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting the text from a textview";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view;
                previousCompliment = tv.getText().toString();
            }
        });
        onView(withId(R.id.compliments_relativeView)).perform(click());
        onView(withId(R.id.compliment_textview)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting the text from a textview";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view;
                currentCompliment = tv.getText().toString();
            }
        });
        currentCompliment = previousCompliment;
        assertNotSame("Compliments are the same", currentCompliment, previousCompliment);
    }
}
