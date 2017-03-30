package excelian.maze.explorer;

public class MovementBlockedException extends RuntimeException {
    public MovementBlockedException() {
        super("Movement is blocked!");
    }
}
