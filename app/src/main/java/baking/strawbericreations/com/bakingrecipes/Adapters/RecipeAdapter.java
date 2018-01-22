package baking.strawbericreations.com.bakingrecipes.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.R;
import baking.strawbericreations.com.bakingrecipes.UserInterface.OnItemClickListener;

/**
 * Created by redrose on 1/17/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    private ArrayList<Recipe> recipeItemList = new ArrayList<Recipe>();
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private  static Recipe item;



    public RecipeAdapter(Context context, ArrayList<Recipe> recipeItemList) {
        this.recipeItemList = recipeItemList;
        this.mContext = context;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_row, parent, false);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
    final    Recipe recipe = recipeItemList.get(position);
        holder.title.setText(recipe.getName());
        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Recipe item) {
                Toast toast = Toast.makeText(mContext,"item clicked",Toast.LENGTH_LONG);
            }
        });

     /*   View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(recipe);
            }
        };*/
    }

    @Override
    public int getItemCount() {
        Log.i("size",recipeItemList.toString());
        return recipeItemList.size();
    }
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public  static  class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        private OnItemClickListener onItemClickListener;

        public RecipeHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.recipeText);
            view.setOnClickListener(this);
        }

        public void setOnItemClickListener(OnItemClickListener iListener){
            this.onItemClickListener = iListener;
        }
        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(item);
        }
    }

}
