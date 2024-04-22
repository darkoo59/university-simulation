package com.example.universitysimulation.utils;

import com.example.universitysimulation.dto.*;
import com.example.universitysimulation.dto.request.EducationTitleRequest;
import com.example.universitysimulation.dto.request.SubjectRequest;
import com.example.universitysimulation.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.example.universitysimulation.HelperTests.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ObjectsMapperTests {
    @Test
    public void convertAcademicTitleToDTOTest() {
        // Arrange
        AcademicTitle academicTitle = new AcademicTitle();
        academicTitle.setTitle("title_test");
        academicTitle.setId(1l);

        // Act
        AcademicTitleDTO dto = ObjectsMapper.convertAcademicTitleToDTO(academicTitle);

        // Assert
        assertEquals(academicTitle.getId(), dto.getId());
        assertEquals(academicTitle.getTitle(), dto.getTitle());
    }

    @Test
    public void convertEducationTitleToDTOTest() {
        // Arrange
        EducationTitle educationTitle = new EducationTitle();
        educationTitle.setTitle("title_test");
        educationTitle.setId(1l);

        // Act
        EducationTitleDTO dto = ObjectsMapper.convertEducationTitleToDTO(educationTitle);

        // Assert
        assertEquals(educationTitle.getId(), dto.getId());
        assertEquals(educationTitle.getTitle(), dto.getTitle());
    }

    @Test
    public void convertEducationTitleRequestToEntityTest() {
        // Arrange
        EducationTitleRequest request = new EducationTitleRequest();
        request.setTitle("title_test");

        // Act
        EducationTitle entity = ObjectsMapper.convertEducationTitleRequestToEntity(request);

        // Assert
        assertEquals(entity.getTitle(), request.getTitle());
    }

    @Test
    public void convertScientificFieldToDTOTest() {
        // Arrange
        ScientificField scientificField = new ScientificField();
        scientificField.setField("field_test");
        scientificField.setId(1l);

        // Act
        ScientificFieldDTO dto = ObjectsMapper.convertScientificFieldToDTO(scientificField);

        // Assert
        assertEquals(scientificField.getId(), dto.getId());
        assertEquals(scientificField.getField(), dto.getField());
    }

    @Test
    public void convertSubjectToDTOTest() {
        // Arrange
        Subject subject = createNewSubject();

        // Act
        SubjectDTO dto = ObjectsMapper.convertSubjectToDTO(subject);

        // Assert
        assertEquals(subject.getId(), dto.getId());
        assertEquals(subject.getEspb(), dto.getEspb());
        assertEquals(subject.getName(), dto.getName());
        assertEquals(subject.getDepartment().getId(), dto.getDepartment().getId());
    }

    @Test
    public void convertSubjectRequestToEntityTest() {
        // Arrange
        SubjectRequest request = new SubjectRequest();
        request.setName("test");
        request.setEspb(9);

        // Act
        Subject entity = ObjectsMapper.convertSubjectRequestToEntity(request);

        // Assert
        assertEquals(request.getName(), entity.getName());
        assertEquals(request.getEspb(), entity.getEspb());
    }

    @Test
    public void convertDepartmentEntityToDTOTest() {
        // Arrange
        Department department = createNewDepartment();

        // Act
        DepartmentDTO dto = ObjectsMapper.convertDepartmentEntityToDTO(department);

        // Assert
        assertEquals(dto.getId(), department.getId());
        assertEquals(dto.getShortName(), department.getShortName());
        assertEquals(dto.getName(), department.getName());
        assertEquals(dto.getHeadOfDepartment().getId(), department.getHeadOfDepartment().getId());
        assertEquals(dto.getSecretary().getId(), department.getSecretary().getId());
    }

    @Test
    public void convertMemberEntityToDTOTest() {
        // Arrange
        Member member = createNewMember();

        // Act
        MemberDTO dto = ObjectsMapper.convertMemberEntityToDTO(member);

        // Assert
        assertEquals(dto.getId(), member.getId());
        assertEquals(dto.getFirstname(), member.getFirstname());
        assertEquals(dto.getLastname(), member.getLastname());
        assertEquals(dto.getDepartmentName(), member.getDepartment().getName());
        assertEquals(dto.getEducationTitle().getId(), member.getEducationTitle().getId());
        assertEquals(dto.getAcademicTitle().getId(), member.getAcademicTitle().getId());
        assertEquals(dto.getScientificField().getId(), member.getScientificField().getId());
    }

    @Test
    public void convertMemberAcademicTitleHistoryToDTOTest() {
        // Arrange
        AcademicTitleHistory entity = createAcademicTitleHistory();

        // Act
        MemberAcademicTitleHistoryDTO dto = ObjectsMapper.convertMemberAcademicTitleHistoryToDTO(entity);

        // Assert
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getStartDate(), entity.getStartDate());
        assertEquals(dto.getEndDate(), entity.getEndDate());
        assertEquals(dto.getAcademicTitle().getId(), entity.getAcademicTitle().getId());
    }

    @Test
    public void convertAcademicTitleHistoryToDTOTest() {
        // Arrange
        AcademicTitleHistory entity = createAcademicTitleHistory();

        // Act
        AcademicTitleHistoryDTO dto = ObjectsMapper.convertAcademicTitleHistoryToDTO(entity);

        // Assert
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getStartDate(), entity.getStartDate());
        assertEquals(dto.getEndDate(), entity.getEndDate());
        assertEquals(dto.getAcademicTitle().getId(), entity.getMember().getId());
        assertEquals(dto.getMember().getId(), entity.getMember().getId());
    }

    @Test
    public void convertDepartmentManagementHistoryToDTOTest() {
        // Arrange
        DepartmentManagementHistory entity = createDepartmentManagementHistory(1l ,1l, 2l);

        // Act
        DepartmentManagementHistoryDTO dto = ObjectsMapper.convertDepartmentManagementHistoryToDTO(entity);

        // Assert
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getStartDate(), entity.getStartDate());
        assertEquals(dto.getEndDate(), entity.getEndDate());
        assertEquals(dto.getDepartment().getId(), entity.getDepartment().getId());
        assertEquals(dto.getSecretary().getId(), entity.getSecretary().getId());
        assertEquals(dto.getHeadOfDepartment().getId(), entity.getHeadOfDepartment().getId());
    }
}
