package com.example.studentqueue.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.studentqueue.dao.StudentDao;
import com.example.studentqueue.model.Student;
import com.example.studentqueue.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student add(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Optional<Student> get(Long id) {
        return Optional.ofNullable(studentDao.getById(id));
    }

    @Override
    public List<Student> getAll() {
        return studentDao.findAll();
    }

    @Override
    public Student update(Student student) {

        return studentDao.save(student);
    }

    @Override
    public void delete(Long id) {
        studentDao.deleteById(id);
    }

    @Override
    public Optional<Student> findByName(String studentName) {
        return Optional.of(studentDao.findByName(studentName));
    }

    @Override
    public List<Student> getQueue() {
        return studentDao.findAllInQueue().stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getNumberInQueue().compareTo(s2.getNumberInQueue());
            }
        }).collect(Collectors.toList());
    }

    @Override
    public Student addToQueue(Long id) {
        Student student = studentDao.getById(id);
        Integer lastStudentInQueue = studentDao.getLastStudentInQueue();
        student.setNumberInQueue(
                (lastStudentInQueue == null) ? 1 : lastStudentInQueue + 1);
        return update(student);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void leaveFromQueue(Long id) throws Exception {
        Student student = get(id).orElseThrow(() ->
                new Exception("Such a student does not exist with id " + id));
        List<Student> students = studentDao.findAllNextInQueue(student.getNumberInQueue());
        for(Student s : students) {
            s.setNumberInQueue(s.getNumberInQueue() - 1);
            update(s);
        }
        student.setNumberInQueue(null);
        update(student);
    }
}
