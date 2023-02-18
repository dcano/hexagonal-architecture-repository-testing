package com.twba.students;

import com.twba.kernel.domain.ContactInfo;
import com.twba.kernel.exceptions.OptimisticLockRepositoryException;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudentRepositoryInMemory implements StudentRepository {

    private final List<Student> students;

    public StudentRepositoryInMemory() {
        students = new ArrayList<>();
    }

    @Override
    public Student save(Student student) {

        Student studentToSave = Student.builder()
                .withStudentId(student.getStudentId())
                .withStudentName(student.getStudentName())
                .withStudentType(student.getStudentType())
                .withActive(student.isActive())
                .withGender(student.getGender())
                .withBirthDate(student.getBirthDate())
                .withContactInfo(student.getContactInfo())
                .withInterests(student.getInterests())
                .withLocation(student.getLocation())
                .withCustomNotificationChannel(student.getCustomNotificationChannel())
                .withLearningProfile(student.getLearningProfile())
                .withOccupation(student.getOccupation())
                .withMailAddress(student.getMailAddress())
                .withPersonalId(student.getPersonalId())
                .withStudyPlan(student.getStudyPlan())
                .withVersion(Objects.isNull(student.getVersion())?0L:student.getVersion()+1)
                .instanceExisting();

        checkOptimisticLock(studentToSave);
        students.add(studentToSave);
        return studentToSave;
    }

    @Override
    public Optional<Student> retrieveStudentWithEmail(ContactInfo contactInfo) {
        return students.stream()
                .filter(s -> s.getContactInfo().getEmailAddress().equals(contactInfo.getEmailAddress()))
                .findFirst();
    }

    @Override
    public Publisher<Student> saveReactive(Student student) {
        return null;
    }

    private void checkOptimisticLock(final Student studentToSave) {
        Student existingStudent = students.stream().filter(s -> s.getStudentId().equals(studentToSave.getStudentId())).findFirst().orElse(null);

        if(Objects.isNull(existingStudent)) return;

        if(studentToSave.isStaleWith(existingStudent)) {
            throw new OptimisticLockRepositoryException("Stale data found updating student class with id " + studentToSave.getStudentId().value());
        }
        students.remove(studentToSave);
    }
}
