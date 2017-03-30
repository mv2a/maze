package excelian.maze.explorer;

import excelian.maze.model.MazeCoordinate;

public class MovementBlockedException extends RuntimeException {
    public MovementBlockedException(MazeCoordinate location) {
        super(String.format("Movement to location %s is blocked!", location));
    }
}
