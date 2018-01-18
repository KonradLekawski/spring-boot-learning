package com.kodekul.kondrat.bfsword.global;

import com.kodekul.kondrat.bfsword.global.error.ApiError;
import com.kodekul.kondrat.bfsword.global.exception.IdNotExistsException;
import com.kodekul.kondrat.bfsword.global.exception.IncompleteDataException;
import com.kodekul.kondrat.bfsword.logger.LogerInfo;
import com.kodekul.kondrat.bfsword.logger.LoggerInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private LoggerInterface logger;

    public RestExceptionHandler(LoggerInterface logger) {
        this.logger = logger;
    }

    @ExceptionHandler(IdNotExistsException.class)
    protected ResponseEntity<ApiError> handleIdNotExist (IdNotExistsException ex) {
        ApiError apiError = new ApiError("Id not exists", "CLIENT SIDE");
        logger.logError(LogerInfo.ID_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ApiError> handleNoSuchElementException (NoSuchElementException ex) {
        ApiError apiError = new ApiError("Element not Found", "NOT FOUND");
        logger.logError(LogerInfo.ELEMENT_NOT_FOUND );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(IncompleteDataException.class)
    protected ResponseEntity<ApiError> handleIncompleteDataException (IncompleteDataException ex) {
        ApiError apiError = new ApiError("Json incomplete", "INCOMPLETE DATA");
        logger.logError(LogerInfo.JSON_DATA_INCOMPLETE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


}