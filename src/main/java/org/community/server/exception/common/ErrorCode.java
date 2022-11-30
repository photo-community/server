package org.community.server.exception.common;

import org.springframework.http.HttpStatus;

/**
 * An interface for extending classes that have an enumeration of error lists.
 * findMatchBizCode should be implemented to find a value
 * that matches either msg or httpStatus in one of the enumeration lists and return a bizCode.
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
public interface ErrorCode {
    String getMsg();

    Integer getBizCode();

    HttpStatus getHttpStatus();

    Integer findMatchBizCode(String failMessage);
}