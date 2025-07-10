package ru.voldemar.bonch_prak_2;

import java.net.URISyntaxException;

import ru.voldemar.bonch_prak_2.gui.Window;

public class Main {

    public static void main(String... args) throws URISyntaxException {
        Window frame = new Window("Pathfinder");
        
        //        CompletableFuture.runAsync(() -> {
//                    var pathFinder = new AStarPathFinder(mazePainter);
//                    var path = pathFinder.findPath(maze);
//                    maze.getCells()
//                            .stream()
//                            .flatMap(Collection::stream)
//                            .filter(cell -> cell.getType() == CellType.FIELD)
//                            .filter(path::contains)
//                            .forEach(cell -> cell.setOnPath(true));
//                    System.out.println(maze);
//                    System.out.println(path);
////                    mazePainter.setMaze(maze);
//                }
//        ).thenRun(frame::repaint);
    }
}
