package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import baking.strawbericreations.com.bakingrecipes.Model.Ingredients;
import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;
import baking.strawbericreations.com.bakingrecipes.widget.BakingWidget;

public class RecipeDetailActivity extends AppCompatActivity {
    String toPrint = "";
    String recipe_name = "";
    static String STACK_RECIPE_DETAIL = "STACK_RECIPE_DETAIL";
    static String STACK_RECIPE_STEP_DETAIL = "STACK_RECIPE_STEP_DETAIL";

    Bundle extras;
    String Ingredients;

    private ArrayList<Ingredients> ingList = new ArrayList<>();
    private ArrayList<String> widitem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        if (savedInstanceState == null) {
            extras = getIntent().getExtras();

            int id = extras.getInt("id");
            recipe_name = extras.getString("Recipe name");
            Log.i("Recipe Name", recipe_name);

            Ingredients = (String) extras.getSerializable("Ingredients");
            Log.i("List Of Ingredients", Ingredients);


            try {
                JSONArray widgets_res = new JSONArray(Ingredients);

                for(int i=0;i<widgets_res.length();i++){
                    JSONObject jobj = widgets_res.getJSONObject(i);
                    Ingredients item = new Ingredients();
                    String ingredient = (jobj.optString("ingredient"));
                    item.setIngredient(ingredient);
                    Log.i("chhhhhhhhhhhhhyiu",ingredient);
                    ingList.add(item);
                    widitem.add(ingredient);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            for(int i=0; i<widitem.size(); i++){
                toPrint +=  widitem.get(i) + "\n";
            }

            String Steps = (String) extras.getSerializable("Steps");
            Log.i("List of Steps", Steps);

            final DetailFragment fragment1 = new DetailFragment();
            fragment1.setArguments(extras);
            Log.i("Extras coming in frag", extras.toString());


            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment1).addToBackStack(STACK_RECIPE_DETAIL)
                    .commit();

            if (findViewById(R.id.recipe_linear_layout).getTag() != null && findViewById(R.id.recipe_linear_layout).getTag().equals("tablet-land")) {
                final Steps_fragment fragment2 = new Steps_fragment();
                fragment2.setArguments(extras);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2, fragment2).addToBackStack(STACK_RECIPE_STEP_DETAIL)
                        .commit();
            } else {

            }

        } else {
            recipe_name = savedInstanceState.getString("Title");
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(recipe_name);
        updateWidget();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Recipe name", recipe_name);
//        Log.i("title", recipe_name);

    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()) {
           // Respond to the action bar's Up/Home button
           case android.R.id.home:
               NavUtils.navigateUpFromSameTask(this);
               return true;
       }

       return super.onOptionsItemSelected(item);
    }

    private void updateWidget() {
        Intent i = new Intent(this, BakingWidget.class);

        Toast.makeText(getApplicationContext(), "from the activity",
                Toast.LENGTH_SHORT).show();
        i.putExtra("INGREDIENTS", toPrint);
        sendBroadcast(i);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("recipe_name");

    }
}