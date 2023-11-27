package com.bogdyan.fullstackbackend.controller;

import com.bogdyan.fullstackbackend.model.Test;
import com.bogdyan.fullstackbackend.service.QuestionGroupService;
import com.bogdyan.fullstackbackend.service.QuestionService;
import com.bogdyan.fullstackbackend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {
    private final TestService testService;
    private final QuestionGroupService questionGroupService;
    private final QuestionService questionService;

    @Autowired
    public TestController(TestService testService, QuestionGroupService questionGroupService, QuestionService questionService){
        this.testService = testService;
        this.questionGroupService = questionGroupService;
        this.questionService = questionService;
    }

    @GetMapping("/test/{id}")
    public String listTestInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("test", testService.findById(id));
        model.addAttribute("groups", questionGroupService.getAll());
        return "testInfo";
    }

    @PostMapping("/{tid}/change-question-group")
    public String changeGroup(@PathVariable("tid") int tid, int group, Model model){
        Test test = testService.findById(tid);
        test.setQuestionGroup(questionGroupService.findById(group));
        test.getQuestions().clear();
        testService.save(test);
        model.addAttribute("test",test);
        model.addAttribute("groups", questionGroupService.getAll());
        return "testInfo";
    }

    @PostMapping("/test/{tid}/delete-question/{qid}")
    public String deleteQuestion(@PathVariable("tid") int tid, @PathVariable("qid") int qid, Model model){
        Test test = testService.findById(tid);
        test.getQuestions().remove(questionService.findById(qid));
        testService.save(test);
        model.addAttribute("test",test);
        model.addAttribute("groups", questionGroupService.getAll());
        return "testInfo";
    }
}
