package com.ltp.gradesubmission.dto;

import org.springframework.stereotype.Component;

import com.ltp.gradesubmission.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {

    private Long courseId;
    private String courseName;
    private Student student;

}
