package com.test.solarsystem.controller;

import com.test.solarsystem.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
class ControllerAdvisorTest {

    @InjectMocks
    ControllerAdvisor controllerAdvisor;

    @Test
    void givenExceptionWhenHandleExceptionThenReturnInternalServerErrorResponseEntity() {
        ResponseEntity<Object> response = controllerAdvisor.handleException(new Exception("Test exception"));

        assertThat(HttpStatus.INTERNAL_SERVER_ERROR).isEqualTo(response.getStatusCode());
    }

    @Test
    void givenRuntimeExceptionWhenHandleRuntimeExceptionThenReturnInternalServerErrorResponseEntity() {
        ResponseEntity<Object> response = controllerAdvisor.handleRuntimeException(new RuntimeException("Test Runtime exception"));

        assertThat(HttpStatus.INTERNAL_SERVER_ERROR).isEqualTo(response.getStatusCode());
    }

    @Test
    void givenThrowableWhenHandleThrowableThenReturnInternalServerErrorResponseEntity() {
        ResponseEntity<Object> response = controllerAdvisor.handleThrowable(new RuntimeException("Test Throwable"));

        assertThat(HttpStatus.INTERNAL_SERVER_ERROR).isEqualTo(response.getStatusCode());
    }

    @Test
    void givenBadRequestExceptionWhenHandleBadRequestExceptionThenReturnBadRequestResponseEntity() {
        ResponseEntity<Object> response = controllerAdvisor.handleBadRequestException(new BadRequestException("Exception"));

        assertThat(HttpStatus.BAD_REQUEST).isEqualTo(response.getStatusCode());
    }

}