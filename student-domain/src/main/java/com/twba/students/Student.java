package com.twba.students;

import com.twba.kernel.domain.*;
import com.twba.kernel.fwk.Entity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Student extends Entity {

    private StudentId studentId;
    private StudentName studentName;
    private LocalDate birthDate;
    private Gender gender;
    private Occupation occupation;
    private PersonalId personalId;
    private ContactInfo contactInfo;
    private StudentType studentType;
    private List<CategoryId> interests;
    protected List<Education> education;
    private Location location;
    private MailAddress mailAddress;
    private boolean active;
    private LearningProfile learningProfile;
    private StudyPlan studyPlan;
    private CustomNotificationChannel customNotificationChannel;

    Student(StudentId studentId,
            StudentName studentName,
            LocalDate birthDate,
            Gender gender,
            Occupation occupation,
            PersonalId personalId,
            ContactInfo contactInfo,
            StudentType studentType,
            List<CategoryId> interests,
            List<Education> education,
            Long version,
            Location location,
            boolean active,
            MailAddress mailAddress,
            LearningProfile learningProfile,
            StudyPlan studyPlan,
            CustomNotificationChannel customNotificationChannel) {
        super(version);
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setBirthDate(birthDate);
        this.setGender(gender);
        this.setOccupation(occupation);
        this.setPersonalId(personalId);
        this.setContactInfo(contactInfo);
        this.setStudentType(studentType);
        this.setStudentName(studentName);
        this.setInterests(interests);
        this.setEducation(education);
        this.setLocation(location);
        this.setActive(active);
        this.setMailAddress(mailAddress);
        this.setStudyPlan(studyPlan);
        this.setLearningProfile(learningProfile);
        this.setCustomNotificationChannel(customNotificationChannel);
    }

    protected void setLearningProfile(LearningProfile learningProfile) {
        if (Objects.isNull(learningProfile)) {
            learningProfile = LearningProfile.empty();
        }
        this.learningProfile = learningProfile;
    }

    LearningProfile getLearningProfile() {
        return learningProfile;
    }

    protected void setStudyPlan(StudyPlan studyPlan) {
        if (Objects.isNull(studyPlan)) {
            studyPlan = StudyPlan.empty();
        }
        this.studyPlan = studyPlan;
    }

    StudyPlan getStudyPlan() {
        return studyPlan;
    }

    StudentId getStudentId() {
        return studentId;
    }

    private void setStudentId(StudentId studentId) {
        this.studentId = studentId;
    }

    StudentName getStudentName() {
        return studentName;
    }

    private void setStudentName(StudentName studentName) {
        this.studentName = studentName;
    }

    LocalDate getBirthDate() {
        return birthDate;
    }

    private void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    Gender getGender() {
        return gender;
    }

    private void setGender(Gender gender) {
        this.gender = gender;
    }

    Occupation getOccupation() {
        return occupation;
    }

    protected void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    PersonalId getPersonalId() {
        return personalId;
    }

    private void setPersonalId(PersonalId personalId) {
        this.personalId = personalId;
    }

    ContactInfo getContactInfo() {
        return contactInfo;
    }

    private void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    StudentType getStudentType() {
        return studentType;
    }

    private void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    List<CategoryId> getInterests() {
        return Objects.isNull(interests) ? Collections.emptyList() : Collections.unmodifiableList(interests);
    }

    private void setInterests(List<CategoryId> interests) {
        this.interests = interests;
    }

    List<Education> getEducation() {
        return Objects.isNull(interests) ? Collections.emptyList() : Collections.unmodifiableList(education);
    }

    void setEducation(List<Education> education) {
        this.education = education;
    }

    Location getLocation() {
        return location;
    }

    boolean isActive() {
        return active;
    }

    void setActive(boolean active) {
        this.active = active;
    }

    private void setLocation(Location location) {
        this.location = location;
    }

    MailAddress getMailAddress() {
        return mailAddress;
    }

    private void setMailAddress(MailAddress mailAddress) {
        this.mailAddress = mailAddress;
    }

    CustomNotificationChannel getCustomNotificationChannel() {
        return customNotificationChannel;
    }

    private void setCustomNotificationChannel(CustomNotificationChannel customNotificationChannel) {
        this.customNotificationChannel = customNotificationChannel;
    }

    void deleteStudent() {
        this.setActive(false);
        this.publishEvent(new StudentDeletedEvent(studentId));
    }


    void updateLearningProfileWith(LearningProfile learningProfile) {
        if (Objects.nonNull(this.getLearningProfile()) && !this.getLearningProfile().getLearningProfileAnswers().isEmpty()) {
            this.publishEvent(new StudentLearningProfileDeleted(getStudentId(), getLearningProfile()));
        }
        this.setLearningProfile(learningProfile);
        this.publishEvent(new StudentLearningProfileUpdated(getStudentId(), learningProfile));
    }


    void updateStudyPlanWith(StudyPlan studyPlan) {
        if (Objects.nonNull(this.getStudyPlan()) && !this.getStudyPlan().getStudyPlanAnswers().isEmpty()) {
            this.publishEvent(new StudentStudyPlanDeleted(getStudentId(), getStudyPlan()));
        }
        this.setStudyPlan(studyPlan);
        this.publishEvent(new StudentStudyPlanUpdated(getStudentId(), studyPlan));
    }


    @Override
    public String aggregateId() {
        return studentId.value();
    }

    void addEducationDetail(Education education) {
        this.education.add(education);
        this.publishEvent(new EducationAddedToStudentEvent(getStudentId(), education));
    }

    void updatePersonalInfo(StudentName studentName,
                            ContactInfo contactInfo,
                            Gender gender,
                            PersonalId personalId,
                            StudentType studentType,
                            LocalDate birthDate,
                            MailAddress mailAddress,
                            List<CategoryId> interests) {
        setStudentName(studentName);
        setContactInfo(contactInfo);
        setGender(gender);
        setPersonalId(personalId);
        setStudentType(studentType);
        setBirthDate(birthDate);
        setMailAddress(mailAddress);
        setLocation(location);
        setInterests(interests);
    }

    void updateProfile(StudentName studentName,
                       ContactInfo contactInfo,
                       Gender gender,
                       Occupation occupation,
                       PersonalId personalId,
                       List<CategoryId> interests,
                       StudentType studentType,
                       LocalDate birthDate,
                       List<Education> educationInfo,
                       MailAddress mailAddress) {
        setStudentName(studentName);
        setContactInfo(contactInfo);
        setGender(gender);
        setOccupation(occupation);
        setPersonalId(personalId);
        setInterests(interests);
        setStudentType(studentType);
        setBirthDate(birthDate);
        setEducation(educationInfo);
        setLocation(location);
        setMailAddress(mailAddress);
    }


    void enable() {
        if (!isActive()) { //ensure idempotency
            setActive(true);
            this.publishEvent(new StudentEnabledEvent(getStudentId()));
        }
    }


    void disable() {
        if (isActive()) { //ensure idempotency
            setActive(false);
            this.publishEvent(new StudentDisabledEvent(getStudentId()));
        }
    }

    static StudentBuilder builder() {
        return new StudentBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(getVersion(), student.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, getVersion());
    }


    static class StudentBuilder {

        private StudentId studentId;
        private StudentName studentName;
        private LocalDate birthDate;
        private Gender gender;
        private Occupation occupation;
        private PersonalId personalId;
        private ContactInfo contactInfo;
        private StudentType studentType;
        private List<CategoryId> interests;
        private List<Education> education;
        private Location location;
        private boolean active;
        private long version;
        private MailAddress mailAddress;
        private LearningProfile learningProfile;
        private StudyPlan studyPlan;
        private CustomNotificationChannel customNotificationChannel;

        StudentId getStudentId() {
            return studentId;
        }

        StudentBuilder withStudentId(StudentId studentId) {
            this.studentId = studentId;
            return this;
        }

        StudentName getStudentName() {
            return studentName;
        }

        StudentBuilder withStudentName(StudentName studentName) {
            this.studentName = studentName;
            return this;
        }

        LocalDate getBirthDate() {
            return birthDate;
        }

        StudentBuilder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        Gender getGender() {
            return gender;
        }

        StudentBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        Occupation getOccupation() {
            return occupation;
        }

        StudentBuilder withOccupation(Occupation occupation) {
            this.occupation = occupation;
            return this;
        }

        PersonalId getPersonalId() {
            return personalId;
        }

        StudentBuilder withPersonalId(PersonalId personalId) {
            this.personalId = personalId;
            return this;
        }

        ContactInfo getContactInfo() {
            return contactInfo;
        }

        StudentBuilder withContactInfo(ContactInfo contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        StudentType getStudentType() {
            return studentType;
        }

        StudentBuilder withStudentType(StudentType studentType) {
            this.studentType = studentType;
            return this;
        }

        List<CategoryId> getInterests() {
            return interests;
        }

        StudentBuilder withEducation(List<Education> education) {
            this.education = education;
            return this;
        }

        List<Education> getEducation() {
            return education;
        }

        StudentBuilder withInterests(List<CategoryId> interests) {
            this.interests = interests;
            return this;
        }

        long getVersion() {
            return version;
        }

        StudentBuilder withVersion(long version) {
            this.version = version;
            return this;
        }

        Location getLocation() {
            return location;
        }

        StudentBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        boolean isActive() {
            return active;
        }

        StudentBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        MailAddress getMailAddress() {
            return mailAddress;
        }

        StudentBuilder withMailAddress(MailAddress mailAddress) {
            this.mailAddress = mailAddress;
            return this;
        }

        StudentBuilder withLearningProfile(LearningProfile learningProfile) {
            this.learningProfile = learningProfile;
            return this;
        }

        StudentBuilder withStudyPlan(StudyPlan studyPlan) {
            this.studyPlan = studyPlan;
            return this;
        }

        StudyPlan getStudyPlan() {
            return studyPlan;
        }

        StudentBuilder withCustomNotificationChannel(CustomNotificationChannel customNotificationChannel) {
            this.customNotificationChannel = customNotificationChannel;
            return this;
        }

        CustomNotificationChannel getCustomNotificationChannel() {
            return customNotificationChannel;
        }

        LearningProfile getLearningProfile() {
            return learningProfile;
        }

        Student createNew() {
            Student student = new Student(StudentId.of(UUID.randomUUID().toString()),
                    getStudentName(),
                    getBirthDate(),
                    getGender(),
                    getOccupation(),
                    getPersonalId(),
                    getContactInfo(),
                    getStudentType(),
                    getInterests(),
                    getEducation(),
                    null,
                    getLocation(),
                    true,
                    getMailAddress(),
                    getLearningProfile(),
                    getStudyPlan(),
                    getCustomNotificationChannel());

            student.publishEvent(new StudentCreatedEvent(
                    student.getStudentId(),
                    student.getBirthDate(),
                    student.getContactInfo(),
                    student.getGender(),
                    student.getOccupation(),
                    student.getPersonalId(),
                    student.getStudentName(),
                    student.getStudentType(),
                    student.getInterests(),
                    student.getLocation(),
                    student.getMailAddress()
            ));
            student.updateStudyPlanWith(getStudyPlan());
            student.updateLearningProfileWith(getLearningProfile());
            return student;
        }

        Student instanceExisting() {
            return new Student(getStudentId(),
                    getStudentName(),
                    getBirthDate(),
                    getGender(),
                    getOccupation(),
                    getPersonalId(),
                    getContactInfo(),
                    getStudentType(),
                    getInterests(),
                    getEducation(),
                    getVersion(),
                    getLocation(),
                    isActive(),
                    getMailAddress(),
                    getLearningProfile(),
                    getStudyPlan(),
                    getCustomNotificationChannel());
        }
    }


}
