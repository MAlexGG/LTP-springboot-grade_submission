package com.ltp.gradesubmission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.service.GradeService;

import jakarta.validation.Valid;


@Controller
public class GradeController {

    GradeService gradeService;

    public GradeController(GradeService gradeService){
        this.gradeService = gradeService;
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id){     
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getGrades());
        return "grades";
    }
    
    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result){
        if(result.hasErrors()) return "form";
        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }    
}
