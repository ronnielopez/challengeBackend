package com.example.challenge2.controllers;

import com.example.challenge2.models.Card;
import com.example.challenge2.models.ResponseBodyDTO;
import com.example.challenge2.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class creditCardController {
    @Autowired
    ValidationService validationService;
    @RequestMapping(value = "/creditCard", method = RequestMethod.POST)
    public ResponseEntity<?> creditCard(
        @RequestBody @Validated Card cardRequest // request body
    ) {
        Map<String, Object> isValid = validationService.validateCard(cardRequest);
        System.out.println(cardRequest.toString());
        HttpStatus status = HttpStatus.OK;
        ResponseBodyDTO<Card> responseBody = new ResponseBodyDTO<Card>();
        // Validate the card
        if (!isValid.get("isValid").equals(true)) {
            status = HttpStatus.BAD_REQUEST;
            responseBody.setStatus(status);
            responseBody.setMessage(isValid.get("message").toString());
            responseBody.setData(cardRequest);
            return ResponseEntity.status(status).body(responseBody);
        }
        responseBody.setStatus(status);
        responseBody.setMessage("Success");
        responseBody.setData(cardRequest);
        return ResponseEntity.status(status).body(responseBody);
    }
}
