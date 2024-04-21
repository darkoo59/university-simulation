package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void saveMemberTest() {
        // Create and save an academic title
        Member member = memberRepository.save(createNewMember());

        // Verify that the saved academic title is not null
        assertNotNull(member);
        assertNotNull(member.getId()); // Ensure the ID is generated
    }

    @Test
    @Transactional
    public void deleteMemberTest() {
        // Create and save an academic title
        Member member = memberRepository.save(createNewMember());
        assertNotNull(member);

        // Delete the saved academic title
        memberRepository.delete(member);

        // Verify that the academic title is deleted
        Optional<Member> deleted = memberRepository.findById(member.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Create and save an academic title with a specific ID
        Member member = memberRepository.save(createNewMember());
        assertNotNull(member);

        // Find the saved academic title by ID
        Optional<Member> found = memberRepository.findById(member.getId());

        // Verify that the academic title is found and matches the saved one
        assertTrue(found.isPresent());
        assertEquals(member.getId(), found.get().getId());
    }

    private Member createNewMember() {
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
}
