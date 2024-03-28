package com.example.universitysimulation.utils;
import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.model.AcademicTitle;
import org.modelmapper.ModelMapper;
public class ObjectsMapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static AcademicTitleDTO convertAcademicTitleToDTO(AcademicTitle academicTitle){
        return modelMapper.map(academicTitle, AcademicTitleDTO.class);
    }

    public static AcademicTitle convertAcademicTitleRequestToEntity(AcademicTitleRequest academicTitleRequest) {
        return modelMapper.map(academicTitleRequest, AcademicTitle.class);
    }
}
