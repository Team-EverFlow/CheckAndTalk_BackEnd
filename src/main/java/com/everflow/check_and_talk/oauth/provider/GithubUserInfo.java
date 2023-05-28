package com.everflow.check_and_talk.oauth.provider;

import com.everflow.check_and_talk.enums.ProviderEnum;

import java.util.Map;

public class GithubUserInfo extends OAuth2UserInfo {

    private Map<String, Object> attributes;

    public GithubUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public ProviderEnum getProvider() {
        return ProviderEnum.Github;
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("login").toString();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
