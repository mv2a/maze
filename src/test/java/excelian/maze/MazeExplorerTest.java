package excelian.maze;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MazeExplorerTest {

    private final Maze mazeMock = mock(Maze.class);
    private MazeExplorer explorer;

    private MazeCoordinate startLocation = new MazeCoordinate(1, 1);

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
    public void shouldMoveForwardToLeftIfFieldIsSpace() {
        explorer.turnLeft();
        shouldMoveLeftWhenFieldIs(MazeStructure.SPACE);
    }

    @Test
    public void shouldMoveForwardToLeftIfFieldIsExit() {
        explorer.turnLeft();
        shouldMoveLeftWhenFieldIs(MazeStructure.EXIT);
    }

    @Test
    public void shouldMoveForwardToLeftIfFieldIsStart() {
        explorer.turnLeft();
        shouldMoveLeftWhenFieldIs(MazeStructure.START);
    }

    @Test
    public void shouldMoveForwardToRightIfFieldIsSpace() {
        explorer.turnRight();
        shouldMoveRightWhenFieldIs(MazeStructure.SPACE);
    }

    @Test
    public void shouldMoveForwardToRightIfFieldIsStart() {
        explorer.turnRight();
        shouldMoveRightWhenFieldIs(MazeStructure.START);
    }

    @Test
    public void shouldMoveForwardToRightIfFieldIsExit() {
        explorer.turnRight();
        shouldMoveRightWhenFieldIs(MazeStructure.EXIT);
    }

    @Test
    public void shouldMoveForwardToDownIfFieldIsSpace() {
        explorer.turnRight();
        explorer.turnRight();
        shouldMoveDownWhenFieldIs(MazeStructure.SPACE);
    }

    @Test
    public void shouldMoveForwardToDownIfFieldIsStart() {
        explorer.turnRight();
        explorer.turnRight();
        shouldMoveDownWhenFieldIs(MazeStructure.START);
    }

    @Test
    public void shouldMoveForwardToDownIfFieldIsExit() {
        explorer.turnRight();
        explorer.turnRight();
        shouldMoveDownWhenFieldIs(MazeStructure.EXIT);
    }


    private void shouldMoveUpWhenFieldIs(MazeStructure field) {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(field);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.UP));
        assertThat(loc.getCoordinate(), is(startLocation.above()));
    }

    private void shouldMoveLeftWhenFieldIs(MazeStructure field) {
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(field);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.LEFT));
        assertThat(loc.getCoordinate(), is(startLocation.toTheLeft()));
    }

    private void shouldMoveRightWhenFieldIs(MazeStructure field) {
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(field);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.RIGHT));
        assertThat(loc.getCoordinate(), is(startLocation.toTheRight()));
    }

    private void shouldMoveDownWhenFieldIs(MazeStructure field) {
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(field);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.DOWN));
        assertThat(loc.getCoordinate(), is(startLocation.below()));
    }


}
