package com.everflow.check_and_talk.oauth;

import com.everflow.check_and_talk.oauth.provider.GithubUserInfo;
import com.everflow.check_and_talk.oauth.provider.OAuth2UserInfo;
import com.everflow.check_and_talk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("github")) {
            oAuth2UserInfo = new GithubUserInfo(oAuth2User.getAttributes());
        }

        OAuth2UserInfo oAuth2UserNullSafe = Objects.requireNonNull(oAuth2UserInfo);
        if (!userService.existUserByUsername(oAuth2UserNullSafe.getName())) {
            userService.saveUserDto(oAuth2UserNullSafe.toUserDto());
        }
        return oAuth2User;
    }
}
