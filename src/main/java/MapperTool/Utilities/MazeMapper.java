package MapperTool.Utilities;

import MapperTool.POJOs.Coordinate;
import MapperTool.POJOs.Maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MazeMapper {

    private static int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static String solve(String inputMaze){
        Maze maze = new Maze(inputMaze);
        List<Coordinate> shortestPath = findShortestPath(maze);
        return maze.buildPath(shortestPath);
    }

    private static List<Coordinate> findShortestPath(Maze maze) {
        LinkedList<Coordinate> nextSteps = new LinkedList<>();
        Coordinate start = maze.getStartPoint();
        nextSteps.add(start);

        while (!nextSteps.isEmpty()) {
            Coordinate cur = nextSteps.remove();

            if (!maze.isValid(cur.getxPos(), cur.getyPos()) || maze.getChecked()[cur.getyPos()][cur.getxPos()]) {
                continue;
            }
            if (maze.isWall(cur.getxPos(), cur.getyPos())) {
                maze.getChecked()[cur.getyPos()][cur.getxPos()] = true;
                continue;
            }
            if (maze.isEnd(cur.getxPos(), cur.getyPos())) {
                return tracePath(cur.getPrevCoord());
            }
            AtomicInteger directionCounter = new AtomicInteger(0);
            Arrays.stream(DIRECTIONS).forEach(c -> {
                Coordinate coordinate = new Coordinate(cur.getxPos() + DIRECTIONS[directionCounter.get()][0],cur.getyPos() + DIRECTIONS[directionCounter.get()][1], cur);
                nextSteps.add(coordinate);
                directionCounter.incrementAndGet();
            });
            maze.getChecked()[cur.getyPos()][cur.getxPos()] = true;
        }
        return Collections.emptyList();
    }

    private static List<Coordinate> tracePath(Coordinate current) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate toStore = current;

        while (toStore.getPrevCoord() != null) {
            path.add(toStore);
            toStore = toStore.getPrevCoord();
        }
        return path;
    }
}
