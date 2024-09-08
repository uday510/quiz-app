package com.app.quizapp.service.impl;

import com.app.quizapp.model.Question;
import com.app.quizapp.model.QuestionWrapper;
import com.app.quizapp.model.Quiz;
import com.app.quizapp.repository.QuestionRepository;
import com.app.quizapp.repository.QuizRepository;
import com.app.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Quiz createQuiz(String category, int numQuestions, String title) {
        Pageable pageable = PageRequest.of(0, numQuestions);
        Page<Question> questionPage = questionRepository.findRandomQuestionsByCategory(category, pageable);

        List<Question> questions = questionPage.getContent();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setCategory(category);
        quiz.setQuestions(questions);

        return quizRepository.save(quiz);
    }

    @Override
    public List<QuestionWrapper> getQuizzes(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        List<Question> questions = quiz.orElseThrow().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for (Question question : questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
            );
            questionWrappers.add(questionWrapper);
        }

        return questionWrappers;
    }
}
