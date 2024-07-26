package kz.timka.springweb.services;

import kz.timka.springweb.exceptions.ResourceNotFoundException;
import kz.timka.springweb.model.Student;
import kz.timka.springweb.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void changeScoreById(Long studentId, Integer delta) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Unable to change student's score, student not found by id " + studentId));
        student.setScore(student.getScore() + delta);
        studentRepository.save(student);
    }

    public List<Student> findByScoreBetween(Integer min, Integer max) {
        return studentRepository.findAllByScoreBetween(min, max);
    }
    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found by id: " + id));

        student.setName(studentDetails.getName());
        student.setScore(studentDetails.getScore());

        return studentRepository.save(student);
    }


    public Student save(Student student) {
    }
}
