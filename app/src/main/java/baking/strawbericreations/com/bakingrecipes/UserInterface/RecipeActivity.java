package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import baking.strawbericreations.com.bakingrecipes.AsyncTasks.RecipeDownload;
import baking.strawbericreations.com.bakingrecipes.R;

public class RecipeActivity extends AppCompatActivity {

    Toolbar toolbar;
    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Baking Time");

        Toast networkerror = Toast.makeText(this, "Please connect to the INTERNET", Toast.LENGTH_LONG);


    if (!isNetworkAvailable(this) == false) {

          RecipeDownload task = new RecipeDownload();
           task.execute(url);
       }
       else
         networkerror.show();
    }



    public Boolean isNetworkAvailable(Context context){

        Boolean resultValue = false; // Initial Value

        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            resultValue = true;
        }

        return resultValue;
    }

}
