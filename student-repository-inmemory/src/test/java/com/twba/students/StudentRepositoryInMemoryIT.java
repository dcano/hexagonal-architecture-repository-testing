package com.twba.students;

import org.junit.jupiter.api.BeforeEach;

public class StudentRepositoryInMemoryIT extends StudentRepositoryTest {

    @BeforeEach
    public void setup() {
        super.studentRepository = new StudentRepositoryInMemory();
    }

}
