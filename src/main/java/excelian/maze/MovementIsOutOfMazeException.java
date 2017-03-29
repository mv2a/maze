package excelian.maze;

public class MovementIsOutOfMazeException extends RuntimeException {
    public MovementIsOutOfMazeException() {
        super("Movement is out of the maze!");
    }
}
