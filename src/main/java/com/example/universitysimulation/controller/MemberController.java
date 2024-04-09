package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.MemberAcademicTitleHistoryDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.request.MemberRequest;
import com.example.universitysimulation.exception.AlreadyExistInDataBaseException;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAll() {
        List<MemberDTO> members = memberService.getAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getById(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        MemberDTO member = memberService.getById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("/{id}/academic-title-history")
    public ResponseEntity<List<MemberAcademicTitleHistoryDTO>> getAcademicTitlesHistory(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        List<MemberAcademicTitleHistoryDTO> academicTitleHistory = memberService.getAcademicTitleHistory(id);
        return new ResponseEntity<>(academicTitleHistory, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MemberDTO> create(@Valid @RequestBody MemberRequest memberRequest) {
        MemberDTO member = memberService.create(memberRequest);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        memberService.delete(id);
        return new ResponseEntity<>("Member removed!", HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<MemberDTO> updateByPut(@Valid @RequestBody MemberRequest memberRequest, @PathVariable("id") Long id){
        MemberDTO member = memberService.update(memberRequest, id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PatchMapping(path="/{memberId}/educationTitle/{educationTitleId}")
    public ResponseEntity<MemberDTO> updateEducationTitle(@PathVariable("memberId") Long memberId, @PathVariable("educationTitleId") Long educationTitleId){
        MemberDTO member = memberService.updateEducationTitle(memberId, educationTitleId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PatchMapping(path="/{memberId}/scientificField/{scientificFieldId}")
    public ResponseEntity<MemberDTO> updateScientificField(@PathVariable("memberId") Long memberId, @PathVariable("scientificFieldId") Long scientificFieldId){
        MemberDTO member = memberService.updateScientificField(memberId, scientificFieldId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PatchMapping(path="/{memberId}/academicTitle/{academicTitleId}")
    public ResponseEntity<MemberDTO> updateAcademicTitle(@PathVariable("memberId") Long memberId, @PathVariable("academicTitleId") Long academicTitleId){
        MemberDTO member = memberService.updateAcademicTitle(memberId, academicTitleId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PatchMapping(path="/{memberId}/department/{departmentId}")
    public ResponseEntity<MemberDTO> updateDepartment(@PathVariable("memberId") Long memberId, @PathVariable("departmentId") Long departmentId) throws NotFoundInDataBaseException, AlreadyExistInDataBaseException {
        MemberDTO member = memberService.updateDepartment(memberId, departmentId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
