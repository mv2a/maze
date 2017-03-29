package excelian.maze;

import java.util.ArrayList;
import java.util.List;

public class MazeExplorer implements Explorer {

    private Maze maze;
    private List<MazeCoordinate> movement;
    private Direction headingDirection = Direction.UP;

    void startExplore(Maze maze) {
        this.maze = maze;
        this.headingDirection = Direction.UP;
        this.movement = new ArrayList<>();
        this.movement.add(maze.getStartLocation());
    }

    @Override
    public void moveForward() {

    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

    @Override
    public List<Direction> getPossibleDirections() {
        return null;
    }

    @Override
    public MazeStructure whatsInFront() {
        return null;
    }

    @Override
    public List<MazeCoordinate> getMovements() {
        return null;
    }

    @Override
    public ExplorerLocation getLocation() {
        return new ExplorerLocation(movement.get(movement.size() - 1), headingDirection);
    }
}
