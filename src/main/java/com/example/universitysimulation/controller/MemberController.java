package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.MemberRequest;
import com.example.universitysimulation.dto.request.SubjectRequest;
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

    @PatchMapping(path="/{id}")
    public ResponseEntity<MemberDTO> updateByPatch(@Valid @RequestBody MemberRequest memberRequest,@PathVariable("id") Long id){
        MemberDTO member = memberService.update(memberRequest, id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
