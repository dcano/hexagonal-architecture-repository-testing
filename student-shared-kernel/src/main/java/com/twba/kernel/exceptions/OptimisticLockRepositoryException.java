package com.twba.kernel.exceptions;

public class OptimisticLockRepositoryException extends RuntimeException {

    public OptimisticLockRepositoryException(String message) {
        super(message);
    }

}
