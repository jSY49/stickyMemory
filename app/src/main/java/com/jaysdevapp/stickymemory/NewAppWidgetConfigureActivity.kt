package com.jaysdevapp.stickymemory

import android.annotation.SuppressLint
import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allmyreview.myDDayAdapter
import com.jaysdevapp.stickymemory.Dao.ddayDao
import com.jaysdevapp.stickymemory.Repository.DdayRepository
import com.jaysdevapp.stickymemory.database.AppDatabase_dday
import com.jaysdevapp.stickymemory.databinding.NewAppWidgetConfigureBinding
import com.jaysdevapp.stickymemory.dataclasses.Dday
import com.jaysdevapp.stickymemory.viewModel.DdayViewModel

/**
 * The configuration screen for the [NewAppWidget] AppWidget.
 */
class NewAppWidgetConfigureActivity : Activity() {
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private var onClickListener = View.OnClickListener {    //add widget 버튼 리스너
        val context = this@NewAppWidgetConfigureActivity
        if(ddayAdapter.checkId!=-1){
            Log.d("NewAppWidgetConfigureActivity","check  = ${ddayAdapter.getcheckData()}")
        }

        // When the button is clicked, store the string locally
        saveTitlePref(context, appWidgetId, ddayAdapter.getcheckData()) //디데이 저장
        // It is the responsibility of the configuration activity to update the app widget
        val appWidgetManager = AppWidgetManager.getInstance(context)
        updateAppWidget(context, appWidgetManager, appWidgetId)

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }
    private lateinit var binding: NewAppWidgetConfigureBinding
    private var ddayAdapter = myDDayAdapter(arrayListOf())
    private lateinit var ddayViewModel: DdayViewModel
    @SuppressLint("NotifyDataSetChanged")
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED)

        binding = NewAppWidgetConfigureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener(onClickListener)

        binding.ddayRecycler.apply {
            layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            adapter = ddayAdapter
        }


        val repository: MutableLiveData<DdayRepository> = MutableLiveData()
        val ddayDao = AppDatabase_dday.getDatabase(this)!!.ddayDao()
        repository.value = DdayRepository(ddayDao)
        var readAllData = repository.value!!.readAllData

        readAllData.observe(ProcessLifecycleOwner.get(), Observer {
            readAllData.value?.let {
                Log.d("NewAppWidgetConfigureActivity", "onCreate - ${it}")
                ddayAdapter.update(it)
                ddayAdapter.notifyDataSetChanged()

            }

        })


        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

//        appWidgetText.setText(loadTitlePref(this@NewAppWidgetConfigureActivity, appWidgetId))
    }

}


private const val PREFS_NAME = "com.jaysdevapp.stickymemory.NewAppWidget"
private const val PREF_PREFIX_KEY = "appwidget_"

// Write the prefix to the SharedPreferences object for this widget
internal fun saveTitlePref(context: Context, appWidgetId: Int, dday: Dday) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
//    prefs.putString(PREF_PREFIX_KEY + appWidgetId, text)

    var setData = setOf(dday.ddayThing,dday.date)
    prefs.putStringSet(PREF_PREFIX_KEY+appWidgetId, setData)
    prefs.apply()
}

// Read the prefix from the SharedPreferences object for this widget.
// If there is no preference saved, get the default from a resource
internal fun loadTitlePref(context: Context, appWidgetId: Int):  Set<String?> {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
    val setValue = prefs.getStringSet(PREF_PREFIX_KEY + appWidgetId, null)
//    return titleValue ?: context.getString(R.string.appwidget_text)
    return setValue ?: setOf("","")
}

internal fun deleteTitlePref(context: Context, appWidgetId: Int) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.remove(PREF_PREFIX_KEY + appWidgetId)
    prefs.apply()
}