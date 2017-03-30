package excelian.maze.explorer;

import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;

import java.util.List;
import java.util.Optional;

public interface Explorer {

    void moveForward();

    default void moveForward(int nrOfFields) {
        for (int i = 0; i < nrOfFields; i++) moveForward();
    }

    void turnLeft();

    void turnRight();

    void turnTo(ClockWiseDirection direction);

    default void moveToLeft() {
        moveTo(ClockWiseDirection.LEFT);
    }

    default void moveToRight() {
        moveTo(ClockWiseDirection.RIGHT);
    }

    default void moveToUp() {
        moveTo(ClockWiseDirection.UP);
    }

    default void moveToDown() {
        moveTo(ClockWiseDirection.DOWN);
    }

    default void moveTo(ClockWiseDirection direction) {
        turnTo(direction);
        moveForward();
    }

    List<ClockWiseDirection> getPossibleDirections();

    Optional<MazeStructure> whatsInFront();

    MazeStructure whereAmI();

    List<MazeCoordinate> getMovement();

    ExplorerLocation getLocation();


}
