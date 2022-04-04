package com.example.SpringLogin.Controllers.Enseignant;

import com.example.SpringLogin.Entities.AffectationModule;
import com.example.SpringLogin.Entities.Question;
import com.example.SpringLogin.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enseignant/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/module")
    public List<AffectationModule> getModules(){
        return questionService.getAffectations();
    }

    @GetMapping("")
    public List<Question> getQuestions(){
        return questionService.getQuestions();
    }

    @PostMapping("")
    public String addQuestion(@RequestBody Question question){
        try{
            questionService.addQuestion(question);
            return "Question added successfully";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("{id}")
    public String delQuestion(@PathVariable(name = "id") Long id){
        try{
            questionService.deleteQuestion(id);
            return "Question deleted Successfully";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }


    @PutMapping("")
    public String modQuestion(@RequestBody Question question) {
        try {
            questionService.modifyQuestion(question);
            return "Question modified Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
