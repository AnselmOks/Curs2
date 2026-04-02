package org.skypro.java.course2.examinerservice.service;

import org.skypro.java.course2.examinerservice.domain.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    public JavaQuestionService(Random random) {
        this.questions = new HashSet<>();
        this.random = random;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            for (Question q : questions) {
                if (q.equals(question)) {
                    return q;
                }
            }
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(questions));
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        List<Question> list = new ArrayList<>(questions);
        return list.get(random.nextInt(list.size()));
    }
}
