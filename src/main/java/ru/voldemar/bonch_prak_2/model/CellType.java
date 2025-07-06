package ru.voldemar.bonch_prak_2.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum CellType {

    START('S', Color.GRAY),
    END('E', Color.DARK_GRAY),
    BLOCK('B', Color.PINK),
    FIELD('F', Color.ORANGE);

    private static final Map<Character, CellType> CELL_TYPE_MAPPED_BY_CHAR_CODE = Map.of(
            CellType.START.getCode(), CellType.START,
            CellType.END.getCode(), CellType.END,
            CellType.BLOCK.getCode(), CellType.BLOCK,
            CellType.FIELD.getCode(), CellType.FIELD
    );

    private final char code;
    private final Color color;

    public static CellType getByCode(char code) {
        return CELL_TYPE_MAPPED_BY_CHAR_CODE.get(code);
    }
}
