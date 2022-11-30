package org.community.server.api.user.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.community.server.exception.common.ApiErrorMessage;
import org.community.server.exception.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.*;

/**
 * Improve readability by managing the list of errors
 * that occur during user processing as an enumeration.
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
@Getter
@RequiredArgsConstructor
public enum UserCrudErrorCode implements ErrorCode {

    USER_CRUD_FAIL(BAD_REQUEST, -999, ApiErrorMessage.USER_CRUD_FAIL),
    USER_NOT_FOUND(NOT_FOUND, -1, ApiErrorMessage.USER_NOT_FOUND),
    INCORRECT_USERID_OR_PASSWORD(UNAUTHORIZED, -4, ApiErrorMessage.INCORRECT_USERID_OR_PASSWORD),
    INSUFFICIENT_AUTHORIZATION(UNAUTHORIZED, -5, ApiErrorMessage.INSUFFICIENT_AUTHORIZATION),
    USER_ID_IS_NULL(BAD_REQUEST, -6, ApiErrorMessage.USER_ID_IS_NULL),
    USER_ID_IS_EMPTY(BAD_REQUEST, -7, ApiErrorMessage.USER_ID_IS_EMPTY),
    USER_NAME_IS_NULL(BAD_REQUEST, -8, ApiErrorMessage.USER_NAME_IS_NULL),
    USER_NAME_IS_EMPTY(BAD_REQUEST, -9, ApiErrorMessage.USER_NAME_IS_EMPTY),
    USER_PASSWORD_IS_NULL(BAD_REQUEST, -10, ApiErrorMessage.USER_PASSWORD_IS_NULL),
    USER_PASSWORD_IS_EMPTY(BAD_REQUEST, -11, ApiErrorMessage.USER_PASSWORD_IS_EMPTY);

    private static final Map<String, UserCrudErrorCode> bizCodes =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(UserCrudErrorCode::getMsg, Function.identity())));
    private final HttpStatus httpStatus;
    private final Integer bizCode;
    private final String msg;

    public Integer findMatchBizCode(final String failMessage) {
        return Optional.ofNullable(bizCodes.get(failMessage))
                .orElse(USER_CRUD_FAIL)
                .getBizCode();
    }

}
