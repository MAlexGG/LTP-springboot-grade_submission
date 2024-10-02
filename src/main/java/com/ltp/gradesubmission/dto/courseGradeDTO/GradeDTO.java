package com.ltp.gradesubmission.dto.courseGradeDTO;

import org.springframework.stereotype.Component;

import com.ltp.gradesubmission.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    
    private Long id;
    private String score;
    private Student student;

}
