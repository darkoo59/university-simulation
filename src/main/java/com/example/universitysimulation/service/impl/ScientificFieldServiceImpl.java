package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.ScientificFieldDTO;
import com.example.universitysimulation.dto.request.ScientificFieldRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.ScientificField;
import com.example.universitysimulation.repository.ScientificFieldRepository;
import com.example.universitysimulation.service.ScientificFieldService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScientificFieldServiceImpl implements ScientificFieldService {
    private final ScientificFieldRepository scientificFieldRepository;
    @Override
    public List<ScientificFieldDTO> getAll() {
        return scientificFieldRepository
                .findAll()
                .stream()
                .map(ObjectsMapper::convertScientificFieldToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ScientificFieldDTO getById(Long id) throws NotFoundInDataBaseException{
        return ObjectsMapper.convertScientificFieldToDTO(findById(id));
    }

    @Override
    public ScientificFieldDTO create(ScientificFieldRequest scientificFieldRequest) {
        ScientificField scientificField = new ScientificField();
        scientificField.setField(scientificFieldRequest.getField());
        return ObjectsMapper.convertScientificFieldToDTO(scientificFieldRepository.save(scientificField));
    }

    @Override
    public void delete(Long id) throws NotFoundInDataBaseException{
        if(scientificFieldRepository.findById(id).isEmpty()) {
            throw new NotFoundInDataBaseException("Scientific field with id "+id+ " not found");
        }
        scientificFieldRepository.deleteById(id);
    }

    @Override
    public ScientificFieldDTO update(ScientificFieldRequest scientificFieldRequest, Long id) {
        ScientificField scientificField = findById(id);
        scientificField.setField(scientificFieldRequest.getField());
        ScientificField savedScientificField = scientificFieldRepository.save(scientificField);
        return ObjectsMapper.convertScientificFieldToDTO(savedScientificField);
    }

    private ScientificField findById(Long id) {
        Optional<ScientificField> optionalScientificField = scientificFieldRepository.findById(id);
        if (optionalScientificField.isEmpty())
            throw new NotFoundInDataBaseException("Scientific field with id " + id + " not found");
        return optionalScientificField.get();
    }
}
