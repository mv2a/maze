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
        turnTo(ClockWiseDirection.LEFT);
        moveForward();
    }

    default void moveToRight() {
        turnTo(ClockWiseDirection.RIGHT);
        moveForward();
    }

    default void moveToUp() {
        turnTo(ClockWiseDirection.UP);
        moveForward();
    }

    default void moveToDown() {
        turnTo(ClockWiseDirection.DOWN);
        moveForward();
    }

    List<ClockWiseDirection> getPossibleDirections();

    Optional<MazeStructure> whatsInFront();

    MazeStructure whereAmI();

    List<MazeCoordinate> getMovement();

    ExplorerLocation getLocation();


}
