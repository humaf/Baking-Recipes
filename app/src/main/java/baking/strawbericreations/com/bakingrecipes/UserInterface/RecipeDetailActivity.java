package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


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
            /*
                ArrayList<Steps> =

             */


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
        }
        else {
            recipe_name= savedInstanceState.getString("Title");
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(recipe_name);
   }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Recipe name",recipe_name);
        Log.i("title",recipe_name);
    }


    }
