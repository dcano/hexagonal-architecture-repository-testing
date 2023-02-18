package com.twba.students;

import com.twba.kernel.fwk.DomainEventPayload;
import com.twba.kernel.fwk.TenantId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@EqualsAndHashCode
@Getter(AccessLevel.PACKAGE)
public class StudentStudyPlanUpdated extends DomainEventPayload {
    private final String studentId;
    private final StudyPlan updatedStudyPlanAnswers;

    StudentStudyPlanUpdated(StudentId studentId, StudyPlan updatedStudyPlanAnswers) {
        this(Instant.now(), UUID.randomUUID().toString(), studentId, TenantId.B2C(), updatedStudyPlanAnswers);
    }
    StudentStudyPlanUpdated(Instant occurredOn, String eventUid, StudentId studentId, @NotNull(message = "lblTenantIdNotNull") @Valid TenantId tenantId, StudyPlan updatedStudyPlanAnswers) {
        super(occurredOn, eventUid, tenantId.getId());
        this.studentId = studentId.value();
        this.updatedStudyPlanAnswers = updatedStudyPlanAnswers;
    }
}
