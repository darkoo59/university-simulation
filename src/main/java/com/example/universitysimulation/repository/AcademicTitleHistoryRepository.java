package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long> {
    @Query("SELECT ath FROM AcademicTitleHistory ath " +
            "WHERE ath.member.id = :memberId " +
            "  AND ath.academicTitle.id = :academicTitleId " +
            "  AND ath.endDate IS NULL")
    List<AcademicTitleHistory> findByCurrentAcademicTitle(@Param("memberId") Long memberId,
                                                              @Param("academicTitleId") Long academicTitleId);

    @Query("SELECT ath FROM AcademicTitleHistory ath WHERE ath.member.id=:id")
    List<AcademicTitleHistory> findAllByMemberId(@Param("id") Long id);
}
