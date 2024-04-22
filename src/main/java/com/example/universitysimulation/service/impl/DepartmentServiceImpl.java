package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.DepartmentDTO;
import com.example.universitysimulation.dto.DepartmentManagementHistoryDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.DepartmentRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.model.DepartmentManagementHistory;
import com.example.universitysimulation.model.Member;
import com.example.universitysimulation.repository.DepartmentRepository;
import com.example.universitysimulation.service.DepartmentManagementHistoryService;
import com.example.universitysimulation.service.DepartmentService;
import com.example.universitysimulation.service.MemberService;
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
    private final MemberService memberService;
    private final DepartmentManagementHistoryService departmentManagementHistoryService;

    @Override
    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll().stream().map(ObjectsMapper::convertDepartmentEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getById(Long id) throws NotFoundInDataBaseException {
        return ObjectsMapper.convertDepartmentEntityToDTO(findById(id));
    }

    @Override
    public DepartmentDTO create(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.getName());
        department.setShortName(departmentRequest.getShortName());
        return ObjectsMapper.convertDepartmentEntityToDTO(departmentRepository.save(department));
    }

    @Override
    public void delete(Long id) throws NotFoundInDataBaseException {
        if (departmentRepository.findById(id).isEmpty()) {
            throw new NotFoundInDataBaseException("Department with id " + id + " not found");
        }
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentDTO update(DepartmentRequest departmentRequest, Long id) {
        Department department = findById(id);
        department.setName(departmentRequest.getName());
        department.setShortName(departmentRequest.getShortName());
        Department savedDepartment = departmentRepository.save(department);
        return ObjectsMapper.convertDepartmentEntityToDTO(savedDepartment);
    }

    @Override
    public List<MemberDTO> getAllMembers(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty())
            throw new NotFoundInDataBaseException("Department with id " + id + " not found");
        return optionalDepartment.get().getMembers().stream().map(ObjectsMapper::convertMemberEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SubjectDTO> getAllSubjects(Long id) {
        Department department = findById(id);
        return department.getSubjects().stream().map(ObjectsMapper::convertSubjectToDTO).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateHeadOfDepartment(Long departmentId, Long memberId) {
        Department department = findById(departmentId);
        Member member = memberService.findById(memberId);
        return setHeadOfDepartment(department, member);
    }

    @Override
    public DepartmentDTO updateSecretary(Long departmentId, Long memberId) {
        Department department = findById(departmentId);
        Member member = memberService.findById(memberId);
        return setSecretary(department, member);
    }

    @Override
    public List<DepartmentManagementHistoryDTO> getDepartmentManagementHistory(Long id) {
        Department department = findById(id);
        return department.getManagementHistories().stream().map(ObjectsMapper::convertDepartmentManagementHistoryToDTO).collect(Collectors.toList());
    }

    private Department findById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty())
            throw new NotFoundInDataBaseException("Department with id " + id + " not found");
        return optionalDepartment.get();
    }

    public DepartmentDTO setHeadOfDepartment(Department department, Member member) {
        return setDepartmentManagement(department, member, department.getSecretary());
    }

    public DepartmentDTO setSecretary(Department department, Member member) {
        return setDepartmentManagement(department, department.getHeadOfDepartment(), member);
    }

    private DepartmentDTO setDepartmentManagement(Department department, Member newHead, Member newSecretary) {
        for (DepartmentManagementHistory dmh : department.getManagementHistories()) {
            if (dmh.getEndDate() == null && !dmh.getHeadOfDepartment().getId().equals(newHead.getId())) {
                endCurrentManagement(dmh);
                createNewManagement(department, newHead, newSecretary);
                return fetchDepartmentDTO(department);
            }
        }
        createNewManagement(department, newHead, newSecretary);
        return fetchDepartmentDTO(department);
    }

    private void endCurrentManagement(DepartmentManagementHistory dmh) {
        dmh.setEndDate(LocalDate.now());
        departmentManagementHistoryService.save(dmh);
    }

    private void createNewManagement(Department department, Member newHead, Member newSecretary) {
        DepartmentManagementHistory newDmh = new DepartmentManagementHistory();
        newDmh.setDepartment(department);
        newDmh.setHeadOfDepartment(newHead);
        newDmh.setSecretary(newSecretary);
        newDmh.setStartDate(LocalDate.now());
        departmentManagementHistoryService.save(newDmh);
    }

    private DepartmentDTO fetchDepartmentDTO(Department department) {
        return ObjectsMapper.convertDepartmentEntityToDTO(departmentRepository.findById(department.getId()).get());
    }
}
