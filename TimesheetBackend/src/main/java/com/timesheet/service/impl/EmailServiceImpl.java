package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Department;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.MailConfig;
import com.manage.employeemanagementmodel.entity.enums.Gender;
import com.manage.employeemanagementmodel.entity.enums.MailType;
import com.timesheet.repository.DepartmentRepository;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.repository.MailConfigRepository;
import com.timesheet.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private final MailConfigRepository mailConfigRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmailServiceImpl(MailConfigRepository mailConfigRepository, EmployeeRepository employeeRepository,
                            DepartmentRepository departmentRepository) {
        this.mailConfigRepository = mailConfigRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void sendEmailToPM(Employee employee, String type) {
        MailConfig mailConfig = mailConfigRepository.findByType(MailType.NOTIFY_EMPLOYEE);
        Employee buddy = null;
        if (employee.getBuddy() != null) {
            buddy = employeeRepository.findById(employee.getBuddy().getId()).orElse(null);
        }

        Department department = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
        employee.setBuddy(buddy);
        employee.setDepartment(department);
        if (buddy != null) {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

            mailSender.setHost(mailConfig.getHost());
            mailSender.setPort(Integer.parseInt(mailConfig.getPort()));
            mailSender.setUsername(mailConfig.getUserName());
            mailSender.setPassword(mailConfig.getPassword());

            Properties mailProperties = new Properties();
            mailProperties.setProperty("mail.smtp.auth", mailConfig.isSmtpAuth() ? "true" : "false");
            mailProperties.setProperty("mail.smtp.starttls.enable", mailConfig.isSmtpSecured() ? "true" : "false");

            mailSender.setJavaMailProperties(mailProperties);

            MimeMessage message = mailSender.createMimeMessage();

            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom(mailConfig.getMailFrom(), mailConfig.getSenderName());
                helper.setTo(buddy.getEmail());
                helper.setSubject("Notify about details information of your employee");
                final Context ctx = new Context();
                ctx.setVariable("genderCall", buddy.getGender() == Gender.MALE ? "Mr." : "Mrs.");
                ctx.setVariable("name", buddy.getFullName());
                ctx.setVariable("imageResourceName", "myPhoto");
                if (type.equals("NEW")) {
                    ctx.setVariable("notifyText", "Notify to PM that he has new member!");
                    System.out.println("Notify to PM that he has new member!");
                } else {
                    ctx.setVariable("notifyText", "Notify to PM that HR has changed his member's information!");
                    System.out.println("Notify to PM that HR has changed his member's information!");
                }
                ctx.setVariable("employee", employee);
                InputStreamSource imageReSource = new FileSystemResource("employee-photos/" + employee.getId() + "/" + employee.getPhoto());
                helper.addInline("myPhoto", imageReSource, "image/png");
                mailSender.send(message);
            } catch (UnsupportedEncodingException | MessagingException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void sendEmailToEmployee(Employee employee, String independenceDay) {

    }

}
