package com.kira.emercmdplat.pojo;

import lombok.Data;

import java.util.Objects;

/**
 * @Author: kira
 * @Date: 2020/7/24 14:03
 * @Description:
 */
@Data
public class WebSocketUser {

    private String token;

    private Long resourceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebSocketUser))
            return false;
        WebSocketUser that = (WebSocketUser) o;
        boolean result = token.equals(that.token);
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 31 * token.hashCode();
        return result;
    }
}
