package com.twba;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EducationInfoDto {

    @NotNull
    private EducationType educationType;
    @NotBlank
    private String institution;
    @NotBlank
    private int yearOfCompletion;

}
