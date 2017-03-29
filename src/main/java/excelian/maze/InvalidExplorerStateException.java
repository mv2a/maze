package excelian.maze;

public class InvalidExplorerStateException extends RuntimeException {
    public InvalidExplorerStateException() {
        super("Explorer not started exploring yet!");
    }
}
