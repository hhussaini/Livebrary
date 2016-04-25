// ResourceHelperException is application specific exception
package com.blb.exceptions;

public class ResourceHelperException extends Exception {
    private String exceptionMessage = "";

    public ResourceHelperException() {
        super();
    }

    public ResourceHelperException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return exceptionMessage;
    }
}
