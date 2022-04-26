package com.example.studentqueue.controler;

import java.util.List;
import java.util.stream.Collectors;
import com.example.studentqueue.dto.StudentRequest;
import com.example.studentqueue.dto.StudentResponse;
import com.example.studentqueue.exeption.DataProcessingException;
import com.example.studentqueue.model.Student;
import com.example.studentqueue.service.StudentService;
import com.example.studentqueue.service.mapping.StudentMapping;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    private final StudentMapping mapper;

    public StudentController(StudentService service
            , StudentMapping mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public StudentResponse get(@PathVariable Long id) {
        return mapper.mapToDto(service.get(id).orElseThrow(()
                -> new DataProcessingException("Such student don't exist")));
    }

    @GetMapping("/getAll")
    public List<StudentResponse> getAll() {
        return service.getAll().stream()
                .map(student -> mapper.mapToDto(student))
                .collect(Collectors.toList());
    }

    @PostMapping("/{name}")
    public StudentResponse save(@PathVariable String name) {
        Student newStudent = new Student();
        newStudent.setName(name);
        return mapper.mapToDto(service.add(newStudent));
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id,
                                  @RequestBody StudentRequest dto) {
        Student student = mapper.mapToModel(dto);
        student.setId(id);
        return mapper.mapToDto(service.update(student));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/addToQueue/{id}")
    public StudentResponse addToQueue(@PathVariable Long id) {
        return mapper.mapToDto(service.addToQueue(id));
    }

    @GetMapping("/getQueue")
    public List<StudentResponse> getQueue() {
        return service.getQueue().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @PutMapping("/leaveFromQueue/{id}")
    public List<StudentResponse> leaveFromQueue(@PathVariable Long id) {
        service.leaveFromQueue(id);
        return getQueue();
    }
}
