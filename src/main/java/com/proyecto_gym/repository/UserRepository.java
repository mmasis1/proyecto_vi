package com.proyecto_gym.repository;

import com.proyecto_gym.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
