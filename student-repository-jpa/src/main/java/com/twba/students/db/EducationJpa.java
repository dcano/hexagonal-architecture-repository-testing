package com.twba.students.db;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EducationJpa {

    private String institution;
    private int endingYear;
    private String educationType;

}
