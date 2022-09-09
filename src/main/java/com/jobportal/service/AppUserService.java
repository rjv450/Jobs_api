package com.jobportal.service;

import com.jobportal.form.AppUserForm;
import com.jobportal.view.AppUserView;

public interface AppUserService {
    AppUserView add(AppUserForm form);
    
}
