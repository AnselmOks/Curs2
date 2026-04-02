package org.skypro.java.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.java.course2.examinerservice.domain.Question;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerServiceImpl;

    @Test
    void getRandomQuestions_shouldReturnRequestedAmountOfUniqueQuestions() {
        Question q1 = new Question("q1", "a1");
        Question q2 = new Question("q2", "a2");
        Question q3 = new Question("q3", "a3");
        when(questionService.getAll()).thenReturn(Arrays.asList(q1, q2, q3));
        when(questionService.getRandomQuestion())
                .thenReturn(q1, q1, q2, q3);
        Collection<Question> result = examinerServiceImpl.getQuestions(3);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList(q1, q2, q3)));
        verify(questionService, times(4)).getRandomQuestion();
    }

    @Test
    void getRandomQuestions_withZeroAmount_shouldReturnEmptyList() {
        when(questionService.getAll()).thenReturn(Arrays.asList(new Question("q1", "a1")));
        Collection<Question> result = examinerServiceImpl.getQuestions(0);
        assertTrue(result.isEmpty());
        verify(questionService, never()).getRandomQuestion();
    }

    @Test
    void getRandomQuestions_whenAmountExceedsTotal_shouldThrow() {
        when(questionService.getAll()).thenReturn(Arrays.asList(new Question("q1", "a1")));
        assertThrows(IllegalArgumentException.class,
                () -> examinerServiceImpl.getQuestions(2));
    }

    @Test
    void getRandomQuestions_withNegativeAmount_shouldThrow() {
        when(questionService.getAll()).thenReturn(Arrays.asList(new Question("q1", "a1")));
        assertThrows(IllegalArgumentException.class,
                () -> examinerServiceImpl.getQuestions(-1));
    }

}
