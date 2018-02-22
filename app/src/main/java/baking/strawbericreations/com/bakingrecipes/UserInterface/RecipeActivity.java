package baking.strawbericreations.com.bakingrecipes.UserInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import baking.strawbericreations.com.bakingrecipes.R;

public class RecipeActivity extends AppCompatActivity {
    static String SELECTED_RECIPES="Selected_Recipes";

    public static String ALL_RECIPES="All_Recipes";
    Toolbar toolbar;
    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Baking Recipe");
    }


}
