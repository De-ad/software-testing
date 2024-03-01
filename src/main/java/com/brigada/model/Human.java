package com.brigada.model;

import com.brigada.model.enumerations.EmotionalState;
import com.brigada.model.enumerations.SmellType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Человек.
 */
@Getter
@EqualsAndHashCode
public class Human implements Shadable, SmellSensible, Deadable, Comparable<Human> {

    /**
     * Имя.
     */
    private String name;

    /**
     * Рост, см.
     */
    private double height;

    /**
     * Вес, кг.
     */
    private double weight;

    /**
     * Эмоциональное состояние. По умолчанию стабильное.
     */
    private EmotionalState emotionalState = EmotionalState.STABLE;

    /**
     * А живой ли, собственно?
     */
    @Getter(AccessLevel.NONE)
    private boolean isAlive = true;

    /**
     * Создает человека.
     *
     * @param name   Имя человека.
     * @param height Рост человека.
     * @param weight Вес человека.
     */
    public Human(String name, double height, double weight) {
        setName(name);
        setHeight(height);
        setWeight(weight);
    }

    /**
     * Увеличить (ухудшить) уровень {@link EmotionalState эмоционального состояния}. {@link Deadable Мертвых} не трогаем.
     */
    private void increaseEmotionalState() {
        if (isDead()) {
            return;
        }
        int nextLevel = this.emotionalState.getLevel() + 1;
        setEmotionalState(EmotionalState.getStateByLevel(nextLevel));
    }

    /**
     * Потерять вес. Если веса недостаточно, {@link Deadable смерть}. {@link Deadable Мертвых} не трогаем.
     */
    private void looseWeight() {
        if (isDead()) {
            return;
        }
        double loosedWeight = this.weight - 100;
        if (loosedWeight <= 0) {
            dead();
        } else {
            setWeight(loosedWeight);
        }
    }

    /**
     * Установить имя. {@link Deadable Мертвых} не трогаем.
     *
     * @param name Имя.
     */
    public void setName(String name) {
        if (isDead()) {
            return;
        }
        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    /**
     * Установить рост. {@link Deadable Мертвых} не трогаем.
     *
     * @param height Рост. Слишком длинный умирает.
     */
    public void setHeight(double height) {
        if (isDead()) {
            return;
        }
        if (height <= 0 || height == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("Height must be positive non-inf value");
        } else if (height >= 300d) {
            dead();
        } else {
            this.height = height;
        }
    }

    /**
     * Установить вес. {@link Deadable Мертвых} не трогаем.
     *
     * @param weight Вес. Слишком полный умирает.
     */
    public void setWeight(double weight) {
        if (isDead()) {
            return;
        }
        if (weight <= 0 || weight == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("Weight must be positive non-inf value");
        } else if (weight > 700) {
            dead();
        } else {
            this.weight = weight;
        }
    }

    /**
     * Установить эмоциональное состояние. {@link Deadable Мертвых} не трогаем.
     *
     * @param emotionalState Эмоциональное состояние. {@link EmotionalState Обезумевшие} теряют вес.
     */
    public void setEmotionalState(EmotionalState emotionalState) {
        if (isDead()) {
            return;
        }
        if (emotionalState == null) {
            throw new NullPointerException("Emotional state can't be null");
        }
        if (emotionalState.equals(EmotionalState.MAD)) {
            looseWeight();
        }
        this.emotionalState = emotionalState;
    }

    /**
     * Отбросить тень
     *
     * @return Размер тени, см^2. {@link Deadable Живой} отбрасывает 3/5 рост * вес, {@link Deadable мертвый} - по периметру: 2 * (рост + вес)
     */
    @Override
    public double castShadow() {
        return isDead() ? 2 * (height + weight) : height * weight * 3 / 5;
    }

    /**
     * Учуять запах. {@link Deadable Мертвые} не чувствуют.
     *
     * @param smellType Запах, поступивший на органы чувств.
     */
    @Override
    public void detectSmell(SmellType smellType) {
        if (isDead()) {
            return;
        }
        switch (smellType) {
            case WEED:
                setEmotionalState(EmotionalState.CHILL);
                break;
            case SOFT:
                setEmotionalState(EmotionalState.STABLE);
                break;
            case BURNING:
                increaseEmotionalState();
                break;
            case PUTRID:
                increaseEmotionalState();
                increaseEmotionalState();
                break;
            default:
                throw new IllegalArgumentException("Unknown smell type");
        }
    }

    /**
     * {@link Deadable Умереть}
     */
    @Override
    public void dead() {
        this.isAlive = false;
    }

    /**
     * {@link Deadable Мертв?}
     *
     * @return Мертв ли?
     */
    @Override
    public boolean isDead() {
        return this.isAlive;
    }

    /**
     * Сравнить двух людей.
     *
     * @param o С кем сравнить.
     * @return Толще? -> 1. Стройнее? -> -1. Иначе сравниваем по equals.
     */
    @Override
    public int compareTo(Human o) {
        if (this.equals(o)) {
            return 0;
        } else if (this.weight > o.weight) {
            return 1;
        } else {
            return -1;
        }
    }

}
