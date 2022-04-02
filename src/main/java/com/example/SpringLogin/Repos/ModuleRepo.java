package com.example.SpringLogin.Repos;

import com.example.SpringLogin.Entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepo extends JpaRepository<Module,Long> {
}
