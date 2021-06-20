package com.exercise.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_SYNC_HISTORY")
public class CdrSyncHistory {

    @Id
    @Column
    @JsonIgnore
    @GeneratedValue
    private long entryId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column
    private long unmatchedCalls;

    @PrePersist
    void createdAt() {
        this.dateCreated = new Date();
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getUnmatchedCalls() {
        return unmatchedCalls;
    }

    public void setUnmatchedCalls(long unmatchedCalls) {
        this.unmatchedCalls = unmatchedCalls;
    }
}
