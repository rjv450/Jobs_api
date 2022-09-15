package com.jobportal.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jobportal.form.JobForm;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Job {
    public static enum Status {
        NOTALIVE((byte) 0),
        ISALIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;
    private String jobTitle;
    private String jobDesc;
    private String skills;
    private String qualification;
    private Long vacancy;

    @Temporal(TemporalType.DATE)
    private Date lastDateSubmit;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public Job() {

    }

    public Job(Integer jobId) {
        this.jobId = jobId;
    }

    public Job(JobForm form, Integer userId) {
        this.user = new User(userId);
        this.jobTitle = form.getJobTitle();
        this.jobDesc = form.getJobDesc();
        this.skills = form.getSkills();
        this.qualification = form.getQualification();
        this.vacancy=form.getVacancy();
        this.lastDateSubmit = form.getLastDateSubmit();
        this.status = Status.ISALIVE.value;
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public Job update(JobForm form) {

        this.jobTitle = form.getJobTitle();
        this.jobDesc = form.getJobDesc();
        this.skills = form.getSkills();
        this.qualification = form.getQualification();
        this.vacancy=form.getVacancy();
        this.lastDateSubmit = form.getLastDateSubmit();
        this.status = Status.ISALIVE.value;
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
        return this;
    }

    public Long getVacancy() {
        return vacancy;
    }

    public void setVacancy(Long vacancy) {
        this.vacancy = vacancy;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Date getLastDateSubmit() {
        return lastDateSubmit;
    }

    public void setLastDateSubmit(Date lastDateSubmit) {
        this.lastDateSubmit = lastDateSubmit;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobId != null ? jobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Job)) {
            return false;
        }
        return Objects.equals(jobId, ((Job) object).jobId);
    }

    @Override
    public String toString() {
        return "Job [createDate=" + createDate + ", jobDesc=" + jobDesc + ", jobId=" + jobId + ", jobTitle=" + jobTitle
                + ", lastDateSubmit=" + lastDateSubmit + ", qualification=" + qualification + ", skills=" + skills
                + ", status=" + status + ", updateDate=" + updateDate + ", user=" + user + ", vacancy=" + vacancy + "]";
    }



}
