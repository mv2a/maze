package excelian.maze.explorer;

import excelian.maze.model.MazeCoordinate;

public class ExplorerPosition {

    private MazeCoordinate coordinate;

    private ClockWiseDirection direction;

    public ExplorerPosition(MazeCoordinate location, ClockWiseDirection direction) {
        this.coordinate = location;
        this.direction = direction;
    }

    public MazeCoordinate getCoordinate() {
        return coordinate;
    }

    public ClockWiseDirection getDirection() {
        return direction;
    }

    public ExplorerPosition withCoordinate(MazeCoordinate newCoordinate) {
        return new ExplorerPosition(newCoordinate, direction);
    }

    public ExplorerPosition withDirection(ClockWiseDirection newDirection) {
        return new ExplorerPosition(coordinate, newDirection);
    }

    public ExplorerPosition turnLeft() {
        return new ExplorerPosition(coordinate, direction.turnLeft());
    }

    public ExplorerPosition turnRight() {
        return new ExplorerPosition(coordinate, direction.turnRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExplorerPosition that = (ExplorerPosition) o;

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
        return "ExplorerPosition{" +
                "coordinate=" + coordinate +
                ", direction=" + direction +
                '}';
    }
}
