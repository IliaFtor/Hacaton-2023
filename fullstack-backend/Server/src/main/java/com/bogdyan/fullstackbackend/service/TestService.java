package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.Test;
import com.bogdyan.fullstackbackend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    public Test findById(int id){
        return testRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Test not found"));
    }

    public void save(Test test){
        testRepository.save(test);
    }
}
