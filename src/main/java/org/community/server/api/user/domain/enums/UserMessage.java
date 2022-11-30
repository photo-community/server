package org.community.server.api.user.domain.enums;

import lombok.RequiredArgsConstructor;
import org.community.server.api.common.ApiSuccessMessage;
import org.community.server.api.common.SuccessMessage;

/**
 * Manage return messages as an enumeration when user-related processing is successful
 *
 * @author MC Lee
 * @created 2022-10-25
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
@RequiredArgsConstructor
public enum UserMessage implements SuccessMessage {
    SUCCESS_ADD_USER(ApiSuccessMessage.SUCCESS_ADD_USER),
    SUCCESS_REMOVE_USER(ApiSuccessMessage.SUCCESS_REMOVE_USER),
    SUCCESS_FIND_USER_ONE(ApiSuccessMessage.SUCCESS_ADD_USER);

    private final String successMsg;

    @Override
    public String getSuccessMsg() {
        return successMsg;
    }
}
