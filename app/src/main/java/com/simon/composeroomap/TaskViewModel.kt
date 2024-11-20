package com.simon.composeroomap.modelview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simon.composeroomap.data.Task
import com.simon.composeroomap.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val daoTareas: TaskDao) : ViewModel() {
    private val _tareas = MutableStateFlow<List<Task>>(emptyList())
    val tareas: StateFlow<List<Task>> get() = _tareas

    init {
        cargarTareas()
    }

    private fun cargarTareas() {
        viewModelScope.launch {
            _tareas.value = daoTareas.getAllTasks()
        }
    }

    fun agregarTarea(nombreTarea: String) {
        viewModelScope.launch {
            val nuevaTarea = Task(name = nombreTarea)
            daoTareas.insert(nuevaTarea)
            cargarTareas()
        }
    }

    fun eliminarTarea(tarea: Task) {
        viewModelScope.launch {
            daoTareas.delete(tarea)
            cargarTareas()
        }
    }
}
