package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.EducationTitleDTO;
import com.example.universitysimulation.dto.request.EducationTitleRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.EducationTitle;
import com.example.universitysimulation.repository.EducationTitleRepository;
import com.example.universitysimulation.service.EducationTitleService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EducationTitleServiceImpl implements EducationTitleService {
    private final EducationTitleRepository educationTitleRepository;
    @Override
    public List<EducationTitleDTO> getAll() {
        return educationTitleRepository
                .findAll()
                .stream()
                .map(ObjectsMapper::convertEducationTitleToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EducationTitleDTO getById(Long id) throws NotFoundInDataBaseException{
        EducationTitle educationTitle = findById(id);
        return ObjectsMapper.convertEducationTitleToDTO(educationTitle);
    }

    @Override
    public EducationTitleDTO create(EducationTitleRequest educationTitleRequest) {
        EducationTitle educationTitle = new EducationTitle();
        educationTitle.setTitle(educationTitleRequest.getTitle());
        return ObjectsMapper.convertEducationTitleToDTO(educationTitleRepository.save(educationTitle));
    }

    @Override
    public void delete(Long id) throws NotFoundInDataBaseException{
        if(educationTitleRepository.findById(id).isEmpty()) {
            throw new NotFoundInDataBaseException("Education title with id "+id+ " not found");
        }
        educationTitleRepository.deleteById(id);
    }

    @Override
    public EducationTitleDTO update(EducationTitleRequest educationTitleRequest, Long id) {
        if(educationTitleRepository.findById(id).isEmpty())
            throw new NotFoundInDataBaseException("Education title with id "+id+ " not found");
        EducationTitle educationTitle = ObjectsMapper.convertEducationTitleRequestToEntity(educationTitleRequest);
        educationTitle.setId(id);
        EducationTitle savedEducationTitle = educationTitleRepository.save(educationTitle);
        return ObjectsMapper.convertEducationTitleToDTO(savedEducationTitle);
    }

    private EducationTitle findById(Long id) {
        Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(id);
        if(optionalEducationTitle.isEmpty())
            throw new NotFoundInDataBaseException("Education title with id " + id + " not found");
        return optionalEducationTitle.get();
    }
}
