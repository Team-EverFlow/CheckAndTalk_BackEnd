package com.everflow.check_and_talk.oauth.provider;

import com.everflow.check_and_talk.dto.UserDto;
import com.everflow.check_and_talk.enums.ProviderEnum;

import java.util.Map;

public abstract class OAuth2UserInfo {
    public abstract String getProviderId();

    public abstract ProviderEnum getProvider();

    public abstract String getEmail();

    public abstract String getName();

    public abstract Map<String, Object> getAttributes();

    public UserDto toUserDto() {
        return UserDto.builder()
                .email(this.getEmail())
                .name(this.getName())
                .providerId(this.getProviderId())
                .provider(this.getProvider())
                .build();
    }
}