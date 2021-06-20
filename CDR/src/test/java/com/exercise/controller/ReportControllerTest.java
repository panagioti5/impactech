package com.exercise.controller;

import com.exercise.entities.Cdr;
import com.exercise.entities.MinutesNumber;
import com.exercise.utils.TestUtils;
import com.exercise.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReportControllerTest {

    @Test
    void findNumberOfCompletedAndIcompletedMinutes() throws Exception {

        Cdr completed = TestUtils.getInstance().createNewCdrPOJO();
        Cdr inCompleted = TestUtils.getInstance().createNewCdrPOJO();;
        inCompleted.setAnswer_start(null);
        inCompleted.setDuration(450L);
        List<Cdr> cdrs = new ArrayList<>();
        cdrs.add(completed);
        cdrs.add(inCompleted);

        MinutesNumber expected = new MinutesNumber(completed.getDuration(), inCompleted.getDuration());
        MinutesNumber underTest = Utils.getInstance().getCompletedAndIncompletedMinsCalls(cdrs);

        assertThat(expected.getIncompleteMinutes()).isEqualTo(underTest.getIncompleteMinutes());
        assertThat(expected.getSuccessfulMinutes()).isEqualTo(underTest.getSuccessfulMinutes());
    }
}