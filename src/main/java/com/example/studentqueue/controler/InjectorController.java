package com.example.studentqueue.controler;

import com.example.studentqueue.model.Student;
import com.example.studentqueue.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectorController {
    private final StudentService studentService;

    public InjectorController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/injector")
    public void inject() {
        Student student1 = new Student();
        student1.setName("Name1");
        Student student2 = new Student();
        student2.setName("Name2");
        studentService.add(student1);
        studentService.add(student2);
    }
}
