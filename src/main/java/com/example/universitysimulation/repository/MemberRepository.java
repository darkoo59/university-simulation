package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
