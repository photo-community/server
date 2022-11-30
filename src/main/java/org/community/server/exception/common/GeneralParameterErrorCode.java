package org.community.server.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An enumeration class with a list of exceptions that are raised <br>
 * when a violation of the rules or an invalid parameter is passed.
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
@Getter
@RequiredArgsConstructor
public enum GeneralParameterErrorCode implements ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, -1, GeneralErrorMessage.INVALID_PARAMETER);

    private static final Map<String, GeneralParameterErrorCode> bizCodes =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(GeneralParameterErrorCode::getMsg, Function.identity())));
    private final HttpStatus httpStatus;
    private final Integer bizCode;
    private final String msg;

    public Integer findMatchBizCode(final String failMessage) {
        return Optional.ofNullable(bizCodes.get(failMessage))
                .orElse(INVALID_PARAMETER)
                .getBizCode();
    }
}
