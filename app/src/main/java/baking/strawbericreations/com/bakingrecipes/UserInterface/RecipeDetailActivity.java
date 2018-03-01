package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;

public class RecipeDetailActivity extends AppCompatActivity {
    String recipe_name="";
    static String STACK_RECIPE_DETAIL="STACK_RECIPE_DETAIL";
    static String STACK_RECIPE_STEP_DETAIL="STACK_RECIPE_STEP_DETAIL";
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        if (savedInstanceState == null) {
            extras = getIntent().getExtras();

            int id = extras.getInt("id");
            recipe_name = extras.getString("Recipe name");
            Log.i("Recipe Name", recipe_name);

            String Ingredients = (String) extras.getSerializable("Ingredients");
            Log.i("List Of Ingredients", Ingredients);
            String Steps = (String) extras.getSerializable("Steps");
            Log.i("List of Steps", Steps);

            final DetailFragment fragment1 = new DetailFragment();
            fragment1.setArguments(extras);
            Log.i("Extras coming in frag",extras.toString());


   FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,fragment1).addToBackStack(STACK_RECIPE_DETAIL)
                    .commit();

            if (findViewById(R.id.recipe_linear_layout).getTag()!=null && findViewById(R.id.recipe_linear_layout).getTag().equals("tablet-land")) {
                final Steps_fragment fragment2 = new Steps_fragment();
                fragment2.setArguments(extras);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2, fragment2).addToBackStack(STACK_RECIPE_STEP_DETAIL)
                        .commit();
            }
            else
            {

            }

        }
        else {
            recipe_name= savedInstanceState.getString("Title");
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(recipe_name);
   }


    public void onListItemClick(List<Steps> stepsOut, int clickedItemIndex, String recipeName,String Steps) {

        final Steps_fragment fragment = new Steps_fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        getSupportActionBar().setTitle(recipeName);
        Bundle stepBundle = new Bundle();
        stepBundle.putString("Title",recipeName);
        stepBundle.putString("Steps",Steps);
        Log.i("cccccccccccccc",stepBundle.toString());
        fragment.setArguments(stepBundle);

        fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(STACK_RECIPE_STEP_DETAIL)
                    .commit();

    }

   @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Recipe name",recipe_name);
        Log.i("title",recipe_name);
    }


    }
