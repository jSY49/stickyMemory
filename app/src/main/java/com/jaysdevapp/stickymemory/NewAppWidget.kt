package com.jaysdevapp.stickymemory

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [NewAppWidgetConfigureActivity]
 */
class NewAppWidget : AppWidgetProvider() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            deleteTitlePref(context, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

@RequiresApi(Build.VERSION_CODES.S)
internal fun
        updateAppWidget(   //여기서 ui업데이트
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val dateText = loadTitlePref_date(context, appWidgetId)
    val nameText = loadTitlePref_name(context, appWidgetId)

    val views = RemoteViews(context.packageName, R.layout.new_app_widget)   //Ui remote하고
    // Construct the RemoteViews object

    if(!dateText.isNullOrBlank()){
        views.setTextViewText(R.id.appwidget_text_nm, nameText)
        views.setTextViewText(R.id.appwidget_text_date, calDate(dateText) )

    }

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}