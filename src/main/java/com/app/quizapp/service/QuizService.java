package com.app.quizapp.service;

import com.app.quizapp.model.QuestionWrapper;
import com.app.quizapp.model.Quiz;
import com.app.quizapp.model.QuizResponse;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(String category, int numQuestions, String title);

    List<QuestionWrapper> getQuizzes(int id);

    int submitQuiz(int id, List<QuizResponse> responses);
}
