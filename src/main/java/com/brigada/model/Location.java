package com.brigada.model;

import com.brigada.model.enumerations.SmellType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Локация.
 */
@Getter
public class Location {

    /**
     * Название локации.
     */
    private String name;

    /**
     * Посетители локации.
     */
    private final TreeSet<Object> residents = new TreeSet<>();

    /**
     * Создает локацию с заданным именем.
     *
     * @param name Имя локации.
     */
    public Location(String name) {
        setName(name);
    }

    /**
     * Добавить посетителя в локацию.
     *
     * @param resident Посетитель.
     * @return True, если посетителя не было в локации, иначе false.
     */
    public boolean appendResident(Object resident) {
        return this.residents.add(resident);
    }

    /**
     * Убрать посетителя из локации.
     *
     * @param resident Посетитель.
     * @return True, если посетитель был в локации, иначе false.
     */
    public boolean removeResident(Object resident) {
        return this.residents.remove(resident);
    }

    /**
     * Обнаружить все тени.
     *
     * @return List занятого тенями пространства.
     */
    public List<Double> findAllShadows() {
        List<Double> shadows = new ArrayList<>();
        for (Object resident : residents) {
            if (resident instanceof Shadable) {
                shadows.add(((Shadable) resident).castShadow());
            }
        }
        return shadows;
    }

    /**
     * Распространение запаха.
     *
     * @param smellType Запах, который будет передан всем, {@link SmellSensible воспринимающим запах}.
     */
    public void spreadSmell(SmellType smellType) {
        for (Object resident : residents) {
            if (resident instanceof SmellSensible) {
                ((SmellSensible) resident).detectSmell(smellType);
            }
        }
        findBodies();
    }

    /**
     * Поиск тел в локации с последующей очисткой. Если тело было найдено, распространяется {@link SmellType запах} гнили.
     */
    private void findBodies() {
        boolean detected = false;
        Set<Deadable> deadResidents = new TreeSet<>();
        for (Object resident : residents) {
            if (resident instanceof Deadable) {
                Deadable deadResident = ((Deadable) resident);
                if (deadResident.isDead()) {
                    detected = true;
                    deadResidents.add(deadResident);
                }

            }
        }
        if (detected) {
            residents.removeAll(deadResidents);
            spreadSmell(SmellType.PUTRID);
        }
    }

    /**
     * Установка имени для локации.
     *
     * @param name Имя локации.
     */
    public void setName(String name) {
        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

}
