package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;


public class Steps_fragment extends Fragment {

    ArrayList<String> recipe;
    private ArrayList<Steps> stepList = new ArrayList<>();

    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;
    private OnDetailItemListener detailItemListener;

    public Steps_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        simpleExoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.player_View);
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

        ImageView thumbnail = (ImageView)rootView.findViewById(R.id.thumb_Image);

        TextView  steps_details =(TextView)rootView.findViewById(R.id.recipe_step_detail_text);

        Button previous =(Button)rootView.findViewById(R.id.previousStep);

        Button next = (Button)rootView.findViewById(R.id.nextStep);

        recipe = new ArrayList<>();
        Bundle b = getActivity().getIntent().getExtras();
        String steps = b.getString("Steps");

        String step = (String) b.getSerializable("Steps");

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
                item.setVideoURL(videoURL);
                stepList.add(item);
               steps_details.setText(description);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }

}
