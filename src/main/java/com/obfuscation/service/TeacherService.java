package com.obfuscation.service;

import com.obfuscation.domain.Teacher;

import java.util.List;

public interface TeacherService {

    void save(Teacher teacher);

    void update(long id, Teacher newTeacher);

    void delete(long id);

    Teacher getById(long id);

    List<Teacher> getAllTeachers();
}
