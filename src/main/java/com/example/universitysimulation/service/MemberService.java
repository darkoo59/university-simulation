package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.DepartmentDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.request.DepartmentRequest;
import com.example.universitysimulation.dto.request.MemberRequest;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getAll();

    MemberDTO getById(Long id);
    MemberDTO create(MemberRequest memberRequest);
    public void delete(Long id);
    public MemberDTO update(MemberRequest memberRequest, Long id);
}
