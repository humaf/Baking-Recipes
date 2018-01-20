package baking.strawbericreations.com.bakingrecipes.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.List;

/**
 * Created by redrose on 1/17/18.
 */

public class Recipe implements Serializable {

    private Integer id;
    private String name;
    private JSONArray ingredients = null;
    private JSONArray steps = null;

    private Integer servings;
    private String image;


    public Recipe(){
        super();
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public JSONArray getIngredients() {
        return ingredients;
    }

    public void setIngredients(JSONArray ingredients) {
        this.ingredients = ingredients;
    }



    public JSONArray getSteps() {
        return steps;
    }

    public void setSteps(JSONArray steps) {
        this.steps = steps;
    }



    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
