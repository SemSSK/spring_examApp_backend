package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ExamenQuestionsKey implements Serializable {
    @Column(name="exam_id")
    private Long examId;
    @Column(name="question_id")
    private Long questionId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((examId == null) ? 0 : examId.hashCode() );
        result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(!(obj instanceof ExamenQuestionsKey)){
            return false;
        }

        ExamenQuestionsKey key = (ExamenQuestionsKey)obj;

        return (this.examId.equals(key.examId)
                && this.questionId.equals(key.questionId));
    }

}
