package com.jobportal.service;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.jobportal.entity.Job;
import com.jobportal.form.JobForm;

import com.jobportal.view.jobListView;
@Service
public interface JobService {

    Collection<jobListView> list();

    jobListView add(@Valid JobForm form);

    void delete(Integer job_id);

    jobListView get(Integer job_id);

    jobListView update(Integer job_id, @Valid JobForm form);

    Collection<jobListView> alllist();

    List<Job> findAll();

    List<Job> listAll();

    Collection<jobListView> inactivejobs();

  

}
