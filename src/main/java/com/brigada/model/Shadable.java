package com.brigada.model;

/**
 * Тот, кто отбрасывает тень.
 */
public interface Shadable {

    /**
     * Отбросить тень.
     *
     * @return Размер тени, см^2.
     */
    double castShadow();

}
