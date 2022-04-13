package com.example.SpringLogin.Repos;

import com.example.SpringLogin.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    Collection<Question> findAllByModule(Module module);
}
