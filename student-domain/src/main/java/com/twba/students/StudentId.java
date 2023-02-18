package com.twba.students;

import com.twba.kernel.fwk.ValueObject;

import java.util.Objects;

public class StudentId extends ValueObject {

    private final String value;

    private StudentId(String value) {
        this.value = value;
    }

    String value() {
        return value;
    }

    static StudentId of(String value) {
        if(Objects.isNull(value)) {
            return null;
        }
        return new StudentId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentId studentId = (StudentId) o;
        return Objects.equals(value, studentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
