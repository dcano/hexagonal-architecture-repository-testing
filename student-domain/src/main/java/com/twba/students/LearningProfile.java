package com.twba.students;

import com.twba.kernel.fwk.ValueObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class LearningProfile extends ValueObject {

    private final List<StudentSurveyAnswer> learningProfileAnswers;

    private LearningProfile(List<StudentSurveyAnswer> learningProfileAnswers) {
        this.learningProfileAnswers = learningProfileAnswers;
    }

    static LearningProfile with(List<StudentSurveyAnswer> learningProfileAnswers) {
        return new LearningProfile(learningProfileAnswers);
    }

    static LearningProfile empty() {
        return new LearningProfile(new ArrayList<>());
    }

    LearningProfile addAnswer(StudentSurveyAnswer studentSurveyAnswer) {
        if(learningProfileAnswers.stream().noneMatch(a -> a.getQuestion().equals(studentSurveyAnswer.getQuestion()))) {
            learningProfileAnswers.add(studentSurveyAnswer);
        }
        return this;
    }

    List<StudentSurveyAnswer> getLearningProfileAnswers() {
        return Collections.unmodifiableList(learningProfileAnswers);
    }

}
