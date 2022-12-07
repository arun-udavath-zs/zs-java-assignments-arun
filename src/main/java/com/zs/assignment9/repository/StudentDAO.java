package com.zs.assignment9.repository;

import com.zs.assignment9.model.Student;

public interface StudentDAO {

    Student addStudent(int id, String firstName, String lastName);

    Student fetchStudentById(int id);
}
