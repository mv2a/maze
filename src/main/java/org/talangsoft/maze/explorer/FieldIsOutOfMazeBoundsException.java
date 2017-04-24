package org.talangsoft.maze.explorer;

public class FieldIsOutOfMazeBoundsException extends RuntimeException {
    public FieldIsOutOfMazeBoundsException() {
        super("Field is out of the maze!");
    }
}
