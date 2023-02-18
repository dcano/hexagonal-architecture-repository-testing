package com.twba.students;

import com.twba.kernel.domain.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

class StudentExamples {

    private static Student.StudentBuilder randomStudent() {
        return Student.builder()
                .withActive(true)
                .withBirthDate(LocalDate.of(1990, 10, 25))
                .withEducation(Collections.singletonList(Education.builder()
                        .educationType(EducationType.MASTERS_DEGREE)
                        .endingYear(2018)
                        .institution("Test Institution")
                        .build()))
                .withGender(Gender.MALE)
                .withInterests(Collections.singletonList(CategoryId.of("TECHNOLOGY")))
                .withContactInfo(ContactInfo.with("random@myemail.com", "999-0000-000"))
                .withLocation(Location.in("MyState", "MyCity"))
                .withOccupation(Occupation.of("CIVIL ENGINEER"))
                .withStudentName(StudentName.with("John", "Doe"))
                .withStudentType(StudentType.B2C)
                .withMailAddress(MailAddress.builder().build())
                .withCustomNotificationChannel(CustomNotificationChannel.with(Collections.singletonList(NotificationChannelType.EMAIL)))
                .withLearningProfile(LearningProfile.empty())
                .withPersonalId(PersonalId.with("StudentIdentificationNumber", "PASSPORT"))
                .withStudyPlan(StudyPlan.empty());
    }

    static Student randomNewStudent() {
        return randomStudent()
                .createNew();
    }

    static Student randomExistingStudent() {
        return randomStudent()
                .withStudentId(StudentId.of(UUID.randomUUID().toString()))
                .withVersion(5)
                .instanceExisting();
    }


}
