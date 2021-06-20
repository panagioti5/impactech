package com.exercise.validation;

import com.exercise.constants.Constants;
import com.exercise.core.constants.ErrorCode;
import com.exercise.core.utils.CoreUtils;
import com.exercise.entities.Cdr;
import com.exercise.repository.CdrRepository;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.function.Function;

public interface CdrValidation extends Function<Cdr, ErrorCode> {

    static CdrValidation operationNameExists(CoreUtils coreUtils){
        StringBuilder path = new StringBuilder("domain_name/");
        return cdr ->{
            String output =  coreUtils.callMicroserviceAPI(Constants.PBX_MICROSERVICE_URI,path.append(cdr.getDomain_name()).toString(), RequestMethod.GET);
            if (Constants.TRUE.equalsIgnoreCase(output)){
                return ErrorCode.SUCCESS;
            }
            else if(Constants.FALSE.equalsIgnoreCase(output)) {
                return ErrorCode.PBX_NAME_NOT_FOUND;
            }
            else{
                return ErrorCode.FAILURE;
            }
        };
    }

    static CdrValidation cdrIdExists(CdrRepository cdrRepository){
        return cdr -> cdrRepository.findById(cdr.getCdrId()).isPresent()
                ? ErrorCode.SUCCESS : ErrorCode.CDR_NOT_FOUND;
    }

    static CdrValidation uuidExists(CdrRepository cdrRepository){
        return cdr -> cdrRepository.findByUuid(cdr.getUuid()).isPresent()
                ? ErrorCode.UUID_EXISTS : ErrorCode.SUCCESS;
    }

    default CdrValidation and (CdrValidation other) {
        return cdr -> {
            ErrorCode result = this.apply(cdr);
            return result.equals(ErrorCode.SUCCESS) ? other.apply(cdr) : result;
        };
    }

}
