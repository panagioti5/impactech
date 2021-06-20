package com.exercise.repository;

import com.exercise.entities.CdrSyncHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface CdrSyncHistoryRepository extends JpaRepository<CdrSyncHistory, Long> {

    @Query(nativeQuery = true,
            value = "SELECT DATE_CREATED " +
            "FROM TB_SYNC_HISTORY h " +
            "ORDER BY DATE_CREATED DESC " +
            "FETCH FIRST 1 ROWS ONLY"
    )
    Date findLatestSyncDate();

}
