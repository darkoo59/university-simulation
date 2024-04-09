package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.MemberAcademicTitleHistoryDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.request.MemberRequest;
import com.example.universitysimulation.exception.AlreadyExistInDataBaseException;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.*;
import com.example.universitysimulation.repository.*;
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
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AcademicTitleRepository academicTitleRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final EducationTitleRepository educationTitleRepository;
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    private final DepartmentRepository departmentRepository;

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
            throw new NotFoundInDataBaseException("Member with id " + id + " not found");
        Member member = memberRepository.findById(id).get();
        member.setFirstname(memberRequest.getFirstname());
        member.setLastname(memberRequest.getLastname());
        optionalAcademicTitle.ifPresent(member::setAcademicTitle);
        optionalEducationTitle.ifPresent(member::setEducationTitle);
        optionalScientificField.ifPresent(member::setScientificField);
        Member savedMember = memberRepository.save(member);
        return ObjectsMapper.convertMemberEntityToDTO(savedMember);
    }

    @Override
    public MemberDTO updateEducationTitle(Long memberId, Long educationTitleId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            throw new NotFoundInDataBaseException("Member with id " + memberId + " not found");
        }
        Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(educationTitleId);
        if (optionalEducationTitle.isEmpty()) {
            throw new NotFoundInDataBaseException("Educational title with id " + educationTitleId + " not found");
        }
        Member member = optionalMember.get();
        member.setEducationTitle(optionalEducationTitle.get());
        Member savedMember = memberRepository.save(member);
        return ObjectsMapper.convertMemberEntityToDTO(savedMember);
    }

    @Override
    public MemberDTO updateScientificField(Long memberId, Long scientificFieldId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            throw new NotFoundInDataBaseException("Member with id " + memberId + " not found");
        }
        Optional<ScientificField> optionalScientificField = scientificFieldRepository.findById(scientificFieldId);
        if (optionalScientificField.isEmpty()) {
            throw new NotFoundInDataBaseException("Scientific field with id " + scientificFieldId + " not found");
        }
        Member member = optionalMember.get();
        member.setScientificField(optionalScientificField.get());
        Member savedMember = memberRepository.save(member);
        return ObjectsMapper.convertMemberEntityToDTO(savedMember);
    }

    @Override
    public MemberDTO updateAcademicTitle(Long memberId, Long academicTitleId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            throw new NotFoundInDataBaseException("Member with id " + memberId + " not found");
        }
        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(academicTitleId);
        if (optionalAcademicTitle.isEmpty()) {
            throw new NotFoundInDataBaseException("Academic title with id " + academicTitleId + " not found");
        }
        Member member = optionalMember.get();
        Optional<AcademicTitleHistory> optionalAcademicTitleHistory = academicTitleHistoryRepository.findByCurrentAcademicTitle(memberId, member.getAcademicTitle().getId());
        if (optionalAcademicTitleHistory.isPresent()) {
            AcademicTitleHistory currentAcademicTitle = optionalAcademicTitleHistory.get();
            currentAcademicTitle.setEndDate(LocalDate.now());
            academicTitleHistoryRepository.save(currentAcademicTitle);
        }
        member.setAcademicTitle(optionalAcademicTitle.get());
        AcademicTitleHistory newAcademicTitle = new AcademicTitleHistory();
        newAcademicTitle.setMember(member);
        newAcademicTitle.setAcademicTitle(optionalAcademicTitle.get());
        newAcademicTitle.setStartDate(LocalDate.now());
        newAcademicTitle.setScientificField(member.getScientificField());
        academicTitleHistoryRepository.save(newAcademicTitle);
        Member savedMember = memberRepository.save(member);
        return ObjectsMapper.convertMemberEntityToDTO(savedMember);
    }

    @Override
    public List<MemberAcademicTitleHistoryDTO> getAcademicTitleHistory(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            throw new NotFoundInDataBaseException("Member with id " + id + " not found");
        }
        return optionalMember.get().getAcademicTitleHistory()
                .stream()
                .map(ObjectsMapper::convertMemberAcademicTitleHistoryToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO updateDepartment(Long memberId, Long departmentId) throws NotFoundInDataBaseException, AlreadyExistInDataBaseException {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            throw new NotFoundInDataBaseException("Member with id " + memberId + " not found");
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty())
            throw new NotFoundInDataBaseException("Department with id " + departmentId + " not found");
        if (departmentRepository.isDepartmentWithMemberApartFromDepartment(memberId, departmentId))
            throw new AlreadyExistInDataBaseException("Member department can't be updated because member is " +
                "head of department or secretary on other department!");
        Member memberToUpdate = optionalMember.get();
        memberToUpdate.setDepartment(optionalDepartment.get());
        Member updatedMember = memberRepository.save(memberToUpdate);
        return ObjectsMapper.convertMemberEntityToDTO(updatedMember);
    }
}
