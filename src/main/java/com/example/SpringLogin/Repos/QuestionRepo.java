package com.example.SpringLogin.Repos;

import com.example.SpringLogin.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
}
