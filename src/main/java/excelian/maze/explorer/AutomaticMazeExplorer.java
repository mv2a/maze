package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;


class Field {
    private ClockWiseDirection cameFrom;
    private List<ClockWiseDirection> possibleDirections;
    private MazeCoordinate coordinate;

    public Field(ClockWiseDirection cameFrom, MazeCoordinate coordinate, List<ClockWiseDirection> possibleDirections) {
        this.cameFrom = cameFrom;
        this.coordinate = coordinate;
        this.possibleDirections = new ArrayList(possibleDirections);
        this.possibleDirections.remove(cameFrom);
    }

    public MazeCoordinate getCoordinate() {
        return coordinate;
    }

    public ClockWiseDirection getCameFrom() {
        return cameFrom;
    }

    public List<ClockWiseDirection> getPossibleDirections() {
        return possibleDirections;
    }
}


public class AutomaticMazeExplorer extends MazeExplorer implements AutomaticExplorer {

    public AutomaticMazeExplorer(Maze maze) {
        super(maze);
    }

    public AutomaticMazeExplorer(Maze maze, ClockWiseDirection startingDirection) {
        super(maze, startingDirection);
    }

    Stack<Field> pathToFollow = new Stack<>();

    private boolean findPathTillExit() {
        while (!pathToFollow.isEmpty()) {
            if (whereAmI() == MazeStructure.EXIT) { return true; }
            if (!pathToFollow.peek().getPossibleDirections().isEmpty()) {
                ClockWiseDirection direction = pathToFollow.peek().getPossibleDirections().remove(0);
                MazeCoordinate nextField = calculateNextFieldToMove(direction);
                if(getMovement().contains(nextField)) continue;
                moveTo(direction);
                pathToFollow.add(new Field(direction.opposite(), getLocation().getCoordinate(), getPossibleDirections()));
            } else {
                // move back till there is a possible crossing
                Field previousField;
                do {
                    previousField = pathToFollow.pop();
                    moveTo(previousField.getCameFrom());
                } while (!previousField.getPossibleDirections().isEmpty());
            }
        }
        return false;
    }

    @Override
    public Optional<List<MazeCoordinate>> searchWayOut() {
        // TODO: camefrom is not valid, though it never will be used
        pathToFollow.add(new Field(ClockWiseDirection.DOWN, getLocation().getCoordinate(), getPossibleDirections()));
        boolean exitReached = findPathTillExit();
        if (exitReached) {
            return Optional.of(getMovement());
        } else {
            return Optional.empty();
        }
    }
}
