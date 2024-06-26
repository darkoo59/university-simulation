package com.example.universitysimulation.utils;

import com.example.universitysimulation.dto.*;
import com.example.universitysimulation.dto.request.EducationTitleRequest;
import com.example.universitysimulation.dto.request.SubjectRequest;
import com.example.universitysimulation.model.*;
import org.modelmapper.ModelMapper;
public class ObjectsMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    private ObjectsMapper () {}

    public static AcademicTitleDTO convertAcademicTitleToDTO(AcademicTitle academicTitle){
        return modelMapper.map(academicTitle, AcademicTitleDTO.class);
    }

    public static EducationTitleDTO convertEducationTitleToDTO(EducationTitle educationTitle){
        return modelMapper.map(educationTitle, EducationTitleDTO.class);
    }

    public static EducationTitle convertEducationTitleRequestToEntity(EducationTitleRequest educationTitleRequest) {
        return modelMapper.map(educationTitleRequest, EducationTitle.class);
    }

    public static ScientificFieldDTO convertScientificFieldToDTO(ScientificField scientificField) {
        return modelMapper.map(scientificField, ScientificFieldDTO.class);
    }

    public static SubjectDTO convertSubjectToDTO(Subject subject) {
        SubjectDTO subjectDTO = modelMapper.map(subject, SubjectDTO.class);
        subjectDTO.setDepartment(modelMapper.map(subject.getDepartment(), DepartmentDTO.class));
        return subjectDTO;
    }

    public static Subject convertSubjectRequestToEntity(SubjectRequest subjectRequest) {
        return modelMapper.map(subjectRequest, Subject.class);
    }

    public static DepartmentDTO convertDepartmentEntityToDTO(Department department) {
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public static MemberDTO convertMemberEntityToDTO(Member member) {
        MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
        memberDTO.setDepartmentName(member.getDepartment().getName());
        return memberDTO;
    }

    public static MemberAcademicTitleHistoryDTO convertMemberAcademicTitleHistoryToDTO(AcademicTitleHistory ath) {
        return modelMapper.map(ath, MemberAcademicTitleHistoryDTO.class);
    }

    public static AcademicTitleHistoryDTO convertAcademicTitleHistoryToDTO(AcademicTitleHistory ath) {
        return modelMapper.map(ath, AcademicTitleHistoryDTO.class);
    }

    public static DepartmentManagementHistoryDTO convertDepartmentManagementHistoryToDTO(DepartmentManagementHistory departmentManagementHistory) {
        return modelMapper.map(departmentManagementHistory, DepartmentManagementHistoryDTO.class);
    }
}
