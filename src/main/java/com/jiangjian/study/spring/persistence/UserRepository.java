package com.jiangjian.study.spring.persistence;

import com.jiangjian.study.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {
}
