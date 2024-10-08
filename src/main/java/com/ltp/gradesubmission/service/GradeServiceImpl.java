package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.dto.courseGradeDTO.CourseGradesDTO;
import com.ltp.gradesubmission.dto.courseGradeDTO.GradeDTO;
import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.exception.StudentNotEnrolledException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    
    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    CourseRepository courseRepository;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return unwrapGrade(grade, studentId, courseId);
    }
    

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Student student = StudentServiceImpl.unwrapStudent(studentRepository.findById(studentId), studentId);
        Course course = CourseServiceImpl.unwrapCourse(courseRepository.findById(courseId), courseId);
        if(!student.getCourses().contains(course)) throw new StudentNotEnrolledException(studentId, courseId);
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }


    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        Grade unwrappedGrade = unwrapGrade(grade, studentId, courseId);
        unwrappedGrade.setScore(score);
        return gradeRepository.save(unwrappedGrade);
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public CourseGradesDTO getCourseGrades(Long courseId) {
        List<Grade> grades = gradeRepository.findByCourseId(courseId);
        Optional<Course> course = courseRepository.findById(courseId);
        List<GradeDTO> gradeDTO = grades.stream().map(grade -> new GradeDTO(grade.getId(), grade.getScore(), grade.getStudent())).collect(Collectors.toList());
        return new CourseGradesDTO(course, gradeDTO);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>)gradeRepository.findAll();
    }

    static Grade unwrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
        if (entity.isPresent()) return entity.get();
        else throw new GradeNotFoundException(studentId, courseId);
    }

}
