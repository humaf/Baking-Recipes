package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.List;

import baking.strawbericreations.com.bakingrecipes.Adapters.*;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.R;

public class RecipeFragment extends Fragment {

    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private RecipeAdapter adapter;

    RecyclerView recyclerView;

    private List<Recipe> recipeList = new ArrayList<>();


    public RecipeFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe,container,false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_recipe);


         adapter = new RecipeAdapter((ArrayList<Recipe>) recipeList);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setHasFixedSize(true);



        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  RecipeDownload task = new RecipeDownload();
        //   task.execute(url);

    }
/*
    public class RecipeDownload  extends AsyncTask<String, Void, ArrayList<Recipe>> {
        private ArrayList<Recipe> recipeList;



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
                JSONObject response = new JSONObject(result);
                JSONArray results = response.optJSONArray("results");
                recipeList = new ArrayList<Recipe>();
                for (int i = 0; i < results.length(); i++) {
                    JSONObject res = results.optJSONObject(i);
                    Recipe item = new Recipe();
                    String recipeName = (res.optString("name"));
                    Log.i("json data",recipeName);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return recipeList;
        }

      protected void onPostExecute(ArrayList<Recipe> result) {

          adapter.notifyDataSetChanged();

        }
    }
*/
    }




