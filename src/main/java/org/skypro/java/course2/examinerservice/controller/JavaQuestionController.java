package org.skypro.java.course2.examinerservice.controller;

import org.skypro.java.course2.examinerservice.domain.Question;
import org.skypro.java.course2.examinerservice.service.JavaQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("exam/java")
@RestController
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestParam("question") String questionText,
                                                @RequestParam("answer") String questionAnswer) {
        Question createdQuestion = javaQuestionService.add(questionText, questionAnswer);
        return ResponseEntity.ok(createdQuestion);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Question> removeQuestion(@RequestParam("question") String questionText,
                                                   @RequestParam("answer") String questionAnswer) {
        Question removedQuestion = javaQuestionService.remove(new Question(questionText, questionAnswer));
        if (removedQuestion == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(removedQuestion);
        }
    }

    @GetMapping()
    public ResponseEntity<Collection<Question>> getQuestions() {
        Collection<Question> questions = javaQuestionService.getAll();
        return ResponseEntity.ok(questions);
    }

}
