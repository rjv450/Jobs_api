package com.jobportal.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.ExceplExporter.JobExcelExporter;
import com.jobportal.entity.Job;
import com.jobportal.form.JobForm;

import com.jobportal.service.JobService;
import com.jobportal.view.jobListView;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/admin")
    public Collection<jobListView> alllist(Principal p) {
        return jobService.alllist();
    }
    @GetMapping("/inactivejobs")
    public Collection<jobListView> inactivejobs(Principal p) {
        return jobService.inactivejobs();
    }

    @GetMapping
    public Collection<jobListView> list(Principal p) {
        return jobService.list();
    }

    @PostMapping
    public jobListView add(@Valid @RequestBody JobForm form) {
        return jobService.add(form);
    }

    @GetMapping("/{job_id}")
    public jobListView get(@PathVariable("job_id") Integer job_id) {
        return jobService.get(job_id);
    }

    @DeleteMapping("/{job_id}")
    public void delete(@PathVariable("job_id") Integer job_id) {
        System.out.println("hi");
        jobService.delete(job_id);
    }

    @PutMapping("/{job_id}")
    public jobListView update(
            @PathVariable("job_id") Integer job_id, @Valid @RequestBody JobForm form) {
        return jobService.update(job_id, form);
    }

    @GetMapping("/download/excel")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=jobs1.xlsx");
        ByteArrayInputStream stream = JobExcelExporter.JobListToExcelFile(listJobs());
        IOUtils.copy(stream, response.getOutputStream());
    }

    private List<Job> listJobs() {
        System.out.println("hello1");
        return jobService.listAll();
    }
}
