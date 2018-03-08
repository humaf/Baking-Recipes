package baking.strawbericreations.com.bakingrecipes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.R;
import baking.strawbericreations.com.bakingrecipes.UserInterface.OnItemClickListener;
import baking.strawbericreations.com.bakingrecipes.UserInterface.RecipeDetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

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
     final  Recipe recipe = recipeItemList.get(position);
       System.out.println("------------------------" + position);

        holder.title.setText(recipe.getName());
      Log.i("-----------",recipe.getName().toString());
        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println("gottcha!!");
                final  Intent intent = new Intent(mContext, RecipeDetailActivity.class);

                intent.putExtra("Recipe name",recipe.getName());
                intent.putExtra("Id",recipe.getId());
                Log.i("ID",recipe.getId().toString());
                intent.putExtra("Ingredients", recipe.getIngredients().toString());
                Log.i("ing adpater ",recipe.getIngredients().toString());
                intent.putExtra("Steps",recipe.getSteps().toString());
                mContext.startActivity(intent);
            }
    });
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

        @BindView(R.id.recipeText)
        public TextView title;
        public int position;
        private OnItemClickListener onItemClickListener;

        public RecipeHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            title.setOnClickListener(this);
        }

        public void setOnItemClickListener(OnItemClickListener iListener){
            this.onItemClickListener = iListener;
        }
        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
