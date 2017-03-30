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

public class MazeExplorer implements Explorer {

    private Maze maze;
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

    private boolean isValidFieldToMoveTo(MazeStructure field) {
        return !field.equals(MazeStructure.WALL);

    }

    @Override
    public void moveForward() {
        MazeCoordinate nextFieldToMove = calculateNextFieldToMove(position);
        if (isValidFieldToMoveTo(maze.whatsAt(nextFieldToMove))) {
            this.movement.add(nextFieldToMove);
            this.position = position.withCoordinate(nextFieldToMove);
        } else {
            throw new MovementBlockedException(nextFieldToMove);
        }
    }

    protected MazeCoordinate calculateNextFieldToMove(ExplorerPosition el) {
        switch (el.getDirection()) {
            case UP:
                if (el.getCoordinate().getY() == 0) throw new FieldIsOutOfMazeBoundsException();
                return el.getCoordinate().above();
            case DOWN:
                if (el.getCoordinate().getY() == maze.getDimensionY() - 1)
                    throw new FieldIsOutOfMazeBoundsException();
                return el.getCoordinate().below();
            case LEFT:
                if (el.getCoordinate().getX() == 0) throw new FieldIsOutOfMazeBoundsException();
                return el.getCoordinate().toTheLeft();
            case RIGHT:
                if (el.getCoordinate().getX() == maze.getDimensionX() - 1)
                    throw new FieldIsOutOfMazeBoundsException();
                return el.getCoordinate().toTheRight();
        }
        throw new UnsupportedOperationException(String.format("Direction %s not supported!", el.getDirection()));
    }

    @Override
    public void turnLeft() {
        position = position.turnLeft();
    }

    @Override
    public void turnRight() {
        position = position.turnRight();
    }

    private Predicate<ClockWiseDirection> isValidFieldToMoveTo() {
        return d -> {
            Optional<MazeStructure> ms = whatsInDirection(d);
            return ms.isPresent() && isValidFieldToMoveTo(ms.get());
        };
    }

    @Override
    public List<ClockWiseDirection> getPossibleDirections() {
        return Arrays.stream(ClockWiseDirection.values())
                .filter(isValidFieldToMoveTo())
                .collect(Collectors.toList());
    }


    private Optional<MazeStructure> whatsInDirection(ClockWiseDirection direction) {
        try {
            MazeCoordinate nextFieldToMove = calculateNextFieldToMove(position.withDirection(direction));
            return Optional.of(maze.whatsAt(nextFieldToMove));
        } catch (FieldIsOutOfMazeBoundsException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MazeStructure> whatsInFront() {
        return whatsInDirection(position.getDirection());
    }

    @Override
    public MazeStructure whatsAtMyLocation() {
        return maze.whatsAt(position.getCoordinate());
    }

    @Override
    public List<MazeCoordinate> getMovement() {
        return movement;
    }

    public ExplorerPosition getPosition() {
        return position;
    }

    @Override
    public void turnTo(ClockWiseDirection direction) {
        position = position.withDirection(direction);
    }
}
