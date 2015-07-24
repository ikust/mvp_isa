package co.infinum.boatit.tests;

import org.junit.Test;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import co.infinum.boatit.R;
import co.infinum.boatit.activities.LoginActivity;

/**
 * Created by ivan on 24/07/15.
 */
public class LoginInstrumentTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity activity;

    public LoginInstrumentTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testSuccessfullLogin() {
        Espresso.onView(ViewMatchers.withId(R.id.username)).perform(ViewActions.typeText("admin@infinum.co"));
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("infinum1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.swipeDown());
        Espresso.onView(ViewMatchers.withId(R.id.login)).perform(ViewActions.click());

        //New activity is shown
        Espresso.onView(ViewMatchers.withText("MVP")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
}
