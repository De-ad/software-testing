package com.brigada.model;

import com.brigada.model.enumerations.EmotionalState;
import com.brigada.model.enumerations.SmellType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private Location location;
    private List<Human> humans;

    @BeforeEach
    public void initLocation() {
        location = new Location("Red room");
        humans = List.of(
                new Human("Foo", 250, 540),
                new Human("Bar", 101, 301),
                new Human("Baz", 277, 411)
        );

        for (Human human : humans) {
            location.appendResident(human);
        }
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    public void whenInvalidNameThenExpectedIllegalArgumentException(String invalidName) {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Location(invalidName)
        );
        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    public void whenValidNameThenExpectedGetName() {
        String expectedName = "Black room";
        location.setName(expectedName);
        assertEquals(expectedName, location.getName());
    }

    @Test
    public void whenAppendTwiceThenReject() {
        Human human = humans.get(0);
        assertFalse(location.appendResident(human));
    }

    @Test
    public void whenRemoveTwiceThenReject() {
        Human human = humans.get(0);
        location.removeResident(human);
        assertFalse(location.removeResident(human));
    }

    @Test
    public void castShadowsTest() {

        Double[] expectedShadows = humans.stream()
                .map(Human::castShadow)
                .sorted()
                .toArray(Double[]::new);

        Double[] actualShadows = location.findAllShadows().toArray(new Double[0]);
        assertArrayEquals(expectedShadows, actualShadows);
    }

    @Test
    public void whenSpreadNullThenExpectedNullPointerException() {
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> location.spreadSmell(null)
        );
        assertEquals("Smell cannot be null", exception.getMessage());
    }

    @Test
    public void whenSpreadWeedThenAllOfSmellSensibleFeelChill() {
        for (Human human : humans) {
            human.setEmotionalState(EmotionalState.STRESSED);
        }

        location.spreadSmell(SmellType.WEED);

        humans.forEach((human) -> assertEquals(EmotionalState.CHILL, human.getEmotionalState()));
    }

    @Test
    public void whenSpreadSoftThenAllOfSmellSensibleFeelStable() {
        for (Human human : humans) {
            human.setEmotionalState(EmotionalState.STRESSED);
        }

        location.spreadSmell(SmellType.SOFT);

        humans.forEach((human) -> assertEquals(EmotionalState.STABLE, human.getEmotionalState()));
    }

    @Test
    public void whenSpreadBurningThenAllOfSmellSensibleIncreaseTheirStable() {
        for (Human human : humans) {
            human.setEmotionalState(EmotionalState.STABLE);
        }

        location.spreadSmell(SmellType.BURNING);

        humans.forEach((human) -> assertEquals(
                EmotionalState.getStateByLevel(EmotionalState.STABLE.getLevel() + 1),
                human.getEmotionalState()
        ));
    }

    @Test
    public void whenSpreadPutridThenAllOfSmellSensibleIncreaseTheirStableTwice() {
        for (Human human : humans) {
            human.setEmotionalState(EmotionalState.STABLE);
        }

        location.spreadSmell(SmellType.PUTRID);

        humans.forEach((human) -> assertEquals(
                EmotionalState.getStateByLevel(EmotionalState.STABLE.getLevel() + 2),
                human.getEmotionalState()
        ));
    }

    @Test
    public void whenResidentIsDeadAllOfSmellSensibleIncreaseTheirStableAtLeastTwice() {
        location.spreadSmell(SmellType.WEED);

        Human shaggy = new Human("Шегги", 205, 74);
        shaggy.setEmotionalState(EmotionalState.STRESSED);
        location.appendResident(shaggy);

        int beforeSpreading = location.getResidents().size();
        location.spreadSmell(SmellType.BURNING);

        assertAll(
                () -> assertEquals(beforeSpreading - 1, location.getResidents().size()),
                () -> {
                    humans.forEach((human) -> assertEquals(
                            EmotionalState.getStateByLevel(EmotionalState.CHILL.getLevel() + 3),
                            human.getEmotionalState()
                    ));
                }
        );
    }

}