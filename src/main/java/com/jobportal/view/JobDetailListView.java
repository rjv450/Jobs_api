package com.jobportal.view;

import com.jobportal.entity.Job;

public class JobDetailListView extends jobListView {
  public JobDetailListView(Job job){
    super(
        job.getJobId(),
        job.getJobTitle(),
        job.getJobDesc(),
        job.getQualification(),
        job.getSkills(),
        job.getLastDateSubmit(),
        job.getStatus(),
        job.getCreateDate(),
        job.getUpdateDate()
    );
  }
}
