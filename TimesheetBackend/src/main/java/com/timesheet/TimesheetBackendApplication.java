package com.timesheet;

import io.sentry.Sentry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan({"com.manage.employeemanagementmodel.entity"})
@EnableScheduling
public class TimesheetBackendApplication {
    @Value("${sentry.dsn}")
    private String sentryDsn;

    @PostConstruct
    public void sentryInit() {
        Sentry.init(sentryDsn);
    }
    public static void main(String[] args) {
        SpringApplication.run(TimesheetBackendApplication.class, args);
    }

}
