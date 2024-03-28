package com.example.universitysimulation.utils;
import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.EducationTitleDTO;
import com.example.universitysimulation.dto.ScientificFieldDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.dto.request.EducationTitleRequest;
import com.example.universitysimulation.dto.request.ScientificFieldRequest;
import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.EducationTitle;
import com.example.universitysimulation.model.ScientificField;
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

    public static ScientificFieldDTO convertScientificFieldToDTO(ScientificField scientificField){
        return modelMapper.map(scientificField, ScientificFieldDTO.class);
    }

    public static ScientificField convertScientificFieldRequestToEntity(ScientificFieldRequest scientificFieldRequest) {
        return modelMapper.map(scientificFieldRequest, ScientificField.class);
    }
}
