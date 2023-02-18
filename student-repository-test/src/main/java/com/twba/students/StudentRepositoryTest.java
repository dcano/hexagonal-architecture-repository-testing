package com.twba.students;

import org.junit.jupiter.api.Test;

import static com.twba.students.StudentExamples.randomExistingStudent;
import static com.twba.students.StudentExamples.randomNewStudent;
import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryTest {

    StudentRepository studentRepository;

    @Test
    public void shouldCreateStudent() {
        Student expected = randomNewStudent();
        Student actual = studentRepository.save(expected);

        assertAll("Create Student",
                () -> assertEquals(0L, actual.getVersion()),
                () -> assertEquals(expected.getStudentName(), actual.getStudentName()),
                () -> assertNotNull(actual.getStudentId())
        );
    }

    @Test
    public void shouldUpdateExistingStudent() {
        Student expected = randomExistingStudent();
        Student actual = studentRepository.save(expected);
        assertAll("Update Student",
                () -> assertEquals(expected.getVersion()+1, actual.getVersion()),
                () -> assertEquals(expected.getStudentName(), actual.getStudentName()),
                () -> assertEquals(expected.getStudentId(), actual.getStudentId())
        );
    }

}
