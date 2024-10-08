package com.ltp.gradesubmission.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.dto.courseGradeDTO.CourseGradesDTO;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.service.GradeService;

import lombok.AllArgsConstructor;

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
@AllArgsConstructor
public class GradeController {

    GradeService gradeService;
    
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.getGrade(studentId, courseId) , HttpStatus.OK);
    }
    
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(@RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.saveGrade(grade, studentId, courseId), HttpStatus.CREATED);
    }

    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.updateGrade(grade.getScore(), studentId, courseId) , HttpStatus.OK);
    }
    
    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        gradeService.deleteGrade(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        return new ResponseEntity<>(gradeService.getStudentGrades(studentId) , HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<CourseGradesDTO> getCoursesGrades(@PathVariable Long courseId) {
        CourseGradesDTO courseGradesDTO = gradeService.getCourseGrades(courseId);
        return new ResponseEntity<>(courseGradesDTO , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getGrades() {
        return new ResponseEntity<>(gradeService.getAllGrades() , HttpStatus.OK);
    }

    
}
