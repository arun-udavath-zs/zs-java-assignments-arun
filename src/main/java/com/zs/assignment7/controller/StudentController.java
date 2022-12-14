package com.zs.assignment7.controller;

import com.zs.assignment7.exception.DatabaseConnectionFailedException;
import com.zs.assignment7.exception.InternalServerErrorException;
import com.zs.assignment7.model.Student;
import com.zs.assignment7.service.DepartmentService;
import com.zs.assignment7.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    private final DepartmentService departmentService;

    public StudentController() {
        this.studentService = new StudentService();
        this.departmentService = new DepartmentService();
    }


    public void start() {

        try {
            departmentService.insertDataIntoDepartment();
            studentService.createStudentTable();
            studentService.insertDataIntoStudentTable();
            List<Student> studentList = studentService.innerJoinStudentAndDept();
            studentService.saveToFile(studentList);
            studentService.compressFile();
        } catch (DatabaseConnectionFailedException e) {
            logger.error("Something went wrong: " + e.getMessage());
        } catch (InternalServerErrorException e) {
            logger.error("Something went wrong: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong :" + e.getMessage());
        }

    }
}
