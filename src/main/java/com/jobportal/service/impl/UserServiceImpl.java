/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobportal.service.impl;

import com.jobportal.entity.User;
import com.jobportal.exception.BadRequestException;
import com.jobportal.exception.NotFoundException;
import com.jobportal.form.LoginForm;
import com.jobportal.form.UserForm;
import com.jobportal.repository.UserRepository;
import com.jobportal.security.config.SecurityConfig;
import com.jobportal.security.util.InvalidTokenException;
import com.jobportal.security.util.SecurityUtil;
import com.jobportal.security.util.TokenExpiredException;
import com.jobportal.security.util.TokenGenerator;
import com.jobportal.security.util.TokenGenerator.Status;
import com.jobportal.security.util.TokenGenerator.Token;
import com.jobportal.service.UserService;
import com.jobportal.view.LoginView;
import com.jobportal.view.UserView;

import static com.jobportal.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

// import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 *
 * @author nirmal
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";
    // @Autowired
    // private ContactRepository contactRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public UserView add(UserForm form) {
        return new UserView(userRepository.save(new User(
                form.getName(),
                form.getEmail(),
                passwordEncoder.encode(form.getPassword()))));
    }

    @Override
    public UserView currentUser() {
        return new UserView(
                userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new));
    }

    @Override
    public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        User user = userRepository.findByEmail(form.getEmail()).orElseThrow(UserServiceImpl::badRequestException);
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(),
                securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }

    @Override
    @Transactional
    public UserView userUpdate(UserForm form) {
        Optional<User> currentUser = userRepository.findById(SecurityUtil.getCurrentUserId());
        return currentUser.map((user) -> {
            return new UserView(userRepository.save(user.update(form)));
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public UserView userUpdateById(Integer userId, UserForm form) {
        Optional<User> otherUser = userRepository.findById(userId);
        return otherUser.map((user) -> {
            return new UserView(userRepository.save(user.update(form)));
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public void deletebyUserId(Integer userId) {
        User user = userRepository.findByUserIdAndStatus(userId, User.Status.ACTIVE.value)
                .orElseThrow(NotFoundException::new);
        user.setStatus(User.Status.INACTIVE.value);
        userRepository.save(user);
    }

    @Override
    public LoginView refresh(String refreshToken) throws BadRequestException {
        Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }

        int userId;
        try {
            userId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

        User user = userRepository.findByUserIdAndPassword(userId, password)
                .orElseThrow(UserServiceImpl::badRequestException);

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new LoginView(
                user,
                new LoginView.TokenView(accessToken.value, accessToken.expiry),
                new LoginView.TokenView(refreshToken, status.expiry));
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

    @Override
    public Collection<User> list() {
        return userRepository.findAll();
    }
}
