package com.zs.assignment9.service;

import com.zs.assignment9.model.Student;
import com.zs.assignment9.repository.StudentDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentDAOImpl studentDAO;

    public StudentServiceImpl(StudentDAOImpl studentDAO) {
        this.studentDAO = studentDAO;
    }

    /**
     * used to add the student in the database
     *
     * @param id        id of a student
     * @param firstName student first name
     * @param lastName  student last name
     * @return student added in the database
     * @throws Exception if any of the input value is null exception is thrown
     */
    @Override
    public Student addStudent(int id, String firstName, String lastName) throws Exception {
        if (firstName == null) {
            logger.error("first name is null");
            throw new ServiceException("First name is null");
        }
        if (lastName == null) {
            logger.error("last name is null");
            throw new ServiceException("Last name is null");
        }
        return studentDAO.addStudent(id, firstName, lastName);
    }

    /**
     * used to fetch the student
     *
     * @param id student id is used to find the unique student
     * @return student fetched from database
     */
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
