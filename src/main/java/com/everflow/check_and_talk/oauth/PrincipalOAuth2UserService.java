package com.everflow.check_and_talk.oauth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;

public class PrincipalOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        OAuth2User user = new DefaultOAuth2User(
                AuthorityUtils.createAuthorityList("ROLE_USER"),
                getUserAttributes(userRequest),
                userNameAttributeName
        );
        return user;
    }

    private Map<String, Object> getUserAttributes(OAuth2UserRequest userRequest) {
        Map<String, Object> attributes = new HashMap<>();

        return attributes;
    }
}
