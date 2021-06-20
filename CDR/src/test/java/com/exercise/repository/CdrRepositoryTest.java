package com.exercise.repository;

import com.exercise.constants.Constants;
import com.exercise.core.constants.ErrorCode;
import com.exercise.core.utils.CoreUtils;
import com.exercise.entities.Cdr;
import com.exercise.exceptions.CdrNotFoundException;
import com.exercise.utils.TestUtils;
import com.exercise.validation.CdrValidation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class CdrRepositoryTest {

    @Autowired
    private CdrRepository underTest;

    @MockBean
    private CoreUtils coreUtils;


    @Test
    void findAnExistingByUUID() throws ParseException {
        //GIVEN
        Cdr givenCdr = TestUtils.getInstance().createNewCdrPOJO();
        underTest.save(givenCdr);

        //WHEN
        Optional<Cdr> expected = underTest.findByUuid(givenCdr.getUuid());

        //THEN
        assertThat(expected.get()).isEqualTo(givenCdr);
    }

    @Test
    void findACdrIDThatDoesNotExists() {
        long cdrID = 55;
        assertThatThrownBy(() -> underTest.findCdrById(cdrID))
                .isInstanceOf(CdrNotFoundException.class)
                .hasMessageContaining("No CRD with ID: " + cdrID);
    }

    @Test
    void operationNameThatNotExists() throws ParseException {
        ErrorCode expected = ErrorCode.PBX_NAME_NOT_FOUND;
        Cdr givenCdr = TestUtils.getInstance().createNewCdrPOJO();
        given(coreUtils.callMicroserviceAPI(anyString(),anyString(), Mockito.any(RequestMethod.class)))
                .willReturn(Constants.FALSE);

        assertThat(expected).isEqualTo(CdrValidation.operationNameExists(coreUtils).apply(givenCdr));
    }



}