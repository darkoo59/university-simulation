package com.example.universitysimulation;

import com.example.universitysimulation.model.*;

import java.time.LocalDate;

public class HelperTests {

    // Helper method to create a sample AcademicTitleHistory object
    public static AcademicTitleHistory createAcademicTitleHistory() {
        AcademicTitle academicTitle = new AcademicTitle(1L, "test");
        ScientificField scientificField = new ScientificField(1L, "test");
        Member member = new Member();
        member.setId(1L);

        AcademicTitleHistory academicTitleHistory = new AcademicTitleHistory();
        academicTitleHistory.setAcademicTitle(academicTitle);
        academicTitleHistory.setStartDate(LocalDate.now());
        academicTitleHistory.setEndDate(LocalDate.now().plusDays(1));
        academicTitleHistory.setScientificField(scientificField);
        academicTitleHistory.setMember(member);

        return academicTitleHistory;
    }

    // Helper method to create a DepartmentManagementHistory with specified department and members
    public static DepartmentManagementHistory createDepartmentManagementHistory(Long departmentId, Long secretaryId, Long headId) {
        DepartmentManagementHistory history = new DepartmentManagementHistory();
        Department department = new Department();
        department.setId(departmentId);
        Member secretary = new Member();
        secretary.setId(secretaryId);
        Member head = new Member();
        head.setId(headId);
        history.setDepartment(department);
        history.setSecretary(secretary);
        history.setHeadOfDepartment(head);
        history.setStartDate(LocalDate.now());
        history.setEndDate(LocalDate.now().plusDays(1));
        return history;
    }

    // Helper method to create a sample Department object
    public static Department createNewDepartment() {
        Department department = new Department();
        department.setId(1l);
        department.setName("name_test");
        department.setShortName("short_test");
        Member secretary = new Member();
        Member head = new Member();
        secretary.setId(1l);
        head.setId(1l);
        department.setSecretary(secretary);
        department.setHeadOfDepartment(head);
        return department;
    }

    // Helper method to create a sample Member object
    public static Member createNewMember() {
        Member member = new Member();
        Department department = new Department();
        AcademicTitle academicTitle = new AcademicTitle();
        EducationTitle educationTitle = new EducationTitle();
        ScientificField scientificField = new ScientificField();
        department.setId(1l);
        academicTitle.setId(1l);
        educationTitle.setId(1l);
        scientificField.setId(1l);
        member.setDepartment(department);
        member.setAcademicTitle(academicTitle);
        member.setEducationTitle(educationTitle);
        member.setScientificField(scientificField);
        member.setFirstname("firstname_test");
        member.setLastname("lastname_test");
        return member;
    }

    // Helper method to create a sample Subject object
    public static Subject createNewSubject() {
        Subject subject = new Subject();
        Department department = new Department();
        department.setId(1l);
        subject.setId(1l);
        subject.setName("test_subject");
        subject.setEspb(9);
        subject.setDepartment(department);
        return subject;
    }
}
