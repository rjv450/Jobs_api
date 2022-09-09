/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobportal.repository;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.Repository;

import com.jobportal.entity.User;

/**
 *
 * @author nirmal
 */
public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(Integer userId);

    Optional<User> findByUserIdAndPassword(Integer userId, String password);

    Optional<User> findByEmail(String email);

    Collection<User> findAll();


    User save(User user);

    Optional<User> findByUserIdAndStatus(Integer userId, byte value);

    // deletebyUserId
}
