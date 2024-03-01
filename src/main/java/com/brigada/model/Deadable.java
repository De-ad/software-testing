package com.brigada.model;

/**
 * Тот, кто смертен.
 */
public interface Deadable {

    /**
     * Умереть.
     */
    void dead();

    /**
     * Узнать, мёртв ли смертный.
     *
     * @return Мёртв ли смертный?
     */
    boolean isDead();

}
