package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService
{
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new IllegalStateException("No student exist");
        }
        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("Student Not Found"));
    }

    @Override
    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new IllegalStateException("Student with ID " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public void updateStudent(Long studentId, String update_name, String update_email) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new RuntimeException("Student Not Found"));
        if(update_name != null &&
                update_name.length() > 0 &&
                !Objects.equals(student.getName(), update_name)){
            student.setName(update_name);
        }
        if(update_email != null &&
                update_email.length() > 0 &&
                !Objects.equals(student.getEmail(), update_email)){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(update_email);
        }
        studentRepository.save(student);
    }
}
