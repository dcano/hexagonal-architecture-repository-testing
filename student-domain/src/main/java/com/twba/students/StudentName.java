package com.twba.students;

import com.twba.kernel.domain.PersonName;

public class StudentName extends PersonName {

    private StudentName(String firstName, String lastName) {
        super(firstName, lastName);
    }

    static StudentName with(String firstName, String lastName) {
        return new StudentName(firstName, lastName);
    }

}
