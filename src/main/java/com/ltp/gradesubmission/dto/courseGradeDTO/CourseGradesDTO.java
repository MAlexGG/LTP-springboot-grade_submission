package com.ltp.gradesubmission.dto.courseGradeDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ltp.gradesubmission.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseGradesDTO {

    private Optional<Course> course;
    private List<GradeDTO> grades;

}
