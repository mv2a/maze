package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;


class Breadcrumb {
    private Optional<HeadingDirectionClockWise> cameFrom;
    private List<HeadingDirectionClockWise> possibleDirections;

    public Breadcrumb(HeadingDirectionClockWise cameFrom, List<HeadingDirectionClockWise> possibleDirections) {
        this.cameFrom = Optional.of(cameFrom);
        this.possibleDirections = new ArrayList(possibleDirections);
        this.possibleDirections.remove(cameFrom);
    }


    public Breadcrumb(List<HeadingDirectionClockWise> possibleDirections) {
        this.cameFrom = Optional.empty();
        this.possibleDirections = new ArrayList(possibleDirections);
        this.possibleDirections.remove(cameFrom);
    }

    public Optional<HeadingDirectionClockWise> getCameFrom() {
        return cameFrom;
    }

    public List<HeadingDirectionClockWise> getPossibleDirections() {
        return possibleDirections;
    }

}

public class AutomaticMazeExplorer extends MazeExplorer implements AutomaticExplorer {

    public AutomaticMazeExplorer(Maze maze) {
        super(maze);
    }

    private Stack<Breadcrumb> pathFollowed = new Stack<>();

    private boolean findPathTillExit() {
        while (!pathFollowed.isEmpty()) {
            if (!pathFollowed.peek().getPossibleDirections().isEmpty()) {
                // Get the first direction
                HeadingDirectionClockWise direction = pathFollowed.peek().getPossibleDirections().remove(0);
                ExplorerPosition nextPosition = getPosition().withDirection(direction).calculateMoveForwardPositionInMaze(maze);
                // TODO: make it more efficient than Olog(N)
                if (getMovement().contains(nextPosition.getCoordinate())) continue;
                moveTo(direction);
                if (whatsAtMyLocation() == MazeStructure.EXIT) {
                    return true;
                }
                pathFollowed.add(new Breadcrumb(direction.opposite(), getPossibleDirections()));
            } else {
                // move back till there is a possible crossing
                Breadcrumb previousBreadcrumb;
                do {
                    previousBreadcrumb = pathFollowed.pop();
                    if (previousBreadcrumb.getCameFrom().isPresent()) {
                        moveTo(previousBreadcrumb.getCameFrom().get());
                    }
                } while (!previousBreadcrumb.getPossibleDirections().isEmpty());
            }
        }
        return false;
    }

    @Override
    public synchronized Optional<List<MazeCoordinate>> searchWayOut() {
        pathFollowed.add(new Breadcrumb(getPossibleDirections()));
        boolean exitReached = findPathTillExit();
        if (exitReached) {
            return Optional.of(getMovement());
        } else {
            return Optional.empty();
        }
    }
}
