package com.twba.students;

import com.twba.kernel.fwk.ValueObject;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Education extends ValueObject {

    private final String institution;
    private final int endingYear;
    private final EducationType educationType;

    private Education(String institution, int endingYear, EducationType educationType) {
        this.institution = institution;
        this.endingYear = endingYear;
        this.educationType = educationType;
    }
}
