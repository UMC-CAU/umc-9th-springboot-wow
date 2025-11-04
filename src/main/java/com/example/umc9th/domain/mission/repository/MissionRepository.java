package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 기본 CRUD 메서드는 JpaRepository에 정의돼있음.
    @Query("SELECT m FROM Mission m JOIN m.store s " +
            "WHERE s.address = :selectedAddress " +
            "AND m.status IN :statusList " +
            "AND m.dueDate >= :today")
    Page<Mission> findAvailableMissionsByRegion(
            @Param("selectedAddress") String selectedAddress,
            @Param("today") LocalDate today,
            @Param("statusList") List<String> statusList,
            Pageable pageable
    );
}
