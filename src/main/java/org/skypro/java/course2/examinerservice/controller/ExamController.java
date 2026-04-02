package org.skypro.java.course2.examinerservice.controller;

import org.skypro.java.course2.examinerservice.domain.Question;
import org.skypro.java.course2.examinerservice.service.ExaminerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ExamController {

    private final ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/exam/get/{amount}")
    public ResponseEntity<Collection<Question>> getQuestions(@PathVariable("amount") int amount) {
        Collection<Question> questions = examinerService.getQuestions(amount);
        return ResponseEntity.ok(questions);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> exceptionHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
