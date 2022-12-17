package com.twba.kernel.fwk;

public interface ConcurrencyAware {

    long getVersion();
    boolean isStaleWith(ConcurrencyAware existingEntity);


}
