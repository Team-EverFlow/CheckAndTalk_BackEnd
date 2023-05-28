package com.everflow.check_and_talk.dto;

import com.everflow.check_and_talk.enums.ProviderEnum;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String email;
    private ProviderEnum provider;

    private String providerId;

    private String name;
}
