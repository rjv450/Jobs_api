/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobportal.service;

import com.jobportal.entity.User;
import com.jobportal.exception.BadRequestException;
import com.jobportal.form.LoginForm;
import com.jobportal.form.UserForm;
import com.jobportal.view.LoginView;
import com.jobportal.view.UserView;

import java.util.Collection;
import org.springframework.validation.Errors;

/**
 *
 * @author nirmal
 */
public interface UserService {

    UserView add(UserForm form);

    UserView currentUser();

    UserView userUpdate(UserForm form);

    UserView userUpdateById(Integer userId, UserForm form);
 
    void deletebyUserId(Integer userId);

    LoginView login(LoginForm form, Errors errors) throws BadRequestException;

    LoginView refresh(String refreshToken) throws BadRequestException;

    Collection<User> list();


    // deletebyUserId
}
