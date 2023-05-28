package com.everflow.check_and_talk.services;

import com.everflow.check_and_talk.dto.UserDto;
import com.everflow.check_and_talk.entity.UserInfo;
import com.everflow.check_and_talk.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = this.loadUserEntityByUsername(username);
        UserDto.UserDtoBuilder userDtoBuilder = UserDto.builder()
                .name(user.getUsername())
                .email(user.getEmail())
                .provider(user.getProvider())
                .providerId(user.getProviderId());
        return userDtoBuilder.build();
    }

    public boolean existUserByUsername(String username) {
        return userRepository.existsUserInfoByUsername(username);
    }

    public UserInfo loadUserEntityByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void saveUserDto(UserDto userDto) {
        UserInfo userInfo = UserInfo.builder()
                .username(userDto.getName())
                .email(userDto.getEmail())
                .provider(userDto.getProvider())
                .providerId(userDto.getProviderId())
                .build();
        this.saveUserInfoEntity(userInfo);
    }

    public void saveUserInfoEntity(UserInfo user) {
        userRepository.save(user);
    }
}
