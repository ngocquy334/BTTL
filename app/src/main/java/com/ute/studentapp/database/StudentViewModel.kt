package com.ute.studentapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {
    // Chuyển đổi Flow từ Room sang LiveData để Fragment dễ dàng quan sát trên UI
    val allStudents: LiveData<List<Student>> = repository.allStudents.asLiveData(Dispatchers.IO)

    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(student)
    }

    fun update(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(student)
    }

    fun delete(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(student)
    }
}

class StudentViewModelFactory(private val repository: StudentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
