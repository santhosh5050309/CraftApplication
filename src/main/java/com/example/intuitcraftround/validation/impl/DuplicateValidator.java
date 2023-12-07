package com.example.intuitcraftround.validation.impl;

import com.example.intuitcraftround.validation.TreeConstructionValidator;

import java.util.HashSet;
import java.util.Set;

import static com.example.intuitcraftround.constants.TreeConstructorConstants.NULL_IDENTIFIER;


public class DuplicateValidator implements TreeConstructionValidator {
    @Override
    public void validate(String[] data) throws IllegalArgumentException {
        Set<String> uniqueElements = new HashSet<>();
        for (String element : data) {
            if (!element.equals(NULL_IDENTIFIER) && !uniqueElements.add(element)) {
                throw new IllegalArgumentException("Duplicate element found: " + element);
            }
        }
    }
}
