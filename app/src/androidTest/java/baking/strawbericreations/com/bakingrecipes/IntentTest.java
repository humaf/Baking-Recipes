package baking.strawbericreations.com.bakingrecipes;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import baking.strawbericreations.com.bakingrecipes.IdlingResources.MyIdlingResources;
import baking.strawbericreations.com.bakingrecipes.UserInterface.RecipeActivity;
import baking.strawbericreations.com.bakingrecipes.UserInterface.RecipeDetailActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.text.format.DateUtils;

import java.util.concurrent.TimeUnit;


/**
 * Created by redrose on 2/28/18.
 */

public class IntentTest {

    @Rule
    public IntentsTestRule<RecipeActivity> mActivityTestRule = new IntentsTestRule<RecipeActivity>(RecipeActivity.class);


    @Before
    public void resetTimeout() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS);
    }


    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
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

        onView(withId(R.id.recycler_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));

        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(waitingTime * 2, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(waitingTime * 2, TimeUnit.SECONDS);

        MyIdlingResources idlingResource = new MyIdlingResources(DateUtils.SECOND_IN_MILLIS * waitingTime);
        Espresso.registerIdlingResources(idlingResource);

        intended(hasComponent(RecipeDetailActivity.class.getName()));
        Espresso.unregisterIdlingResources(idlingResource);
    }


}
