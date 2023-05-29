package com.example.oauth.service;

import com.example.oauth.entity.PrincipalDetails;
import com.example.oauth.entity.UserEntity;
import com.example.oauth.entity.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PrincipalOauthUserService extends DefaultOAuth2UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;

  @Autowired
  public PrincipalOauthUserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.encoder = encoder;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    log.info("getAttributes: {}", oAuth2User.getAttributes());

    String provider = userRequest.getClientRegistration().getRegistrationId();
    String providerId = oAuth2User.getAttribute("id");
    String loginId = provider + "_" + providerId;

    Optional<UserEntity> optionalUser = userRepository.findByLoginId(loginId);
    UserEntity user;

    if (optionalUser.isEmpty()) {
      user = UserEntity.builder()
              .loginId(loginId)
              .name(oAuth2User.getAttribute("name"))
              .provider(provider)
              .providerId(providerId)
              .email(oAuth2User.getAttribute("email"))
              .build();
      userRepository.save(user);
    } else {
      user = optionalUser.get();
    }
    return new PrincipalDetails(user, oAuth2User.getAttributes());
  }
}
