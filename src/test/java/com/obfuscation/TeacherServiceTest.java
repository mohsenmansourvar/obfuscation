package com.obfuscation;

import com.obfuscation.domain.Teacher;
import com.obfuscation.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    void save() {
        Teacher teacher = createTeacher();
        teacherService.save(teacher);

        Teacher teacherById = teacherService.getById(teacher.getId());

        assertNotNull(teacherById);
        assertEquals("Ali", teacherById.getFirstName());
        assertEquals("Ebrahimi", teacherById.getLastName());
        assertEquals("1111111111", teacherById.getNationalCode());
        assertEquals("Tehran", teacherById.getAddress());
        assertEquals("123", teacherById.getTelephone());
    }

    @Test
    void getById() {
        Teacher teacher = createTeacher();
        teacherService.save(teacher);

        Teacher teacherById = teacherService.getById(teacher.getId());

        assertNotNull(teacherById);
        assertEquals("Ali", teacherById.getFirstName());
        assertEquals("Ebrahimi", teacherById.getLastName());
        assertEquals("1111111111", teacherById.getNationalCode());
        assertEquals("Tehran", teacherById.getAddress());
        assertEquals("123", teacherById.getTelephone());

    }

    @Test
    void delete() {
        Teacher teacher = createTeacher();
        teacherService.save(teacher);

        teacherService.delete(teacher.getId());

        assertThrows(IllegalArgumentException.class, () -> teacherService.getById(teacher.getId()));
    }

    @Test
    void update() {
        Teacher teacher = createTeacher();
        teacherService.save(teacher);

        Teacher updatedTeacher = Teacher.builder()
                .telephone("111")
                .build();

        teacherService.update(teacher.getId(), updatedTeacher);

        Teacher teacherById = teacherService.getById(teacher.getId());

        assertNotNull(teacherById);
        assertEquals("Ali", teacherById.getFirstName());
        assertEquals("Ebrahimi", teacherById.getLastName());
        assertEquals("1111111111", teacherById.getNationalCode());
        assertEquals("Tehran", teacherById.getAddress());
        assertEquals("111", teacherById.getTelephone());
    }

    @Test
    void getAllTeachers() {
        Teacher teacher1 = createTeacher();
        teacherService.save(teacher1);

        Teacher teacher2 = Teacher.builder()
                .firstName("Mary")
                .lastName("Ebrahimi")
                .nationalCode("0000000000")
                .address("Tehran")
                .telephone("321")
                .build();
        teacherService.save(teacher2);

        List<Teacher> allTeachers = teacherService.getAllTeachers();

        assertNotNull(allTeachers);
        assertEquals(2, allTeachers.size());
    }

    private Teacher createTeacher() {
        return Teacher.builder()
                .firstName("Ali")
                .lastName("Ebrahimi")
                .nationalCode("1111111111")
                .address("Tehran")
                .telephone("123")
                .build();
    }
}
