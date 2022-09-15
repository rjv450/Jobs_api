package com.jobportal.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.jobportal.entity.Job;
import com.jobportal.entity.Job.Status;
import com.jobportal.view.jobListView;

@org.springframework.stereotype.Repository
public interface JobReposistory extends Repository<Job, Integer> {

    Collection<jobListView> findAllByUserUserIdAndStatus(Integer currentUserId, byte value);

    Job save(Job job);

    Job findByJobIdAndStatus(Integer jobId, byte value);
    Job findByJobId(Integer jobId);

    Optional<Job> findByJobIdAndUserUserId(Integer job_id, Integer currentUserId);

    Collection<jobListView> findAllByUserUserId(Integer currentUserId);

    List<Job> findAll();








}
