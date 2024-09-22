package com.ltp.gradesubmission.exception;

public class StudentNotEnrolledInAnyCourseException extends RuntimeException {
    public StudentNotEnrolledInAnyCourseException(Long studentId){
        super("The student with id " + studentId + " is not enrolled in any course");
    }
}
