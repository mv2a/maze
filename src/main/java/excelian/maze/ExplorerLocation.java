package excelian.maze;

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;
}

public class ExplorerLocation {

    private MazeCoordinate location;

    private Direction direction;

    public ExplorerLocation(MazeCoordinate location, Direction direction) {
        this.location = location;
        this.direction = direction;
    }

    public MazeCoordinate getLocation() {
        return location;
    }

    public Direction getDirection() {
        return direction;
    }
}
