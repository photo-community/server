package org.community.server.exception.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 요청 처리 중 발생하는 에러의 상세 설명을 수록
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiErrorMessage {
    // common error
    public static final String INSUFFICIENT_AUTHORIZATION = "You do not have sufficient privileges to request this process.";
    // end of common error

    // user error
    public static final String USER_CRUD_FAIL = "User requests failed.";
    public static final String USER_NOT_FOUND = "The user could not be found.";
    public static final String INCORRECT_USERID_OR_PASSWORD = "userId or password is incorrect.";
    public static final String USER_ID_IS_NULL = "userId is null. It must be provided.";
    public static final String USER_ID_IS_EMPTY = "userId must be not empty.";
    public static final String USER_NAME_IS_NULL = "name is null. It must be provided.";
    public static final String USER_NAME_IS_EMPTY = "name must be empty.";
    public static final String USER_PASSWORD_IS_NULL = "password is null. It must be provided.";
    public static final String USER_PASSWORD_IS_EMPTY = "password must be not empty.";
    // end of user error
}
