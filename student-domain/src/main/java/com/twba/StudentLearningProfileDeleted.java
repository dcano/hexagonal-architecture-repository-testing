package com.twba;


import com.twba.kernel.fwk.DomainEventPayload;
import com.twba.kernel.fwk.TenantId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@EqualsAndHashCode
@Getter(AccessLevel.PACKAGE)
class StudentLearningProfileDeleted extends DomainEventPayload {

    private final String studentId;
    private final LearningProfile previousLearningProfile;

    StudentLearningProfileDeleted(StudentId studentId, LearningProfile previousLearningProfile) {
        this(Instant.now(), UUID.randomUUID().toString(), studentId, TenantId.B2C(), previousLearningProfile);
    }
    StudentLearningProfileDeleted(Instant occurredOn, String eventUid, StudentId studentId, @NotNull(message = "lblTenantIdNotNull") @Valid TenantId tenantId, LearningProfile previousLearningProfile) {
        super(occurredOn, eventUid, tenantId.getId());
        this.studentId = studentId.value();
        this.previousLearningProfile = previousLearningProfile;
    }
}
