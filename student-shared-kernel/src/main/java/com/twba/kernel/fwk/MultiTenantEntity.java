package com.twba.kernel.fwk;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;

/**
 * Created by alonsotd on 10/10/2017.
 */

public abstract class MultiTenantEntity extends Entity {

    @NotNull(message = "lblTenantIdNotNull")
    @Valid
    private final TenantId tenantId;

    public MultiTenantEntity(TenantId tenantId, long version) {
        super(version);
        this.tenantId = tenantId;
        this.validateProperty("tenantId");
    }

    public TenantId getTenantId() {
        return tenantId;
    }
}
