package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.DepartmentDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.dto.request.DepartmentRequest;
import com.example.universitysimulation.exception.IncapableException;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.model.DepartmentManagementHistory;
import com.example.universitysimulation.model.Member;
import com.example.universitysimulation.repository.DepartmentManagementHistoryRepository;
import com.example.universitysimulation.repository.DepartmentRepository;
import com.example.universitysimulation.repository.MemberRepository;
import com.example.universitysimulation.service.DepartmentService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final MemberRepository memberRepository;
    private final DepartmentManagementHistoryRepository departmentManagementHistoryRepository;
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

    @Override
    public List<MemberDTO> getAllMembers(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw new NotFoundInDataBaseException("Department with id " + id + " not found");
        }
        return optionalDepartment.get().getMembers()
                .stream()
                .map(ObjectsMapper::convertMemberEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectDTO> getAllSubjects(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw new NotFoundInDataBaseException("Department with id " + id + " not found");
        }
        return optionalDepartment.get().getSubjects()
                .stream()
                .map(ObjectsMapper::convertSubjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateHeadOfDepartment(Long departmentId, Long memberId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new NotFoundInDataBaseException("Department with id " + departmentId + " not found");
        }
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            throw new NotFoundInDataBaseException("Member with id " + memberId + " not found");
        }
        Member member = optionalMember.get();
        Department department = optionalDepartment.get();
        if(!member.getDepartment().getId().equals(departmentId)){
            throw new IncapableException("Provided member can't be head of the department because it is in different department!");
        }
        for (DepartmentManagementHistory dmh : department.getManagementHistories()){
            if(dmh.getEndDate() == null && !dmh.getHeadOfDepartment().getId().equals(memberId)) {
                DepartmentManagementHistory newDmh = new DepartmentManagementHistory();
                newDmh.setDepartment(department);
                newDmh.setSecretary(department.getSecretary());
                newDmh.setStartDate(LocalDate.now());
                newDmh.setHeadOfDepartment(member);
                departmentManagementHistoryRepository.save(newDmh);
                dmh.setEndDate(LocalDate.now());
                departmentManagementHistoryRepository.save(dmh);
                return ObjectsMapper.convertDepartmentEntityToDTO(departmentRepository.findById(departmentId).get());
            }
        }

        DepartmentManagementHistory newDmh = new DepartmentManagementHistory();
        newDmh.setDepartment(department);
        newDmh.setSecretary(department.getSecretary());
        newDmh.setStartDate(LocalDate.now());
        newDmh.setHeadOfDepartment(member);
        departmentManagementHistoryRepository.save(newDmh);
        return ObjectsMapper.convertDepartmentEntityToDTO(departmentRepository.findById(departmentId).get());
    }

    @Override
    public DepartmentDTO updateSecretary(Long departmentId, Long memberId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new NotFoundInDataBaseException("Department with id " + departmentId + " not found");
        }
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            throw new NotFoundInDataBaseException("Member with id " + memberId + " not found");
        }
        Member member = optionalMember.get();
        Department department = optionalDepartment.get();
        if(!member.getDepartment().getId().equals(departmentId)){
            throw new IncapableException("Provided member can't be secretary because it is in different department!");
        }
        for (DepartmentManagementHistory dmh : department.getManagementHistories()){
            if(dmh.getEndDate() == null && !dmh.getHeadOfDepartment().getId().equals(memberId)) {
                DepartmentManagementHistory newDmh = new DepartmentManagementHistory();
                newDmh.setDepartment(department);
                newDmh.setSecretary(member);
                newDmh.setStartDate(LocalDate.now());
                newDmh.setHeadOfDepartment(department.getHeadOfDepartment());
                departmentManagementHistoryRepository.save(newDmh);
                dmh.setEndDate(LocalDate.now());
                departmentManagementHistoryRepository.save(dmh);
                return ObjectsMapper.convertDepartmentEntityToDTO(departmentRepository.findById(departmentId).get());
            }
        }

        DepartmentManagementHistory newDmh = new DepartmentManagementHistory();
        newDmh.setDepartment(department);
        newDmh.setSecretary(member);
        newDmh.setStartDate(LocalDate.now());
        newDmh.setHeadOfDepartment(department.getHeadOfDepartment());
        departmentManagementHistoryRepository.save(newDmh);
        return ObjectsMapper.convertDepartmentEntityToDTO(departmentRepository.findById(departmentId).get());
    }
}
