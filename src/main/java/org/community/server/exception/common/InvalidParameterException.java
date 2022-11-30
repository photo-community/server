package org.community.server.exception.common;

import org.springframework.validation.Errors;

/**
 * Exception class that handles errors
 * that occur during @Valid validation.
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
public abstract class InvalidParameterException extends BizException {

    private final Errors errors;
    private final ErrorCode errorCode;

    public InvalidParameterException(Errors errors, ErrorCode errorCode) {
        super(GeneralParameterErrorCode.INVALID_PARAMETER);
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public Errors getErrors() {
        return errors;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
