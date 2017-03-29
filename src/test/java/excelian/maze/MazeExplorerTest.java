package excelian.maze;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MazeExplorerTest {

    public static final int mazeDimension = 10;
    MazeCoordinate bottomRightCorner = new MazeCoordinate(mazeDimension - 1, mazeDimension - 1);
    MazeCoordinate topLeftCorner = new MazeCoordinate(0, 0);

    private final Maze mazeMock = mock(Maze.class);
    private MazeExplorer explorer;

    private MazeCoordinate startLocation = new MazeCoordinate(1, 1);


    @Before
    public void Setup() {
        explorer = new MazeExplorer();
        when(mazeMock.getStartLocation()).thenReturn(startLocation);
        when(mazeMock.getDimensionX()).thenReturn(mazeDimension);
        when(mazeMock.getDimensionY()).thenReturn(mazeDimension);
    }

    @Test
    public void shouldInitializeInStartLocationTest() {
        explorer.startExplore(mazeMock, ClockWiseDirection.UP);

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.UP));
        assertThat(loc.getCoordinate(), is(startLocation));
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsSpace() {
        explorer.startExplore(mazeMock, ClockWiseDirection.UP);

        shouldMoveUpWhenFieldIs(MazeStructure.SPACE);
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsExit() {
        explorer.startExplore(mazeMock, ClockWiseDirection.UP);

        shouldMoveUpWhenFieldIs(MazeStructure.EXIT);
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsStart() {
        explorer.startExplore(mazeMock, ClockWiseDirection.UP);

        shouldMoveUpWhenFieldIs(MazeStructure.START);
    }

    @Test
    public void shouldThrowExceptionWhenMoveForwardAndFieldIsWall() {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);
        explorer.startExplore(mazeMock, ClockWiseDirection.UP);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(MovementBlockedByWallException.class)
                .hasMessageContaining("Movement is blocked by wall!");
    }

    @Test
    public void shouldThrowExceptionWhenMoveToUpAndFieldIsOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(topLeftCorner);
        explorer.startExplore(mazeMock, ClockWiseDirection.UP);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(MovementIsOutOfMazeException.class)
                .hasMessageContaining("Movement is out of the maze!");

    }

    @Test
    public void shouldThrowExceptionWhenMoveToLeftAndFieldIsOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(topLeftCorner);
        explorer.startExplore(mazeMock, ClockWiseDirection.LEFT);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(MovementIsOutOfMazeException.class)
                .hasMessageContaining("Movement is out of the maze!");
    }

    @Test
    public void shouldThrowExceptionWhenMoveToDownAndFieldIsOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(bottomRightCorner);
        explorer.startExplore(mazeMock, ClockWiseDirection.DOWN);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(MovementIsOutOfMazeException.class)
                .hasMessageContaining("Movement is out of the maze!");

    }

    @Test
    public void shouldThrowExceptionWhenMoveToRightAndFieldIsOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(bottomRightCorner);
        explorer.startExplore(mazeMock, ClockWiseDirection.RIGHT);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(MovementIsOutOfMazeException.class)
                .hasMessageContaining("Movement is out of the maze!");
    }

    @Test
    public void shouldMoveToRightIfFieldIsSpace() {
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.SPACE);
        explorer.startExplore(mazeMock, ClockWiseDirection.RIGHT);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.RIGHT));
        assertThat(loc.getCoordinate(), is(startLocation.toTheRight()));
    }

    @Test
    public void shouldMoveToLeftIfFieldIsSpace() {
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.SPACE);
        explorer.startExplore(mazeMock, ClockWiseDirection.LEFT);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.LEFT));
        assertThat(loc.getCoordinate(), is(startLocation.toTheLeft()));
    }

    @Test
    public void shouldMoveDownIfFieldIsSpace() {
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.SPACE);
        explorer.startExplore(mazeMock, ClockWiseDirection.DOWN);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.DOWN));
        assertThat(loc.getCoordinate(), is(startLocation.below()));
    }

    @Test
    public void whatsInFrontShouldReturnWallIfAbove() {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);
        explorer.startExplore(mazeMock, ClockWiseDirection.UP);

        assertThat(explorer.whatsInFront(), is(Optional.of(MazeStructure.WALL)));
    }

    @Test
    public void whatsInFrontShouldReturnWallIfLeft() {
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.WALL);
        explorer.startExplore(mazeMock, ClockWiseDirection.LEFT);

        assertThat(explorer.whatsInFront(), is(Optional.of(MazeStructure.WALL)));
    }

    @Test
    public void whatsInFrontShouldReturnWallIfRight() {
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.WALL);
        explorer.startExplore(mazeMock, ClockWiseDirection.RIGHT);

        assertThat(explorer.whatsInFront(), is(Optional.of(MazeStructure.WALL)));
    }

    @Test
    public void whatsInFrontShouldReturnWallIfDown() {
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.WALL);
        explorer.startExplore(mazeMock, ClockWiseDirection.DOWN);

        assertThat(explorer.whatsInFront(), is(Optional.of(MazeStructure.WALL)));
    }

    @Test
    public void whatsInFrontShouldReturnNoneIfOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(topLeftCorner);
        explorer.startExplore(mazeMock, ClockWiseDirection.UP);

        assertThat(explorer.whatsInFront(), is(Optional.empty()));
    }

    private void shouldMoveUpWhenFieldIs(MazeStructure field) {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(field);
        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.UP));
        assertThat(loc.getCoordinate(), is(startLocation.above()));
    }

}
