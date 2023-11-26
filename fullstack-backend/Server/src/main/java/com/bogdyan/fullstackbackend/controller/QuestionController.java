package com.bogdyan.fullstackbackend.controller;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.service.AnswerService;
import com.bogdyan.fullstackbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public QuestionController(QuestionService questionService, AnswerService answerService){
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/question/{id}")
    public String listQuestionInfo(@PathVariable("id") Integer id, Model model){
        model.addAttribute("question", questionService.findById(id));
        return "QuestionInfo";
    }

    @PostMapping("/{id}/change-answer/{answer-id}")
    public String changeQuestionAnswer(@PathVariable("id") int id, @PathVariable("answer-id") int aid, String text, Model model){
        Answer answer = answerService.findById(aid);
        answer.setContent(text);
        answerService.save(answer);
        model.addAttribute("question", questionService.findById(id));
        return "QuestionInfo";
    }
}
