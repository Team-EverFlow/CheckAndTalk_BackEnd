package com.everflow.check_and_talk.repository;

import com.everflow.check_and_talk.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);

    boolean existsUserInfoByUsername(String username);
}
