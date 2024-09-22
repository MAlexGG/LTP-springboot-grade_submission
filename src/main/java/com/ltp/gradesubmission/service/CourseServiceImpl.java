package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.dto.EnrollmentDTO;
import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.exception.CourseWithNotEnrolledStudentsException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    StudentRepository studentRepository;
    EnrollmentDTO enrollmentDTO;


    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course, id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {     
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    public EnrollmentDTO addStudentToCourse(Long studentId, Long courseId){
        Course course = getCourse(courseId);
        Optional<Student> student = studentRepository.findById(studentId);
        Student unwrappedStudent = StudentServiceImpl.unwrapStudent(student, studentId);
        course.getStudents().add(unwrappedStudent);
        courseRepository.save(course);
        enrollmentDTO.setCourseId(courseId);
        enrollmentDTO.setCourseName(course.getSubject());
        enrollmentDTO.setStudent(unwrappedStudent);
        return enrollmentDTO;
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        if(course.getStudents().isEmpty()) throw new CourseWithNotEnrolledStudentsException(id);
        else return course.getStudents();
    }


    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new CourseNotFoundException(id);
    }


}
