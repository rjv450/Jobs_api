package com.jobportal.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.Job;
import com.jobportal.exception.NotFoundException;
import com.jobportal.form.JobForm;
import com.jobportal.repository.JobReposistory;
import com.jobportal.security.util.SecurityUtil;
import com.jobportal.service.JobService;
import com.jobportal.view.JobDetailListView;
import com.jobportal.view.jobListView;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobReposistory jobReposistory;

    @Override
    public Collection<jobListView> list() {
        return jobReposistory.findAllByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), Job.Status.ISALIVE.value);

    }

    @Override
    public jobListView add(@Valid JobForm form) {
        return new JobDetailListView(jobReposistory.save(new Job(form, SecurityUtil.getCurrentUserId())));
    }

    @Override
    @Transactional
    public void delete(Integer job_id) {
        System.out.println("hi");
        Job job = jobReposistory.findByJobIdAndStatus(job_id, Job.Status.ISALIVE.value);
        System.out.println("bie");
        job.setStatus(Job.Status.NOTALIVE.value);

        return;

    }

    @Override
    public jobListView get(Integer job_id) throws NotFoundException {
        return jobReposistory.findByJobIdAndUserUserId(job_id, SecurityUtil.getCurrentUserId()).map((job) -> {
            return new jobListView(job);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public jobListView update(Integer job_id, @Valid JobForm form)throws NotFoundException {
        return jobReposistory.findByJobIdAndUserUserId(job_id, SecurityUtil.getCurrentUserId()).map((job)->{
            return new jobListView(jobReposistory.save(job.update(form)));
        }).orElseThrow(NotFoundException::new);
    }

}

