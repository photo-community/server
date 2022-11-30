package org.community.server.exception.controllerAdvice;

import org.community.server.exception.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Objects;

/**
 * Utility class for formalized error handling.
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
public class GeneralControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(GeneralControllerAdvice.class);

    /**
     * Creates a standardized error response message format.
     *
     * @param httpStatus an http error occurred
     * @param e          List of exceptions that validate @Valid or @Validated
     * @return ResponseEntity<ErrorResponseDTO>
     */
    public static ResponseEntity<ErrorResponseDTO> handleGeneralException(HttpStatus httpStatus, Exception... e) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .errorCode(httpStatus.value())
                .message(Arrays.stream(e)
                        .filter(Objects::nonNull).findFirst()
                        .map(Exception::getMessage)
                        .orElseGet(httpStatus::getReasonPhrase))
                .build();

        log.error(response.getMsg());

        return new ResponseEntity<>(response, getHttpHeader(), httpStatus);
    }

    /**
     * 정Creates a standardized error contains paramaters response message format.
     * This method is used for exceptions that get BindingResult information through @Valid validation.
     *
     * @param httpStatus an http error occurred
     * @param e          List of exceptions that validate @Valid or @Validated
     * @return ResponseEntity<ErrorResponseDTO>
     */
    public static ResponseEntity<ErrorResponseDTO> handleValidParameterException(HttpStatus httpStatus, ErrorCode errorCode, InvalidParameterException... e) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .errorCode(httpStatus.value())
                .message(Arrays.stream(e)
                        .filter(Objects::nonNull).findFirst()
                        .map(Exception::getMessage)
                        .orElse(httpStatus.toString()))
                .errors(Arrays.stream(e)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElseThrow(() -> new BizException(GeneralParameterErrorCode.INVALID_PARAMETER))
                        .getErrors(), errorCode)
                .build();

        log.error(response.getMsg());

        return new ResponseEntity<>(response, getHttpHeader(), httpStatus);
    }

    /**
     * Setting the error information header to be forwarded to the client.
     *
     * @return error response header
     */
    private static HttpHeaders getHttpHeader() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return responseHeaders;
    }

}