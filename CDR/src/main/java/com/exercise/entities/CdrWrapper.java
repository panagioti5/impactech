package com.exercise.entities;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CdrWrapper {
    private List<Cdr> cdrs;

    public List<Cdr> getCdrs() {
        return cdrs;
    }

    public void setCdrs(List<Cdr> cdrs) {
        this.cdrs = cdrs;
    }
}
