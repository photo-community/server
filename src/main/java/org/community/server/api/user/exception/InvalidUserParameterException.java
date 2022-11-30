package org.community.server.api.user.exception;

import org.community.server.exception.common.ErrorCode;
import org.community.server.exception.common.InvalidParameterException;
import org.springframework.validation.Errors;

/**
 * Exception class that handles errors that occur after validating the parameters passed to the user controller.
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
public class InvalidUserParameterException extends InvalidParameterException {
    public InvalidUserParameterException(Errors errors, ErrorCode errorCode) {
        super(errors, errorCode);
    }
}
