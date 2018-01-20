package baking.strawbericreations.com.bakingrecipes.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.R;

/**
 * Created by redrose on 1/17/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    private ArrayList<Recipe> recipeItemList = new ArrayList<Recipe>();

    public class RecipeHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public RecipeHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public RecipeAdapter(ArrayList<Recipe> recipeItemList) {
        this.recipeItemList = recipeItemList;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_row, parent, false);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        Recipe recipe = recipeItemList.get(position);
        holder.title.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        Log.i("size",recipeItemList.toString());
        return recipeItemList.size();
    }

}
