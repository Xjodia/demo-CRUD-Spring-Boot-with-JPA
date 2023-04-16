package com.example.demo.student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long studentId);

    void addNewStudent(Student student);

    void deleteStudent(Long studentId);

    void updateStudent(Long studentId, String name, String email);
}
