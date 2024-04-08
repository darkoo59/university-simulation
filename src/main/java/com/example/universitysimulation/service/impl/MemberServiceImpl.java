package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.request.MemberRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.*;
import com.example.universitysimulation.repository.*;
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
    private final AcademicTitleRepository academicTitleRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final EducationTitleRepository educationTitleRepository;
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
        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(memberRequest.getAcademicTitleId());
        Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(memberRequest.getEducationTitleId());
        Optional<ScientificField> optionalScientificField = scientificFieldRepository.findById(memberRequest.getScientificTitleId());
        member.setFirstname(memberRequest.getFirstname());
        member.setLastname(memberRequest.getLastname());
        optionalAcademicTitle.ifPresent(member::setAcademicTitle);
        optionalEducationTitle.ifPresent(member::setEducationTitle);
        optionalScientificField.ifPresent(member::setScientificField);
        return ObjectsMapper.convertMemberEntityToDTO(memberRepository.save(member));
    }

    @Override
    public void delete(Long id) {
        if(memberRepository.findById(id).isEmpty()) {
            throw new NotFoundInDataBaseException("Member with id "+id+ " not found");
        }
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO update(MemberRequest memberRequest, Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(id);
        Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(id);
        Optional<ScientificField> optionalScientificField = scientificFieldRepository.findById(id);
        if(memberRepository.findById(id).isEmpty())
            throw new NotFoundInDataBaseException("Member with id "+id+ " not found");
        Member member = memberRepository.findById(id).get();
        member.setFirstname(memberRequest.getFirstname());
        member.setLastname(memberRequest.getLastname());
        optionalAcademicTitle.ifPresent(member::setAcademicTitle);
        optionalEducationTitle.ifPresent(member::setEducationTitle);
        optionalScientificField.ifPresent(member::setScientificField);
        Member savedMember = memberRepository.save(member);
        return ObjectsMapper.convertMemberEntityToDTO(savedMember);
    }
}
