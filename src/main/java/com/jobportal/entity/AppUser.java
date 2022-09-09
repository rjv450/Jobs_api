package com.jobportal.entity;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jobportal.form.AppUserForm;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AppUser {
    public static enum Status{
        INACTIVE((byte)0),
        ACTIVE((byte)1);
        public final byte value;
        private Status(byte value){
            this.value=value;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private byte status;
    @Enumerated(EnumType.STRING)
    private UsersRole usersRole;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    public AppUser(){

    }
    public AppUser(Integer userId){
        this.userId=userId;
    }
    public AppUser(String name,String email,String password){
        this.name= name;
        this.email=email;
        this.password=password;
        this.status=Status.ACTIVE.value;
        Date dt = new Date();
        this.createDate=dt;
        this.updateDate=dt;
    }
    public AppUser update(AppUserForm form){
        this.name=form.getName();
        this.email=form.getEmail();
        Date dt = new Date();
        this.updateDate=dt;
        return this;
        
    }

}
