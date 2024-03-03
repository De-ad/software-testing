package com.brigada.model.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Виды запахов.
 */
@AllArgsConstructor
@Getter
public enum SmellType {

    /**
     * Сорняк, свежескошенная трава.
     */
    WEED("weed"),

    /**
     * Свежее нижнее бельё, легкий привкус ванили.
     */
    SOFT("soft"),

    /**
     * Гарь, неполное сгорание сложных органических веществ.
     */
    BURNING("burning"),

    /**
     * Гнилостый, сладковатая сырно-пряная луковица.
     */
    PUTRID("putrid");

    /**
     * Строковое представление запаха.
     */
    private final String title;

    @Override
    public String toString() {
        return this.title;
    }

}
