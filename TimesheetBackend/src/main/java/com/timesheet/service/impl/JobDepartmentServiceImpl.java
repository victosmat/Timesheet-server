package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.JobDepartment;
import com.timesheet.dto.department.JobDepartmentDto;
import com.timesheet.repository.JobDepartmentRepository;
import com.timesheet.service.JobDepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDepartmentServiceImpl implements JobDepartmentService {

    private final JobDepartmentRepository jobDepartmentRepository;

    public JobDepartmentServiceImpl(JobDepartmentRepository jobDepartmentRepository) {
        this.jobDepartmentRepository = jobDepartmentRepository;
    }

    @Override
    public List<JobDepartment> findAll(String keyword) {
        return (keyword != null) ? jobDepartmentRepository.findAllbyKeyword(keyword) : jobDepartmentRepository.findAll();
    }

    @Override
    public JobDepartment saveJobDepartment(JobDepartmentDto jobDepartmentDto) {
        if (jobDepartmentDto.getId() != null) {
            JobDepartment jobDepartment = jobDepartmentRepository.findById(jobDepartmentDto.getId()).orElse(null);
            if (jobDepartment != null) {
                jobDepartment.setName(jobDepartmentDto.getName());
                jobDepartment.setJobDepartment(jobDepartmentDto.getJobDepartment());
                jobDepartment.setDescription(jobDepartmentDto.getDescription());
                jobDepartment.setSalaryRange(jobDepartmentDto.getSalaryRange());
                return jobDepartmentRepository.save(jobDepartment);
            }
            return null;
        }
        JobDepartment jobDepartment = new JobDepartment();
        jobDepartment.setName(jobDepartmentDto.getName());
        jobDepartment.setJobDepartment(jobDepartmentDto.getJobDepartment());
        jobDepartment.setDescription(jobDepartmentDto.getDescription());
        jobDepartment.setSalaryRange(jobDepartmentDto.getSalaryRange());
        return jobDepartmentRepository.save(jobDepartment);
    }

    @Override
    public JobDepartment findById(Integer id) {
        return jobDepartmentRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJobDepartment(Integer id) {
        try {
            jobDepartmentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
