package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.request.MemberRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.model.Member;
import com.example.universitysimulation.repository.DepartmentRepository;
import com.example.universitysimulation.repository.MemberRepository;
import com.example.universitysimulation.service.AcademicTitleService;
import com.example.universitysimulation.service.EducationTitleService;
import com.example.universitysimulation.service.MemberService;
import com.example.universitysimulation.service.ScientificFieldService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AcademicTitleService academicTitleService;
    private final ScientificFieldService scientificFieldService;
    private final EducationTitleService educationTitleService;
    @Override
    public List<MemberDTO> getAll() {
        return memberRepository.findAll()
                .stream()
                .map(ObjectsMapper::convertMemberEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO getById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty())
            throw new NotFoundInDataBaseException("Member with id " + id + " not found");
        return ObjectsMapper.convertMemberEntityToDTO(optionalMember.get());
    }

    @Override
    public MemberDTO create(MemberRequest memberRequest) {
        Member member = new Member();
        member.setFirstname(memberRequest.getFirstname());
        member.setLastname(memberRequest.getLastname());
        member.setAcademicTitle(ObjectsMapper.convertAcademicTitleDTOToEntity(academicTitleService.getById(memberRequest.getAcademicTitleId())));
        member.setScientificField(ObjectsMapper.convertScientificFieldDTOToEntity(scientificFieldService.getById(memberRequest.getScientificTitleId())));
        member.setEducationTitle(ObjectsMapper.convertEducationTitleDTOToEntity(educationTitleService.getById(memberRequest.getEducationTitleId())));
        return ObjectsMapper.convertMemberEntityToDTO(memberRepository.save(member));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MemberDTO update(MemberRequest memberRequest, Long id) {
        return null;
    }
}
