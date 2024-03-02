package com.brigada.model.enumerations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class EmotionalStateTest {

    private List<EmotionalState> emotionalStates;

    @BeforeEach
    @DisplayName("Initialize a list of emotional states values")
    public void initEmotionalStates() {
        this.emotionalStates = List.of(EmotionalState.values());
    }

    @Test
    @DisplayName("Check if enum contains valid values")
    public void checkIfEnumContainsValidValues() {
        String[] expectedEmotionalStates = {"chill", "stable", "confused", "stressed", "mad"};
        String[] actualEmotionalStates = emotionalStates.stream()
                .map(EmotionalState::getTitle)
                .toArray(String[]::new);
        Arrays.sort(expectedEmotionalStates);
        Arrays.sort(actualEmotionalStates);
        assertArrayEquals(expectedEmotionalStates, actualEmotionalStates);
    }

    @ParameterizedTest
    @EnumSource(EmotionalState.class)
    @DisplayName("Check if toString, getTitle and name methods returns equivalent values")
    public void strRepresentationAndTitleAndNameToLowerShouldBeEquivalent(EmotionalState state) {
        assertAll(
                "toString, getTitle, nameToLower",
                () -> assertEquals(state.getTitle(), state.toString()),
                () -> assertEquals(state.getTitle(), state.name().toLowerCase()),
                () -> assertEquals(state.toString(), state.name().toLowerCase())
        );
    }

    @Test
    @DisplayName("Check if enum match to it level")
    public void whenLevelFromEnumThenEnumFromLevel() {
        assertAll("toString, getTitle, nameToLower is Equivalent",
                () -> emotionalStates.forEach(val -> {
                    assertEquals(val, EmotionalState.getStateByLevel(val.getLevel()));
                })
        );
    }

    @Test
    @DisplayName("Check that out of range level leads to use extreme values")
    public void whenLevelOutOfRangeThenExtremeEnum() {
        int min = emotionalStates
                .stream()
                .map(EmotionalState::getLevel)
                .min(Integer::compareTo)
                .get();
        int max = emotionalStates
                .stream()
                .map(EmotionalState::getLevel)
                .max(Integer::compareTo)
                .get();
        assertAll("Level is out of range",
                () -> assertEquals(EmotionalState.getStateByLevel(max), EmotionalState.getStateByLevel(max + 1)),
                () -> assertEquals(EmotionalState.getStateByLevel(min), EmotionalState.getStateByLevel(min - 1))
        );
    }

    @Test
    @DisplayName("Check that level in allowed range returns different enums")
    public void whenLevelInRangeThenDifferentEnum() {
        int min = emotionalStates
                .stream()
                .map(EmotionalState::getLevel)
                .min(Integer::compareTo)
                .get();
        int max = emotionalStates
                .stream()
                .map(EmotionalState::getLevel)
                .max(Integer::compareTo)
                .get();
        Set<EmotionalState> states = new TreeSet<>();
        for (int level = min; level <= max; level++) {
            EmotionalState state = EmotionalState.getStateByLevel(level);
            assertTrue(states.add(state));
        }
    }

}