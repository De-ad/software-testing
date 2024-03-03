package com.brigada.model;

import com.brigada.model.enumerations.EmotionalState;
import com.brigada.model.enumerations.SmellType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    private final String validName = "Maksim";
    private final double validHeight = Human.MAX_HEIGHT;
    private final double validWeight = Human.MAX_WEIGHT;

    private Human aliveHuman;
    private Human deadHuman;

    @BeforeEach
    public void initHumans() {
        aliveHuman = new Human(validName, validHeight, validWeight);
        deadHuman = new Human(validName, validHeight + 1, validWeight);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    @DisplayName("IllegalArgument exception after invalid name in constructor")
    public void whenInvalidNameThenExpectedIllegalArgumentException(String invalidName) {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Human(invalidName, validHeight, validWeight)
        );
        assertEquals("Invalid name", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, -5d, 0d, Double.POSITIVE_INFINITY, Double.NaN})
    @DisplayName("IllegalArgument exception after invalid height in constructor")
    public void whenInvalidHeightThenExpectedIllegalArgumentException(double invalidHeight) {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Human(validName, invalidHeight, validWeight)
        );
        assertEquals("Height must be positive non-inf value", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, -5d, 0d, Double.POSITIVE_INFINITY, Double.NaN})
    @DisplayName("IllegalArgument exception after invalid weight in constructor")
    public void whenInvalidWeightThenExpectedIllegalArgumentException(double invalidWeight) {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Human(validName, validHeight, invalidWeight)
        );
        assertEquals("Weight must be positive non-inf value", exception.getMessage());
    }

    @Test
    @DisplayName("Human dies when height is greater than allowed")
    public void whenHeightMoreThanMaximumThenDead() {
        Human human = new Human(validName, Human.MAX_HEIGHT + 1, validWeight);
        assertTrue(human.isDead());
    }

    @Test
    @DisplayName("Human dies when weight is greater than allowed")
    public void whenWeightMoreThanMaximumThenDead() {
        Human human = new Human(validName, validHeight, Human.MAX_WEIGHT + 1);
        assertTrue(human.isDead());
    }

    @Test
    @DisplayName("CompareTo test")
    public void compareToTest() {
        Human copy = new Human(aliveHuman.getName(), aliveHuman.getHeight(), aliveHuman.getWeight());
        Human lessWeight = new Human(aliveHuman.getName(), aliveHuman.getHeight(), aliveHuman.getWeight() - 1);
        Human lessHeight = new Human(aliveHuman.getName(), aliveHuman.getHeight() - 1, aliveHuman.getWeight());
        assertAll(
                () -> assertEquals(0, aliveHuman.compareTo(copy)),
                () -> assertEquals(1, aliveHuman.compareTo(lessWeight)),
                () -> assertEquals(-1, lessWeight.compareTo(aliveHuman)),
                () -> assertEquals(0, aliveHuman.compareTo(lessHeight)),
                () -> assertEquals(aliveHuman.compareTo(copy) == 0, aliveHuman.equals(copy))
        );
    }

    @Test
    @DisplayName("NullPointerException exception after setting emotional state as null")
    public void whenChangeEmotionalStateToNullThenExpectedNullPointerException() {
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> aliveHuman.setEmotionalState(null)
        );
        assertEquals("Emotional state can't be null", exception.getMessage());
    }

    @Test
    @DisplayName("Smell determines the emotional state")
    public void whenChangeSmellThenChangeEmotionalState() {
        assertAll(
                () -> {
                    aliveHuman.setEmotionalState(EmotionalState.STABLE);
                    aliveHuman.detectSmell(SmellType.WEED);
                    assertEquals(EmotionalState.CHILL, aliveHuman.getEmotionalState());
                },
                () -> {
                    aliveHuman.setEmotionalState(EmotionalState.STRESSED);
                    aliveHuman.detectSmell(SmellType.SOFT);
                    assertEquals(EmotionalState.STABLE, aliveHuman.getEmotionalState());
                },
                () -> {
                    aliveHuman.setEmotionalState(EmotionalState.STABLE);
                    aliveHuman.detectSmell(SmellType.BURNING);
                    assertEquals(EmotionalState.CONFUSED, aliveHuman.getEmotionalState());
                },
                () -> {
                    aliveHuman.setEmotionalState(EmotionalState.STABLE);
                    aliveHuman.detectSmell(SmellType.PUTRID);
                    assertEquals(EmotionalState.STRESSED, aliveHuman.getEmotionalState());
                }
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("NullPointerException exception after detecting smell type as null")
    public void whenChangeSmellToNullThenExpectedNullPointerException(SmellType smellType) {
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> aliveHuman.detectSmell(smellType)
        );
        assertEquals("Smell cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("If weight is enough and emotional state is mad, then human will loss weight")
    public void whenEmotionalStateIsMadAndWeightIsEnoughThenLooseWeight() {
        double actual = Human.STRESS_WEIGHT_LOSS + 0.1d;
        double expected = actual - Human.STRESS_WEIGHT_LOSS;

        aliveHuman.setWeight(actual);
        aliveHuman.setEmotionalState(EmotionalState.MAD);
        assertEquals(expected, aliveHuman.getWeight());
    }

    @Test
    @DisplayName("If weight isn't enough and emotional state is mad, then human will die")
    public void whenEmotionalStateIsMadAndWeightIsNotEnoughThenDie() {
        double actual = Human.STRESS_WEIGHT_LOSS;

        aliveHuman.setWeight(actual);
        aliveHuman.setEmotionalState(EmotionalState.MAD);

        assertTrue(aliveHuman.isDead());
    }

    @Test
    @DisplayName("Shadow casting for dead and alive humans is different")
    public void castShadowTest() {
        assertAll("Alive and dead has different ways to calculate shadow",
                () -> assertEquals(aliveHuman.getHeight() * aliveHuman.getWeight() * 3 / 5, aliveHuman.castShadow()),
                () -> assertEquals(2 * (deadHuman.getHeight() + deadHuman.getWeight()), deadHuman.castShadow())
        );

    }

    @Test
    @DisplayName("You can’t change dead's state")
    public void whenIsDeadThenCantChangeState() {
        assertAll(
                () -> {
                    double expected = deadHuman.getHeight();
                    deadHuman.setHeight(deadHuman.getHeight() - 1);
                    assertEquals(expected, deadHuman.getHeight());
                },
                () -> {
                    double expected = deadHuman.getWeight();
                    deadHuman.setWeight(deadHuman.getWeight() - 1);
                    assertEquals(expected, deadHuman.getWeight());
                },
                () -> {
                    EmotionalState expected = deadHuman.getEmotionalState();
                    deadHuman.setEmotionalState(EmotionalState.CHILL);
                    assertEquals(expected, deadHuman.getEmotionalState());
                }
        );
    }

}