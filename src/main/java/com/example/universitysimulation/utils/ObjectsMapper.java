package com.example.universitysimulation.utils;
import com.example.universitysimulation.dto.*;
import com.example.universitysimulation.dto.request.*;
import com.example.universitysimulation.model.*;
import org.modelmapper.ModelMapper;
public class ObjectsMapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static AcademicTitleDTO convertAcademicTitleToDTO(AcademicTitle academicTitle){
        return modelMapper.map(academicTitle, AcademicTitleDTO.class);
    }

    public static AcademicTitle convertAcademicTitleRequestToEntity(AcademicTitleRequest academicTitleRequest) {
        return modelMapper.map(academicTitleRequest, AcademicTitle.class);
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

    public static ScientificField convertScientificFieldRequestToEntity(ScientificFieldRequest scientificFieldRequest) {
        return modelMapper.map(scientificFieldRequest, ScientificField.class);
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

    public static Department convertDepartmentRequestToEntity(DepartmentRequest departmentRequest) {
        return modelMapper.map(departmentRequest, Department.class);
    }

    public static MemberDTO convertMemberEntityToDTO(Member member) {
        MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
        memberDTO.setDepartmentName(member.getDepartment().getName());
        return memberDTO;
    }

    public static AcademicTitle convertAcademicTitleDTOToEntity(AcademicTitleDTO dto) {
        return modelMapper.map(dto, AcademicTitle.class);
    }

    public static ScientificField convertScientificFieldDTOToEntity(ScientificFieldDTO dto) {
        return modelMapper.map(dto, ScientificField.class);
    }

    public static EducationTitle convertEducationTitleDTOToEntity(EducationTitleDTO dto) {
        return modelMapper.map(dto, EducationTitle.class);
    }

    public static AcademicTitleHistoryDTO convertAcademicTitleHistoryToDTO(AcademicTitleHistory ath) {
        return modelMapper.map(ath, AcademicTitleHistoryDTO.class);
    }
}
