package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    public Answer findById(int id){
        return answerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Answer not found"));
    }

    public void changeAnswer(int id, String text, String score){
        Answer answer = findById(id);
        if (text != ""){
            answer.setContent(text);
        }
        boolean canParseInt;
        try {
            Integer.parseInt(score);
            canParseInt = true;
        } catch (NumberFormatException e) {
            canParseInt = false;
        }
        if (canParseInt){
            answer.setAnswerScore(Integer.parseInt(score));
        }
        save(answer);
    }

    public void save(Answer answer){
        answerRepository.save(answer);
    }

    public void deleteAnswer(int id){
        answerRepository.deleteById(id);
    }
}
