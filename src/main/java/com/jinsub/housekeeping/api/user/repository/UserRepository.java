package com.jinsub.housekeeping.api.user.repository;

import com.jinsub.housekeeping.api.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
