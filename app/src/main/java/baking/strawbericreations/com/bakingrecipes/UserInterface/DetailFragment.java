package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baking.strawbericreations.com.bakingrecipes.Adapters.RecipeDetailAdapter;
import baking.strawbericreations.com.bakingrecipes.Model.Ingredients;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.R;

import static baking.strawbericreations.com.bakingrecipes.UserInterface.RecipeActivity.SELECTED_RECIPES;

public class DetailFragment extends Fragment {

    ArrayList<String> recipe;
    RecyclerView detailRecycler;
    TextView detailtext;
    static String STACK_RECIPE_DETAIL = "STACK_RECIPE_DETAIL";
    ArrayList<Ingredients> in;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recipe = new ArrayList<>();
        Bundle b = getActivity().getIntent().getExtras();
        int id = b.getInt("Id");
        System.out.println("value in frgad id"+ id);
        String ing = (String) b.getSerializable("Ingredients");
        String recipe_ingredients[] = ing.split("\\},\\{");

        for(int i=0;i<recipe_ingredients.length;i++){
            recipe_ingredients[i]= recipe_ingredients[i].replaceAll("[\\[\\](){}]","");
            System.out.println("Content" + recipe_ingredients[i]);
 }

       Log.i("values in detail frag",ing);
       String step =(String)b.getSerializable("Steps");
       Log.i("values of step de",step);

     /*   if (savedInstanceState != null) {
            recipe = savedInstanceState.getStringArrayList(SELECTED_RECIPES);
            Log.i("Data values in fragment",savedInstanceState.getStringArrayList(SELECTED_RECIPES).toString());
        }// else {
           // recipe = getArguments().getStringArrayList(SELECTED_RECIPES);
        //}*/
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);


        detailtext =(TextView) rootView.findViewById(R.id.recipe_detail_text);

        detailRecycler = (RecyclerView) rootView.findViewById(R.id.detail_recycler);


      detailtext.setText(recipe_ingredients[id - 1]);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        detailRecycler.setLayoutManager(mLayoutManager);
        detailRecycler.setHasFixedSize(true);

        RecipeDetailAdapter mRecipeDetailAdapter = new RecipeDetailAdapter();

        detailRecycler.setAdapter(mRecipeDetailAdapter);
        System.out.println("setting the adpater");
        mRecipeDetailAdapter.notifyDataSetChanged();
        System.out.println("notify change");
        // Inflate the layout for this fragment
        return rootView;
    }


}
