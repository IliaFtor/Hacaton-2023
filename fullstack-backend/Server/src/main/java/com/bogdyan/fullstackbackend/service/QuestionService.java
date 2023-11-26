package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Question;
import com.bogdyan.fullstackbackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public Question findById(int id){
        return questionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Question not found"));
    }
}
