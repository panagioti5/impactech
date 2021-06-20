package com.exercise.controller;

import com.exercise.entities.Pbx;
import com.exercise.repository.PbxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PbxController {

    private final PbxRepository pbxRepository;

    @Autowired
    public PbxController(PbxRepository pbxRepository) {
        this.pbxRepository = pbxRepository;
    }

    @PostMapping("/pbx")
    public void createPbx(@RequestBody Pbx pbx){
        pbxRepository.save(pbx);
    }

    @DeleteMapping("/pbx/{pbxId}")
    public void deletePbx(@PathVariable long pbxId){
        Pbx pbx = pbxRepository.findPbxById(pbxId);
        pbxRepository.delete(pbx);
    }

    @PutMapping("/pbx")
    public void updatePbx(@RequestBody Pbx pbx){
        Pbx pbxById = pbxRepository.findPbxById(pbx.getPbxId());
        pbxRepository.save(pbx);
    }

    @GetMapping("/pbx/{pbxId}")
    public Pbx findPbxById(@PathVariable long pbxId){
        return pbxRepository.findPbxById(pbxId);
    }

    @GetMapping("/pbx/domain_name/{domainName}")
    public boolean isDomainNameExists(@PathVariable String domainName){
        return pbxRepository.findByDomainName(domainName).isPresent();
    }

}
