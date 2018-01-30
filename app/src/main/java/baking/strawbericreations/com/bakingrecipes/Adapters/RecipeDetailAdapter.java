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
    String list_steps;
    private ArrayList<String> StepsItemList = new ArrayList<String>();
    private String[] topp = new String[20];
  //  private StepsItemList[] = new String[];
    private String recipeName;
    private Context mContext;

 //   public RecipeDetailAdapter(Context context,ArrayList<String> StepsItemList) {
         public RecipeDetailAdapter(Context context,String[] StepsItemList) {   
        this.topp = StepsItemList;
        this.mContext = context;
    }
   // public void setFullRecipeData(List<String> recipesIn, Context context) {

    public void setFullRecipeData(String[] recipesIn, Context context) {
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
        final  String step = topp[position];
        Log.i("steps in adapter",step.toString());
    //    holder.textRecyclerView.setText(step.getId() + ". " + step.getShortDescription());
          holder.textRecyclerView.setText(step);
    }

    @Override
    public int getItemCount() {
        int count = topp.length;
       Log.i("size in detail", String.valueOf(count));
        return topp.length;
    }

    public static class DetailHolder extends RecyclerView.ViewHolder{
        TextView textRecyclerView;

        public DetailHolder(View itemView) {
            super(itemView);
            textRecyclerView = (TextView) itemView.findViewById(R.id.detailtv);
          //  itemView.setOnClickListener(this);
        }



    }
}
