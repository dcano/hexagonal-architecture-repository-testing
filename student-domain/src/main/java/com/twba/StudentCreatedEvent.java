package com.twba;


import com.twba.kernel.domain.*;
import com.twba.kernel.fwk.DomainEventPayload;
import com.twba.kernel.fwk.TenantId;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter(AccessLevel.PACKAGE)
class StudentCreatedEvent extends DomainEventPayload {

    private final String studentId;
    private final String birthDate;
    private final ContactInfo contactInfo;
    private final Gender gender;
    private final String occupation;
    private final PersonalId personalId;
    private final StudentName studentName;
    private final StudentType studentType;
    private final List<String> interests;
    private final Location location;
    private final MailAddress mailAddress;

    StudentCreatedEvent(StudentId studentId,
                        LocalDate birthDate,
                        ContactInfo contactInfo,
                        Gender gender,
                        Occupation occupation,
                        PersonalId personalId,
                        StudentName studentName,
                        StudentType studentType,
                        List<CategoryId> interests,
                        Location location,
                        MailAddress mailAddress) {
        this(Instant.now(), UUID.randomUUID().toString(),studentId,  birthDate, contactInfo, gender, occupation, personalId, studentName, studentType, interests, location, mailAddress);
    }

    StudentCreatedEvent(Instant occurredOn,
                        String eventUid,
                        StudentId studentId,
                        LocalDate birthDate,
                        ContactInfo contactInfo,
                        Gender gender,
                        Occupation occupation,
                        PersonalId personalId,
                        StudentName studentName,
                        StudentType studentType,
                        List<CategoryId> interests,
                        Location location,
                        MailAddress mailAddress) {
        super(occurredOn, eventUid, TenantId.B2C().getId());
        this.studentId = studentId.value();
        this.birthDate = birthDate.format(DateTimeFormatter.ISO_DATE);
        this.contactInfo = contactInfo;
        this.gender = gender;
        this.occupation = occupation.value();
        this.personalId = personalId;
        this.studentName = studentName;
        this.studentType = studentType;
        this.interests = interests.stream().map(CategoryId::value).collect(Collectors.toList());
        this.location = location;
        this.mailAddress = mailAddress;
    }

    List<String> getInterests() {
        return Collections.unmodifiableList(interests);
    }

}
