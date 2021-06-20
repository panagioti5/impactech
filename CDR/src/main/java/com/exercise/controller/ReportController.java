package com.exercise.controller;

import com.exercise.core.constants.ErrorCode;
import com.exercise.core.exceptions.ExceptionResponse;
import com.exercise.core.exceptions.NoEntryFoundException;
import com.exercise.entities.CdrSyncHistory;
import com.exercise.entities.MinutesNumber;
import com.exercise.exceptions.PbxNotFoundException;
import com.exercise.repository.CdrHistoryRepository;
import com.exercise.repository.CdrRepository;
import com.exercise.repository.CdrSyncHistoryRepository;
import com.exercise.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReportController {

    private final CdrRepository cdrRepository;
    private final CdrHistoryRepository cdrHistoryRepository;
    private final CdrSyncHistoryRepository cdrSyncHistoryRepository;

    @Autowired
    public ReportController(CdrRepository cdrRepository, CdrHistoryRepository cdrHistoryRepository, CdrSyncHistoryRepository cdrSyncHistoryRepository) {
        this.cdrRepository = cdrRepository;
        this.cdrHistoryRepository = cdrHistoryRepository;
        this.cdrSyncHistoryRepository = cdrSyncHistoryRepository;
    }

    @GetMapping("/cdr/report/total_cdrs")
    public long findCdrById(){
        return cdrHistoryRepository.findAll().size();
    }

    @GetMapping("/cdr/report/number_minutes")
    public MinutesNumber findNumberMinutes(){
        return Utils.getInstance().getCompletedAndIncompletedMinsCalls(cdrRepository.findAll());
    }

    @GetMapping("/cdr/report/unmatched_calls")
    public double retrieveUnmatchedCalls(){
        return cdrSyncHistoryRepository.findAll().
            stream().mapToDouble(CdrSyncHistory::getUnmatchedCalls).sum();
    }

    @GetMapping("/cdr/report/latest_sync_date")
    public String retrieveLatestSyncDate(){
        if  (null != cdrSyncHistoryRepository.findLatestSyncDate()){
            return cdrSyncHistoryRepository.findLatestSyncDate().toString();
        }
        else{
            throw new NoEntryFoundException(new ExceptionResponse("There is no entry found in TB_SYNC_HISTORY",
                    ErrorCode.NO_ENTRY_FOUND, new Date()));
        }
    }

}
