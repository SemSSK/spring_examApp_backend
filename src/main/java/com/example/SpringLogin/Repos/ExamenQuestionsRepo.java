package com.example.SpringLogin.Repos;

import com.example.SpringLogin.Entities.Examen;
import com.example.SpringLogin.Entities.ExamenQuestions;
import com.example.SpringLogin.Entities.ExamenQuestionsKey;
import com.example.SpringLogin.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamenQuestionsRepo extends JpaRepository<ExamenQuestions, ExamenQuestionsKey> {
    public List<ExamenQuestions> findAllByQuestionNumber(int questionNumber);
    public Optional<ExamenQuestions> findByExamenAndQuestion(Examen examen, Question question);
    public void deleteByQuestion(Question question);
    public void deleteByExamen(Examen examen);
}
