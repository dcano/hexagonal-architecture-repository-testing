package com.twba.students;

import com.twba.kernel.fwk.DomainEventPayload;
import com.twba.kernel.fwk.TenantId;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
class StudentDisabledEvent extends DomainEventPayload {

    private final String studentId;

    StudentDisabledEvent(StudentId studentId) {
        this(Instant.now(), UUID.randomUUID().toString(), TenantId.B2C(), studentId);
    }

    StudentDisabledEvent(Instant occurredOn,
                         String eventUid, TenantId tenantId,
                         StudentId studentId) {
        super(occurredOn, eventUid, tenantId.getId());
        this.studentId = studentId.value();
    }
}
