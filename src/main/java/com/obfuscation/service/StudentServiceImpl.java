package com.obfuscation.service;

import com.obfuscation.domain.Student;
import com.obfuscation.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void update(long id, Student newStudent) {
        Student student = getById(id);

        if (newStudent.getFirstName() != null) {
            student.setFirstName(newStudent.getFirstName());
        }
        if (newStudent.getLastName() != null) {
            student.setLastName(newStudent.getLastName());
        }
        if (newStudent.getNationalCode() != null) {
            student.setNationalCode(newStudent.getNationalCode());
        }
        if (newStudent.getAddress() != null) {
            student.setAddress(newStudent.getAddress());
        }
        if (newStudent.getTelephone() != null) {
            student.setTelephone(newStudent.getTelephone());
        }
        if (newStudent.getStudentNumber() != null) {
            student.setStudentNumber(newStudent.getStudentNumber());
        }
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Student by id"));
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
