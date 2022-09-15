package com.jobportal.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jobportal.entity.Job;
import com.jobportal.exception.NotFoundException;
import com.jobportal.form.JobForm;
import com.jobportal.repository.JobReposistory;
import com.jobportal.security.util.SecurityUtil;
import com.jobportal.service.JobService;
import com.jobportal.view.JobDetailListView;
import com.jobportal.view.jobListView;
@EnableScheduling
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobReposistory jobReposistory;

    @Override
    @Scheduled(cron="*/10 * * * * *")
    public Collection<jobListView> list() {
        System.out.println("h1");
        Iterator<jobListView> selectedContactCollection = jobReposistory
                .findAllByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), Job.Status.ISALIVE.value).iterator();

        while (selectedContactCollection.hasNext()) {

            jobListView jlv = selectedContactCollection.next();

            Date date = jlv.getLastDateSubmit();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date local = new Date();
            String localtime = dateFormat.format(local);
            System.out.println(localtime);

            try {
                Date d1 = dateFormat.parse(localtime);
                System.out.println(d1);
                System.out.println(date);
                if (d1.compareTo(date) > 0) {
                    Job job = jobReposistory.findByJobId(jlv.getJobId());
                    job.setStatus(Job.Status.NOTALIVE.value);
                    jobReposistory.save(job);
                    // job.setStatus(Job.Status.ISALIVE.value);
                    System.out.println("hai");

                } else {
                    System.out.println("no");
                }

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
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
    public jobListView update(Integer job_id, @Valid JobForm form) throws NotFoundException {
        return jobReposistory.findByJobIdAndUserUserId(job_id, SecurityUtil.getCurrentUserId()).map((job) -> {
            return new jobListView(jobReposistory.save(job.update(form)));
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public Collection<jobListView> alllist() {
        return jobReposistory.findAllByUserUserId(SecurityUtil.getCurrentUserId());
    }

    @Override
    public List<Job> findAll() {
        return null;

    }

    @Override
    public List<Job> listAll() {
        System.out.println("hello2");
        return jobReposistory.findAll();
    }

    @Override
    public Collection<jobListView> inactivejobs() {
        return jobReposistory.findAllByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), Job.Status.NOTALIVE.value);
    }

}
