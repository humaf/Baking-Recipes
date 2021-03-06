package baking.strawbericreations.com.bakingrecipes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;
import baking.strawbericreations.com.bakingrecipes.UserInterface.OnItemClickListener;
import baking.strawbericreations.com.bakingrecipes.UserInterface.RecipeDetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

import static android.support.test.InstrumentationRegistry.getContext;

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
   //     final Steps step = stepItemList.get(position);
       System.out.println("------------------------" + position);
        String imageUrl = recipe.getImage();
          JSONArray stepitem = recipe.getSteps();
            JSONObject jobj = stepitem.optJSONObject(position);
        String imageUrl1="";
        try {
            imageUrl1 = jobj.getString("thumbnailURL");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("","value of imageUrl1" + imageUrl1);
        System.out.println(" null or empty lets see" + imageUrl1);

        if (imageUrl != null) {
            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            Picasso.with(getContext()).load(builtUri).into(holder.recipeImageView);
        }
  /* else if(imageUrl1 != "") {
            Uri builtUri1 = Uri.parse(imageUrl1).buildUpon().build();
        Picasso.with(getContext()).load(builtUri1).into(holder.recipeImageView);
        //Getting Illegal State exception on line 80.
        }
*/
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

  @Nullable  @BindView(R.id.thumb_Image)
       ImageView recipeImageView;
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
