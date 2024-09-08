package com.app.quizapp.validation;

import com.app.quizapp.enums.DifficultyLevel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public class DifficultyLevelValidator implements Converter<String, DifficultyLevel> {

    @Override
    public DifficultyLevel convert(String source) {
        try {
            return DifficultyLevel.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid difficulty level: " + source);
        }
    }
}