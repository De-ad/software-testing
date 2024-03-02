package com.brigada.model.enumerations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmellTypeTest {

    private List<SmellType> smellTypes;

    @BeforeEach
    @DisplayName("Initialize a list of smell type values")
    public void initSmellTypes() {
        this.smellTypes = List.of(SmellType.values());
    }

    @Test
    @DisplayName("Check if enum contains valid values")
    public void checkIfEnumContainsValidValues() {
        String[] expectedSmellTypes = {"soft", "burning", "weed", "putrid"};
        String[] actualSmellTypes = smellTypes.stream()
                .map(SmellType::getTitle)
                .toArray(String[]::new);
        Arrays.sort(expectedSmellTypes);
        Arrays.sort(actualSmellTypes);
        assertArrayEquals(expectedSmellTypes, actualSmellTypes);
    }

    @ParameterizedTest
    @EnumSource(SmellType.class)
    @DisplayName("Check if toString, getTitle and name methods returns equivalent values")
    public void strRepresentationAndTitleAndNameToLowerShouldBeEquivalent(SmellType type) {
        assertAll(
                "toString, getTitle, nameToLower",
                () -> assertEquals(type.getTitle(), type.toString()),
                () -> assertEquals(type.getTitle(), type.name().toLowerCase()),
                () -> assertEquals(type.toString(), type.name().toLowerCase())
        );
    }

}