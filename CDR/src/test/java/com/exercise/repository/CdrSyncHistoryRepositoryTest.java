package com.exercise.repository;

import com.exercise.entities.CdrSyncHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CdrSyncHistoryRepositoryTest {

    @Autowired
    private CdrRepository cdrRepository;

    @Autowired
    private CdrHistoryRepository cdrHistoryRepository;

    @Autowired
    private CdrSyncHistoryRepository cdrSyncHistoryRepository;

    @Test
    void retrieveLatestSyncDate() throws InterruptedException {
        CdrSyncHistory cdrSyncHistory = new CdrSyncHistory();
        cdrSyncHistory.setUnmatchedCalls(25);
        cdrSyncHistoryRepository.save(cdrSyncHistory);
        TimeUnit.SECONDS.sleep(1);
        cdrSyncHistory.setUnmatchedCalls(66);
        cdrSyncHistoryRepository.save(cdrSyncHistory);

        List<CdrSyncHistory> allEntries = cdrSyncHistoryRepository.findAll();
        Date latestSyncDate = cdrSyncHistoryRepository.findLatestSyncDate();

        CdrSyncHistory expected = allEntries.stream().filter(e -> e.getUnmatchedCalls() == 66).findFirst().orElse(null);
        assertThat(expected.getDateCreated()).isEqualTo(latestSyncDate);

    }
}