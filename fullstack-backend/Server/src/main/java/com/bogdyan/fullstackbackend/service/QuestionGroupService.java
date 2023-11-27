package com.bogdyan.fullstackbackend.service;


import com.bogdyan.fullstackbackend.model.QuestionGroup;
import com.bogdyan.fullstackbackend.repository.QuestionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuestionGroupService {
    private final QuestionGroupRepository questionGroupRepository;

    @Autowired
    public QuestionGroupService(QuestionGroupRepository questionGroupRepository){
        this.questionGroupRepository = questionGroupRepository;
    }

    public QuestionGroup findById(int id) {
        return questionGroupRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Question Group not found"));
    }

    public List<QuestionGroup> getAll(){
        return questionGroupRepository.findAll();
    }
}
