package com.example.SpringLogin.Services.EnseignantService;

import com.example.SpringLogin.Entities.*;
import com.example.SpringLogin.Configrations.SecurityServices.ContextHandlerClass;
import com.example.SpringLogin.Entities.Module;
import com.example.SpringLogin.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QuestionService {

    @Autowired
    private AffectationModuleRepo affectationModuleRepo;
    @Autowired
    private ModuleRepo moduleRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ExamenRepo examenRepo;
    @Autowired
    private ExamenQuestionsRepo examenQuestionsRepo;
    @Autowired
    private ContextHandlerClass contextHandlerClass;


    public QuestionService(){
        System.out.println("QuestionService initialized");
    }

    private Enseignant getEnseignant(){
        return (Enseignant) contextHandlerClass.getCurrentLoggedInUser().getUtilisateur();
    }

    private List<AffectationModule> getAffectations(){
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
        return questionRepo.findAll();
    }

    @Transactional(readOnly = false)
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

    @Transactional(readOnly = false)
    public void deleteQuestion(Long id) throws Exception {
        Optional<Question> optQuestion = questionRepo.findById(id);
        if(optQuestion.isEmpty()){
            throw new Exception("No question to delete");
        }
        Question question = optQuestion.get();
        if(canAlterQuestion(question)){
            question.getExamenQuestions().forEach(examenQuestions -> {
                examenQuestions.getExamen().getExamenQuestions().remove(examenQuestions);
            });
            question.getExamenQuestions().clear();
            questionRepo.deleteById(id);
        }
        else{
            throw new Exception("Cannot delete a question you did not add");
        }
    }

    @Transactional(readOnly = false)
    public void modifyQuestion(Question question) throws Exception{
        Optional<Question> optionalQuestion = questionRepo.findById(question.getQuestionId());
        if(optionalQuestion.isEmpty()){
            throw new Exception("No question to modify");
        }
        Question newQuestion = questionRepo.getById(question.getQuestionId());
        if(canAlterQuestion(newQuestion)){
            newQuestion.setContent(question.getContent());
            newQuestion.setDescription(question.getDescription());
            newQuestion.setTypeAnswer(question.getTypeAnswer());
            newQuestion.setDateCreation(new Timestamp(System.currentTimeMillis()));
        }
        else{
            throw new Exception("Cannot Alter a question you did not add");
        }
    }

}
