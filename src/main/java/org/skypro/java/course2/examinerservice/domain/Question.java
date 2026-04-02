package org.skypro.java.course2.examinerservice.domain;

import java.util.Objects;

public class Question {

    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question questionCompare)) return false;
        return Objects.equals(question, questionCompare.getQuestion()) &&
                Objects.equals(answer, questionCompare.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}
