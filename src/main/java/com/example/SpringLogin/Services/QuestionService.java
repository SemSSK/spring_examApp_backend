package com.example.SpringLogin.Services;

import com.example.SpringLogin.Entities.*;
import com.example.SpringLogin.Configrations.SecurityServices.ContextHandlerClass;
import com.example.SpringLogin.Entities.Module;
import com.example.SpringLogin.Repos.AffectationModuleRepo;
import com.example.SpringLogin.Repos.ModuleRepo;
import com.example.SpringLogin.Repos.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private AffectationModuleRepo affectationModuleRepo;
    @Autowired
    private ModuleRepo moduleRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ContextHandlerClass contextHandlerClass;


    public QuestionService(){
        System.out.println("QuestionService initialized");
    }

    private Enseignant getEnseignant(){
        return (Enseignant) contextHandlerClass.getCurrentLoggedInUser().getUtilisateur();
    }


    public List<AffectationModule> getAffectations(){
        return affectationModuleRepo.findAllByEnseignant(getEnseignant());
    }

    public boolean canAddQuestion(Question question){
        return !affectationModuleRepo.findByEnseignantAndModule(getEnseignant(),question.getModule()).isEmpty();
    }

    public boolean canAlterQuestion(Question question){
        Optional<AffectationModule> affectationModule = affectationModuleRepo.findByEnseignantAndModule(
                getEnseignant(),question.getModule()
        );
        if(affectationModule.isEmpty()){
            return false;
        }
        return affectationModule.get().getType().equals("COURS") ||
                question.getEnseignant().equals(getEnseignant());
    }


    public List<Question> getQuestions() {
        List<Module> modules = new ArrayList<>();
        getAffectations().forEach(affectationModule -> {
            modules.add(affectationModule.getModule());
        });
        return questionRepo.findAll().stream().filter(question -> {
            return modules.contains(question.getModule());
        }).collect(Collectors.toList());
    }

    public void addQuestion(Question question) throws Exception {
        if(canAddQuestion(question)){
            question.setDateCreation(new Timestamp(System.currentTimeMillis()));
            question.setEnseignant(getEnseignant());
            questionRepo.save(question);
        }
        else{
            throw new Exception("Cannot add question on a module you do not teach currently");
        }
    }

    public void deleteQuestion(Long id) throws Exception {
        Question question = questionRepo.getById(id);
        if(canAlterQuestion(question)){
            questionRepo.deleteById(id);
        }
        else{
            throw new Exception("Cannot delete a question you did not add");
        }
    }

    public void modifyQuestion(Question question) throws Exception{
        Question newQuestion = questionRepo.getById(question.getQuestionId());
        if(canAlterQuestion(newQuestion)){
            question.setDateCreation(new Timestamp(System.currentTimeMillis()));
            questionRepo.save(question);
        }
        else{
            throw new Exception("Cannot Alter a question you did not add");
        }
    }

}
