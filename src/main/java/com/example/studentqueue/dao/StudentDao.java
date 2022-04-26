package com.example.studentqueue.dao;

import java.util.List;
import com.example.studentqueue.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    @Query(value = "SELECT MAX(s.numberInQueue) "
            + "FROM Student s ")
    Integer getLastStudentInQueue();

    Student findByName(String studentName);

    @Query(value = "SELECT s FROM Student s WHERE s.numberInQueue > 0")
    List<Student> findAllInQueue();

    @Query(value = "SELECT s FROM Student s WHERE s.numberInQueue > ?1")
    List<Student> findAllNextInQueue(Integer number);
}
