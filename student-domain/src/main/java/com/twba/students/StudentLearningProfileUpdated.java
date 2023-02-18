package com.twba.students;

import com.twba.kernel.fwk.DomainEventPayload;
import com.twba.kernel.fwk.TenantId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@EqualsAndHashCode
@Getter
class StudentLearningProfileUpdated extends DomainEventPayload {
    private final String studentId;
    private final LearningProfile updatedLearningProfile;

    StudentLearningProfileUpdated(StudentId studentId, LearningProfile updatedLearningProfile) {
        this(Instant.now(), UUID.randomUUID().toString(), studentId, TenantId.B2C(), updatedLearningProfile);
    }
    StudentLearningProfileUpdated(Instant occurredOn, String eventUid, StudentId studentId, @NotNull(message = "lblTenantIdNotNull") @Valid TenantId tenantId, LearningProfile updatedLearningProfile) {
        super(occurredOn, eventUid, tenantId.getId());
        this.studentId = studentId.value();
        this.updatedLearningProfile = updatedLearningProfile;
    }
}
