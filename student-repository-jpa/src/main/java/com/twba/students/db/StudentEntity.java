package com.twba.students.db;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;



@Data
@Entity
@Table(schema="students_context", name = "students",
        indexes = {
                @Index(name = "student_email", columnList = "emailAddress"),
                @Index(name = "student_firstName", columnList = "firstName"),
                @Index(name = "student_lastName", columnList = "lastName")
        })
public class StudentEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String occupation;
    private String publicCv;
    private String personalIdType;
    private String personalIdValue;
    private String phoneNumbers;
    private String emailAddress;
    private String studentType;
    private String state;
    private String city;
    private String streetFirst;
    private String streetSecond;
    private String streetNumber;
    private String zipCode;
    private String country;
    private String countryCode;
    private String area;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema = "students_context", name = "student_interests")
    private Set<String> interests;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema = "students_context", name = "student_education")
    @Embedded
    private Set<EducationJpa> education;

    @JdbcTypeCode(SqlTypes.JSON)
    @Basic(fetch = FetchType.EAGER)
    private List<StudyPlanJpa> studyPlanAnswers;

    @JdbcTypeCode(SqlTypes.JSON)
    @Basic(fetch = FetchType.EAGER)
    private List<LearningProfileJpa> learningProfileAnswers;
    @JdbcTypeCode(SqlTypes.JSON)
    @Basic(fetch = FetchType.EAGER)
    private List<NotificationChannelTypeJpa> notificationChannelTypes;

    private boolean active;

    @Version
    private Long version;
}
