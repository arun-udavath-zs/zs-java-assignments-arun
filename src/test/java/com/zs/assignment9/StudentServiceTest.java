package com.zs.assignment9;

import com.zs.assignment9.model.Student;
import com.zs.assignment9.repository.StudentDAOImpl;
import com.zs.assignment9.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentDAOImpl studentDAO;

    @InjectMocks
    private StudentServiceImpl studentService;

    static Stream<Arguments> args_sum_exceptions() {
        return Stream.of(
                arguments(1, null, "ram"),
                arguments(1, "sam", null)
        );
    }

    @Test
    void addStudent() throws Exception {
        Student dummyStudent = new Student(1, "sam", "ram");
        Mockito.when(studentDAO.addStudent(Mockito.anyInt(), Mockito.anyString(),
                Mockito.anyString())).thenReturn(dummyStudent);
        Student actualStudent = studentService.addStudent(1, "sam", "ram");
        assertEquals("sam", actualStudent.getFirstName());

    }

    @Test
    void getStudent() {
        Student dummyStudent = new Student(1, "rakesh", "kucharla");

        Mockito.when(studentDAO.fetchStudentById(Mockito.anyInt())).thenReturn(dummyStudent);
        Student actualStudent = studentService.getStudent(1);
        assertEquals("rakesh", actualStudent.getFirstName());
    }

    @ParameterizedTest
    @MethodSource("args_sum_exceptions")
    void test_sum_exceptions(int id, String firstName, String lastName) throws Exception {
        try {
            Student student = studentService.addStudent(id, firstName, lastName);
            fail("Wasn't expecting a result!!!!");
        } catch (StudentServiceImpl.ServiceException e) {
            //succeeded
        }
    }
}