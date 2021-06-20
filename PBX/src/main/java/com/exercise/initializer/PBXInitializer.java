package com.exercise.initializer;

import com.exercise.core.constants.Constants;
import com.exercise.entities.Pbx;
import com.exercise.repository.PbxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PBXInitializer {

    @Autowired
    private  PbxRepository pbxRepository;

    //When PBX Microservice start create mockpbx.impactpbx.com entry on TB_PBX table
    @EventListener
    public void onMicroserviceStart(ApplicationReadyEvent e){
         if(!pbxRepository.findByDomainName(Constants.IMPACTECH_DOMAIN).isPresent()){
             Pbx impachTechPbx = new Pbx();
             impachTechPbx.setDomainName(Constants.IMPACTECH_DOMAIN);
             pbxRepository.save(impachTechPbx);
         }
        //  JAVA  9
//                .ifPresentOrElse(
//                        (pbx)
//                                -> {return;},
//                        ()
//                        -> {
//                            Pbx impachTechPbx = new Pbx();
//                            impachTechPbx.setDomainName(Constants.IMPACTECH_DOMAIN);
//                            pbxRepository.save(impachTechPbx);
//                        }
//                );
    }
}
