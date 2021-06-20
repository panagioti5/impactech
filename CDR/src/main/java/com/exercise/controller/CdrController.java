package com.exercise.controller;

import com.exercise.core.constants.ErrorCode;
import com.exercise.core.exceptions.ExceptionResponse;
import com.exercise.core.exceptions.RuntimeExceptionImpl;
import com.exercise.core.utils.CoreUtils;
import com.exercise.entities.Cdr;
import com.exercise.entities.CdrHistory;
import com.exercise.exceptions.CdrNotFoundException;
import com.exercise.exceptions.PbxNotFoundException;
import com.exercise.repository.CdrHistoryRepository;
import com.exercise.repository.CdrRepository;
import com.exercise.validation.CdrValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.exercise.validation.CdrValidation.cdrIdExists;
import static com.exercise.validation.CdrValidation.operationNameExists;

@RestController
public class CdrController {

    private final CdrRepository cdrRepository;
    private final CdrHistoryRepository cdrHistoryRepository;

    @Autowired
    private CoreUtils coreUtils;

    @Autowired
    public CdrController(CdrRepository cdrRepository, CdrHistoryRepository cdrHistoryRepository) {
        this.cdrRepository = cdrRepository;
        this.cdrHistoryRepository = cdrHistoryRepository;
    }

    @PostMapping("/cdr")
    public void createCdr(@RequestBody Cdr cdr){
        ErrorCode result = operationNameExists(coreUtils)
                .apply(cdr);

        if(ErrorCode.SUCCESS.equals(result)){
            cdrRepository.save(cdr);
            cdrHistoryRepository.save(new CdrHistory(cdr));
        }
        else if(ErrorCode.PBX_NAME_NOT_FOUND.equals(result)){
            throw new PbxNotFoundException(new ExceptionResponse("No PBX with operation name: "
                    + cdr.getDomain_name() + " found.", ErrorCode.PBX_NAME_NOT_FOUND, new Date()));       //Didn't found the given domain name in PBX microservice
        }
        else{
            throw new RuntimeExceptionImpl(new ExceptionResponse("Something went wrong while trying to call a different Microservice, " +
                    "Please check the logs for the exact error", ErrorCode.FAILURE, new Date()));
        }
    }

    @DeleteMapping("/cdr/{cdrId}")
    public void deleteCdr(@PathVariable long cdrId){
        Cdr cdr = cdrRepository.findCdrById(cdrId);
        List<CdrHistory> historyCdrs = cdrHistoryRepository.findByCdr(cdr);
        historyCdrs.forEach(cdrHistoryRepository::delete); // Delete all entries from tb_cdr_history for the particular CRD_ID
        cdrRepository.delete(cdr);
    }

    @PutMapping("/cdr")
    public void updateCdr(@RequestBody Cdr cdr){
        ErrorCode result = operationNameExists(coreUtils)
                .and(cdrIdExists(cdrRepository))
                .apply(cdr);

        if(ErrorCode.SUCCESS.equals(result)){
            cdrRepository.save(cdr);
            cdrHistoryRepository.save(new CdrHistory(cdr));
        }
        else if(ErrorCode.PBX_NAME_NOT_FOUND.equals(result)){
            throw new PbxNotFoundException(new ExceptionResponse("No PBX with operation name: "
                    + cdr.getDomain_name() + " found.", ErrorCode.PBX_NAME_NOT_FOUND, new Date()));
        }
        else if(ErrorCode.CDR_NOT_FOUND.equals(result)){
            throw new CdrNotFoundException(new ExceptionResponse("No CRD with ID: " +
                    cdr.getCdrId() + " found", ErrorCode.CDR_NOT_FOUND, new Date()));
        }
        else{
            throw new RuntimeExceptionImpl(new ExceptionResponse("Something went wrong while trying to call a different Microservice, " +
                    "Please check the logs for the exact error", ErrorCode.FAILURE, new Date()));
        }
    }

    @GetMapping("/cdr/{cdrId}")
    public Cdr findCdrById(@PathVariable long cdrId){
        return cdrRepository.findCdrById(cdrId);
    }
}
