package excelian.maze;

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
    private ExplorerLocation location;

    public MazeExplorer(Maze maze, ClockWiseDirection startingDirection) {
        this.maze = maze;
        location = new ExplorerLocation(maze.getStartLocation(), startingDirection);
        this.movement = new ArrayList<>();
        this.movement.add(maze.getStartLocation());
    }

    private boolean isValidFieldToMoveTo(MazeStructure field) {
        return !field.equals(MazeStructure.WALL);

    }

    @Override
    public void moveForward() {

        MazeCoordinate nextFieldToMove = calculateNextFieldToMove(location.getDirection());
        if (isValidFieldToMoveTo(maze.whatsAt(nextFieldToMove))) {
            this.movement.add(nextFieldToMove);
            this.location = location.withCoordinate(nextFieldToMove);
        } else {
            throw new MovementBlockedException();
        }
    }

    private MazeCoordinate calculateNextFieldToMove(ClockWiseDirection direction) {
        switch (direction) {
            case UP:
                if (location.getCoordinate().getY() == 0) throw new FieldIsOutOfMazeBoundsException();
                return location.getCoordinate().above();
            case DOWN:
                if (location.getCoordinate().getY() == maze.getDimensionY() - 1) throw new FieldIsOutOfMazeBoundsException();
                return location.getCoordinate().below();
            case LEFT:
                if (location.getCoordinate().getX() == 0) throw new FieldIsOutOfMazeBoundsException();
                return location.getCoordinate().toTheLeft();
            case RIGHT:
                if (location.getCoordinate().getX() == maze.getDimensionX() - 1) throw new FieldIsOutOfMazeBoundsException();
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
            MazeCoordinate nextFieldToMove = calculateNextFieldToMove(direction);
            return Optional.of(maze.whatsAt(nextFieldToMove));
        } catch (FieldIsOutOfMazeBoundsException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MazeStructure> whatsInFront() {
        return whatsInDirection(location.getDirection());
    }

    @Override
    public MazeStructure whereAmI() {
        return maze.whatsAt(location.getCoordinate());
    }

    @Override
    public List<MazeCoordinate> getMovement() {
        return movement;
    }

    @Override
    public ExplorerLocation getLocation() {
        return location;
    }

}
