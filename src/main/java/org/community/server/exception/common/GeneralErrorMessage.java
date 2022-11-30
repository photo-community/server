package org.community.server.exception.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneralErrorMessage {
    public static final String INVALID_PARAMETER = "not enough parameters have passed or an invalid parameter value has passed.";
    public static final String NOT_ALLOWED_CORS = "this url cannot call server resources.";
}
