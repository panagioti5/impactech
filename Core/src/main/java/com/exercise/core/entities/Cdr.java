package com.exercise.core.entities;

import com.exercise.core.constants.Actions;
import com.exercise.core.constants.Direction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public class Cdr {

    @Id
    @GeneratedValue
    @Column
    private long cdrId;

    @Column
    @NotNull(message = "The call is not authenticated!")
    private String uuid;

    @Column
    @NotNull(message = "Please enter the domain name")
    private String domain_name;

    @Column
    private String caller_name;

    @Column
    @NotNull(message = "Please enter a caller Number")
    private long caller_number;

    @Column
    @NotNull(message = "Please enter a destination Number")
    private long destination_number;

    @Column
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date call_start;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date ring_start;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date answer_start;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date call_end;

    @Column
    private long duration;

    @Column
    private String recording;

    @Column
    private boolean click_to_call;

    @Column
    private String click_to_call_data;

    @Column(name = "latest_action")
    @Enumerated(EnumType.STRING)
    private Actions action;

    public long getCdrId() {
        return cdrId;
    }

    public void setCdrId(long cdrId) {
        this.cdrId = cdrId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }

    public String getCaller_name() {
        return caller_name;
    }

    public void setCaller_name(String caller_name) {
        this.caller_name = caller_name;
    }

    public long getCaller_number() {
        return caller_number;
    }

    public void setCaller_number(long caller_number) {
        this.caller_number = caller_number;
    }

    public long getDestination_number() {
        return destination_number;
    }

    public void setDestination_number(long destination_number) {
        this.destination_number = destination_number;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Date getCall_start() {
        return call_start;
    }

    public void setCall_start(Date call_start) {
        this.call_start = call_start;
    }

    public Date getRing_start() {
        return ring_start;
    }

    public void setRing_start(Date ring_start) {
        this.ring_start = ring_start;
    }

    public Date getAnswer_start() {
        return answer_start;
    }

    public void setAnswer_start(Date answer_start) {
        this.answer_start = answer_start;
    }

    public Date getCall_end() {
        return call_end;
    }

    public void setCall_end(Date call_end) {
        this.call_end = call_end;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public boolean isClick_to_call() {
        return click_to_call;
    }

    public void setClick_to_call(boolean click_to_call) {
        this.click_to_call = click_to_call;
    }

    public String getClick_to_call_data() {
        return click_to_call_data;
    }

    public void setClick_to_call_data(String click_to_call_data) {
        this.click_to_call_data = click_to_call_data;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }
}
