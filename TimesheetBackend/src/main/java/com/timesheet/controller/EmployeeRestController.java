package com.timesheet.controller;

import ch.qos.logback.classic.Logger;
import com.manage.employeemanagementmodel.entity.*;
import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.manage.employeemanagementmodel.entity.enums.Gender;
import com.manage.employeemanagementmodel.exception.EmployeeNotFoundException;
import com.timesheet.configuration.security.jwt.JwtTokenUtil;
import com.timesheet.dto.account.AccountRequestDto;
import com.timesheet.dto.account.PasswordRequest;
import com.timesheet.dto.account.RoleDto;
import com.timesheet.dto.employee.*;
import com.timesheet.dto.mapper.employee.EmployeeFormMapper;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.service.*;
import com.timesheet.ultilities.FileUploadUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/app/employees")
@SecurityRequirement(name = "bearer-key")
public class EmployeeRestController {
    private static final Logger log = (Logger) LoggerFactory.getLogger(EmployeeRestController.class);
    private final JwtTokenUtil jwtTokenUtil;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final JobDepartmentService jobDepartmentService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeFormMapper employeeFormMapper;
    private final EmailService emailService;
    private final NoteService noteService;
    private final RoleService roleService;
    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;

    public EmployeeRestController(JwtTokenUtil jwtTokenUtil, EmployeeService employeeService, DepartmentService departmentService, JobDepartmentService jobDepartmentService, EmployeeRepository employeeRepository, EmployeeFormMapper employeeFormMapper, EmailService emailService, NoteService noteService, RoleService roleService, AccountService accountService, PasswordEncoder passwordEncoder) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.jobDepartmentService = jobDepartmentService;
        this.employeeRepository = employeeRepository;
        this.employeeFormMapper = employeeFormMapper;
        this.emailService = emailService;
        this.noteService = noteService;
        this.roleService = roleService;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("page")
    public Page<IEmployeeProfileDto> listByPage(@RequestParam(name = "pageNum") Integer pageNum,
                                                @RequestParam(name = "pageSize") Integer pageSize,
                                                @RequestParam(name = "sortField") String sortField,
                                                @RequestParam(name = "sortDir") String sortDir,
                                                @RequestParam(name = "keyword") String keyword,
                                                @RequestParam(name = "isEnable", required = false) String isEnable,
                                                @RequestParam(name = "level", required = false) String departmentLevelStatus,
                                                @RequestParam(name = "type", required = false) String jobDepartment,
                                                @RequestParam(name = "branch", required = false) String department) {
        if (!isEnable.isEmpty())
            return employeeService.listByPageWithIsEnable(pageNum, pageSize, sortField, sortDir, keyword, isEnable.equals("ACTIVE"), departmentLevelStatus, jobDepartment, department);
        return employeeService.listByPage(pageNum, pageSize, sortField, sortDir, keyword, departmentLevelStatus, jobDepartment, department);
    }

    @GetMapping("pms")
    public ResponseEntity<List<BuddyDto>> getBuddys() {
        return ResponseEntity.ok(employeeService.getBuddys());
    }

    @GetMapping("get_all_role")
    public ResponseEntity<List<RoleDto>> getAllRole() {
        return ResponseEntity.ok(roleService.getAllRole());
    }


    @GetMapping("get_by_id")
    public ResponseEntity<EmployeeDetailDto> getEmployeeById(@RequestParam("id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) return ResponseEntity.badRequest().body(null);
        String buddyName = (employee.getBuddy() != null) ? employee.getBuddy().getFirstName() + " " + employee.getBuddy().getLastName() : "";
        return ResponseEntity.ok(new EmployeeDetailDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getGender(),
                employee.getBirthDate().toString(),
                employee.getEmail(),
                employee.getBank().getName(),
                employee.getBank().getNumber(),
                employee.getPhoto(),
                employee.getHiringDate().toString(),
                buddyName,
                employee.getDepartment().getName(),
                employee.getAccount().getUsername(),
                employee.getJobDepartment().getJobDepartment(),
                employee.getJobDepartment().getSalaryRange(),
                employee.getEmployeeLevelStatus()));
    }

