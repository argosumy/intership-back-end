package com.spd.baraholka.image.exceptionhandler;

import com.spd.baraholka.image.validation.ErrorDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorDto>> handleConstraintValidationException(ConstraintViolationException cve,
                                                                              HttpServletRequest request) {
        List<ErrorDto> errors = new ArrayList<>();

        for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
            ErrorDto errorDto = new ErrorDto(
                    HttpStatus.BAD_REQUEST.value(),
                    cv.getMessage(),
                    LocalDateTime.now(),
                    request.getServletPath()
            );

            errors.add(errorDto);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDto> handleConstraintValidationException(MaxUploadSizeExceededException muse,
                                                                              HttpServletRequest request) {

        String message = "Maximum upload size exceeded; The image exceeds its maximum permitted size of 5 Megabytes.";
        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                message,
                LocalDateTime.now(),
                request.getServletPath()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDto> handleConstraintValidationException(EmptyResultDataAccessException erdae,
                                                                        HttpServletRequest request) {

        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "Entity doesn't exist.",
                LocalDateTime.now(),
                request.getServletPath()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}

