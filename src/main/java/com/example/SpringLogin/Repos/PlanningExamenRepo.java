package com.example.SpringLogin.Repos;

import com.example.SpringLogin.Entities.Module;
import com.example.SpringLogin.Entities.PlanningExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanningExamenRepo extends JpaRepository<PlanningExamen,Long> {

    public List<PlanningExamen> findAllByModule(Module module);
}
