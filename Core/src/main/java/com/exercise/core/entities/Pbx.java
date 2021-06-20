package com.exercise.core.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Pbx {

    @Id
    @GeneratedValue
    @Column
    private long pbxId;

    @Column
    private String domainName;

    public long getPbxId() {
        return pbxId;
    }

    public void setPbxId(long pbxId) {
        this.pbxId = pbxId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
