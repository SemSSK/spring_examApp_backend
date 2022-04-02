package com.example.SpringLogin.RepositoriesTests;

import com.example.SpringLogin.Entities.AffectationModule;
import com.example.SpringLogin.Entities.Enseignant;
import com.example.SpringLogin.Entities.Module;
import com.example.SpringLogin.Repos.AffectationModuleRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class AffectationModuleRepoTests {
    @Autowired
    private AffectationModuleRepo affectationModuleRepo;

    private Enseignant enseignant = new Enseignant();
    private Module module = new Module();

    @Test
    void findByEnseignantAndModule1(){
        enseignant.setUserId(3L);
        module.setId(1L);
        Optional<AffectationModule> affectationModule = affectationModuleRepo.findByEnseignantAndModule(enseignant,module);
        Assertions.assertFalse(affectationModule.isEmpty());
        Assertions.assertNotNull(affectationModule.get());
        Assertions.assertEquals(affectationModule.get().getEnseignant(),enseignant);
        Assertions.assertEquals(affectationModule.get().getModule(),module);
    }

    @Test
    void findByEnseignantAndModule2(){
        enseignant.setUserId(3L);
        module.setId(2L);
        Optional<AffectationModule> affectationModule = affectationModuleRepo.findByEnseignantAndModule(enseignant,module);
        Assertions.assertFalse(affectationModule.isEmpty());;
        Assertions.assertEquals(affectationModule.get().getEnseignant(),enseignant);
        Assertions.assertEquals(affectationModule.get().getModule(),module);
    }

    @Test
    void findByEnseignantAndModule3(){
        enseignant.setUserId(4L);
        module.setId(3L);
        Optional<AffectationModule> affectationModule = affectationModuleRepo.findByEnseignantAndModule(enseignant,module);
        Assertions.assertFalse(affectationModule.isEmpty());;
        Assertions.assertEquals(affectationModule.get().getEnseignant(),enseignant);
        Assertions.assertEquals(affectationModule.get().getModule(),module);
    }

    @Test
    void findByEnseignantAndModule4(){
        enseignant.setUserId(6L);
        module.setId(7L);
        Optional<AffectationModule> affectationModule = affectationModuleRepo.findByEnseignantAndModule(enseignant,module);
        Assertions.assertTrue(affectationModule.isEmpty());
    }

}
