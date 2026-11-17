package com.ute.studentapp.database

import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {
    // Nguồn chân lý duy nhất (Single Source of Truth)
    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun update(student: Student) {
        studentDao.updateStudent(student)
    }

    suspend fun delete(student: Student) {
        studentDao.deleteStudent(student)
    }
}
