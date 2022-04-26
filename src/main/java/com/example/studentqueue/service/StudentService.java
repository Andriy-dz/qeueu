package com.example.studentqueue.service;

import java.util.List;
import java.util.Optional;
import com.example.studentqueue.model.Student;

public interface StudentService {
    Student add(Student student);

    Optional<Student> get(Long id);

    List<Student> getAll();

    Student update(Student student);

    void delete(Long id);

    Optional<Student> findByName(String studentName);

    void leaveFromQueue(Long id) throws Exception;

    List<Student> getQueue();

    Student addToQueue(Long id);
}
