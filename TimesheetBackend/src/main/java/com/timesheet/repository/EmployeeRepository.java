package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.timesheet.dto.*;
import com.timesheet.dto.employee.*;
import com.timesheet.dto.mapper.employee.EmployeeBasicInfoInterfaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT new com.timesheet.dto.project.ProfileDto(CONCAT(em.firstName, ' ', em.lastName), em.account.username, em.birthDate, em.department.name, em.jobDepartment.name ) " +
            "FROM Employee em WHERE em.id = ?1")
    CheckInDto.ProfileDto getEmployeeInfo(Integer id);

    @Query("SELECT em.id FROM Employee em WHERE em.account.username =?1")
    Integer getEmployeeId(String username);

    @Query(value = "SELECT employee.id AS id, CONCAT(employee.first_name, ' ', employee.last_name) AS fullName, jobDepartment.name AS jobDepartmentName, department.name AS departmentName, employee.photo " +
            "FROM Employee employee " +
            "JOIN job_department AS jobDepartment ON employee.job_department_id = jobDepartment.id " +
            "JOIN department AS department ON employee.department_id = department.id " +
            "WHERE employee.buddy_id = ?1", nativeQuery = true)
    Page<StaffViewDto> getAllStaffByBuddyId(Integer buddyId, Pageable pageable);

    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    @Query("SELECT em FROM Employee em ORDER BY em.firstName DESC")
    List<Employee> findAllAndSortByName();

    List<Employee> findByIdNot(Integer id);

    @Query("SELECT em FROM Employee em WHERE em.buddy.id = ?1")
    Set<Employee> findSubEmployeeByParentEmployee(Integer parentId);

    //    @Query("SELECT em FROM Employee em WHERE CONCAT(em.id, ' ', em.email, ' ', em.firstName, ' ', em.lastName) LIKE %?1%")
    @Query(value = "SELECT\n" +
            "    e.id AS ID,\n" +
            "    CONCAT(e.first_name, ' ', e.last_name) AS FullName,\n" +
            "    e.gender AS Gender,\n" +
            "    e.birth_date AS BirthDate,\n" +
            "    e.hiring_date AS HiringDate,\n" +
            "    e.email AS Email,\n" +
            "    CONCAT(b.first_name, ' ', b.last_name) AS BuddyName,\n" +
            "    d.name AS DepartmentName,\n" +
            "    e.level_status AS LevelStatus,\n" +
            "    jd.salary_range AS Salary,\n" +
            "    GROUP_CONCAT(r.name ORDER BY r.name ASC) AS Roles,\n" +
            "    e.enabled AS IsEnabled\n" +
            "FROM\n" +
            "    employee e\n" +
            "LEFT JOIN\n" +
            "    department d ON e.department_id = d.id\n" +
            "LEFT JOIN\n" +
            "    employee b ON e.buddy_id = b.id\n" +
            "LEFT JOIN\n" +
            "    account a ON e.account_id = a.id\n" +
            "LEFT JOIN\n" +
            "    job_department jd ON e.job_department_id = jd.id\n" +
            "LEFT JOIN\n" +
            "    user_role ur ON a.id = ur.user_id\n" +
            "LEFT JOIN\n" +
            "    role r ON ur.role_id = r.id\n" +
            "WHERE (LOWER(e.first_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.last_name) LIKE LOWER(CONCAT('%', :keyword, '%')))\n" +
            "    AND e.enabled = :isActive\n" +
            "    AND e.level_status LIKE CONCAT('%', :departmentLevelStatus, '%')\n" +
            "    AND jd.job_department LIKE CONCAT('%', :jobDepartment, '%')\n" +
            "    AND d.name LIKE CONCAT('%', :department, '%')\n" +
            "GROUP BY\n" +
            "    e.id\n" +
            "ORDER BY\n" +
            "    e.id", nativeQuery = true)
    Page<IEmployeeProfileDto> findAllWithIsActiveAndLevel(@Param("keyword") String keyword, Pageable pageable, @Param("isActive") Boolean isActive, @Param("departmentLevelStatus") String departmentLevelStatus, @Param("jobDepartment") String jobDepartment, @Param("department") String department);

    @Query(value = "SELECT\n" +
            "    e.id AS ID,\n" +
            "    CONCAT(e.first_name, ' ', e.last_name) AS FullName,\n" +
            "    e.gender AS Gender,\n" +
            "    e.birth_date AS BirthDate,\n" +
            "    e.hiring_date AS HiringDate,\n" +
            "    e.email AS Email,\n" +
            "    CONCAT(b.first_name, ' ', b.last_name) AS BuddyName,\n" +
            "    d.name AS DepartmentName,\n" +
            "    e.level_status AS LevelStatus,\n" +
            "    jd.salary_range AS Salary,\n" +
            "    GROUP_CONCAT(r.name ORDER BY r.name ASC) AS Roles,\n" +
            "    e.enabled AS IsEnabled\n" +
            "FROM\n" +
            "    employee e\n" +
            "LEFT JOIN\n" +
            "    department d ON e.department_id = d.id\n" +
            "LEFT JOIN\n" +
            "    employee b ON e.buddy_id = b.id\n" +
            "LEFT JOIN\n" +
            "    account a ON e.account_id = a.id\n" +
            "LEFT JOIN\n" +
            "    job_department jd ON e.job_department_id = jd.id\n" +
            "LEFT JOIN\n" +
            "    user_role ur ON a.id = ur.user_id\n" +
            "LEFT JOIN\n" +
            "    role r ON ur.role_id = r.id\n" +
            "WHERE (LOWER(e.first_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.last_name) LIKE LOWER(CONCAT('%', :keyword, '%')))\n" +
            "    AND e.level_status LIKE CONCAT('%', :departmentLevelStatus, '%')\n" +
            "    AND jd.job_department LIKE CONCAT('%', :jobDepartment, '%')\n" +
            "    AND d.name LIKE CONCAT('%', :department, '%')\n" +
            "GROUP BY\n" +
            "    e.id\n" +
            "ORDER BY\n" +
            "    e.id", nativeQuery = true)
    Page<IEmployeeProfileDto> findAllWithLevel(@Param("keyword") String keyword, Pageable pageable, @Param("departmentLevelStatus") String departmentLevelStatus, @Param("jobDepartment") String jobDepartment, @Param("department") String department);

    @Query(value = "SELECT\n" +
            "    e.id AS ID,\n" +
            "    CONCAT(e.first_name, ' ', e.last_name) AS FullName,\n" +
            "    e.gender AS Gender,\n" +
            "    e.birth_date AS BirthDate,\n" +
            "    e.hiring_date AS HiringDate,\n" +
            "    e.email AS Email,\n" +
            "    CONCAT(b.first_name, ' ', b.last_name) AS BuddyName,\n" +
            "    d.name AS DepartmentName,\n" +
            "    e.level_status AS LevelStatus,\n" +
            "    jd.salary_range AS Salary,\n" +
            "    GROUP_CONCAT(r.name ORDER BY r.name ASC) AS Roles,\n" +
            "    e.enabled AS IsEnabled\n" +
            "FROM\n" +
            "    employee e\n" +
            "LEFT JOIN\n" +
            "    department d ON e.department_id = d.id\n" +
            "LEFT JOIN\n" +
            "    employee b ON e.buddy_id = b.id\n" +
            "LEFT JOIN\n" +
            "    account a ON e.account_id = a.id\n" +
            "LEFT JOIN\n" +
            "    job_department jd ON e.job_department_id = jd.id\n" +
            "LEFT JOIN\n" +
            "    user_role ur ON a.id = ur.user_id\n" +
            "LEFT JOIN\n" +
            "    role r ON ur.role_id = r.id\n" +
            "WHERE\n" +
            "    (LOWER(e.first_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.last_name) LIKE LOWER(CONCAT('%', :keyword, '%')))\n" +
            "    AND (jd.job_department = :jobDepartment OR :jobDepartment IS NULL)\n" +
            "    AND (d.name = :department OR :department IS NULL)\n" +
            "GROUP BY\n" +
            "    e.id\n" +
            "ORDER BY\n" +
            "    e.id", nativeQuery = true)
    Page<IEmployeeProfileDto> findAll(@Param("keyword") String keyword, Pageable pageable, @Param("jobDepartment") String jobDepartment, @Param("department") String department);

    @Query(value = "SELECT \n" +
            "    e.id AS ID,\n" +
            "    CONCAT(e.first_name, ' ', e.last_name) AS FullName,\n" +
            "    e.gender AS Gender,\n" +
            "    e.birth_date AS BirthDate,\n" +
            "    e.hiring_date AS HiringDate,\n" +
            "    e.email AS Email,\n" +
            "    CONCAT(b.first_name, ' ', b.last_name) AS BuddyName,\n" +
            "    d.name AS DepartmentName,\n" +
            "    e.level_status AS LevelStatus,\n" +
            "    jd.salary_range AS Salary,\n" +
            "    GROUP_CONCAT(r.name ORDER BY r.name ASC) AS Roles,\n" +
            "    e.enabled AS IsEnabled\n" +
            "FROM employee e\n" +
            "LEFT JOIN department d ON e.department_id = d.id\n" +
            "LEFT JOIN employee b ON e.buddy_id = b.id\n" +
            "LEFT JOIN account a ON e.account_id = a.id\n" +
            "LEFT JOIN job_department jd ON e.job_department_id = jd.id\n" +
            "LEFT JOIN user_role ur ON a.id = ur.user_id\n" +
            "LEFT JOIN role r ON ur.role_id = r.id\n" +
            "GROUP BY e.id;\n", nativeQuery = true)
    Page<IEmployeeProfileDto> findAllEmployee(Pageable pageable);

    @Query("SELECT em FROM Employee em WHERE CONCAT(em.id, ' ', em.email, ' ', em.firstName, ' ', em.lastName) LIKE %?1%")
    public Slice<Employee> findAllWithSlice(String keyword, Pageable pageable);

    @Query("SELECT em FROM Employee em WHERE em.email = :email")
    Employee getEmployeeByEmail(@Param("email") String email);

    boolean existsById(Integer id);

    @Query(value = "SELECT\n" +
            "\te.id as ID,\n" +
            "    CONCAT(e.first_name, ' ', e.last_name) AS FullName,\n" +
            "    e.gender AS Gender,\n" +
            "    e.birth_date AS BirthDate,\n" +
            "    e.hiring_date AS HiringDate,\n" +
            "    e.email AS Email,\n" +
            "    CONCAT(b.first_name, ' ', b.last_name) AS BuddyName,\n" +
            "    d.name AS DepartmentName\n" +
            "FROM employee e\n" +
            "LEFT JOIN department d ON e.department_id = d.id\n" +
            "LEFT JOIN employee b ON e.buddy_id = b.id\n" +
            "LEFT JOIN account a ON e.account_id = a.id\n" +
            "WHERE e.id = :employeeId", nativeQuery = true)
    EmployeeProfileDto findEmployeeProfile(@Param("employeeId") Integer employeeId);

    EmployeeBasicInfoInterfaceDto findEmployeeById(Integer id);

    EmployeeBasicInfoDto findEmployeeByEmail(String email);

    @Query(value = "SELECT r.role_name FROM employees e " +
            "JOIN employee_roles er ON e.employee_id = er.employee_id " +
            "JOIN roles r ON er.role_id = r.role_id " +
            "WHERE e.employee_id = ?1;", nativeQuery = true)
    List<String> getRolesById(Integer id);


    @Query("SELECT em.id FROM Employee em")
    List<Integer> getAllEmployeeId();

    @Query("SELECT new com.timesheet.dto.employee.BuddyDto(em.id, CONCAT(em.firstName, ' ', em.lastName)) FROM Employee em WHERE em.jobDepartment.jobDepartment = 'PM'")
    List<BuddyDto> getBuddys();

    @Query("SELECT new com.timesheet.dto.employee.EmployeeManageDto(em.id, CONCAT(em.firstName, ' ', em.lastName), em.gender, em.birthDate, em.hiringDate, em.email, CONCAT(em.buddy.firstName, ' ', em.buddy.lastName), em.department.name, em.employeeLevelStatus, em.jobDepartment.salaryRange, em.enabled) " +
            "FROM Employee em")
    List<EmployeeManageDto> findAllCheck();
}
