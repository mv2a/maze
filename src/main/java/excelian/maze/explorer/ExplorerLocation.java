package excelian.maze.explorer;

import excelian.maze.model.MazeCoordinate;

public class ExplorerLocation {

    private MazeCoordinate coordinate;

    private ClockWiseDirection direction;

    public ExplorerLocation(MazeCoordinate location, ClockWiseDirection direction) {
        this.coordinate = location;
        this.direction = direction;
    }

    public MazeCoordinate getCoordinate() {
        return coordinate;
    }

    public ClockWiseDirection getDirection() {
        return direction;
    }

    public ExplorerLocation withCoordinate(MazeCoordinate newCoordinate) {
        return new ExplorerLocation(newCoordinate, direction);
    }

    public ExplorerLocation withDirection(ClockWiseDirection newDirection) {
        return new ExplorerLocation(coordinate, newDirection);
    }

    public ExplorerLocation turnLeft() {
        return new ExplorerLocation(coordinate, direction.turnLeft());
    }

    public ExplorerLocation turnRight() {
        return new ExplorerLocation(coordinate, direction.turnRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExplorerLocation that = (ExplorerLocation) o;

        if (!coordinate.equals(that.coordinate)) return false;
        return direction == that.direction;

    }

    @Override
    public int hashCode() {
        int result = coordinate.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ExplorerLocation{" +
                "coordinate=" + coordinate +
                ", direction=" + direction +
                '}';
    }
}
