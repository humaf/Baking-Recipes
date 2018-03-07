package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
import baking.strawbericreations.com.bakingrecipes.Adapters.*;
import baking.strawbericreations.com.bakingrecipes.IdlingResources.MyIdlingResources;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeFragment extends Fragment
{

    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private RecipeAdapter adapter ;

    @BindView(R.id.recycler_recipe)
    RecyclerView recyclerView;

    public RecipeFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this,rootView);

        Toast nonetwork = Toast.makeText(getContext(),"Please Connect to the Internet",Toast.LENGTH_LONG);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        MyIdlingResources idlingResource = (MyIdlingResources)((RecipeActivity)getActivity()).getIdlingResource();

        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        if(!isNetworkAvailable(getContext())== false) {
            RecipeDownload task = new RecipeDownload();
            task.execute(url);
        }
        else
                nonetwork.show();

        return rootView;
    }

    public Boolean isNetworkAvailable(Context context){

        Boolean resultValue = false; // Initial Value

        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            resultValue = true;
        }
        return resultValue;
    }


    public class RecipeDownload  extends AsyncTask<String, Void, ArrayList<Recipe>> {
        private ArrayList<Recipe> recipeList;

        @Override
        protected ArrayList<Recipe> doInBackground(String... urls) {
            long st = System.currentTimeMillis();
            Log.i("TAG", "starting");
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
            long requestEndTime = System.currentTimeMillis();
            Log.i("TAG", "ending");

            try {
                 JSONArray results = new JSONArray(result);
                recipeList = new ArrayList<Recipe>();
                for (int i = 0; i < results.length(); i++) {
                JSONObject res = results.optJSONObject(i);
                Recipe item = new Recipe();
                JSONArray ing; //= new JSONArray();
                JSONArray steps; //= new JSONArray();
                    JSONArray ing_widget;
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
            long end = System.currentTimeMillis();
            long totaltime  = requestEndTime -st;
            System.out.println("++++++++++++++++++++++++time" + totaltime);
            long Parsetime = end - requestEndTime;
            System.out.println("--------------------------time" + Parsetime);

            Log.i( "","request end " + totaltime );
            Log.i("", "parse time "  +Parsetime);
        return recipeList;
}
      protected void onPostExecute(ArrayList<Recipe> result) {
          long st1 = System.currentTimeMillis();
          adapter = new RecipeAdapter(getContext(),result);
          long st2 = System.currentTimeMillis();
          Log.i("", "st2 - st1 "+(st2-st1));
          recyclerView.setAdapter(adapter);
          long st3 = System.currentTimeMillis();
          Log.i("", "st3 - st2 "+(st3-st2));
          adapter.notifyDataSetChanged();
          long st4 = System.currentTimeMillis();
          Log.i("", "st4 - st3 "+(st4-st3));
          adapter.setOnItemClickListener(new OnItemClickListener() {
               @Override
               public void onItemClick(View view, int position) {
                   Intent RecipeIntent = new Intent(getActivity(),RecipeDetailActivity.class);
                   getActivity().startActivity(RecipeIntent);
               }

        });
          long st5 = System.currentTimeMillis();
          Log.i("", "st5 - st4 "+(st5-st4));
      }
    }

    }
