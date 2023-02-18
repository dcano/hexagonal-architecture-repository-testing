package com.twba.students;

import com.twba.kernel.domain.ContactInfo;
import com.twba.kernel.domain.Gender;
import com.twba.kernel.domain.MailAddress;
import com.twba.kernel.domain.PersonalId;
import com.twba.students.db.*;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class StudentRepositoryJpa implements StudentRepository{

    private final StudentRepositoryJpaHelper studentRepositoryJpaHelper;

    public StudentRepositoryJpa(StudentRepositoryJpaHelper studentRepositoryJpaHelper) {
        this.studentRepositoryJpaHelper = studentRepositoryJpaHelper;
    }

    @Override
    public Student save(Student student) {
        return toDomain(studentRepositoryJpaHelper.save(toJpa(student)));
    }

    @Override
    public Optional<Student> retrieveStudentWithEmail(ContactInfo contactInfo) {
        return Optional.ofNullable(toDomain(studentRepositoryJpaHelper.findStudentEntityByEmailAddressAndActive(contactInfo.getEmailAddress(), true)));
    }

    @Override
    public Publisher<Student> saveReactive(Student student) {
        return null;
    }

    private static StudentEntity toJpa(Student student) {
        if(Objects.isNull(student)) {
            return  null;
        }
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getStudentId() != null ? student.getStudentId().value() : UUID.randomUUID().toString());
        studentEntity.setBirthDate(student.getBirthDate());
        studentEntity.setEmailAddress(student.getContactInfo().getEmailAddress());
        studentEntity.setFirstName(student.getStudentName().getFirstName());
        studentEntity.setLastName(student.getStudentName().getLastName());
        studentEntity.setGender(student.getGender().name());
        studentEntity.setOccupation(student.getOccupation().occupation());
        studentEntity.setInterests(student.getInterests().stream().map(CategoryId::value).collect(Collectors.toSet()));
        studentEntity.setEducation(student.getEducation().stream().map(StudentRepositoryJpa::toEducationJpa).collect(Collectors.toSet()));
        studentEntity.setId(student.getStudentId().value());
        studentEntity.setPersonalIdType(student.getPersonalId().type());
        studentEntity.setPersonalIdValue(student.getPersonalId().value());
        studentEntity.setPhoneNumbers(String.join(",", student.getContactInfo().getPhoneNumbers()));
        studentEntity.setPublicCv(student.getOccupation().publicCv());
        studentEntity.setStudentType(student.getStudentType().name());
        studentEntity.setVersion(student.getVersion());
        studentEntity.setCity(student.getMailAddress().getCity());
        studentEntity.setState(student.getMailAddress().getState());
        studentEntity.setArea(student.getMailAddress().getArea().orElse(""));
        studentEntity.setCountry(student.getMailAddress().getCountry());
        studentEntity.setCountryCode(student.getMailAddress().getCountryCode().orElse(""));
        studentEntity.setStreetFirst(student.getMailAddress().getStreetFirst());
        studentEntity.setStreetSecond(student.getMailAddress().getStreetSecond().orElse(""));
        studentEntity.setStreetNumber(student.getMailAddress().getNumber());
        studentEntity.setZipCode(student.getMailAddress().getZip());
        studentEntity.setActive(student.isActive());

        if (Objects.isNull(student.getLearningProfile()) || Objects.isNull(student.getLearningProfile().getLearningProfileAnswers())) {
            studentEntity.setLearningProfileAnswers(new ArrayList<>());
        } else {
            studentEntity.setLearningProfileAnswers(student.getLearningProfile().getLearningProfileAnswers().stream().map(a -> new LearningProfileJpa(a.getQuestion(), a.getAnswers())).collect(Collectors.toList()));
        }
        if (Objects.isNull(student.getStudyPlan()) || Objects.isNull(student.getStudyPlan().getStudyPlanAnswers())) {
            studentEntity.setStudyPlanAnswers(new ArrayList<>());
        } else {
            studentEntity.setStudyPlanAnswers(student.getStudyPlan().getStudyPlanAnswers().stream().map(a -> new StudyPlanJpa(a.getQuestion(), a.getAnswers())).collect(Collectors.toList()));
        }

        studentEntity.setNotificationChannelTypes(student.getCustomNotificationChannel().getNotificationChannels().stream().map(notificationChannelType -> new NotificationChannelTypeJpa(notificationChannelType.name())).collect(Collectors.toList()));
        return studentEntity;
    }

    private static Student toDomain(StudentEntity studentEntity) {
        if(Objects.isNull(studentEntity)) {
            return null;
        }
        return Student.builder()
                .withBirthDate(studentEntity.getBirthDate())
                .withContactInfo(ContactInfo.with(studentEntity.getEmailAddress(), studentEntity.getPhoneNumbers().split(",")))
                .withEducation(studentEntity.getEducation().stream().map(edJpa -> Education.builder()
                        .educationType(EducationType.valueOf(edJpa.getEducationType()))
                        .endingYear(edJpa.getEndingYear())
                        .institution(edJpa.getInstitution())
                        .build()).collect(Collectors.toList()))
                .withGender(Gender.valueOf(studentEntity.getGender()))
                .withInterests(studentEntity.getInterests().stream().map(CategoryId::of).collect(Collectors.toList()))
                .withOccupation(Occupation.of(studentEntity.getOccupation(), studentEntity.getPublicCv()))
                .withPersonalId(PersonalId.with(studentEntity.getPersonalIdValue(), studentEntity.getPersonalIdType()))
                .withStudentId(StudentId.of(studentEntity.getId()))
                .withStudentName(StudentName.with(studentEntity.getFirstName(), studentEntity.getLastName()))
                .withStudentType(StudentType.valueOf(studentEntity.getStudentType()))
                .withVersion(studentEntity.getVersion())
                .withActive(studentEntity.isActive())
                .withMailAddress(MailAddress.builder()
                        .withArea(studentEntity.getArea())
                        .withCity(studentEntity.getCity())
                        .withCountry(studentEntity.getCountry())
                        .withCountryCode(studentEntity.getCountryCode())
                        .withNumber(studentEntity.getStreetNumber())
                        .withState(studentEntity.getState())
                        .withStreetFirst(studentEntity.getStreetFirst())
                        .withStreetSecond(studentEntity.getStreetSecond())
                        .withZip(studentEntity.getZipCode())
                        .build())
                .withStudyPlan(Objects.isNull(studentEntity.getStudyPlanAnswers()) ? StudyPlan.empty() : StudyPlan.with(studentEntity.getStudyPlanAnswers().stream().map(a -> StudentSurveyAnswer.with(a.getQuestionId(), a.getAnswers())).collect(Collectors.toList())))
                .withLearningProfile(Objects.isNull(studentEntity.getLearningProfileAnswers()) ? LearningProfile.empty() : LearningProfile.with(studentEntity.getLearningProfileAnswers().stream().map(a -> StudentSurveyAnswer.with(a.getQuestionId(), a.getAnswers())).collect(Collectors.toList())))
                .withCustomNotificationChannel(Objects.isNull(studentEntity.getNotificationChannelTypes()) ? CustomNotificationChannel.empty() : CustomNotificationChannel.with(studentEntity.getNotificationChannelTypes()
                        .stream().map(nfc -> NotificationChannelType.valueOf(nfc.getNotificationChannel())).collect(Collectors.toList())))
                .instanceExisting();

    }

    private static EducationJpa toEducationJpa(Education education) {
        EducationJpa educationJpa = new EducationJpa();
        educationJpa.setEducationType(education.getEducationType().name());
        educationJpa.setEndingYear(education.getEndingYear());
        educationJpa.setInstitution(education.getInstitution());
        return educationJpa;
    }

}
