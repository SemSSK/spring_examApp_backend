package com.example.SpringLogin;

import com.example.SpringLogin.Entities.Module;
import com.example.SpringLogin.Entities.PlanningExamen;
import com.example.SpringLogin.Repos.ModuleRepo;
import com.example.SpringLogin.Repos.PlanningExamenRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SpringLoginApplicationTests {

	@Autowired
	private PlanningExamenRepo planningExamenRepo;
	@Autowired
	private ModuleRepo moduleRepo;

	@Test
	void contextLoads() {
	}

}
