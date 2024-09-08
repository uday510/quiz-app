package com.app.quizapp.service;

import com.app.quizapp.dto.QuestionDTO;
import com.app.quizapp.enums.DifficultyLevel;
import com.app.quizapp.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    Question findById(Integer id);

    Question createQuestion(QuestionDTO questionDTO);

    Question updateQuestion(Integer id, QuestionDTO questionDTO);

    void deleteQuestion(Integer id);

    List<Question> getQuestionsByCategory(String category);

    List<Question> getQuestionsByDifficulty(DifficultyLevel difficulty);
}
