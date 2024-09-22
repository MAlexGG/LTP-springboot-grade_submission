package com.ltp.gradesubmission.exception;

public class CourseWithNotEnrolledStudentsException extends RuntimeException {
    public CourseWithNotEnrolledStudentsException(Long courseId){
        super("The course with id " + courseId + " has not any students enrolled");
    }
}
