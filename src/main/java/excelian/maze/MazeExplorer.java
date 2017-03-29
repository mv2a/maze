package excelian.maze;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.ArrayList;
import java.util.List;

public class MazeExplorer implements Explorer {

    private Maze maze;
    private List<MazeCoordinate> movement;
    private ExplorerLocation location;

    public void startExplore(Maze maze) {
        this.maze = maze;
        location = new ExplorerLocation(maze.getStartLocation(), ClockWiseDirection.UP);
        this.movement = new ArrayList<>();
        this.movement.add(maze.getStartLocation());
    }

    @Override
    public void moveForward() {

        MazeCoordinate nextFieldToMove;
        try {
            nextFieldToMove = nextFieldToMove();
        } catch (IllegalArgumentException ex) {
            throw new MovementIsOutOfMazeException();
        }

        switch (maze.whatsAt(nextFieldToMove)) {
            case START:
            case EXIT:
            case SPACE:
                this.movement.add(nextFieldToMove);
                this.location = location.withCoordinate(nextFieldToMove);
                break;
            case WALL:
                throw new MovementBlockedByWallException();
        }
    }

    private MazeCoordinate nextFieldToMove() {
        switch (location.getDirection()) {
            case UP:
                return location.getCoordinate().above();
            case DOWN:
                return location.getCoordinate().below();
            case LEFT:
                return location.getCoordinate().toTheLeft();
            case RIGHT:
                return location.getCoordinate().toTheRight();
        }
        throw new UnsupportedOperationException(String.format("Direction %s not supported!", location.getDirection()));
    }

    @Override
    public void turnLeft() {
        location = location.turnLeft();
    }

    @Override
    public void turnRight() {
        location = location.turnRight();
    }

    @Override
    public List<ClockWiseDirection> getPossibleDirections() {
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
        return location;
    }
}
