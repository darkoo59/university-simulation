package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.AcademicTitleHistoryDTO;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitleHistory;
import com.example.universitysimulation.model.Member;
import com.example.universitysimulation.repository.AcademicTitleHistoryRepository;
import com.example.universitysimulation.repository.MemberRepository;
import com.example.universitysimulation.service.AcademicTitleHistoryService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.universitysimulation.utils.Constants.academicTitleHistoryMissingId;
import static com.example.universitysimulation.utils.Constants.memberMissingId;

@RequiredArgsConstructor
@Service
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    private final MemberRepository memberRepository;
    @Override
    public List<AcademicTitleHistoryDTO> getAll() {
        return academicTitleHistoryRepository
                .findAll()
                .stream()
                .map(ObjectsMapper::convertAcademicTitleHistoryToDTO)
                .toList();
    }

    @Override
    public AcademicTitleHistoryDTO getById(Long id) {
        Optional<AcademicTitleHistory> optionalAcademicTitleHistory = academicTitleHistoryRepository.findById(id);
        if(optionalAcademicTitleHistory.isEmpty())
            throw new NotFoundInDataBaseException(academicTitleHistoryMissingId(id));
        return ObjectsMapper.convertAcademicTitleHistoryToDTO(optionalAcademicTitleHistory.get());
    }

    @Override
    public List<AcademicTitleHistoryDTO> getByMemberId(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty())
            throw new NotFoundInDataBaseException(memberMissingId(id));
        return academicTitleHistoryRepository
                .findAllByMemberId(id)
                .stream()
                .map(ObjectsMapper::convertAcademicTitleHistoryToDTO)
                .toList();
    }
}
