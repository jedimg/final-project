package com.example.laptop.finalproject;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.view.WindowManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MapsActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void unlockScreen() {
        final MainActivity activity = activityTestRule.getActivity();
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        activity.runOnUiThread(wakeUpDevice);
    }

    @Test
    public void testMapActivity() throws Exception {

        onView(withId(R.id.etPostcode)).perform(typeText("SE1 3QX"), closeSoftKeyboard());
        onView(withId(R.id.btnFindNearby)).perform(click());

        Thread.sleep(5000);

        closeSoftKeyboard();
        Thread.sleep(1000);
        onView(withId(R.id.btnRestaurantList)).perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.rvRestaurantList)).check(matches(isDisplayed()));
        onView(withId(R.id.btnRestaurantList)).perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.map)).check(matches(isDisplayed()));

        Thread.sleep(1000);

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject marker = device.findObject(new UiSelector().descriptionContains("Chicken Cottage"));

        marker.click();
        Thread.sleep(1000);
        marker.click();
        Thread.sleep(1000);
        onView(withId(R.id.tvCostText)).check(matches(isDisplayed()));
        Thread.sleep(1000);

        UiObject button1 = device.findObject(new UiSelector().textContains("Reviews"));
        button1.click();
        Thread.sleep(1000);
        onView(withId(R.id.rvUserReviews)).check(matches(isDisplayed()));
        Thread.sleep(1000);

        onView(withId(R.id.rvUserReviews)).perform(scrollToPosition(2));
        Thread.sleep(2000);

        onView(withText("Meryem")).check(matches(isDisplayed()));
        Thread.sleep(1000);
        UiObject button2 = device.findObject(new UiSelector().textContains("Menu"));
        button2.click();
        Thread.sleep(2000);

        onView(withId(R.id.wvMenu)).check(matches(isDisplayed()));
        Thread.sleep(1000);
    }
}
