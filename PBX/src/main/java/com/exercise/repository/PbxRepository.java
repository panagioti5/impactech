package com.exercise.repository;

import com.exercise.core.constants.ErrorCode;
import com.exercise.core.exceptions.ExceptionResponse;
import com.exercise.entities.Pbx;
import com.exercise.exceptions.PbxNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PbxRepository extends JpaRepository<Pbx, Long> {

    Optional<Pbx> findByDomainName(String domainName);

    default Pbx findPbxById(long pbxId) {
        Optional<Pbx> pbx = this.findById(pbxId);
        if(!pbx.isPresent()){
            throw new PbxNotFoundException(new ExceptionResponse("No PBX with ID: " + pbxId + " found", ErrorCode.PBX_ID_NOT_FOUND, new Date()));
        }
        return pbx.get();
    }
}
