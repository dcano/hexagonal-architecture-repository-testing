package com.twba.kernel.fwk;

public interface Traceable {

    void setCorrelationId(CorrelationId correlationId);
    CorrelationId correlationId();

}
