package com.example.challenge2.service;

import com.example.challenge2.models.Card;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationService {

    public Map<String, Object> validateCard(Card card) {
        Map<String, Object> validationResult = new HashMap<>();

        // Validate the card number
        if (!validateCardNumber(card.getCardNumber())) {
            validationResult.put("isValid", false);
            validationResult.put("message", "Invalid card number");
            return validationResult;
        }

        // Validate the CVC based on card type
        if (!validateCvc(card.getCardNumber(), card.getCvc(), card.getIssuer())) {
            validationResult.put("isValid", false);
            validationResult.put("message", "Invalid CVC");
            return validationResult;
        }

        // Validate the month and year
        if (!validateMonth(card.getMonth(), card.getYear())) {
            validationResult.put("isValid", false);
            validationResult.put("message", "Card expired");
            return validationResult;
        }

        // Validate the card holder name
        if (!validateCardHolderName(card.getCardHolderName())) {
            validationResult.put("isValid", false);
            validationResult.put("message", "Invalid card holder name");
            return validationResult;
        }

        // Validate Luhn check
        if (!luhnCheck(card.getCardNumber())) {
            validationResult.put("isValid", false);
            validationResult.put("message", "Invalid card number by luhn check");
            return validationResult;
        }

        // All validations passed, the card is valid
        validationResult.put("isValid", true);
        validationResult.put("message", "Card is valid");
        return validationResult;
    }

    public boolean validateCardNumber(String cardNumber) {
        return cardNumber.matches("^[0-9]{16,19}$") && luhnCheck(cardNumber);
    }

    public boolean validateCvc(String cardNumber, String cvc, String issuer) {
        if (cardNumber.startsWith("34") || cardNumber.startsWith("37") || issuer.equals("amex")) {
            return cvc.matches("^[0-9]{4}$");
        } else {
            return cvc.matches("^[0-9]{3}$");
        }
    }

    public boolean validateMonth(String month, String year) {
        // Get the current year and month
        int currentYear = java.time.Year.now().getValue();
        int currentMonth = java.time.Month.from(java.time.LocalDate.now()).getValue();
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        // Check if the card's expiration year and month are after or equal to the current year and month
        return (yearInt > currentYear) || (yearInt == currentYear && monthInt >= currentMonth);
    }

    public boolean validateCardHolderName(String cardHolderName) {
        return cardHolderName.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    private boolean luhnCheck(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
