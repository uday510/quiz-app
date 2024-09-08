package com.app.quizapp.controller;
import com.app.quizapp.dto.QuestionDTO;
import com.app.quizapp.enums.DifficultyLevel;
import com.app.quizapp.model.Question;
import com.app.quizapp.service.QuestionService;
import com.app.quizapp.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Question>>> getAllQuestion() {
       List<Question> questions = questionService.getAllQuestions();
       return ResponseEntity.ok(new ApiResponse<>(questions, "Questions fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Question>> getQuestionById(@PathVariable String id) {
        try {
            Integer integerId = Integer.valueOf(id);
            Question question = questionService.findById(integerId);
            return buildResponse(question);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(null, "Invalid question ID format"));
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Question>> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        Question newQuestion = questionService.createQuestion(questionDTO);
        return ResponseEntity.created(URI.create("/api/questions/" + newQuestion.getId()))
                .body(new ApiResponse<>(newQuestion, "Question created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Question>> updateQuestion(@PathVariable Integer id, @Valid @RequestBody QuestionDTO questionDTO) {
        Question updatedQuestion = questionService.updateQuestion(id, questionDTO);
        return ResponseEntity.ok(new ApiResponse<>(updatedQuestion, "Question updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(new ApiResponse<>(null, "Question deleted successfully"));
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<ApiResponse<List<Question>>> getQuestionsByDifficulty(@PathVariable String difficulty) {
        List<Question> questions = questionService.getQuestionsByDifficulty(DifficultyLevel.valueOf(difficulty.toUpperCase()));
        return ResponseEntity.ok(new ApiResponse<>(questions, "Questions fetched successfully"));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<Question>>> getQuestionsByCategory(@PathVariable String category) {
        List<Question> questions = questionService.getQuestionsByCategory(category);
        return ResponseEntity.ok(new ApiResponse<>(questions, "Questions fetched successfully"));
    }

    private <T> ResponseEntity<ApiResponse<T>> buildResponse(T data) {
        return ResponseEntity.ok(new ApiResponse<>(data, "Question fetched successfully"));
    }

//    @GetMapping("/load")
    public ResponseEntity<ApiResponse<Void>> loadQuestions() {
        // questions for java, spring, python, javascript
        QuestionDTO q1 = new QuestionDTO();
        q1.setQuestionTitle("What is the print function in Python?");
        q1.setOption1("print()");
        q1.setOption2("printf()");
        q1.setOption3("println()");
        q1.setOption4("console.log()");
        q1.setCorrectOption("print()");
        q1.setDifficultyLevel(DifficultyLevel.EASY);
        q1.setCategory("python");

        QuestionDTO q2 = new QuestionDTO();
        q2.setQuestionTitle("What is the main method in Java?");
        q2.setOption1("public static void main(String[] args)");
        q2.setOption2("public void main(String[] args)");
        q2.setOption3("private static void main(String[] args)");
        q2.setOption4("private void main(String[] args)");
        q2.setCorrectOption("public static void main(String[] args)");
        q2.setDifficultyLevel(DifficultyLevel.EASY);
        q2.setCategory("java");

        QuestionDTO q3 = new QuestionDTO();
        q3.setQuestionTitle("What is the syntax for a for loop in Go?");
        q3.setOption1("for i := 0; i < 10; i++");
        q3.setOption2("for (i = 0; i < 10; i++)");
        q3.setOption3("for i in range(10)");
        q3.setOption4("for i = 0 to 10");
        q3.setCorrectOption("for i := 0; i < 10; i++");
        q3.setDifficultyLevel(DifficultyLevel.MEDIUM);
        q3.setCategory("go");

        QuestionDTO q4 = new QuestionDTO();
        q4.setQuestionTitle("What is the puts function in Ruby?");
        q4.setOption1("puts()");
        q4.setOption2("print()");
        q4.setOption3("println()");
        q4.setOption4("console.log()");
        q4.setCorrectOption("puts()");
        q4.setDifficultyLevel(DifficultyLevel.EASY);
        q4.setCategory("ruby");

        QuestionDTO q5 = new QuestionDTO();
        q5.setQuestionTitle("What is the syntax for a while loop in Java?");
        q5.setOption1("while (true)");
        q5.setOption2("while (false)");
        q5.setOption3("for (;;) {}");
        q5.setOption4("do {} while (true)");
        q5.setCorrectOption("while (true)");
        q5.setDifficultyLevel(DifficultyLevel.EASY);

        QuestionDTO q6 = new QuestionDTO();
        q6.setQuestionTitle("What is the len function in Python?");
        q6.setOption1("len()");
        q6.setOption2("size()");
        q6.setOption3("length()");
        q6.setOption4("count()");
        q6.setCorrectOption("len()");
        q6.setDifficultyLevel(DifficultyLevel.EASY);
        q6.setCategory("python");

        QuestionDTO q7 = new QuestionDTO();
        q7.setQuestionTitle("What is the syntax for a switch statement in Go?");
        q7.setOption1("switch {}");
        q7.setOption2("switch case {}");
        q7.setOption3("select {}");
        q7.setOption4("match {}");
        q7.setCorrectOption("switch {}");
        q7.setDifficultyLevel(DifficultyLevel.MEDIUM);
        q7.setCategory("go");

        QuestionDTO q8 = new QuestionDTO();
        q8.setQuestionTitle("What is the require function in Ruby?");
        q8.setOption1("require()");
        q8.setOption2("import()");
        q8.setOption3("include()");
        q8.setOption4("load()");
        q8.setCorrectOption("require()");
        q8.setDifficultyLevel(DifficultyLevel.EASY);
        q8.setCategory("ruby");


        QuestionDTO q9 = new QuestionDTO();
        q9.setQuestionTitle("What is the try-catch block in Java?");
        q9.setOption1("try {} catch () {}");
        q9.setOption2("try {} except () {}");
        q9.setOption3("try {} finally {}");
        q9.setOption4("try {} catch () {} finally {}");
        q9.setCorrectOption("try {} catch () {}");
        q9.setDifficultyLevel(DifficultyLevel.MEDIUM);
        q9.setCategory("java");

        QuestionDTO q10 = new QuestionDTO();
        q10.setQuestionTitle("What is the syntax for a for loop in Python?");
        q10.setOption1("for i in range(10)");
        q10.setOption2("for (i = 0; i < 10; i++)");
        q10.setOption3("for i = 0 to 10");
        q10.setOption4("for i := 0; i < 10; i++");
        q10.setCorrectOption("for i in range(10)");
        q10.setDifficultyLevel(DifficultyLevel.EASY);
        q10.setCategory("python");

        questionService.createQuestion(q1);
        questionService.createQuestion(q2);
        questionService.createQuestion(q3);
        questionService.createQuestion(q4);
        questionService.createQuestion(q5);
        questionService.createQuestion(q6);
        questionService.createQuestion(q7);
        questionService.createQuestion(q8);
        questionService.createQuestion(q9);
        questionService.createQuestion(q10);


        return ResponseEntity.ok(new ApiResponse<>(null, "Questions loaded successfully"));


    }

}