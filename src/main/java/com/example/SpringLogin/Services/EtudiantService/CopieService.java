package com.example.SpringLogin.Services.EtudiantService;

import com.example.SpringLogin.Configrations.SecurityServices.ContextHandlerClass;
import com.example.SpringLogin.Entities.*;
import com.example.SpringLogin.Entities.Module;
import com.example.SpringLogin.Enumarators.PrésenceEtats;
import com.example.SpringLogin.Repos.CopieRepo;
import com.example.SpringLogin.Repos.PlanningExamenRepo;
import com.example.SpringLogin.Repos.PrésencesRepo;
import com.example.SpringLogin.Repos.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CopieService {

    /*@Autowired
    private CopieRepo copieRepo;
    @Autowired
    private ContextHandlerClass contextHandlerClass;
    @Autowired
    private UtilisateurRepo utilisateurRepo;
    @Autowired
    private PlanningExamenRepo planningExamenRepo;
    @Autowired
    private PrésencesRepo présencesRepo;


    private Etudiant getEtudiant(){
        return (Etudiant) contextHandlerClass.getCurrentLoggedInUser().getUtilisateur();
    }

    private boolean isPostingInTime(PlanningExamen planningExamen){
        Long time = System.currentTimeMillis();
        Long endOfExam = planningExamen.getDateOfExame().getTime() + planningExamen.getDuration().getTime() + 5000;
        return time >= planningExamen.getDateOfExame().getTime() &&
                time <= endOfExam;
    }

    private boolean canPostCopy(SessionExamen sessionExamen){
        EtudiantSessionKey etudiantSessionKey = new EtudiantSessionKey();
        etudiantSessionKey.setEtudiantId(getEtudiant().getUserId());
        etudiantSessionKey.setSessionId(sessionExamen.getSessionId());
        Optional<Présences> présences = présencesRepo.findById(etudiantSessionKey);

        if(présences.isEmpty())
        {
            return false;
        }
        return présences.get().getState().equals(PrésenceEtats.PRESENT);
    }

    private boolean isConcernedByExam(PlanningExamen planningExamen){
        return planningExamen.getEtudiants().contains(getEtudiant());
    }

    private Copie parseCopie(Map<String,Object> travaille) throws Exception{
        Copie copie = (Copie) travaille.get("copie");
        return copie;
    }

    private PlanningExamen planningExamen(Map<String,Object> travaille) throw

    @Transactional(readOnly = false)
    public void postCopy(Map<String,Object> travaille){

    }
*/
}
