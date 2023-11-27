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
    public String changeQuestionAnswer(@PathVariable("id") int id, @PathVariable("answer-id") int aid, String text, String score, Model model){
        answerService.changeAnswer(aid, text, score);
        model.addAttribute("question", questionService.findById(id));
        return "QuestionInfo";
    }

    @PostMapping("/{id}/add-answer")
    public String addAnswer(@PathVariable("id") String id, String name, int score, Model model){
        return "QuestionInfo";
    }

    @PostMapping("/{qid}/delete-answer/{aid}")
    public String deleteAnswer(@PathVariable("qid") int qid, @PathVariable("aid") int aid, Model model) {
        answerService.deleteAnswer(aid);
        model.addAttribute("question", questionService.findById(qid));
        return "QuestionInfo";
    }
}
