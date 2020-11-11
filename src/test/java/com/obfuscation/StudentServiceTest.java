package com.obfuscation;

import com.obfuscation.domain.Student;
import com.obfuscation.domain.Teacher;
import com.obfuscation.service.StudentService;
import com.obfuscation.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Test
    void save() {

        Teacher teacher = Teacher.builder()
                .firstName("Reza")
                .lastName("Ebrahimi")
                .telephone("0022")
                .build();
        teacherService.save(teacher);

        Student student = Student.builder()
                .firstName("Mohsen")
                .lastName("Mansouri")
                .nationalCode("0000000000")
                .address("Tehran")
                .telephone("000")
                .studentNumber("321")
                .teacher(teacher)
                .build();

        studentService.save(student);

        Student studentById = studentService.getById(student.getId());

        Teacher teacherById = teacherService.getById(teacher.getId());

        assertNotNull(studentById);
        assertEquals("Mohsen", studentById.getFirstName());
        assertEquals("Mansouri", studentById.getLastName());
        assertEquals("Tehran", studentById.getAddress());
        assertEquals("000", studentById.getTelephone());
        assertEquals("0000000000", studentById.getNationalCode());
        assertEquals("321", studentById.getStudentNumber());
        assertEquals("Reza", teacherById.getFirstName());
        assertEquals("Ebrahimi", teacherById.getLastName());
        assertEquals("0022", teacherById.getTelephone());
    }

    @Test
    void delete() {
        Student student = createStudent();
        studentService.save(student);

        studentService.delete(student.getId());

        assertThrows(IllegalArgumentException.class, () -> studentService.getById(student.getId()));
    }

    @Test
    void getById() {
        Student student = createStudent();
        studentService.save(student);

        Student studentById = studentService.getById(student.getId());

        assertNotNull(studentById);
        assertEquals("Mohsen", studentById.getFirstName());
        assertEquals("Mansouri", studentById.getLastName());
        assertEquals("Tehran", studentById.getAddress());
        assertEquals("000", studentById.getTelephone());
        assertEquals("0000000000", studentById.getNationalCode());
        assertEquals("321", studentById.getStudentNumber());
    }

    @Test
    void update() {
        Student student = createStudent();
        studentService.save(student);

        Student updatedStudent = Student.builder()
                .studentNumber("111")
                .telephone("001")
                .build();

        studentService.update(student.getId(), updatedStudent);

        Student studentById = studentService.getById(student.getId());

        assertNotNull(studentById);
        assertEquals("Mohsen", studentById.getFirstName());
        assertEquals("Mansouri", studentById.getLastName());
        assertEquals("Tehran", studentById.getAddress());
        assertEquals("001", studentById.getTelephone());
        assertEquals("0000000000", studentById.getNationalCode());
        assertEquals("111", studentById.getStudentNumber());
    }

    @Test
    void getAllStudents() {
        Student student1 = createStudent();
        studentService.save(student1);

        Student student2 = Student.builder()
                .firstName("Liam")
                .lastName("Mansouri")
                .nationalCode("9999999999")
                .address("Tehran")
                .telephone("112")
                .studentNumber("222")
                .build();
        studentService.save(student2);

        List<Student> allStudent = studentService.getAllStudent();

        assertEquals(2, allStudent.size());
    }

    private Student createStudent() {
        return Student.builder()
                .firstName("Mohsen")
                .lastName("Mansouri")
                .nationalCode("0000000000")
                .address("Tehran")
                .telephone("000")
                .studentNumber("321")
                .build();
    }
}
