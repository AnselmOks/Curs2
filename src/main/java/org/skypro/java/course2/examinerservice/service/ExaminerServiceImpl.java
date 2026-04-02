package org.skypro.java.course2.examinerservice.service;

import org.skypro.java.course2.examinerservice.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int total = questionService.getAll().size();
        if (amount < 0) {
            throw new IllegalArgumentException("Количество не может быть меньше нуля " + amount);
        }
        if (amount > total) {
            throw new IllegalArgumentException("Количество запрашиваемых вопросов " + amount + " больше объема списка вопросов " + total);
        }
        return Stream.generate(questionService::getRandomQuestion)
                .distinct()
                .limit(amount)
                .collect(Collectors.toList());
    }
}
