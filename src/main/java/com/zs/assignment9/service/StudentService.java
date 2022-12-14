package com.zs.assignment9.service;

import com.zs.assignment9.exception.BadRequestException;
import com.zs.assignment9.model.Student;

public interface StudentService {

    Student addStudent(int id, java.lang.String firstName, java.lang.String lastName) throws BadRequestException;

    Student getStudent(int id) throws BadRequestException;
}
