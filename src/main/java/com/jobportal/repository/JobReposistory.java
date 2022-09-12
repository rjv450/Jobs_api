package com.jobportal.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.jobportal.entity.Job;
import com.jobportal.view.jobListView;

@org.springframework.stereotype.Repository
public interface JobReposistory extends Repository<Job, Integer> {

    Collection<jobListView> findAllByUserUserIdAndStatus(Integer currentUserId, byte value);

    Job save(Job job);

    Job findByJobIdAndStatus(Integer jobId, byte value);

    Optional<Job> findByJobIdAndUserUserId(Integer job_id, Integer currentUserId);

}
