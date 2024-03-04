package com.brigada.model;

import com.brigada.model.enumerations.SmellType;

/**
 * Тот, кто обладает обонянием.
 */
public interface SmellSensible {
    /**
     * Учуять запах.
     *
     * @param smellType Запах, поступивший на органы чувств.
     */
    void detectSmell(SmellType smellType);

}
