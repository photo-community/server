package org.community.server.exception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * An exception handling class that handles errors returned to the client side.
 * Difference from InvalidParameterException which inherits from BizException:
 * BizException does not have an internal server errorCode negotiated in advance.
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
@Getter
public class BizException extends RuntimeException {
    private final String message;
    private final Integer bizCode;
    private final HttpStatus httpStatus;

    public BizException(ErrorCode code) {
        super(code.getMsg(), new Throwable(code.getHttpStatus().getReasonPhrase()));
        this.message = code.getMsg();
        this.bizCode = code.getBizCode();
        this.httpStatus = code.getHttpStatus();
    }
}
