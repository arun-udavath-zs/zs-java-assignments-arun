package com.zs.assignment9.service;

import com.zs.assignment9.model.Student;
import com.zs.assignment9.repository.StudentDAOImpl;

public class StudentServiceImpl implements StudentService {

    private final StudentDAOImpl studentDAO;

    public StudentServiceImpl(StudentDAOImpl studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student addStudent(int id, String firstName, String lastName) throws Exception {
        if (firstName == null) {
            throw new ServiceException("First name is null");
        }
        if (lastName == null) {
            throw new ServiceException("Last name is null");
        }
        return studentDAO.addStudent(id, firstName, lastName);
    }

    @Override
    public Student getStudent(int id) {
        return studentDAO.fetchStudentById(id);
    }

    public static class ServiceException extends Exception {
        ServiceException(String message) {
            super(message);
        }
    }
}
