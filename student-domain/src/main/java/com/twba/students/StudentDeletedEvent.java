package com.twba.students;

import com.twba.kernel.fwk.DomainEventPayload;
import com.twba.kernel.fwk.TenantId;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
public class StudentDeletedEvent extends DomainEventPayload {

    private final String studentId;

    StudentDeletedEvent(StudentId studentId) {
        this(Instant.now(), UUID.randomUUID().toString(), studentId, TenantId.B2C());
    }

    StudentDeletedEvent(Instant occurredOn, String eventUid, StudentId studentId, TenantId tenantId) {
        super(occurredOn, eventUid, tenantId.getId());
        this.studentId = studentId.value();
    }
}
