package com.zs.assignment9.service;

import com.zs.assignment9.dao.StudentDAOImpl;
import com.zs.assignment9.exception.BadRequestException;
import com.zs.assignment9.model.Student;

public class StudentServiceImpl implements StudentService {
    private final StudentDAOImpl studentDAO;

    public StudentServiceImpl() {
        this.studentDAO = new StudentDAOImpl();
    }

    /**
     * This method used to add the student in the database
     *
     * @param id        id of a student
     * @param firstName student first name
     * @param lastName  student last name
     * @return student added in the database
     * @throws Exception if any of the input value is null exception is thrown
     */
    @Override
    public Student addStudent(int id, String firstName, String lastName) throws BadRequestException {
        if(id < 0){
            throw new BadRequestException("id is not valid");
        }
        if (firstName == null) {
            throw new BadRequestException("First name is null");
        }
        if (lastName == null) {
            throw new BadRequestException("Last name is null");
        }
        return studentDAO.addStudent(id, firstName, lastName);
    }

    /**
     *This method used to fetch the student
     *
     * @param id student id is used to find the unique student
     * @return student fetched from database
     */
    @Override
    public Student getStudent(int id) throws BadRequestException {
        if(id < 0){
            throw new BadRequestException("id is not valid");
        }
        return studentDAO.fetchStudentById(id);
    }

}
