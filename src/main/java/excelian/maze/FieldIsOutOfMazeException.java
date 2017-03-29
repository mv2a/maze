package excelian.maze;

public class FieldIsOutOfMazeException extends RuntimeException {
    public FieldIsOutOfMazeException() {
        super("Movement is out of the maze!");
    }
}
