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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import baking.strawbericreations.com.bakingrecipes.Adapters.RecipeAdapter;
import baking.strawbericreations.com.bakingrecipes.Model.Ingredients;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.R;

/**
 * Created by redrose on 1/17/18.
 */

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

        JSONObject response = null;
      //  try {
           // response = new JSONObject(result);
            JSONArray results = response.optJSONArray("Value");
            Log.i("json res", results.toString());
            recipeList = new ArrayList<Recipe>();
            for (int i = 0; i < results.length(); i++) {
                JSONObject res = results.optJSONObject(i);
                Recipe item = new Recipe();
                String recipeName = (res.optString("name"));
                item.setName(recipeName);
                Log.i("json data", recipeName);
            }
     //   }catch (JSONException e) {
       //     e.printStackTrace();
       // }



        return recipeList;
    }


   protected void onPostExecute(ArrayList<Recipe> result) {

       myRecycler.setAdapter(adapter);
    }
}




