package excelian.maze;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MazeExplorer implements Explorer {

    private Maze maze;
    private List<MazeCoordinate> movement;
    private ExplorerLocation location;

    public final void startExplore(Maze maze) {
        startExplore(maze, ClockWiseDirection.UP);
    }

    public final void startExplore(Maze maze, ClockWiseDirection startingDriection) {
        this.maze = maze;
        location = new ExplorerLocation(maze.getStartLocation(), startingDriection);
        this.movement = new ArrayList<>();
        this.movement.add(maze.getStartLocation());
    }

    @Override
    public void moveForward() {
        MazeCoordinate nextFieldToMove = calculateAndVerifyNextFieldToMove();

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

    private MazeCoordinate calculateAndVerifyNextFieldToMove() {
        MazeCoordinate nextFieldToMove;
        try {
            nextFieldToMove = calculateNextFieldToMove();
        } catch (IllegalArgumentException ex) {
            throw new MovementIsOutOfMazeException();
        }
        if (nextFieldToMove.getX() >= maze.getDimensionX() || nextFieldToMove.getY() >= maze.getDimensionY()) {
            throw new MovementIsOutOfMazeException();
        }
        return nextFieldToMove;
    }

    private MazeCoordinate calculateNextFieldToMove() {
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
    public Optional<MazeStructure> whatsInFront() {
        try {
            MazeCoordinate nextFieldToMove = calculateAndVerifyNextFieldToMove();
            return Optional.of(maze.whatsAt(nextFieldToMove));
        } catch (MovementIsOutOfMazeException ex) {
            return Optional.empty();
        }
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
