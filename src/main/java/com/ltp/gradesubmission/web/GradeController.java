package com.ltp.gradesubmission.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.Grade;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/grade")
public class GradeController {
    

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/grade/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(@RequestBody @PathVariable Grade grade, @PathVariable Long studentId, Long courseId) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/grade/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/grade/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/grade/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/grade/course/{courseId}")
    public ResponseEntity<List<Grade>> getCoursesGrades(@PathVariable Long courseId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/grade/all")
    public ResponseEntity<List<Grade>> getGrades() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
}
