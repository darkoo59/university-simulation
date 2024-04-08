package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long> {
    @Query("SELECT ath FROM AcademicTitleHistory ath " +
            "WHERE ath.member.id = :memberId " +
            "  AND ath.academicTitle.id = :academicTitleId " +
            "  AND ath.endDate IS NULL")
    Optional<AcademicTitleHistory> findByCurrentAcademicTitle(@Param("memberId") Long memberId,
                                                              @Param("academicTitleId") Long academicTitleId);
}