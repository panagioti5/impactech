package com.exercise.entities;

import com.exercise.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_CDR")
public class Cdr extends com.exercise.core.entities.Cdr {

    @PrePersist
    private void prePersistFunction(){
        Utils.getInstance().convertEnumsToUpperCase(this);
    }

    @PreUpdate
    public void preUpdateFunction(){
        Utils.getInstance().convertEnumsToUpperCase(this);
    }

    @OneToMany(mappedBy = "cdr")
    @JsonIgnore
    private List<CdrHistory> cdrHistory;

    public List<CdrHistory> getCdrHistory() {
        return cdrHistory;
    }

    public void setCdrHistory(List<CdrHistory> cdrHistory) {
        this.cdrHistory = cdrHistory;
    }

}
