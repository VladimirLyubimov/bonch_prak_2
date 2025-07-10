package ru.voldemar.bonch_prak_2.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum CellType {

    START('S'),
    END('E'),
    BLOCK('B'),
    FIELD('F'),
    PATH('.');

    private static final Map<Character, CellType> CELL_TYPE_MAPPED_BY_CHAR_CODE = Map.of(
            CellType.START.getCode(), CellType.START,
            CellType.END.getCode(), CellType.END,
            CellType.BLOCK.getCode(), CellType.BLOCK,
            CellType.FIELD.getCode(), CellType.FIELD

    );

    private final char code;

    public static CellType getByCode(char code) {
        return CELL_TYPE_MAPPED_BY_CHAR_CODE.get(code);
    }
}
