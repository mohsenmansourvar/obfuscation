package com.obfuscation.service;

import com.obfuscation.domain.Teacher;
import com.obfuscation.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void update(long id, Teacher newTeacher) {
        Teacher teacher = getById(id);

        if (newTeacher.getFirstName() != null) {
            teacher.setFirstName(newTeacher.getFirstName());
        }
        if (newTeacher.getLastName() != null) {
            teacher.setLastName(newTeacher.getLastName());
        }
        if (newTeacher.getNationalCode() != null) {
            teacher.setNationalCode(newTeacher.getNationalCode());
        }
        if (newTeacher.getSpecialty() != null) {
            teacher.setSpecialty(newTeacher.getSpecialty());
        }
        if (newTeacher.getAddress() != null) {
            teacher.setAddress(newTeacher.getAddress());
        }
        if (newTeacher.getTelephone() != null) {
            teacher.setTelephone(newTeacher.getTelephone());
        }
        teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void delete(long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher getById(long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no teacher by id"));
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}
