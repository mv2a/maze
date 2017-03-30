package excelian.maze.game;

public class InvalidExplorerStateException extends RuntimeException {
    public InvalidExplorerStateException() {
        super("Exploring not started yet!");
    }
}
