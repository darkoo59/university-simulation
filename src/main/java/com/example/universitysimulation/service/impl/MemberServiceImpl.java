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

import static com.example.universitysimulation.utils.Constants.*;

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
                .toList();
    }

    @Override
    public MemberDTO getById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty())
            throw new NotFoundInDataBaseException(memberMissingId(id));
        return ObjectsMapper.convertMemberEntityToDTO(optionalMember.get());
    }

    @Override
    public MemberDTO create(MemberRequest memberRequest) throws NotFoundInDataBaseException {
        Member member = new Member();
        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(memberRequest.getAcademicTitleId());
        if(optionalAcademicTitle.isEmpty())
            throw new NotFoundInDataBaseException(academicTitleMissingId(memberRequest.getAcademicTitleId()));
        Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(memberRequest.getEducationTitleId());
        if(optionalEducationTitle.isEmpty())
            throw new NotFoundInDataBaseException(educationTitleMissingId(memberRequest.getEducationTitleId()));
        Optional<ScientificField> optionalScientificField = scientificFieldRepository.findById(memberRequest.getScientificTitleId());
        if(optionalScientificField.isEmpty())
            throw new NotFoundInDataBaseException(scientificFieldMissingId(memberRequest.getScientificTitleId()));
        Optional<Department> optionalDepartment = departmentRepository.findById(memberRequest.getDepartmentId());
        if(optionalDepartment.isEmpty())
            throw new NotFoundInDataBaseException(departmentMissingId(memberRequest.getDepartmentId()));

        member.setFirstname(memberRequest.getFirstname());
        member.setLastname(memberRequest.getLastname());

        member.setAcademicTitle(optionalAcademicTitle.get());
        member.setEducationTitle(optionalEducationTitle.get());
        member.setScientificField(optionalScientificField.get());
        member.setDepartment(optionalDepartment.get());
        return ObjectsMapper.convertMemberEntityToDTO(memberRepository.save(member));
    }

    @Override
    public void delete(Long id) {
        if(memberRepository.findById(id).isEmpty()) {
            throw new NotFoundInDataBaseException(memberMissingId(id));
        }
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO update(MemberRequest memberRequest, Long id) {
        Member member = findById(id);
        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(id);
        Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(id);
        Optional<ScientificField> optionalScientificField = scientificFieldRepository.findById(id);

        member.setFirstname(memberRequest.getFirstname());
        member.setLastname(memberRequest.getLastname());

        optionalAcademicTitle.ifPresent(member::setAcademicTitle);
        optionalEducationTitle.ifPresent(member::setEducationTitle);
        optionalScientificField.ifPresent(member::setScientificField);
        return ObjectsMapper.convertMemberEntityToDTO(memberRepository.save(member));
    }

    @Override
    public MemberDTO updateEducationTitle(Long memberId, Long educationTitleId) {
        Member member = findById(memberId);
        EducationTitle educationTitle = educationTitleRepository.findById(educationTitleId)
                .orElseThrow(() -> new NotFoundInDataBaseException(educationTitleMissingId(educationTitleId)));
        member.setEducationTitle(educationTitle);
        Member savedMember = memberRepository.save(member);
        return ObjectsMapper.convertMemberEntityToDTO(savedMember);
    }

    @Override
    public MemberDTO updateScientificField(Long memberId, Long scientificFieldId) {
        Member member = findById(memberId);
        ScientificField scientificField = scientificFieldRepository.findById(scientificFieldId)
                .orElseThrow(() -> new NotFoundInDataBaseException(scientificFieldMissingId(scientificFieldId)));
        member.setScientificField(scientificField);
        return ObjectsMapper.convertMemberEntityToDTO(memberRepository.save(member));
    }

    @Override
    public MemberDTO updateAcademicTitle(Long memberId, Long academicTitleId) {
        Member member = findById(memberId);
        AcademicTitle academicTitle = academicTitleRepository.findById(academicTitleId)
                .orElseThrow(() -> new NotFoundInDataBaseException(academicTitleMissingId(academicTitleId)));

        List<AcademicTitleHistory> currentTitles = academicTitleHistoryRepository.findByCurrentAcademicTitle(memberId, member.getAcademicTitle().getId());
        if(!currentTitles.isEmpty()) {
            for(AcademicTitleHistory currentAcademicTitle : currentTitles) {
                currentAcademicTitle.setEndDate(LocalDate.now());
                academicTitleHistoryRepository.save(currentAcademicTitle);
            }
        }
        member.setAcademicTitle(academicTitle);

        AcademicTitleHistory newAcademicTitle = new AcademicTitleHistory();
        newAcademicTitle.setMember(member);
        newAcademicTitle.setAcademicTitle(academicTitle);
        newAcademicTitle.setStartDate(LocalDate.now());
        newAcademicTitle.setScientificField(member.getScientificField());
        academicTitleHistoryRepository.save(newAcademicTitle);

        Member savedMember = memberRepository.save(member);

        return ObjectsMapper.convertMemberEntityToDTO(savedMember);
    }

    @Override
    public List<MemberAcademicTitleHistoryDTO> getAcademicTitleHistory(Long id) {
        return findById(id).getAcademicTitleHistory()
                .stream()
                .map(ObjectsMapper::convertMemberAcademicTitleHistoryToDTO)
                .toList();
    }

    @Override
    public MemberDTO updateDepartment(Long memberId, Long departmentId) throws NotFoundInDataBaseException, AlreadyExistInDataBaseException {
        Optional<Member> optionalMemberToUpdate = memberRepository.findById(memberId);
        if(optionalMemberToUpdate.isEmpty())
            throw new NotFoundInDataBaseException(memberMissingId(memberId));
        Member memberToUpdate = optionalMemberToUpdate.get();
        Optional<Department> optionalNewDepartment = departmentRepository.findById(departmentId);
        if(optionalNewDepartment.isEmpty())
            throw new NotFoundInDataBaseException(departmentMissingId(departmentId));
        if (departmentRepository.isDepartmentWithMemberApartFromDepartment(memberId, departmentId)) {
            throw new AlreadyExistInDataBaseException("Member department can't be updated because member is "
                    + "head of department or secretary in another department");
        }
        memberToUpdate.setDepartment(optionalNewDepartment.get());
        Member updatedMember = memberRepository.save(memberToUpdate);
        return ObjectsMapper.convertMemberEntityToDTO(updatedMember);
    }

    @Override
    public Member findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty())
            throw new NotFoundInDataBaseException(memberMissingId(id));
        return optionalMember.get();
    }
}
