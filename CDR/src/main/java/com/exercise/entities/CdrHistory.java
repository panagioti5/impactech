package com.exercise.entities;

import com.exercise.core.constants.Actions;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "TB_CDR_HISTORY")
public class CdrHistory {

    public CdrHistory(){}

    public CdrHistory(Cdr cdr) {
        this.cdr = cdr;
        this.action = cdr.getAction();
    }

    @Id
    @JsonIgnore
    @GeneratedValue
    private long entryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cdr_id")
    private Cdr cdr;

    @Column
    @Enumerated(EnumType.STRING)
    private Actions action;


    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public Cdr getCdr() {
        return cdr;
    }

    public void setCdrAndAction(Cdr cdr) {
        this.cdr = cdr;
        this.action = cdr.getAction();
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }
}
