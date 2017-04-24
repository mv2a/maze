package org.talangsoft.maze.explorer;

import org.talangsoft.maze.model.MazeCoordinate;

public class MovementBlockedException extends RuntimeException {
    public MovementBlockedException(MazeCoordinate location) {
        super(String.format("Movement to location %s is blocked!", location));
    }
}
