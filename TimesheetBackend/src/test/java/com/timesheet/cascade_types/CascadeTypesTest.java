package com.timesheet.cascade_types;

import com.manage.employeemanagementmodel.entity.*;
import com.manage.employeemanagementmodel.entity.enums.Gender;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;
import com.timesheet.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
public class CascadeTypesTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private EmployeeRepository employeeRepository;

    //PERSIST
    @Test
    public void whenParentSavedThenChildSaved() {
        Employee employee = new Employee();
        addEmployeeInfo(employee);
        Note note = new Note();
        addNoteInfo(note);
        employee.setNotes(Arrays.asList(note));
        note.setEmployee(employee);
        testEntityManager.persist(employee);
        testEntityManager.flush();
        testEntityManager.clear();
    }

    //MERGE
    @Test
    public void whenParentSavedThenMerged() {
        Note note = testEntityManager.find(Note.class, 1);
        Employee employee = note.getEmployee();
        employee.setFirstName("John2");
        employee.setLastName("Doe2");
        testEntityManager.persist(note);
//        testEntityManager.merge(employee);
        testEntityManager.flush();
        testEntityManager.clear();
    }

    //REMOVE
    @Test
    public void whenParentRemovedThenChildRemoved() {
        Note note = testEntityManager.find(Note.class, 1);
        Employee employee = note.getEmployee();
        testEntityManager.remove(employee);
        testEntityManager.flush();
        testEntityManager.clear();
    }

    //REFRESH
    //reread the value of a given instance from the database, not from the persistence object
    @Test
    public void whenParentRefreshedThenChildRefreshed() {
        Employee employee = new Employee();
        addEmployeeInfo(employee);
        Note note = new Note();
        addNoteInfo(note);
        employee.setNotes(Arrays.asList(note));
        testEntityManager.persist(employee);
        testEntityManager.flush();
        employee.setFirstName("TEST");
        note.setNote("TEST REFRESH");
        testEntityManager.refresh(employee);

        assertThat(employee.getFirstName()).isEqualTo("TEST");
        assertThat(note.getNote()).isEqualTo("TEST REFRESH");
    }

    //DETACH
    //The detach operation removes the entity from the persistent context.
    //When we use CascadeType.DETACH, the child entity will also get removed from the persistent context.
    @Test
    public void whenParentDetachedThenChildDetached() {
        Employee employee = testEntityManager.find(Employee.class, 1);
        Note note = employee.getNotes().get(0);
        assertThat(testEntityManager.getEntityManager().contains(employee)).isTrue();
        assertThat(testEntityManager.getEntityManager().contains(note)).isTrue();
        testEntityManager.detach(employee);
        assertThat(testEntityManager.getEntityManager().contains(employee)).isFalse();
        assertThat(testEntityManager.getEntityManager().contains(note)).isFalse();
    }

    //LOCK
    //reattaches the entity and its associated child entity with the persistent context again.
    @Test
    public void whenDetachedAndLockedThenBothReattached() {

    }

    //REPLICATE
    //SAVE_UPDATE


    private void addNoteInfo(Note note) {
        note.setNote("This is a note");
        note.setDateSubmit(LocalDate.now());
        note.setDateModify(LocalDate.now());
        note.setWorkingTime(8);
        note.setStatus(TimeSheetStatus.NEW);
        note.setWorkingType(WorkingType.ONSITE);
        Task task = new Task();
        task.setId(1);
        note.setTask(task);
    }

    private void addEmployeeInfo(Employee employee) {
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setGender(Gender.MALE);
        employee.setBirthDate(LocalDate.of(1999, 6, 13));
        employee.setHiringDate(LocalDate.of(2023, 1, 1));
        employee.setEmail("nmq13061999@gmail.com");
        employee.setEnabled(true);
        employee.setPhoto(null);
        Department department = new Department();
        department.setId(1);
        employee.setDepartment(department);
        employee.setBuddy(null);
    }

}
