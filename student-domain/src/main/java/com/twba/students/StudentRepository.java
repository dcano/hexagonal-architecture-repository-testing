package com.twba.students;

import com.twba.kernel.domain.ContactInfo;
import org.reactivestreams.Publisher;

import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);
    Optional<Student> retrieveStudentWithEmail(ContactInfo contactInfo);
    Publisher<Student> saveReactive(Student student);

}
