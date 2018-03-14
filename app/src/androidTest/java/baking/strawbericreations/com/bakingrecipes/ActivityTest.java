package baking.strawbericreations.com.bakingrecipes;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.text.format.DateUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import baking.strawbericreations.com.bakingrecipes.IdlingResources.MyIdlingResources;
import baking.strawbericreations.com.bakingrecipes.UserInterface.RecipeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by redrose on 3/13/18.
 */

public class ActivityTest {


    @Rule
    public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<>(RecipeActivity.class);


    @Before
    public void resetTimeout() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS);
    }


    @Test
    public void waitFor5Seconds() {
        waitFor(5);
    }

    @Test
    public void waitFor65Seconds() {
        waitFor(65);
    }


    private void waitFor(int waitingTime) {

        onView(withId(R.id.recycler_recipe)).perform(RecyclerViewActions.scrollToPosition(2));
        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(waitingTime * 2, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(waitingTime * 2, TimeUnit.SECONDS);

        MyIdlingResources idlingResource = new MyIdlingResources(DateUtils.SECOND_IN_MILLIS * waitingTime);
        Espresso.registerIdlingResources(idlingResource);

        onView(withText("Yellow Cake")).check(matches(isDisplayed()));
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
