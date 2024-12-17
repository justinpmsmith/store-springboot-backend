package com.allianceseeds.api.domain.validation;

import com.allianceseeds.api.domain.exceptions.InvalidEmailException;
import com.allianceseeds.api.domain.exceptions.InvalidHexException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Validation {

    public static void validateHex(String inputString) {
        try {
            inputString = inputString.replace(":", "");
            BigInteger bi = new BigInteger(inputString, 16);
        } catch (NumberFormatException e) {
            System.out.println("invalid hex exception");
            System.out.println(e);


            throw new InvalidHexException();
        }
    }

    public static void validateEmail(String email){
        if (!isEmailValid(email)) {
            System.out.println("Invalid email");

            throw new InvalidEmailException();
        }
    }
    private static boolean isEmailValid(String input) {
        // Return false if the input is null or empty
        if (input == null || input.isEmpty()) {
            return false;
        }

        // Regex to validate general email format
        // Explanation:
        // - Starts with alphanumeric characters (plus dots, hyphens, or underscores)
        // - Followed by an "@" symbol
        // - Then a valid domain structure (e.g., domain.com or domain.co.za)


        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!input.matches(emailRegex)) {
            return false; // Input is not in valid email format
        }
        // Define the valid domains we expect
        List<String> validDomains = Arrays.asList("accutrak.co.za");

        // Extract the domain part of the email
        String domain = input.substring(input.lastIndexOf("@") + 1);


        // Check if the input email ends with a valid domain
        return validDomains.contains(domain);
    }



    public static boolean isAlphanumeric(String str) {
        return str != null && str.matches("^[a-zA-Z0-9]+$");
    }
}
