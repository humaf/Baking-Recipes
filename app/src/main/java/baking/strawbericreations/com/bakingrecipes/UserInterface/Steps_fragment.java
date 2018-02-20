package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
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
    private BandwidthMeter bandwidthMeter;
    private TextView  steps_details;
    private SimpleExoPlayer player;
    private Handler mainHandler;
    private OnDetailItemListener detailItemListener;
    Steps item;
    String v="";
    int pos;
    public Steps_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        mainHandler = new Handler();
        bandwidthMeter = new DefaultBandwidthMeter();
        simpleExoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.player_View);
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

        ImageView thumbnail = (ImageView)rootView.findViewById(R.id.thumb_Image);

        steps_details =(TextView)rootView.findViewById(R.id.recipe_step_detail_text);

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
                 item = new Steps();
                String shortDescription = (res.optString("shortDescription"));
                item.setShortDescription(shortDescription);
                String description = (res.optString("description"));
                item.setDescription(description);
                String videoURL = (res.optString("videoURL"));
                item.setVideoURL(videoURL);
                stepList.add(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            String i = bundle.getString("description");
            steps_details.setText(i);
           System.out.println("Let's see if it comes here");
             v = bundle.getString("video");
            pos = bundle.getInt("poos");
            System.out.println("if it is same or not " + pos);
            String vii = stepList.get(pos).getVideoURL();

          //  System.out.println("Emptyyyy" + v);
//            Log.i("video is empty",v);
        if(!vii.isEmpty()){
                Log.i("coming till this point",vii);
                initializePlayer(Uri.parse(vii));
            }
                else
            {
                player=null;
                simpleExoPlayerView.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_white_36dp));
                simpleExoPlayerView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
            }
            }
          //  Uri vi = Uri.parse(v);
        //  setStepsData(i,vi);

     return rootView;
    }

 //private void setStepsData(String desc,Uri vid){
/*
     private void setStepsData(String desc){
    // String s = stepList.get(position).getDescription();
     steps_details.setText(desc);
         initializePlayer(Uri.parse(v));

 }
*/
    private void initializePlayer(Uri mediaUri) {
        if (player == null) {
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);
            LoadControl loadControl = new DefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(player);
            String userAgent = Util.getUserAgent(getContext(), "Baking App");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (player!=null) {
            player.stop();
            player.release();
            player=null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }
}
