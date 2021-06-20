package com.exercise.repository;

import com.exercise.core.constants.ErrorCode;
import com.exercise.core.exceptions.ExceptionResponse;
import com.exercise.entities.Cdr;
import com.exercise.exceptions.CdrNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface CdrRepository extends JpaRepository<Cdr,Long> {

    default Cdr findCdrById(long cdrId) {
        Optional<Cdr> cdr = this.findById(cdrId);
        if(!cdr.isPresent()){
            throw new CdrNotFoundException(new ExceptionResponse("No CRD with ID: " + cdrId + " found", ErrorCode.CDR_NOT_FOUND, new Date()));
        }
        return cdr.get();
    }

    Optional<Cdr> findByUuid(String domainName);
}
