package excelian.maze;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MazeExplorerTest {

    private final Maze mazeMock = mock(Maze.class);
    private MazeExplorer explorer;

    private MazeCoordinate startLocation = new MazeCoordinate(1, 1);

    private MazeCoordinate topLeftCorner = new MazeCoordinate(0, 0);

    @Before
    public void Setup() {
        explorer = new MazeExplorer();
        when(mazeMock.getStartLocation()).thenReturn(startLocation);

        explorer.startExplore(mazeMock);
    }

    @Test
    public void shouldInitializeInStartLocationTest() {
        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.UP));
        assertThat(loc.getCoordinate(), is(startLocation));
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsSpace() {
        shouldMoveUpWhenFieldIs(MazeStructure.SPACE);
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsExit() {
        shouldMoveUpWhenFieldIs(MazeStructure.EXIT);
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsStart() {
        shouldMoveUpWhenFieldIs(MazeStructure.START);
    }


    @Test
    public void shouldThrowExceptionWhenMoveForwardAndFieldIsWall() {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(MovementBlockedByWallException.class)
                .hasMessageContaining("Movement is blocked by wall!");
    }

    @Test
    public void shouldThrowExceptionWhenMoveForwardAndFieldIsOutOfBounds() {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);
        when(mazeMock.getStartLocation()).thenReturn(topLeftCorner);
        explorer.startExplore(mazeMock);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(MovementIsOutOfMazeException.class)
                .hasMessageContaining("Movement is out of the maze!");
    }

    @Test
    public void shouldMoveToRightIfFieldIsSpace() {
        explorer.turnRight();
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.SPACE);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.RIGHT));
        assertThat(loc.getCoordinate(), is(startLocation.toTheRight()));
    }

    @Test
    public void shouldMoveToLeftIfFieldIsSpace() {
        explorer.turnLeft();
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.SPACE);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.LEFT));
        assertThat(loc.getCoordinate(), is(startLocation.toTheLeft()));
    }

    @Test
    public void shouldMoveDownIfFieldIsSpace() {
        explorer.turnLeft();
        explorer.turnLeft();
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.SPACE);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.DOWN));
        assertThat(loc.getCoordinate(), is(startLocation.below()));
    }

    private void shouldMoveUpWhenFieldIs(MazeStructure field) {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(field);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.UP));
        assertThat(loc.getCoordinate(), is(startLocation.above()));
    }




}
