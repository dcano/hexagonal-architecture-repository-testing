package com.twba.students;


import com.twba.kernel.fwk.DomainEventPayload;
import com.twba.kernel.fwk.TenantId;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
class StudentEnabledEvent extends DomainEventPayload {

    private final String studentId;

    StudentEnabledEvent(StudentId studentId) {
        this(Instant.now(), UUID.randomUUID().toString(), TenantId.B2C(), studentId);
    }

    StudentEnabledEvent(Instant occurredOn,
                         String eventUid, TenantId tenantId,
                        StudentId studentId) {
        super(occurredOn, eventUid, tenantId.getId());
        this.studentId = studentId.value();
    }
}
