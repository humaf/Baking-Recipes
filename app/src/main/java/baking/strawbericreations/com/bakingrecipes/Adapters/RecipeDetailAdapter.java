package baking.strawbericreations.com.bakingrecipes.Adapters;

/**
 * Created by redrose on 1/30/18.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import baking.strawbericreations.com.bakingrecipes.UserInterface.OnDetailItemListener;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;
import baking.strawbericreations.com.bakingrecipes.UserInterface.Steps_fragment;

/**
 * Created by redrose on 1/24/18.
 */

public class RecipeDetailAdapter extends  RecyclerView.Adapter<RecipeDetailAdapter.DetailHolder> {

    private ArrayList<Steps> StepsItemList = new ArrayList<Steps>();

    private OnDetailItemListener onDetailItemListener;

    String list_steps;
    private String recipeName;
    private Context mContext;

    public RecipeDetailAdapter(Context context, ArrayList<Steps> StepsItemList) {
        this.StepsItemList = StepsItemList;
        this.mContext = context;
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_row, viewGroup, false);
        return new DetailHolder(view);
    }

    public void onBindViewHolder(DetailHolder holder, int position) {
        final Steps steppy = StepsItemList.get(position);

        System.out.println("------------------------" + position);
        holder.textRecyclerView.setText(steppy.getShortDescription());
        Log.i("-----------", steppy.getShortDescription().toString());
        holder.setOnDetailItemListener(new OnDetailItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println("gottcha in adapter!!");

                Bundle bundle = new Bundle();

                bundle.putInt("poos",position);

                System.out.println("positionnnn" + position);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new Steps_fragment();

                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, myFragment)
                        .addToBackStack(null).commit();
            }
      });
    }
    @Override
    public int getItemCount() {
        Log.i("size in detail", StepsItemList.toString());
        return StepsItemList.size();
    }

    public OnDetailItemListener getOnDetailItemListener() {
        return onDetailItemListener;
    }

    public void setOnDetailItemListener(OnDetailItemListener onDetailItemListener) {
        this.onDetailItemListener = onDetailItemListener;
    }


    public static class DetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textRecyclerView;
        private OnDetailItemListener onDetailItemListener;

        public DetailHolder(View itemView) {
            super(itemView);
            textRecyclerView = (TextView) itemView.findViewById(R.id.detailtv);
            textRecyclerView.setOnClickListener(this);
        }

        public void setOnDetailItemListener(OnDetailItemListener onDetailItemListener) {
            this.onDetailItemListener = onDetailItemListener;
        }
        @Override
        public void onClick(View view) {
            onDetailItemListener.onItemClick(view, getAdapterPosition());
        }
    }
}