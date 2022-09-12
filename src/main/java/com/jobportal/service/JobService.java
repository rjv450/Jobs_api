package com.jobportal.service;

import java.util.Collection;

import javax.validation.Valid;

import com.jobportal.form.JobForm;

import com.jobportal.view.jobListView;

public interface JobService {

    Collection<jobListView> list();

    jobListView add(@Valid JobForm form);

    void delete(Integer job_id);

    jobListView get(Integer job_id);

    jobListView update(Integer job_id, @Valid JobForm form);

  

}
