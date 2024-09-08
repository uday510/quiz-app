package com.app.quizapp.repository;

import com.app.quizapp.model.Question;
import com.app.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    @Query(value = "SELECT * FROM question q WHERE q.category=?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQuestions);
}