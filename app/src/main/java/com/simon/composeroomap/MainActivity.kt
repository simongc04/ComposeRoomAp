package com.simon.composeroomap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.simon.composeroomap.data.AppDatabase
import com.simon.composeroomap.modelview.TaskViewModel
import com.simon.composeroomap.view.TaskApp

class MainActivity : ComponentActivity() {

     lateinit var database: AppDatabase

     var taskViewModel: TaskViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = AppDatabase.getDatabase(applicationContext)

        taskViewModel = TaskViewModel(database.taskDao())
        setContent {
            taskViewModel?.let {
                TaskApp(it)
            }
        }
    }
}
