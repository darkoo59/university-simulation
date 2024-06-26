package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.repository.AcademicTitleRepository;
import com.example.universitysimulation.service.AcademicTitleService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.universitysimulation.utils.Constants.academicTitleMissingId;

@RequiredArgsConstructor
@Service
public class AcademicTitleServiceImpl implements AcademicTitleService {
    private final AcademicTitleRepository academicTitleRepository;

    @Override
    public List<AcademicTitleDTO> getAll() {
        return academicTitleRepository.findAll().stream().map(ObjectsMapper::convertAcademicTitleToDTO).toList();
    }

    @Override
    public AcademicTitleDTO getById(Long id) throws NotFoundInDataBaseException {
        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(id);
        if (optionalAcademicTitle.isEmpty())
            throw new NotFoundInDataBaseException(academicTitleMissingId(id));
        return ObjectsMapper.convertAcademicTitleToDTO(optionalAcademicTitle.get());
    }

    @Override
    public AcademicTitleDTO create(AcademicTitleRequest academicTitleRequest) {
        AcademicTitle academicTitle = new AcademicTitle();
        academicTitle.setTitle(academicTitleRequest.getTitle());
        return ObjectsMapper.convertAcademicTitleToDTO(academicTitleRepository.save(academicTitle));
    }

    @Override
    public void delete(Long id) throws NotFoundInDataBaseException {
        if (academicTitleRepository.findById(id).isEmpty()) {
            throw new NotFoundInDataBaseException(academicTitleMissingId(id));
        }
        academicTitleRepository.deleteById(id);
    }

    @Override
    public AcademicTitleDTO update(AcademicTitleRequest academicTitleRequest, Long id) {
        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(id);
        if (optionalAcademicTitle.isEmpty())
            throw new NotFoundInDataBaseException(academicTitleMissingId(id));
        AcademicTitle academicTitle = optionalAcademicTitle.get();
        academicTitle.setTitle(academicTitleRequest.getTitle());
        return ObjectsMapper.convertAcademicTitleToDTO(academicTitleRepository.save(academicTitle));
    }
}
