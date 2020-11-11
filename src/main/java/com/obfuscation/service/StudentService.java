package com.obfuscation.service;

import com.obfuscation.domain.Student;

import java.util.List;

public interface StudentService {

    void save(Student student);

    void update(long id, Student newStudent);

    void delete(long id);

    Student getById(long id);

    List<Student> getAllStudent();
}
