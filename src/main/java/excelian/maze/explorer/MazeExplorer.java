package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Thread safe implementation of a an explorer of the maze
 */
public class MazeExplorer implements Explorer {

    protected Maze maze;
    private List<MazeCoordinate> movement;
    private ExplorerPosition position;

    public MazeExplorer(Maze maze) {
        this(maze, ClockWiseDirection.UP);
    }

    public MazeExplorer(Maze maze, ClockWiseDirection startingDirection) {
        this.maze = maze;
        position = new ExplorerPosition(maze.getStartLocation(), startingDirection);
        this.movement = new ArrayList<>();
        this.movement.add(maze.getStartLocation());
    }

    @Override
    public synchronized final void moveForward() {
        ExplorerPosition newPosition = position.calculateMoveForwardPositionInMaze(maze);
        if (maze.whatsAt(newPosition.getCoordinate()).canBeExplored()) {
            this.movement.add(newPosition.getCoordinate());
            this.position = newPosition;
        } else {
            throw new MovementBlockedException(newPosition.getCoordinate());
        }
    }


    @Override
    public synchronized final void turnLeft() {
        position = position.turnLeft();
    }

    @Override
    public synchronized final void turnRight() {
        position = position.turnRight();
    }

    @Override
    public synchronized final void turnTo(ClockWiseDirection direction) {
        position = position.withDirection(direction);
    }

    @Override
    public synchronized final List<ClockWiseDirection> getPossibleDirections() {
        return Arrays.stream(ClockWiseDirection.values())
                .filter(canBeExplored())
                .collect(Collectors.toList());
    }

    @Override
    public synchronized final Optional<MazeStructure> whatsInFront() {
        return whatsInDirection(position.getDirection());
    }

    @Override
    public synchronized final MazeStructure whatsAtMyLocation() {
        return maze.whatsAt(position.getCoordinate());
    }

    @Override
    public synchronized List<MazeCoordinate> getMovement() {
        return new ArrayList<>(movement);
    }

    @Override
    public synchronized ExplorerPosition getPosition() {
        return position;
    }

    private Predicate<ClockWiseDirection> canBeExplored() {
        return d -> {
            Optional<MazeStructure> ms = whatsInDirection(d);
            return ms.isPresent() && ms.get().canBeExplored();
        };
    }

    private Optional<MazeStructure> whatsInDirection(ClockWiseDirection direction) {
        try {
            ExplorerPosition newPosition = position.withDirection(direction).calculateMoveForwardPositionInMaze(maze);
            return Optional.of(maze.whatsAt(newPosition.getCoordinate()));
        } catch (FieldIsOutOfMazeBoundsException ex) {
            return Optional.empty();
        }
    }

}
