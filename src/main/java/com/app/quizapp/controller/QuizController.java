package com.app.quizapp.controller;

import com.app.quizapp.model.QuestionWrapper;
import com.app.quizapp.model.Quiz;
import com.app.quizapp.service.QuizService;
import com.app.quizapp.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Object>> createQuiz(
            @RequestParam String category,
            @RequestParam(defaultValue = "5") int numQuestions,
            @RequestParam String title) {

        try {
            Quiz quiz = quizService.createQuiz(category, numQuestions, title);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(quiz, "Quiz created successfully"));
        } catch (Exception e) {
            Logger.getLogger("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, "Error creating quiz"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<QuestionWrapper>>> getQuizzes(@PathVariable int id) {
        try {
            List<QuestionWrapper> questions = quizService.getQuizzes(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(questions, "Questions retrieved successfully"));
        } catch (Exception e) {
            Logger.getLogger("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, "Error retrieving questions"));
    }
}