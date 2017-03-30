package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;


class Field {
    private Optional<ClockWiseDirection> cameFrom;
    private List<ClockWiseDirection> possibleDirections;

    public Field(ClockWiseDirection cameFrom, List<ClockWiseDirection> possibleDirections) {
        this.cameFrom = Optional.of(cameFrom);
        this.possibleDirections = new ArrayList(possibleDirections);
        this.possibleDirections.remove(cameFrom);
    }


    public Field(List<ClockWiseDirection> possibleDirections) {
        this.cameFrom = Optional.empty();
        this.possibleDirections = new ArrayList(possibleDirections);
        this.possibleDirections.remove(cameFrom);
    }


    public Optional<ClockWiseDirection> getCameFrom() {
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

    private Stack<Field> pathFollowed = new Stack<>();

    private boolean findPathTillExit() {
        while (!pathFollowed.isEmpty()) {
            if (whatsAtMyLocation() == MazeStructure.EXIT) {
                return true;
            }
            if (!pathFollowed.peek().getPossibleDirections().isEmpty()) {
                ClockWiseDirection direction = pathFollowed.peek().getPossibleDirections().remove(0);
                MazeCoordinate nextField = calculateNextFieldToMove(direction);
                if (getMovement().contains(nextField)) continue;
                moveTo(direction);
                pathFollowed.add(new Field(direction.opposite(), getPossibleDirections()));
            } else {
                // move back till there is a possible crossing
                Field previousField;
                do {
                    previousField = pathFollowed.pop();
                    moveTo(previousField.getCameFrom().get());
                } while (!previousField.getPossibleDirections().isEmpty());
            }
        }
        return false;
    }

    @Override
    public Optional<List<MazeCoordinate>> searchWayOut() {
        pathFollowed.add(new Field(getPossibleDirections()));
        boolean exitReached = findPathTillExit();
        if (exitReached) {
            return Optional.of(getMovement());
        } else {
            return Optional.empty();
        }
    }
}
