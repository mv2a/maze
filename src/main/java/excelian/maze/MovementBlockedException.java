package excelian.maze;

public class MovementBlockedException extends RuntimeException {
    public MovementBlockedException() {
        super("Movement is blocked!");
    }
}
