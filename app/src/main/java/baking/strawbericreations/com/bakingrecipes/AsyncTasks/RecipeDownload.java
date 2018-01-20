package baking.strawbericreations.com.bakingrecipes.AsyncTasks;

import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import baking.strawbericreations.com.bakingrecipes.Adapters.RecipeAdapter;
import baking.strawbericreations.com.bakingrecipes.Model.Ingredients;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;

/**
 * Created by redrose on 1/17/18.
 */
/*
public class RecipeDownload  extends AsyncTask<String, Void, ArrayList<Recipe>> {
     private ArrayList<Recipe> recipeList;
    RecyclerView myRecycler;
    RecipeAdapter adapter;

    @Override
    protected ArrayList<Recipe> doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {

                char current = (char) data;

                result += current;

                data = reader.read();

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
         JSONArray results = new JSONArray(result);
            recipeList = new ArrayList<Recipe>();
            for (int i = 0; i < results.length(); i++) {
                JSONObject res = results.optJSONObject(i);
                Recipe item = new Recipe();
                JSONArray ing = new JSONArray();
                JSONArray steps = new JSONArray();
                int id =(res.optInt("id"));
                item.setId(id);
                String recipeName = (res.optString("name"));
                Log.i("nameeeeeee",recipeName);
                item.setName(recipeName);
                 ing = (res.optJSONArray("ingredients"));
                Log.i("ingggge",ing.toString());
                item.setIngredients(ing);
                steps = (res.optJSONArray("steps"));
                item.setSteps(steps);
                Log.i("stepps",steps.toString());
                recipeList.add(item);
                Log.i("recipeList",recipeList.toString());
            }
      }catch (JSONException e) {
         e.printStackTrace();
      }
        return recipeList;
    }

  protected void onPostExecute(ArrayList<Recipe> result) {
       myRecycler.setAdapter(adapter);
    }
}

*/


