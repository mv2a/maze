package excelian.maze;

import excelian.maze.model.MazeCoordinate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExplorerLocationTest {

    private ExplorerLocation location;

    @Before
    public void setUp() {
        location = new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.UP);
    }

    @Test
    public void multipleTurnLeftsShouldDoAFullTurnBackToInitialState() {
        ExplorerLocation newLocation = location.turnLeft();
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.LEFT)));

        newLocation = newLocation.turnLeft();
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.DOWN)));

        newLocation = newLocation.turnLeft();
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.RIGHT)));

        newLocation = newLocation.turnLeft();
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.UP)));
    }

    @Test
    public void multipleTurnRightsShouldDoAFullTurnBackToInitialState() {
        ExplorerLocation newLocation = location.turnRight();
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.RIGHT)));

        newLocation = newLocation.turnRight();
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.DOWN)));

        newLocation = newLocation.turnRight();
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.LEFT)));

        newLocation = newLocation.turnRight();
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.UP)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToDown() {
        ExplorerLocation newLocation = location.withDirection(ClockWiseDirection.DOWN);
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.DOWN)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToLeft() {
        ExplorerLocation newLocation = location.withDirection(ClockWiseDirection.LEFT);
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.LEFT)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToRight() {
        ExplorerLocation newLocation = location.withDirection(ClockWiseDirection.RIGHT);
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.RIGHT)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToUP() {
        ExplorerLocation newLocation = location.withDirection(ClockWiseDirection.UP);
        assertThat(newLocation, is(new ExplorerLocation(new MazeCoordinate(1, 1), ClockWiseDirection.UP)));
    }
}
