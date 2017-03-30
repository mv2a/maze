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
        position = new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.UP);
    }

    @Test
    public void multipleTurnLeftsShouldDoAFullTurnBackToInitialState() {
        ExplorerPosition newPosition = position.turnLeft();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.LEFT)));

        newPosition = newPosition.turnLeft();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.DOWN)));

        newPosition = newPosition.turnLeft();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.RIGHT)));

        newPosition = newPosition.turnLeft();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.UP)));
    }

    @Test
    public void multipleTurnRightsShouldDoAFullTurnBackToInitialState() {
        ExplorerPosition newPosition = position.turnRight();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.RIGHT)));

        newPosition = newPosition.turnRight();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.DOWN)));

        newPosition = newPosition.turnRight();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.LEFT)));

        newPosition = newPosition.turnRight();
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.UP)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToDown() {
        ExplorerPosition newPosition = position.withDirection(HeadingDirectionClockWise.DOWN);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.DOWN)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToLeft() {
        ExplorerPosition newPosition = position.withDirection(HeadingDirectionClockWise.LEFT);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.LEFT)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToRight() {
        ExplorerPosition newPosition = position.withDirection(HeadingDirectionClockWise.RIGHT);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.RIGHT)));
    }

    @Test
    public void withDirectionShouldSetNewDirectionToUP() {
        ExplorerPosition newPosition = position.withDirection(HeadingDirectionClockWise.UP);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.UP)));
    }
}
