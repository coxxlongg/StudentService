package kz.timka.springweb.controllers;

import kz.timka.springweb.exceptions.AppError;
import kz.timka.springweb.exceptions.ResourceNotFoundException;
import kz.timka.springweb.model.Student;
import kz.timka.springweb.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found by id: " + id));
        return ResponseEntity.ok(student);
    }

    @GetMapping("/score_between")
    public ResponseEntity<List<Student>> getStudentsByScoreRange(@RequestParam(defaultValue = "0") Integer min,
                                                                 @RequestParam(defaultValue = "100") Integer max) {
        List<Student> students = studentService.findByScoreBetween(min, max);
        return ResponseEntity.ok(students);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/change_score")
    public ResponseEntity<Void> changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        studentService.changeScoreById(studentId, delta);
        return ResponseEntity.noContent().build();
    }


}
