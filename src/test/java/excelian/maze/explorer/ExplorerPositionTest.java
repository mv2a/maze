package excelian.maze.explorer;

import excelian.maze.model.MazeCoordinate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExplorerPositionTest {

    private ExplorerPosition position;

    @Before
    public void setUp() {
        position = new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.UP);
    }

    @Test
    public void multipleTurnLeftsShouldDoAFullTurnBackToInitialState() {
        ExplorerPosition newPosition = position.turnLeft();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.LEFT)));

        newPosition = newPosition.turnLeft();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.DOWN)));

        newPosition = newPosition.turnLeft();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.RIGHT)));

        newPosition = newPosition.turnLeft();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.UP)));
    }

    @Test
    public void multipleTurnRightsShouldDoAFullTurnBackToInitialState() {
        ExplorerPosition newPosition = position.turnRight();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.RIGHT)));

        newPosition = newPosition.turnRight();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.DOWN)));

        newPosition = newPosition.turnRight();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.LEFT)));

        newPosition = newPosition.turnRight();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.UP)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToDown() {
        ExplorerPosition newPosition = position.withDirection(ClockWiseDirection.DOWN);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.DOWN)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToLeft() {
        ExplorerPosition newPosition = position.withDirection(ClockWiseDirection.LEFT);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.LEFT)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToRight() {
        ExplorerPosition newPosition = position.withDirection(ClockWiseDirection.RIGHT);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.RIGHT)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToUP() {
        ExplorerPosition newPosition = position.withDirection(ClockWiseDirection.UP);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), ClockWiseDirection.UP)));
    }
}
