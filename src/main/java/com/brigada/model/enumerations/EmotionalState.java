package com.brigada.model.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Эмоциональное состояние.
 */
@AllArgsConstructor
@Getter
public enum EmotionalState {

    /**
     * Покой, умиротворение и метамедитация.
     */
    CHILL("chill", 0),

    /**
     * Обычный спок.
     */
    STABLE("stable", 1),

    /**
     * Постойте, что-то не так.
     */
    CONFUSED("confused", 2),

    /**
     * Мне страшно...
     */
    STRESSED("stressed", 3),

    /**
     * Что происходит?! Я схожу с ума!
     */
    MAD("mad", 4);

    /**
     * Строковое представление эмоционального состояния.
     */
    private final String title;
    /**
     * Уровень эмоционального состояния. Чем больше, тем хуже.
     */
    private final int level;

    /**
     * Получить эмоциональное состояние по уровню.
     *
     * @param level Уровень эмоционального состояния.
     * @return Если представленный уровень меньше или больше допустимого, будут возвращены минимальное или максимальное значения соотвтетсвенно. Иначе вернется состояне, соответствующее переданному уровню.
     */
    public static EmotionalState getStateByLevel(int level) {
        if (level > EmotionalState.MAD.getLevel()) {
            return EmotionalState.MAD;
        } else if (level < EmotionalState.CHILL.getLevel()) {
            return EmotionalState.CHILL;
        }
        EmotionalState requestedState = null;
        for (EmotionalState state : EmotionalState.values()) {
            if (state.getLevel() == level) {
                requestedState = state;
            }
        }
        return requestedState;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
