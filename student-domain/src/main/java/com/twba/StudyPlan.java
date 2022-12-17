package com.twba;

import com.twba.kernel.fwk.ValueObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StudyPlan extends ValueObject {

    private final List<StudentSurveyAnswer> studyPlanAnswers;

    private StudyPlan(List<StudentSurveyAnswer> studyPlanAnswers) {
        this.studyPlanAnswers = studyPlanAnswers;
    }

    static StudyPlan with(List<StudentSurveyAnswer> learningProfileAnswers) {
        return new StudyPlan(learningProfileAnswers);
    }

    static StudyPlan empty() {
        return new StudyPlan(new ArrayList<>());
    }

    StudyPlan addAnswer(StudentSurveyAnswer studentSurveyAnswer) {
        if(studyPlanAnswers.stream().noneMatch(a -> a.getQuestion().equals(studentSurveyAnswer.getQuestion()))) {
            studyPlanAnswers.add(studentSurveyAnswer);
        }
        return this;
    }

    List<StudentSurveyAnswer> getStudyPlanAnswers() {
        return Collections.unmodifiableList(studyPlanAnswers);
    }

}
