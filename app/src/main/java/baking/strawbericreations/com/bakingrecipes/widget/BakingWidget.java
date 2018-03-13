package baking.strawbericreations.com.bakingrecipes.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.ArrayList;
import baking.strawbericreations.com.bakingrecipes.R;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {


    static ArrayList<String> ingredientsList = new ArrayList<>();


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = "Recipe";
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        if(extras!=null) {
            String title1 = extras.getString("INGREDIENTS");//this value does not come through


            ComponentName provider = new ComponentName(context.getApplicationContext(), BakingWidget.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
            AppWidgetManager manager = AppWidgetManager.getInstance(context.getApplicationContext());


            views.setTextViewText(R.id.appwidget_text, title1);

            //Update the widget
            manager.updateAppWidget(provider, views);

        }
        //  super.onReceive(context, intent);
    }
}

