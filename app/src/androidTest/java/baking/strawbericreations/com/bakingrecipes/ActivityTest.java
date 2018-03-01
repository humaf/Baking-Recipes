package baking.strawbericreations.com.bakingrecipes;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import baking.strawbericreations.com.bakingrecipes.IdlingResources.MyIdlingResources;
import baking.strawbericreations.com.bakingrecipes.UserInterface.RecipeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by redrose on 2/27/18.
 */

public class ActivityTest  {

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<>(RecipeActivity.class);

    private MyIdlingResources mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = (MyIdlingResources) mActivityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void checkText_RecipeActivity() {
        onView(withId(R.id.recycler_recipe)).perform(RecyclerViewActions.scrollToPosition(2));
        onView(withText("Yellow Cake")).check(matches(isDisplayed()));
    }

    @Test
    public void checkPlayerViewIsVisible_RecipeDetailActivity1() {

        onView(withId(R.id.recycler_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.detail_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
