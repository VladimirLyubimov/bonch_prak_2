package ru.voldemar.bonch_prak_2.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MazeUtils {


    public static Maze<Cell> loadFromFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            scanner.nextLine(); // переносим "курсор" на следующую строку в файле
            Cell[][] cells = new Cell[rows][columns];
            for (int i = 0; i < rows; i++) {
                String strRow = scanner.nextLine();
                for (int j = 0; j < columns; j++) {
                    cells[i][j] = new Cell(CellType.getByCode(strRow.charAt(j)), j, i);
                }
            }
            return new Maze<>(cells);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveToFile(Maze<?> maze, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            int rows = maze.getRows();
            int columns = maze.getColumns();
            fileWriter.write(Integer.toString(rows));
            fileWriter.write('\n');
            fileWriter.write(Integer.toString(columns));
            fileWriter.write('\n');
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    fileWriter.write(maze.getCells().get(i).get(j).getType().getCode());
                }
                if(i != rows - 1) {
                    fileWriter.write('\n');
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
