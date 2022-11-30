package org.community.server.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

/**
 * A class for delivering the result data
 * returned by the controller to the client in a consistent format.
 *
 * @param <T> The data type that the controller wants to return
 * @author MC Lee
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
@Getter
public class ResponseDTO<T> {

    @JsonProperty("msg")
    private final String msg;
    @JsonProperty("timestamp")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final boolean error = false;
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseDTO(SuccessMessage message) {
        this.msg = message.getSuccessMsg();
    }

    public ResponseDTO(T data, SuccessMessage message) {
        this.data = data;
        this.msg = message.getSuccessMsg();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE, false, false, true, null);
    }
}
