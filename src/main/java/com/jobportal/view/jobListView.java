
package com.jobportal.view;

import com.jobportal.entity.Job;
import com.jobportal.json.*;
import java.util.Date;

public class jobListView {

    private final int jobId;
    private final String jobTitle;
    private final String jobDesc;
    private final String skills;
    private final String qualification;
    private final Date lastDateSubmit;
    private final byte status;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public jobListView(int jobId, String jobTitle, String jobDesc, String skills, String qualification,
            Date lastDateSubmit,
            byte status, Date createDate, Date updateDate) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.skills = skills;
        this.qualification = qualification;
        this.lastDateSubmit = lastDateSubmit;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public jobListView(Job job){
        this.jobId=job.getJobId();
        this.jobTitle=job.getJobTitle();
        this.jobDesc=job.getJobDesc();
        this.skills=job.getSkills();
        this.qualification=job.getQualification();
        this.lastDateSubmit=job.getLastDateSubmit();
        this.status=job.getStatus();
        this.createDate=job.getCreateDate();
        this.updateDate=job.getUpdateDate();
    }
    public int getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public String getSkills() {
        return skills;
    }

    public String getQualification() {
        return qualification;
    }

    public Date getLastDateSubmit() {
        return lastDateSubmit;
    }

    public byte getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }




}
