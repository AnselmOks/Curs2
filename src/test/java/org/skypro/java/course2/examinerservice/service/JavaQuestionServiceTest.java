package org.skypro.java.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.java.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    private Random mockRandom;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    void addQuestion_shouldCreateAndAddNewQuestion() {
        Question result = javaQuestionService.add("Q1", "A1");
        assertNotNull(result);
        assertEquals("Q1", result.getQuestion());
        assertEquals("A1", result.getAnswer());
        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    void addQuestion_withDuplicate_shouldReturnExistingQuestion() {
        Question first = javaQuestionService.add("Q1", "A1");
        Question second = javaQuestionService.add("Q1", "A1");
        assertSame(first, second);
        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    void addQuestion_shouldAddNewQuestion() {
        Question q = new Question("Q2", "A2");
        Question result = javaQuestionService.add(q);
        assertSame(q, result);
        assertTrue(javaQuestionService.getAll().contains(q));
        assertEquals(1, javaQuestionService.getAll().size());
    }

    @Test
    void remove_existingQuestion_shouldRemoveAndReturnArgument() {
        Question q = javaQuestionService.add("Q4", "A4");
        assertEquals(1, javaQuestionService.getAll().size());
        Question removed = javaQuestionService.remove(q);
        assertSame(q, removed);
        assertTrue(javaQuestionService.getAll().isEmpty());
    }

    @Test
    void remove_nonExistingQuestion_shouldReturnNull() {
        Question q = new Question("Q6", "A6");
        Question removed = javaQuestionService.remove(q);
        assertNull(removed);
        assertTrue(javaQuestionService.getAll().isEmpty());
    }

    @Test
    void getAll_shouldReturnCopyOfQuestions() {
        Question q1 = javaQuestionService.add("Q7", "A7");
        Question q2 = javaQuestionService.add("Q8", "A8");
        Collection<Question> all = javaQuestionService.getAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(q1));
        assertTrue(all.contains(q2));
    }

    @Test
    void getRandomQuestion_whenEmpty_shouldReturnNull() {
        assertNull(javaQuestionService.getRandomQuestion());
    }

    @Test
    void getRandomQuestion_shouldReturnElementFromSet() {
        Question q1 = javaQuestionService.add("Q9", "A9");
        Question q2 = javaQuestionService.add("Q10", "A10");
        when(mockRandom.nextInt(2)).thenReturn(1);
        Question random = javaQuestionService.getRandomQuestion();
        assertSame(q2, random);
    }

}
