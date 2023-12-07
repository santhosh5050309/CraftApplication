package com.example.intuitcraftround.validation.impl;

import com.example.intuitcraftround.validation.TreeConstructionValidator;

import static com.example.intuitcraftround.constants.TreeConstructorConstants.NULL_IDENTIFIER;

public class FormatValidator implements TreeConstructionValidator {
    @Override
    public void validate(String[] data) throws IllegalArgumentException {
        for (String element : data) {
            if (!element.equals(NULL_IDENTIFIER)) {
                try {
                    Integer.parseInt(element);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid input: " + element +
                            " is not a number or " + NULL_IDENTIFIER);
                }
            }
        }
    }
}
