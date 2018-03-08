package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import baking.strawbericreations.com.bakingrecipes.Model.Steps;
import baking.strawbericreations.com.bakingrecipes.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Steps_fragment extends Fragment {

    ArrayList<String> recipe;
    private ArrayList<Steps> stepList = new ArrayList<>();
    @BindView(R.id.thumb_Image)
    ImageView thumbImage ;
    @BindView(R.id.player_View)
    SimpleExoPlayerView simpleExoPlayerView;
    private BandwidthMeter bandwidthMeter;
    @BindView(R.id.recipe_step_detail_text)
     TextView  steps_details;
    @BindView(R.id.previousStep)
    Button previous;
    @BindView(R.id.nextStep)
    Button next;

    private SimpleExoPlayer player;
    private Handler mainHandler;
    private OnDetailItemListener detailItemListener;
    Steps item;
    View rootView;
    int pos;

    public Steps_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        ButterKnife.bind(this,rootView);
        mainHandler = new Handler();
        bandwidthMeter = new DefaultBandwidthMeter();
        simpleExoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.player_View);
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

//           savedInstanceState.putInt("poosition",player.getCurrentPeriodIndex());
//        detailItemListener = (OnDetailItemListener) getActivity();

        recipe = new ArrayList<>();
        Bundle b = getActivity().getIntent().getExtras();

        String step = (String) b.getSerializable("Steps");

        try {
                JSONArray stepj = new JSONArray(step);
                stepList = new ArrayList<Steps>();
                for(int i=0;i<stepj.length();i++) {
                JSONObject res = stepj.optJSONObject(i);
                 item = new Steps();
                    int id = (res.optInt("id"));
                    item.setId(id);
                String shortDescription = (res.optString("shortDescription"));
                item.setShortDescription(shortDescription);
                String description = (res.optString("description"));
                item.setDescription(description);
                String thumbnail = (res.optString("thumbnail"));
                item.setThumbnailURL(thumbnail);
                String videoURL = (res.optString("videoURL"));
                item.setVideoURL(videoURL);
                stepList.add(item);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            pos = bundle.getInt("poos");
            System.out.println("if it is same or not " + pos);
            String vii = stepList.get(pos).getVideoURL();
            String des = stepList.get(pos).getDescription();
            String imageUrl=stepList.get(pos).getThumbnailURL();
            if (imageUrl!="") {
                Uri builtUri = Uri.parse(imageUrl).buildUpon().build();

                Picasso.with(getContext()).load(builtUri).into(thumbImage);
            }
            int id = stepList.get(pos).getId();
            System.out.println("iiiiiiiiiiiiiiiii" + id);
            System.out.println("description coming " + des);
            steps_details.setText(des);
            setVideo(vii);
        }
            System.out.println("check null or not" + stepList.get(pos).getId());

        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (stepList.get(pos).getId() > 0) {

                    if (player!=null){
                        player.stop();
                    }
                    setVideo(stepList.get(pos-1).getVideoURL());
                    steps_details.setText(stepList.get(pos-1).getDescription());
               //    detailItemListener.onItemClick(view,stepList.get(pos).getId() - 1);
                    pos = pos - 1;
                }
                else {
                    Toast.makeText(getActivity(),"You already are in the First step of the recipe", Toast.LENGTH_SHORT).show();
                }
            }});

       next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int lastIndex = stepList.size()-1;
                if (stepList.get(pos).getId() < stepList.get(lastIndex).getId()) {
                    if (player!=null){
                        player.stop();
                    }
                    setVideo(stepList.get(pos+1).getVideoURL());
                    steps_details.setText(stepList.get(pos+1).getDescription());
                    pos = pos + 1;
                }
                else {
                    Toast.makeText(getContext(),"You already are in the Last step of the recipe", Toast.LENGTH_SHORT).show();
                }
            }});

     return rootView;
    }
   private void setVideo(String vstring){

        if(!vstring.isEmpty()){
                Log.i("coming till this point",vstring);
                initializePlayer(Uri.parse(vstring));

          // player = null;
            if (rootView.findViewWithTag("sw600dp-land-recipe_step_detail")!=null) {
                getActivity().findViewById(R.id.fragment_container2).setLayoutParams(new LinearLayout.LayoutParams(-1,-2));
                simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
            }
            else if (isInLandscapeMode(getContext())){
                steps_details.setVisibility(View.GONE);
               player.release();
            }
            }
                else
            {
                player=null;
               Toast novideo = Toast.makeText(getContext(),"No video available for this step",Toast.LENGTH_LONG);
                novideo.show();

            }
    }

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


    public boolean isInLandscapeMode( Context context ) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
