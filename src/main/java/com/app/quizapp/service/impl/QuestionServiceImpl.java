package com.app.quizapp.service.impl;

import com.app.quizapp.dto.QuestionDTO;
import com.app.quizapp.enums.DifficultyLevel;
import com.app.quizapp.model.Question;
import com.app.quizapp.repository.QuestionRepository;
import com.app.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(Integer id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public Question createQuestion(QuestionDTO questionDTO) {

        Question question = new Question();
        question.setQuestionTitle(questionDTO.getQuestionTitle());
        question.setCategory(questionDTO.getCategory());
        question.setOption1(questionDTO.getOption1());
        question.setOption2(questionDTO.getOption2());
        question.setOption3(questionDTO.getOption3());
        question.setOption4(questionDTO.getOption4());
        question.setCorrectOption(questionDTO.getCorrectOption());
        question.setDifficultyLevel(questionDTO.getDifficultyLevel());

        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Integer id, QuestionDTO questionDTO) {

        Question question = findById(id);
        question.setQuestionTitle(questionDTO.getQuestionTitle());
        question.setCategory(questionDTO.getCategory());
        question.setOption1(questionDTO.getOption1());
        question.setOption2(questionDTO.getOption2());
        question.setOption3(questionDTO.getOption3());
        question.setOption4(questionDTO.getOption4());
        question.setCorrectOption(questionDTO.getCorrectOption());
        question.setDifficultyLevel(questionDTO.getDifficultyLevel());

        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> getQuestionsByDifficulty(DifficultyLevel difficulty) {
        return questionRepository.findByDifficultyLevel(difficulty);
    }

}


