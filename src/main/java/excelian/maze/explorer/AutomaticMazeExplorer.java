package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;


class Breadcrumb {
    private Optional<ClockWiseDirection> cameFrom;
    private List<ClockWiseDirection> possibleDirections;

    public Breadcrumb(ClockWiseDirection cameFrom, List<ClockWiseDirection> possibleDirections) {
        this.cameFrom = Optional.of(cameFrom);
        this.possibleDirections = new ArrayList(possibleDirections);
        this.possibleDirections.remove(cameFrom);
    }


    public Breadcrumb(List<ClockWiseDirection> possibleDirections) {
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

    private Stack<Breadcrumb> pathFollowed = new Stack<>();

    private boolean findPathTillExit() {
        while (!pathFollowed.isEmpty()) {
            if (whatsAtMyLocation() == MazeStructure.EXIT) {
                return true;
            }
            if (!pathFollowed.peek().getPossibleDirections().isEmpty()) {
                // Get the first direction
                ClockWiseDirection direction = pathFollowed.peek().getPossibleDirections().remove(0);
                MazeCoordinate nextField = calculateNextFieldToMove(getPosition().withDirection(direction));
                // TODO: make it more efficient than Olog(N)
                if (getMovement().contains(nextField)) continue;
                moveTo(direction);
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
    public Optional<List<MazeCoordinate>> searchWayOut() {
        pathFollowed.add(new Breadcrumb(getPossibleDirections()));
        boolean exitReached = findPathTillExit();
        if (exitReached) {
            return Optional.of(getMovement());
        } else {
            return Optional.empty();
        }
    }
}
