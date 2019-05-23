package MapperTool.POJOs;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Maze {

    private final char START_POINT = 'A';
    private final char END_POINT = 'B';
    private final char WALL = '#';
    private final char PATH = '.';

    private char[][] grid;
    private int height;
    private int width;
    private Coordinate startPoint;
    private Coordinate endPoint;
    private boolean[][] checked;
    private long startTime;
    private long endTime;

    public Maze(String inputMap){
        this.setStartTime(System.nanoTime());
        String[] strings = inputMap.split(System.lineSeparator());
        this.setHeight(strings.length);
        this.setWidth(strings[0].length());
        this.setGrid(new char[getHeight()][getWidth()]);
        this.setChecked(new boolean[getHeight()][getWidth()]);
        AtomicInteger yCounter = new AtomicInteger(0);
        Arrays.stream(strings).forEach(s -> {
            AtomicInteger xCounter = new AtomicInteger(0);
            Stream<Character> chars = s.codePoints().mapToObj( i -> (char)i);
            chars.forEach( c -> {
                this.getGrid()[yCounter.get()][xCounter.get()] = c;
                if (this.getGrid()[yCounter.get()][xCounter.get()] == START_POINT){
                    this.setStartPoint(new Coordinate(xCounter.get(), yCounter.get()));
                }
                if (this.getGrid()[yCounter.get()][xCounter.get()] == END_POINT){
                    this.setEndPoint(new Coordinate(xCounter.get(), yCounter.get()));
                }
                xCounter.incrementAndGet();
            });
            yCounter.incrementAndGet();
        });
    }

    public Coordinate getStartPoint() {
        return startPoint;
    }

    private void setStartPoint(Coordinate startPoint) {
        this.startPoint = startPoint;
    }

    public Coordinate getEndPoint() {
        return endPoint;
    }

    private void setEndPoint(Coordinate endPoint) {
        this.endPoint = endPoint;
    }

    public boolean[][] getChecked() {
        return checked;
    }

    private void setChecked(boolean[][] checked) {
        this.checked = checked;
    }

    private char[][] getGrid() {
        return grid;
    }

    private void setGrid(char[][] grid) {
        this.grid = grid;
    }

    private int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    private int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private long getStartTime() {
        return startTime;
    }

    private void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    private long getEndTime() {
        return endTime;
    }

    private void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public boolean isEnd(int x, int y) {
        return x == endPoint.getxPos() && y == endPoint.getyPos();
    }

    public boolean isWall(int x, int y) {
        return grid[y][x] == WALL;
    }

    public void setChecked(int x, int y, boolean check) {
        checked[y][x] = check;
    }

    public boolean isValid(int x, int y) {
        return !( x < 0 || x >= getWidth() || y < 0 || y >= getHeight());
    }

    public String buildPath(List<Coordinate> path){
        if (path.isEmpty()){
            return "This maze could not be solved.";
        }
        StringBuilder mazeBuilder = new StringBuilder();
        path.forEach( c -> this.getGrid()[c.getyPos()][c.getxPos()] = '\u2588');
        IntStream yStream = IntStream.range(0,this.getHeight());
        AtomicInteger yCounter = new AtomicInteger(0);
        yStream.forEach(y -> {
            IntStream xStream = IntStream.range(0, this.getWidth());
            AtomicInteger xCounter = new AtomicInteger(0);
            xStream.forEach( x -> {
                mazeBuilder.append(this.getGrid()[y][x]);
                xCounter.incrementAndGet();
            });
            mazeBuilder.append("</br>");
            yCounter.incrementAndGet();
        });
        this.setEndTime(System.nanoTime());
        double timeElapsed = (float)(this.getEndTime()-this.getStartTime())/1E9;
        mazeBuilder.append("</br>");
        String timeMessage = "This operation took: " + timeElapsed + " seconds.";
        mazeBuilder.append(timeMessage);
        return mazeBuilder.toString().replace("\"", "\\\"");
    }
}
