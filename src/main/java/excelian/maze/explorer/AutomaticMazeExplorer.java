package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.*;

public class AutomaticMazeExplorer extends MazeExplorer implements AutomaticExplorer {

    public AutomaticMazeExplorer(Maze maze) {
        super(maze);
        movingToHook(maze.getStartLocation());
    }

    private final Stack<Breadcrumb> pathFollowed = new Stack<>();

    private final Set<MazeCoordinate> visitedCoordinates = new HashSet<>();

    @Override
    protected void movingToHook(MazeCoordinate coordinate) {
        // track visited coordinates in a HashSet for fast constant time lookup
        visitedCoordinates.add(coordinate);
    }

    private boolean findPathTillExit() {
        while (!pathFollowed.isEmpty()) {
            if (pathFollowed.peek().getPossibleDirections().isEmpty()) {
                moveBackToFirstFieldWithAlternateRoute();
            } else {
                HeadingDirectionClockWise direction = getFirstPossibleDirection();

                ExplorerPosition nextPosition = getPosition().withDirection(direction).calculateForwardPositionInMaze(maze);

                if (!visitedCoordinates.contains(nextPosition.getCoordinate())) {
                    moveTo(direction);
                    if (whatsAtMyLocation() == MazeStructure.EXIT) {
                        return true;
                    }
                    pathFollowed.add(new Breadcrumb(direction.opposite(), getPossibleDirections()));
                }
            }
        }
        return false;
    }

    private void moveBackToFirstFieldWithAlternateRoute() {
        Breadcrumb previousBreadcrumb;
        do {
            previousBreadcrumb = pathFollowed.pop();
            if (previousBreadcrumb.getArrivingFrom().isPresent()) {
                moveTo(previousBreadcrumb.getArrivingFrom().get());
            }
        } while (!previousBreadcrumb.getPossibleDirections().isEmpty());
    }

    private HeadingDirectionClockWise getFirstPossibleDirection() {
        return pathFollowed.peek().getPossibleDirections().remove(0);
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

class Breadcrumb {
    private final Optional<HeadingDirectionClockWise> arrivingFrom;
    private final List<HeadingDirectionClockWise> possibleDirections;

    public Breadcrumb(HeadingDirectionClockWise arrivingFrom, List<HeadingDirectionClockWise> possibleDirections) {
        this.arrivingFrom = Optional.of(arrivingFrom);
        this.possibleDirections = new ArrayList<>(possibleDirections);
        this.possibleDirections.remove(arrivingFrom);
    }


    public Breadcrumb(List<HeadingDirectionClockWise> possibleDirections) {
        this.arrivingFrom = Optional.empty();
        this.possibleDirections = new ArrayList<>(possibleDirections);
        this.possibleDirections.remove(arrivingFrom);
    }

    public Optional<HeadingDirectionClockWise> getArrivingFrom() {
        return arrivingFrom;
    }

    public List<HeadingDirectionClockWise> getPossibleDirections() {
        return possibleDirections;
    }

}
