package com.obfuscation.controller;

import com.obfuscation.domain.Teacher;
import com.obfuscation.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PutMapping(value = "/teacher")
    public void save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
    }

    @GetMapping(value = "/teacher/{id}")
    public Teacher getById(@PathVariable("id") long id) {
        return teacherService.getById(id);
    }

    @DeleteMapping(value = "/teacher/{id}")
    public void delete(@PathVariable("id") long id) {
        teacherService.delete(id);
    }

    @PostMapping(value = "/teacher/{id}")
    public void update(@PathVariable("id") long id, @RequestBody Teacher newTeacher) {
        teacherService.update(id, newTeacher);
    }

    @GetMapping(value = "/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
