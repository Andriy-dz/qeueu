package com.example.studentqueue.service.mapping;

import com.example.studentqueue.dto.StudentRequest;
import com.example.studentqueue.dto.StudentResponse;
import com.example.studentqueue.model.Student;
import com.example.studentqueue.service.StudentService;
import org.springframework.stereotype.Component;

@Component
public class StudentMapping implements RequestDtoMapper<StudentRequest, Student>,
        ResponseDtoMapper<StudentResponse, Student> {
    private final StudentService service;

    public StudentMapping(StudentService service) {
        this.service = service;
    }

    @Override
    public Student mapToModel(StudentRequest dto) {
        Student student = new Student();
        student.setName(dto.getName());
        return student;
    }

    @Override
    public StudentResponse mapToDto(Student student) {
        StudentResponse dto = new StudentResponse();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setNumberInQueue(student.getNumberInQueue());
        return dto;
    }
}
