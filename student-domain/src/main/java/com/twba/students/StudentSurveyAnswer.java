package com.twba.students;

import com.twba.kernel.fwk.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Getter
class StudentSurveyAnswer extends ValueObject {

    private final String question;
    private final Set<String> answers;

    private StudentSurveyAnswer(String question, Set<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    static StudentSurveyAnswer with(String question, Set<String> answers) {
        return new StudentSurveyAnswer(question, answers);
    }

    static StudentSurveyAnswer forQuestion(String questionId) {
        return new StudentSurveyAnswer(questionId, new HashSet<>());
    }

    StudentSurveyAnswer add(String answer) {
        answers.add(answer);
        return this;
    }

    Set<String> getAnswers() {
        return Collections.unmodifiableSet(answers);
    }

}
