package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.DepartmentDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.dto.request.DepartmentRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.repository.DepartmentRepository;
import com.example.universitysimulation.service.DepartmentService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(ObjectsMapper::convertDepartmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(optionalDepartment.isEmpty())
            throw new NotFoundInDataBaseException("Department with id " + id + " not found");
        return ObjectsMapper.convertDepartmentEntityToDTO(optionalDepartment.get());
    }

    @Override
    public DepartmentDTO create(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.getName());
        department.setShortName(departmentRequest.getShortName());
        return ObjectsMapper.convertDepartmentEntityToDTO(departmentRepository.save(department));
    }

    @Override
    public void delete(Long id) throws NotFoundInDataBaseException{
        if(departmentRepository.findById(id).isEmpty()) {
            throw new NotFoundInDataBaseException("Department with id "+id+ " not found");
        }
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentDTO update(DepartmentRequest departmentRequest, Long id) {
        if(departmentRepository.findById(id).isEmpty())
            throw new NotFoundInDataBaseException("Department with id "+id+ " not found");
        Department department = departmentRepository.findById(id).get();
        department.setName(departmentRequest.getName());
        department.setShortName(departmentRequest.getShortName());
        Department savedDepartment = departmentRepository.save(department);
        return ObjectsMapper.convertDepartmentEntityToDTO(savedDepartment);
    }
}
