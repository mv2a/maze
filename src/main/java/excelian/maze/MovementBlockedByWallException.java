package excelian.maze;

public class MovementBlockedByWallException extends RuntimeException {
    public MovementBlockedByWallException() {
        super("Movement is blocked by wall!");
    }
}
