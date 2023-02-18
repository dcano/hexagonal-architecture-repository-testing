package com.twba.students;

import com.twba.kernel.fwk.ValueObject;

import java.util.Objects;

public class Occupation extends ValueObject {

    private final String occupation;
    private final String publicCv;

    protected Occupation(String occupation, String publicCv) {
        this.occupation = occupation;
        this.publicCv = publicCv;
    }

    public static Occupation of(String occupation) {
        return of(occupation, null);
    }

    public static Occupation of(String occupation, String publicCv) {
        return new Occupation(occupation, publicCv);
    }

    public String value() {
        return occupation;
    }

    public String occupation() {
        return occupation;
    }

    public String publicCv() {
        return publicCv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occupation that = (Occupation) o;
        return Objects.equals(occupation, that.occupation) &&
                Objects.equals(publicCv, that.publicCv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupation, publicCv);
    }
}
