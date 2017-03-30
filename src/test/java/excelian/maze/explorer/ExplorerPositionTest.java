package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ExplorerPositionTest {

    private ExplorerPosition position;

    private Maze mazeMock = mock(Maze.class);

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

    @Test
    public void positionWithDirectionUpShouldCalculateFieldAbove() {
        ExplorerPosition newPosition = new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.UP)
                .calculateMoveForwardPositionInMaze(mazeMock);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 0), HeadingDirectionClockWise.UP)));
    }

    @Test
    public void positionWithDirectionUpShouldFailWhenTopRow() {
        assertThatThrownBy(() ->
                new ExplorerPosition(new MazeCoordinate(1, 0), HeadingDirectionClockWise.UP)
                        .calculateMoveForwardPositionInMaze(mazeMock)
        ).isInstanceOf(FieldIsOutOfMazeBoundsException.class)
                .hasMessageContaining("Field is out of the maze!");
    }

    @Test
    public void positionWithDirectionLeftShouldCalculateFieldToTheLeft() {
        ExplorerPosition newPosition = new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.LEFT)
                .calculateMoveForwardPositionInMaze(mazeMock);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(0, 1), HeadingDirectionClockWise.LEFT)));
    }

    @Test
    public void positionWithDirectionUpShouldFailWhenLeftestColumn() {
        assertThatThrownBy(() ->
                new ExplorerPosition(new MazeCoordinate(0, 1), HeadingDirectionClockWise.LEFT)
                        .calculateMoveForwardPositionInMaze(mazeMock)
        ).isInstanceOf(FieldIsOutOfMazeBoundsException.class)
                .hasMessageContaining("Field is out of the maze!");
    }

    @Test
    public void positionWithDirectionRightShouldCalculateFieldToTheRight() {
        when(mazeMock.getDimensionX()).thenReturn(3);
        when(mazeMock.getDimensionY()).thenReturn(3);
        ExplorerPosition newPosition = new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.RIGHT)
                .calculateMoveForwardPositionInMaze(mazeMock);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(2, 1), HeadingDirectionClockWise.RIGHT)));
    }


    @Test
    public void positionWithDirectionRightShouldFailWhenOutOfMazeDimension() {
        when(mazeMock.getDimensionX()).thenReturn(3);
        when(mazeMock.getDimensionY()).thenReturn(3);
        assertThatThrownBy(() ->
                new ExplorerPosition(new MazeCoordinate(2, 1), HeadingDirectionClockWise.RIGHT)
                        .calculateMoveForwardPositionInMaze(mazeMock)
        ).isInstanceOf(FieldIsOutOfMazeBoundsException.class)
                .hasMessageContaining("Field is out of the maze!");
    }

    @Test
    public void positionWithDirectionDownShouldCalculateFieldBelow() {
        when(mazeMock.getDimensionX()).thenReturn(3);
        when(mazeMock.getDimensionY()).thenReturn(3);
        ExplorerPosition newPosition = new ExplorerPosition(new MazeCoordinate(1, 1), HeadingDirectionClockWise.DOWN)
                .calculateMoveForwardPositionInMaze(mazeMock);
        assertThat(newPosition, is(new ExplorerPosition(new MazeCoordinate(1, 2), HeadingDirectionClockWise.DOWN)));
    }

    @Test
    public void positionWithDirectionDownShouldFailWhenOutOfMazeDimension() {
        when(mazeMock.getDimensionX()).thenReturn(3);
        when(mazeMock.getDimensionY()).thenReturn(3);
        assertThatThrownBy(() ->
                new ExplorerPosition(new MazeCoordinate(1, 2), HeadingDirectionClockWise.DOWN)
                        .calculateMoveForwardPositionInMaze(mazeMock)
        ).isInstanceOf(FieldIsOutOfMazeBoundsException.class)
                .hasMessageContaining("Field is out of the maze!");
    }

}
