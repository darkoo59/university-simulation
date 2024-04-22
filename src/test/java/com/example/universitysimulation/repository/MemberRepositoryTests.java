package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.universitysimulation.HelperTests.createNewMember;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void saveMemberTest() {
        // Arrange
        Member member = createNewMember();

        // Act
        Member saved = memberRepository.save(member);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    @Transactional
    public void deleteMemberTest() {
        // Arrange
        Member member = memberRepository.save(createNewMember());

        // Act
        memberRepository.delete(member);
        Optional<Member> deleted = memberRepository.findById(member.getId());

        // Assert
        assertNotNull(member);
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Arrange
        Member member = memberRepository.save(createNewMember());

        // Act
        Optional<Member> found = memberRepository.findById(member.getId());

        // Assert
        assertNotNull(member);
        assertTrue(found.isPresent());
        assertEquals(member.getId(), found.get().getId());
    }
}
