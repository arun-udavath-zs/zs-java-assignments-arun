package com.zs.assignment7.controller;

import com.zs.assignment7.exception.FileException;
import com.zs.assignment7.exception.InternalServerException;
import com.zs.assignment7.model.Student;
import com.zs.assignment7.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController() {
        this.studentService = new StudentService();
    }

    public void start() {

        try {
            studentService.createStudentTable();
            studentService.insertDataIntoStudentTable();
            studentService.insertDataIntoDepartment();
            studentService.alterStudentTable();
            studentService.updateStudentTable();
            List<Student> studentList = studentService.getStudentWithDept();
            studentService.saveToFile(studentList);
            studentService.compressFile();
        } catch (InternalServerException e) {
            logger.error("Something went wrong: " + e.getMessage());
        } catch (FileException e){
            logger.error("Something went wrong :"+ e.getMessage());
        }

    }
}
