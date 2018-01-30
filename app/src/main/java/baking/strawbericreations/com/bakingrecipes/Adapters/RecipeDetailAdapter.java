package baking.strawbericreations.com.bakingrecipes.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baking.strawbericreations.com.bakingrecipes.Model.Recipe;
import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;

/**
 * Created by redrose on 1/24/18.
 */

public class RecipeDetailAdapter extends  RecyclerView.Adapter<RecipeDetailAdapter.DetailHolder>{

    private ArrayList<Steps> StepsItemList = new ArrayList<Steps>();

    String list_steps;


    private String recipeName;
    private Context mContext;

         public RecipeDetailAdapter(Context context,ArrayList<Steps>StepsItemList) {
        this.StepsItemList = StepsItemList;
        this.mContext = context;
    }
    public void setFullRecipeData(List<String> recipesIn, Context context) {


        list_steps = recipesIn.toString();
        Log.i("null or not",list_steps.toString());
     //   recipeName = recipesIn.get(0).getName();
      //  Log.i("detail adap name",recipeName.toString());
        notifyDataSetChanged();
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_row, viewGroup, false);
        DetailHolder viewHolder = new DetailHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DetailHolder holder, int position) {

        final  Steps steppy = StepsItemList.get(position);
       System.out.println("------------------------" + position);

        holder.textRecyclerView.setText(steppy.getShortDescription());
      Log.i("-----------",steppy.getShortDescription().toString());

    }

    @Override
    public int getItemCount() {
        Log.i("size in detail",StepsItemList.toString() );
        return StepsItemList.size();
    }

    public static class DetailHolder extends RecyclerView.ViewHolder{
        TextView textRecyclerView;

        public DetailHolder(View itemView) {
            super(itemView);
            textRecyclerView = (TextView) itemView.findViewById(R.id.detailtv);
      
        }



    }
}
