package com.exercise.utils;

import com.exercise.core.constants.Actions;
import com.exercise.core.constants.Direction;
import com.exercise.entities.Cdr;
import com.exercise.entities.CdrSyncHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestUtils {

    private TestUtils() {
    }
    private static TestUtils testUtils;

    public static TestUtils getInstance() {
        if (null == testUtils) {
            return new TestUtils();
        } else {
            return testUtils;
        }
    }

    public Cdr createNewCdrPOJO() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Cdr cdr = new Cdr();
        cdr.setUuid("a65b449c-58aa-409e-8c0b-5e23d5bf5de");
        cdr.setDomain_name("mockpbx.impactpbx.com");
        cdr.setCaller_number(35708173931L);
        cdr.setDestination_number(35706391672L);
        cdr.setDirection(Direction.INBOUND);
        cdr.setCall_start(simpleDateFormat.parse("2021-06-13 21:35:41"));
        cdr.setRing_start(simpleDateFormat.parse("2021-06-13 21:35:45"));
        cdr.setAnswer_start(simpleDateFormat.parse("2021-06-13 21:35:49"));
        cdr.setCall_end(simpleDateFormat.parse("2021-06-13 21:38:45"));
        cdr.setDuration(361L);
        cdr.setRecording("ab983a58-1eb1-4a03-a57f-f599b167041f");
        cdr.setClick_to_call(false);
        cdr.setAction(Actions.HANGUP);
        return cdr;
    }


}
