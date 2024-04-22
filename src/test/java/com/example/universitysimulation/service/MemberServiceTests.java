package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.MemberAcademicTitleHistoryDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.request.MemberRequest;
import com.example.universitysimulation.exception.AlreadyExistInDataBaseException;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.Member;
import com.example.universitysimulation.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.universitysimulation.HelperTests.createNewMember;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    public void getAllTest() {
        // Arrange
        memberRepository.save(createNewMember());

        // Act
        List<MemberDTO> founded = memberService.getAll();

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByIdSuccessTest() {
        // Arrange
        Member member = createNewMember();
        Member saved = memberRepository.save(member);

        // Act
        MemberDTO founded = memberService.getById(saved.getId());

        // Assert
        assertNotNull(founded);
        assertEquals(founded.getId(), saved.getId());
    }

    @Test
    public void getByIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.getById(Long.MAX_VALUE);
        });
    }

    @Test
    public void createSuccessTest() {
        // Arrange
        MemberRequest request = new MemberRequest();
        request.setAcademicTitleId(1l);
        request.setEducationTitleId(1l);
        request.setScientificTitleId(1l);
        request.setDepartmentId(1l);
        request.setFirstname("Test");
        request.setLastname("Test");

        // Act
        MemberDTO saved = memberService.create(request);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void createFailureTest() {
        // Arrange
        MemberRequest request = new MemberRequest();
        request.setAcademicTitleId(Long.MAX_VALUE);
        request.setEducationTitleId(Long.MAX_VALUE);
        request.setScientificTitleId(Long.MAX_VALUE);
        request.setDepartmentId(Long.MAX_VALUE);

        // Act & Assert
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.create(request);
        });
    }

    @Test
    public void deleteSuccessTest() {
        // Arrange
        Member member = createNewMember();
        Member saved = memberRepository.save(member);

        // Act
        memberService.delete(saved.getId());

        // Assert
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.getById(saved.getId());
        });
    }

    @Test
    public void deleteFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.delete(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateSuccessTest() {
        // Arrange
        Member member = createNewMember();
        Member saved = memberRepository.save(member);
        MemberRequest request = new MemberRequest();
        request.setAcademicTitleId(1l);
        request.setEducationTitleId(1l);
        request.setScientificTitleId(1l);
        request.setFirstname("Test");
        request.setFirstname("Test");

        // Act
        MemberDTO updated = memberService.update(request, saved.getId());

        // Assert
        assertEquals(updated.getFirstname(), request.getFirstname());
        assertEquals(saved.getId(), updated.getId());
        assertNotEquals(updated.getFirstname(), "firstname_test");
    }

    @Test
    public void updateFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.update(new MemberRequest(), Long.MAX_VALUE);
        });
    }

    @Test
    public void updateEducationTitleSuccessTest() {
        // Arrange & Act
        MemberDTO member = memberService.updateEducationTitle(1l, 2l);

        // Assert
        assertNotNull(member);
        assertEquals(member.getEducationTitle().getId(), 2l);
    }

    @Test
    public void updateEducationTitleFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.updateEducationTitle(1l, Long.MAX_VALUE);
        });
    }

    @Test
    public void updateScientificFieldSuccessTest() {
        // Arrange & Act
        MemberDTO member = memberService.updateScientificField(1l, 2l);

        // Assert
        assertNotNull(member);
        assertEquals(member.getScientificField().getId(), 2l);
    }

    @Test
    public void updateScientificFieldFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.updateScientificField(1l, Long.MAX_VALUE);
        });
    }

    @Test
    public void updateAcademicTitleSuccessTest() {
        // Arrange & Act
        MemberDTO member = memberService.updateAcademicTitle(1l, 2l);

        // Assert
        assertNotNull(member);
        assertEquals(member.getAcademicTitle().getId(), 2l);
    }

    @Test
    public void updateAcademicTitleFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.updateAcademicTitle(1l, Long.MAX_VALUE);
        });
    }
    @Test
    public void getAcademicTitleHistory() {
        // Arrange & Act
        List<MemberAcademicTitleHistoryDTO> founded = memberService.getAcademicTitleHistory(1l);

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getAllSubjectsFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.getAcademicTitleHistory(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateDepartmentFailureOneTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            memberService.updateDepartment(Long.MAX_VALUE, Long.MAX_VALUE);
        });
    }

    @Test
    public void updateDepartmentFailureTwoTest() {
        assertThrows(AlreadyExistInDataBaseException.class, () -> {
            memberService.updateDepartment(4l, 1l);
        });
    }
}
