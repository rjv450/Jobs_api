package com.jobportal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.form.AppUserForm;
import com.jobportal.service.AppUserService;
import com.jobportal.view.AppUserView;

@RestController
@RequestMapping("/users1")
public class AppUsersController {
    @Autowired
    AppUserService appUserService;
    @PostMapping 
    public AppUserView add(@Valid @RequestBody AppUserForm form){
        return appUserService.add(form);
    }
}
