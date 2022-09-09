package com.jobportal.view;

import java.util.Date;

import com.jobportal.entity.AppUser;
import com.jobportal.entity.UsersRole;
import com.jobportal.json.Json;

public class AppUserView {

    private final int userId;
    private final String name;
    private final String email;
    private final short status;
    private UsersRole usersRole;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public AppUserView(AppUser appUser) {
        this.userId = appUser.getUserId();
        this.name = appUser.getName();
        this.usersRole = appUser.getUsersRole();
        this.email = appUser.getEmail();
        this.status = appUser.getStatus();
        this.createDate = appUser.getCreateDate();
        this.updateDate = appUser.getUpdateDate();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public short getStatus() {
        return status;
    }

    public UsersRole getUsersRole() {
        return usersRole;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

}
