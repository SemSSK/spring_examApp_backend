package com.example.SpringLogin.Services.EnseignantService;

import com.example.SpringLogin.Entities.*;
import com.example.SpringLogin.Entities.Module;
import com.example.SpringLogin.Configrations.SecurityServices.ContextHandlerClass;
import com.example.SpringLogin.Repos.AffectationModuleRepo;
import com.example.SpringLogin.Repos.ExamenRepo;
import com.example.SpringLogin.Repos.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ExamenService {

    @Autowired
    private ContextHandlerClass contextHandlerClass;
    @Autowired
    private AffectationModuleRepo affectationModuleRepo;
    @Autowired
    private ExamenRepo examenRepo;
    @Autowired
    private QuestionRepo questionRepo;

    public ExamenService(){
        System.out.println("ExamenService Initialized");
    }

    private Enseignant getEnseignant(){
        return (Enseignant) contextHandlerClass.getCurrentLoggedInUser().getUtilisateur();
    }

    private boolean canAccessExamsofModule(Module module){
        Optional<AffectationModule> affectationModule = affectationModuleRepo.findByEnseignantAndModule(getEnseignant(),module);
        if(affectationModule.isEmpty()){
            return false;
        }
        return affectationModule.get().getType().equals("COURS");
    }

    public Examen getModuleExam(Module module) throws Exception{

        if(canAccessExamsofModule(module)){
            Optional<Examen> examen = examenRepo.findByModule(module);
            if(examen.isEmpty())
            {
                throw new Exception("No Exam created yet");
            }
            return examen.get();
        }
        else{
            throw new Exception("Cannot access exam of a module you are not responsible of");
        }
    }

    private boolean validQuestions(Collection<Question> questionList, Module module){
        for (Question question: questionList) {

            Optional<Question> dbQuestion = questionRepo.findById(question.getQuestionId());

            if(dbQuestion.isEmpty()){
                return false;
            }

            if(!dbQuestion.get().getModule().equals(module)){
                return false;
            }

        }
        return true;
    }

    @Transactional(readOnly = false)
    public void addExamen(Examen examen) throws Exception {

        if(!validQuestions(examen.getQuestions(),examen.getModule())){
            throw new Exception("Invalid questions");
        }

        if(canAccessExamsofModule(examen.getModule())){
            Optional<Examen> currentExam = examenRepo.findByModule(examen.getModule());
            if(!currentExam.isEmpty()) {
                examen.setExamId(currentExam.get().getExamId());
            }
            examen.setDateCreation(new Timestamp(System.currentTimeMillis()));
            examenRepo.save(examen);
        }
        else{
            throw new Exception("Cannot access exam of a module you are not responsible of");
        }

    }

    @Transactional(readOnly = false)
    public void deleteExamen(Long examId) throws Exception {
        Optional<Examen> examen = examenRepo.findById(examId);
        if(examen.isEmpty()){
            throw new Exception("No exam to delete");
        }
        if(canAccessExamsofModule(examen.get().getModule())){
            examenRepo.deleteById(examen.get().getExamId());
        }
        else{
            throw new Exception("Cannot access exam of a module you are not responsible of");
        }
    }

    @Transactional(readOnly = false)
    public void modifyExamen(Examen examen) throws Exception {

        Optional<Examen> realExamen = examenRepo.findById(examen.getExamId());

        if(!validQuestions(examen.getQuestions(),examen.getModule())){
            throw new Exception("Invalid question modules");
        }

        if(realExamen.isEmpty()){
            throw new Exception("No exam to modify");
        }

        if(canAccessExamsofModule(examen.getModule())){
            //Modifying data
            realExamen.get().setDateCreation(new Timestamp(System.currentTimeMillis()));
            realExamen.get().setPublicInfo(examen.getPublicInfo());
            realExamen.get().setQuestions(examen.getQuestions());
        }
        else{
            throw new Exception("Cannot access exam of a module you are not responsible of");
        }
    }


}
