package com.eraasoft.spring.config;

import com.eraasoft.spring.controller.vm.ExceptionResponse;
import com.eraasoft.spring.helper.BundleMessage;
import com.eraasoft.spring.service.bundlemessage.BundleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionConfig {

   @Autowired
   private BundleMessageService  bundleMessageService;

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ExceptionResponse> handleGenericException(Exception exception) {
       String message = bundleMessageService.getMessage(exception.getMessage());
       return ResponseEntity.badRequest().body(new ExceptionResponse(message));
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<BundleMessage>> handleValidationException(MethodArgumentNotValidException exception) {

        List<BundleMessage> bundleMessages = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            //exceptionResponses.add(new ExceptionResponse(fieldError.getDefaultMessage ()));
            String key = fieldError.getDefaultMessage();
            BundleMessage bundleMessage = new BundleMessage();
            bundleMessage.setMessage(bundleMessageService.getMessage(key));
            bundleMessages.add(bundleMessage);
        });

        return ResponseEntity.badRequest().body(bundleMessages);
    }
}
