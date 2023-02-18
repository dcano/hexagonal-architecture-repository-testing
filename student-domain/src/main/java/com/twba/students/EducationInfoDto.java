package com.twba.students;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class EducationInfoDto {

    @NotNull
    private EducationType educationType;
    @NotBlank
    private String institution;
    @NotBlank
    private int yearOfCompletion;

}
