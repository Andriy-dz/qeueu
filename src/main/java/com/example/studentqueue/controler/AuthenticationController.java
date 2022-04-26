package com.example.studentqueue.controler;

import com.example.studentqueue.exeption.DataProcessingException;
import com.example.studentqueue.model.Student;
import com.example.studentqueue.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final StudentService service;

    public AuthenticationController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public Student getPage(@RequestParam String name) {
        return service.findByName(name).orElseThrow(()
                -> new DataProcessingException("Such student don't exist"));
    }

}
