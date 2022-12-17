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

@Getter(AccessLevel.PACKAGE)
@EqualsAndHashCode
public class StudentStudyPlanDeleted extends DomainEventPayload {

    private final String studentId;
    private final StudyPlan previousStudyPlanAnswers;

    StudentStudyPlanDeleted(StudentId studentId, StudyPlan previousStudyPlanAnswers) {
        this(Instant.now(), UUID.randomUUID().toString(), studentId, TenantId.B2C(), previousStudyPlanAnswers);
    }
    StudentStudyPlanDeleted(Instant occurredOn, String eventUid, StudentId studentId, @NotNull(message = "lblTenantIdNotNull") @Valid TenantId tenantId, StudyPlan previousStudyPlanAnswers) {
        super(occurredOn, eventUid, tenantId.getId());
        this.studentId = studentId.value();
        this.previousStudyPlanAnswers = previousStudyPlanAnswers;
    }
}
