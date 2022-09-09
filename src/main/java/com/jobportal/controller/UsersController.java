/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobportal.controller;

import com.jobportal.entity.User;
import com.jobportal.form.UserForm;
import com.jobportal.service.UserService;
import com.jobportal.view.UserView;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nirmal
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserView add(@Valid @RequestBody UserForm form) {
        return userService.add(form);
    }

    @GetMapping
    public Collection<User> list() {
        return userService.list();
    }
    @PutMapping
    public UserView update(@Valid @RequestBody UserForm form){
        return userService.userUpdate(form);
    }
    @PutMapping("/{userId}")
    public UserView update(@PathVariable("userId")Integer userId, @Valid @RequestBody UserForm form){
        return userService.userUpdateById(userId,form);
    }
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Integer userId) {
        userService.deletebyUserId(userId);
    }

}



// @PutMapping("/{contactId}")
// public ContactDetailView update(
//         @PathVariable("contactId") Integer contactId,
//         @Valid @RequestBody ContactForm form
// ) {
//     return contactService.update(contactId, form);
// }