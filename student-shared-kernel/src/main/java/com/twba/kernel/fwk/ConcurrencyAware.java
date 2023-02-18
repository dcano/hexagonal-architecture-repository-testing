package com.twba.kernel.fwk;

public interface ConcurrencyAware {

    Long getVersion();
    boolean isStaleWith(ConcurrencyAware existingEntity);


}
