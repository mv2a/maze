package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.List;
import java.util.Optional;
import java.util.Stack;


class Field {
    private MazeCoordinate coordinate;
    private List<ClockWiseDirection> possibleDirections;

    public Field(MazeCoordinate coordinate, List<ClockWiseDirection> possibleDirections) {
        this.coordinate = coordinate;
        this.possibleDirections = possibleDirections;
    }

    public MazeCoordinate getCoordinate() {
        return coordinate;
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
            if (whereAmI() == MazeStructure.EXIT) {
                return true;
            }
            if (!pathToFollow.peek().getPossibleDirections().isEmpty()) {
                // pick a direction and go with it
                ClockWiseDirection direction = pathToFollow.peek().getPossibleDirections().remove(0);
                moveTo(direction);
                // remove if it got empty
                if (pathToFollow.peek().getPossibleDirections().isEmpty()) {
                    pathToFollow.pop();
                }
                pathToFollow.add(new Field(getLocation().getCoordinate(), getPossibleDirections()));
                pathToFollow.peek().getPossibleDirections().remove(direction.opposite());
                // TODO: remove the direction where we came from (opposite)

            } else {
                // move back till there is a possible crossing
                setLocation(getLocation().withCoordinate(pathToFollow.pop().getCoordinate()));
            }
        }
        return false;
    }

    @Override
    public Optional<List<MazeCoordinate>> searchWayOut() {
        pathToFollow.add(new Field(getLocation().getCoordinate(), getPossibleDirections()));
        boolean exitReached = findPathTillExit();
        if (exitReached) {
            return Optional.of(getMovement());
        } else {
            return Optional.empty();
        }
    }
}
