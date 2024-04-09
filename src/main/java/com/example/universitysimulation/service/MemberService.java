package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.MemberAcademicTitleHistoryDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.request.MemberRequest;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getAll();

    MemberDTO getById(Long id);
    MemberDTO create(MemberRequest memberRequest);
    public void delete(Long id);
    public MemberDTO update(MemberRequest memberRequest, Long id);

    MemberDTO updateEducationTitle(Long memberId, Long educationTitleId);

    MemberDTO updateScientificField(Long memberId, Long scientificFieldId);

    MemberDTO updateAcademicTitle(Long memberId, Long academicTitleId);

    List<MemberAcademicTitleHistoryDTO> getAcademicTitleHistory(Long id);

    MemberDTO updateDepartment(Long memberId, Long departmentId);
}
