package com.jobportal.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class JobForm {
    @NotNull
    private String jobTitle;
    @NotNull
    private String jobDesc;
    @NotNull
    private String skills;
    @NotNull
    private String qualification;
    @NotNull
    private Date lastDateSubmit;
    @NotNull
    private Long vacancy;

    public Long getVacancy() {
        return vacancy;
    }

    public void setVacancy(Long vacancy) {
        this.vacancy = vacancy;
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

}
