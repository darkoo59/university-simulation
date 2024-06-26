package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.SubjectRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.model.Subject;
import com.example.universitysimulation.repository.DepartmentRepository;
import com.example.universitysimulation.repository.SubjectRepository;
import com.example.universitysimulation.service.SubjectService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<SubjectDTO> getAll() {
        return subjectRepository
                .findAll()
                .stream()
                .map(ObjectsMapper::convertSubjectToDTO)
                .toList();
    }

    @Override
    public SubjectDTO getById(Long id) {
        return ObjectsMapper.convertSubjectToDTO(findById(id));
    }

    @Override
    public SubjectDTO create(SubjectRequest subjectRequest) {
        Subject subject = new Subject();
        subject.setName(subjectRequest.getName());
        subject.setEspb(subjectRequest.getEspb());
        Optional<Department> optionalDepartment = departmentRepository.findById(subjectRequest.getDepartmentId());
        if(optionalDepartment.isEmpty())
            throw new NotFoundInDataBaseException("Department with id " + subjectRequest.getDepartmentId() + " not found");
        subject.setDepartment(optionalDepartment.get());
        return ObjectsMapper.convertSubjectToDTO(subjectRepository.save(subject));
    }

    @Override
    public void delete(Long id) throws NotFoundInDataBaseException {
        if (subjectRepository.findById(id).isEmpty()) {
            throw new NotFoundInDataBaseException("Subject with id " + id + " not found");
        }
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDTO update(SubjectRequest subjectRequest, Long id) throws NotFoundInDataBaseException {
        if (subjectRepository.findById(id).isEmpty())
            throw new NotFoundInDataBaseException("Subject with id " + id + " not found");
        Optional<Department> optionalDepartment = departmentRepository.findById(subjectRequest.getDepartmentId());
        if (optionalDepartment.isEmpty())
            throw new NotFoundInDataBaseException("Department with id " + subjectRequest.getDepartmentId() + " not found");
        Subject subject = ObjectsMapper.convertSubjectRequestToEntity(subjectRequest);
        subject.setDepartment(optionalDepartment.get());
        subject.setId(id);
        Subject savedSubject = subjectRepository.save(subject);
        return ObjectsMapper.convertSubjectToDTO(savedSubject);
    }

    @Override
    public SubjectDTO updateDepartment(Long subjectId, Long departmentId) {
        Subject subject = findById(subjectId);
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if(optionalDepartment.isEmpty())
            throw new NotFoundInDataBaseException("Department with id " + departmentId + " not found");

        subject.setDepartment(optionalDepartment.get());
        Subject savedSubject = subjectRepository.save(subject);
        return ObjectsMapper.convertSubjectToDTO(savedSubject);
    }

    private Subject findById(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isEmpty()) throw new NotFoundInDataBaseException("Subject with id " + id + " not found");
        return optionalSubject.get();
    }
}
