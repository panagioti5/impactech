package com.exercise.controller;

import com.exercise.core.constants.ErrorCode;
import com.exercise.core.exceptions.ExceptionResponse;
import com.exercise.core.utils.CoreUtils;
import com.exercise.entities.Cdr;
import com.exercise.entities.CdrHistory;
import com.exercise.exceptions.PbxNotFoundException;
import com.exercise.repository.CdrHistoryRepository;
import com.exercise.repository.CdrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.exercise.validation.CdrValidation.operationNameExists;
import static com.exercise.validation.CdrValidation.uuidExists;

@RestController
public class WebhookController {

    private final CdrRepository cdrRepository;
    private final CdrHistoryRepository cdrHistoryRepository;

    @Autowired
    private CoreUtils coreUtils;

    @Autowired
    public WebhookController(CdrRepository cdrRepository, CdrHistoryRepository cdrHistoryRepository) {
        this.cdrRepository = cdrRepository;
        this.cdrHistoryRepository = cdrHistoryRepository;
    }

    @PostMapping("cdr/v3/webhook")
    public void webhook(@RequestBody Cdr cdr, @RequestHeader(value = "Tenant-UUID") String header){
        ErrorCode result = operationNameExists(coreUtils) // Check whether domain Name exists
                .and(uuidExists(cdrRepository))
                .apply(cdr);

        if(ErrorCode.SUCCESS.equals(result)){
            cdrRepository.save(cdr);
            cdrHistoryRepository.save(new CdrHistory(cdr));
        }
        else if(ErrorCode.UUID_EXISTS.equals(result)){                          //IF UUID Exists then update the entry in TB_CDR and
            Cdr latestCdr = cdrRepository.findByUuid(cdr.getUuid()).get();      //create new Entry in  TB_CDR_HISTORY table with the new action
            cdr.setCdrId(latestCdr.getCdrId());
            cdrRepository.save(cdr);
            cdrHistoryRepository.save(new CdrHistory(cdr));
        }
        else if(ErrorCode.PBX_NAME_NOT_FOUND.equals(result)){
            throw new PbxNotFoundException(new ExceptionResponse("No PBX with operation name: "
                    + cdr.getDomain_name() + " found.", ErrorCode.PBX_NAME_NOT_FOUND, new Date()));
        }
    }

}