    @PostMapping("save")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeSaveDto employeeSaveDto) {
        return ResponseEntity.ok(employeeService.save(employeeSaveDto));
    }

    @PostMapping("save_account")
    public ResponseEntity<Account> saveEmployeeAccount(@RequestBody AccountRequestDto accountRequestDto, @RequestParam(name = "list_role_id") List<Integer> listRoleId) {
        if (accountRequestDto.getId() == null) {
            if (accountService.findByUsername(accountRequestDto.getUsername()) != null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            String password = passwordEncoder.encode(accountRequestDto.getPassword());
            Account account = new Account(null, accountRequestDto.getUsername(), password, null, null);
            List<Role> listRole = new ArrayList<>();
            listRoleId.stream().filter(Objects::nonNull).forEach(id -> listRole.add(roleService.getRoleById(id)));
            account.setRoles(listRole);
            return new ResponseEntity<>(accountService.save(account), HttpStatus.OK);
        } else {
            if (accountService.findByUsername(accountRequestDto.getUsername()) != null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Account account = accountService.getAccountById(accountRequestDto.getId());
            account.setUsername(accountRequestDto.getUsername());
            account.setPassword(passwordEncoder.encode(accountRequestDto.getPassword()));
            return new ResponseEntity<>(accountService.save(account), HttpStatus.OK);
        }
    }

    @PutMapping("update_password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordRequest passwordRequest) {
        Integer accountID = accountService.getAccountByEmployeeId(passwordRequest.getId());
        String oldPassword = passwordRequest.getOldPassword();
        String newPassword = passwordRequest.getNewPassword();
        Account account = accountService.getAccountById(accountID);
        if (passwordEncoder.matches(oldPassword, account.getPassword())) {
            account.setPassword(passwordEncoder.encode(newPassword));
            return ResponseEntity.ok(accountService.save(account));
        }
        return ResponseEntity.badRequest().body("Old password is not correct!");
    }


    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            employeeService.detete(id);
            String employeeImagesDir = "employee-photos/" + id;
            FileUploadUtil.removeDir(employeeImagesDir);
            return ResponseEntity.ok("Delete employee successfully!");
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Delete employee failed!");
        }
    }

    @GetMapping("{id}/notes")
    public ResponseEntity<List<Note>> getAllNotesForEmployee(@PathVariable("id") Integer id, Model model) {
        List<Note> noteList = noteService.listAllNoteByEmployee(id);
        return ResponseEntity.ok(noteList);
    }

    @GetMapping("employee_id")
    public ResponseEntity<?> getEmployeeId(HttpServletRequest request) {
        String accessToken = jwtTokenUtil.getAccessToken(request);
        String username = jwtTokenUtil.getSubject(accessToken);
        try {
            Integer employeeId = employeeService.getEmployeeId(username);
            return ResponseEntity.ok(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("view_staff")
    public ResponseEntity<?> viewStaff(@RequestParam("buddyId") Integer buddyId, @RequestParam("pageNumber") Integer pageNumber,
                                       @RequestParam("pageSize") Integer pageSize, @RequestParam("nameSearch") String nameSearch,
                                       @RequestParam("sortField") String sortField, @RequestParam("sortOrder") String sortOrder) {
        try {
            return ResponseEntity.ok(employeeService.getStaffListByNativeQuery(buddyId, pageNumber, pageSize, nameSearch, sortField, sortOrder));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("get_roles_by_id")
    public ResponseEntity<List<String>> getRolesById(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.ok(employeeService.getRolesById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    @PutMapping("update_role")
    public ResponseEntity<?> updateRole(@RequestParam("id") Integer id, @RequestParam("list_role_id") List<Integer> listRoleId) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            Account account = employee.getAccount();
            List<Role> listRole = new ArrayList<>();
            listRoleId.stream().filter(Objects::nonNull).forEach(roleId -> listRole.add(roleService.getRoleById(roleId)));
            account.setRoles(listRole);
            accountService.save(account);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @PutMapping("update_bank")
    public ResponseEntity<Bank> updateBank(@RequestParam("employeeId") Integer employeeId,
                                           @RequestParam("bankName") String bankName,
                                           @RequestParam("bankNumber") String bankNumber) {
        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            Bank bank = employee.getBank();
            bank.setName(bankName);
            bank.setNumber(bankNumber);
            employee.setBank(bank);
            return ResponseEntity.ok(employeeRepository.save(employee).getBank());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    @PutMapping("update_isEnable")
    public ResponseEntity<Employee> updateIsEnable(@RequestParam("id") Integer id, @RequestParam("isEnable") Boolean isEnable) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            employee.setEnabled(isEnable);
            return ResponseEntity.ok(employeeRepository.save(employee));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }
}
