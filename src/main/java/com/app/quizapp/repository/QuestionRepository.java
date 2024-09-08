package com.app.quizapp.repository;

import com.app.quizapp.enums.DifficultyLevel;
import com.app.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByDifficultyLevel(DifficultyLevel difficultyLevel);

    List<Question> findByCategory(String category);

    List<Question> findByDifficultyLevel(DifficultyLevel difficulty);
}
