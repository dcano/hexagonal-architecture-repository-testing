package com.twba;


import com.twba.kernel.fwk.DomainEventPayload;
import com.twba.kernel.fwk.TenantId;

import java.time.Instant;
import java.util.UUID;

class EducationAddedToStudentEvent extends DomainEventPayload {

    private final String studentId;
    private final Education education;

    EducationAddedToStudentEvent(StudentId studentId, Education education) {
        this(Instant.now(), UUID.randomUUID().toString(), studentId, education, TenantId.B2C());
    }

    EducationAddedToStudentEvent(Instant occurredOn, String eventUid, StudentId studentId, Education education, TenantId tenantId) {
        super(occurredOn, eventUid, tenantId.getId());
        this.studentId = studentId.value();
        this.education = education;
    }

    String getStudentId() {
        return studentId;
    }

    Education getEducation() {
        return education;
    }
}
