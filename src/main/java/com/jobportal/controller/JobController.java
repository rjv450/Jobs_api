package com.jobportal.controller;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.form.JobForm;
import com.jobportal.service.JobService;
import com.jobportal.view.JobDetailListView;
import com.jobportal.view.jobListView;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping
    public Collection<jobListView> list(Principal p){
        return jobService.list();
    }
    @PostMapping
    public jobListView add(@Valid @RequestBody JobForm form){
        return jobService.add(form);
    }
    @GetMapping("/{job_id}")
    public jobListView get(@PathVariable("job_id")Integer job_id){
        return jobService.get(job_id);
    }
    @DeleteMapping("/{job_id}")
    public void delete(@PathVariable("job_id")Integer job_id){
        System.out.println("hi");
        jobService.delete(job_id);
    }
    @PutMapping("/{job_id}")
    public jobListView update(
        @PathVariable("job_id") Integer job_id,@Valid @RequestBody JobForm form){
            return jobService.update(job_id,form);
        }
    
}

