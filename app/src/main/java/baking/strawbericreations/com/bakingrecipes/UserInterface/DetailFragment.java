package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import baking.strawbericreations.com.bakingrecipes.Adapters.RecipeDetailAdapter;
import baking.strawbericreations.com.bakingrecipes.Model.Ingredients;

import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;

import static baking.strawbericreations.com.bakingrecipes.UserInterface.RecipeActivity.SELECTED_RECIPES;

public class DetailFragment extends Fragment {

    ArrayList<String> recipe;

    RecyclerView detailRecycler;
    TextView detailtext;
    static String STACK_RECIPE_DETAIL = "STACK_RECIPE_DETAIL";

    private ArrayList<Steps> stepList = new ArrayList<>();
    private ArrayList<Ingredients> ingList = new ArrayList<>();

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        detailRecycler = (RecyclerView) rootView.findViewById(R.id.detail_recycler);

        detailtext =(TextView) rootView.findViewById(R.id.recipe_detail_text);

        recipe = new ArrayList<>();
        Bundle b = getActivity().getIntent().getExtras();
        int id = b.getInt("Id");
        System.out.println("value in frgad id"+ id);
        String ing = (String) b.getSerializable("Ingredients");


        try {
            JSONArray ingj = new JSONArray(ing);
            ingList = new ArrayList<Ingredients>();
            for(int i=0;i<ingj.length();i++) {
                JSONObject res = ingj.optJSONObject(i);
                Ingredients item = new Ingredients();
                double quantity = (res.optDouble("quantity"));
                item.setQuantity(quantity);
                String measure = (res.optString("measure"));
                item.setMeasure(measure);
                String ingredient = (res.optString("ingredient"));
                item.setIngredient(ingredient);
                detailtext.append("\u2022 " + item.getIngredient() + "\n");
                detailtext.append("\t\t\t Quantity: " + item.getQuantity().toString() + "\n");
                detailtext.append("\t\t\t Measure: " +item.getMeasure() + "\n\n");
                ingList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

      String step =(String)b.getSerializable("Steps");
        try {
            JSONArray stepj = new JSONArray(step);
            stepList = new ArrayList<Steps>();
            for(int i=0;i<stepj.length();i++) {
                JSONObject res = stepj.optJSONObject(i);
                Steps item = new Steps();
                String shortDescription = (res.optString("shortDescription"));
                item.setShortDescription(shortDescription);
                String description = (res.optString("description"));
                item.setDescription(description);
                String videoURL = (res.optString("videoURL"));
                item.getVideoURL();
                stepList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
            System.gc();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        detailRecycler.setLayoutManager(mLayoutManager);
        detailRecycler.setHasFixedSize(true);

    RecipeDetailAdapter mRecipeDetailAdapter = new RecipeDetailAdapter(getContext(),stepList);

        detailRecycler.setAdapter(mRecipeDetailAdapter);
        System.out.println("setting the adpater");
        mRecipeDetailAdapter.notifyDataSetChanged();
        System.out.println("notify change");
        // Inflate the layout for this fragment


        return rootView;
    }


}
