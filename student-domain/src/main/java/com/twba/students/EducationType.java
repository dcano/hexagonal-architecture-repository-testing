package com.twba.students;

public enum EducationType {

    TECHNICIAN ("com.lazulli.ead.commons.shared.Technicial"),
    COLLEGE ("com.lazulli.ead.commons.shared.College"),
    BACHELORS_DEGREE ("com.lazulli.ead.commons.shared.BarchelorsDegree"),
    MASTERS_DEGREE("com.lazulli.ead.commons.shared.MastersDegree");

    private String label;

    String label() {
        return this.label;
    }

    EducationType(String label) {
        this.label = label;
    }

}
