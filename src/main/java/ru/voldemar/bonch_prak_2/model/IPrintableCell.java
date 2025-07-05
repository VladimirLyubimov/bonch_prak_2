package ru.voldemar.bonch_prak_2.model;

public interface IPrintableCell {

    default void print(){
        // Заглушка для отрисовки. Каждый тип клетки должен уметь рисовать себя сам
    }
}
