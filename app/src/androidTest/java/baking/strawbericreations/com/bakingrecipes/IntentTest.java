package baking.strawbericreations.com.bakingrecipes;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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


/**
 * Created by redrose on 2/28/18.
 */

public class IntentTest {

    @Rule
    public IntentsTestRule<RecipeActivity> mActivityTestRule = new IntentsTestRule<RecipeActivity>(RecipeActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void checkIntent_RecipeDetailActivity() {
        //   onView(withId(R.id.recycler_view)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.recycler_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        intended(hasComponent(RecipeDetailActivity.class.getName()));

    }
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}
