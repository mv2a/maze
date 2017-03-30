package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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

    private MazeCoordinate startLocation = new MazeCoordinate(1, 1);


    @Before
    public void Setup() {
        when(mazeMock.getStartLocation()).thenReturn(startLocation);
        when(mazeMock.getDimensionX()).thenReturn(mazeDimension);
        when(mazeMock.getDimensionY()).thenReturn(mazeDimension);
    }

    @Test
    public void shouldInitializeInStartLocationTest() {
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.UP));
        assertThat(loc.getCoordinate(), is(startLocation));
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsSpace() {
        shouldMoveUpWhenFieldIs(new MazeExplorer(mazeMock, ClockWiseDirection.UP), MazeStructure.SPACE);
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsExit() {
        shouldMoveUpWhenFieldIs(new MazeExplorer(mazeMock, ClockWiseDirection.UP), MazeStructure.EXIT);
    }

    @Test
    public void shouldMoveForwardToUpIfFieldIsStart() {
        shouldMoveUpWhenFieldIs(new MazeExplorer(mazeMock, ClockWiseDirection.UP), MazeStructure.START);
    }

    @Test
    public void shouldThrowExceptionWhenMoveForwardAndFieldIsWall() {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(MovementBlockedException.class)
                .hasMessageContaining("Movement is blocked!");
    }

    @Test
    public void shouldThrowExceptionWhenMoveToUpAndFieldIsOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(topLeftCorner);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(FieldIsOutOfMazeBoundsException.class)
                .hasMessageContaining("Field is out of the maze!");

    }

    @Test
    public void shouldThrowExceptionWhenMoveToLeftAndFieldIsOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(topLeftCorner);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.LEFT);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(FieldIsOutOfMazeBoundsException.class)
                .hasMessageContaining("Field is out of the maze!");
    }

    @Test
    public void shouldThrowExceptionWhenMoveToDownAndFieldIsOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(bottomRightCorner);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.DOWN);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(FieldIsOutOfMazeBoundsException.class)
                .hasMessageContaining("Field is out of the maze!");

    }

    @Test
    public void shouldThrowExceptionWhenMoveToRightAndFieldIsOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(bottomRightCorner);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.RIGHT);

        assertThatThrownBy(() ->
                explorer.moveForward()
        ).isInstanceOf(FieldIsOutOfMazeBoundsException.class)
                .hasMessageContaining("Field is out of the maze!");
    }

    @Test
    public void shouldMoveToRightIfFieldIsSpace() {
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.SPACE);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.RIGHT);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.RIGHT));
        assertThat(loc.getCoordinate(), is(startLocation.toTheRight()));
    }

    @Test
    public void shouldMoveToLeftIfFieldIsSpace() {
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.SPACE);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.LEFT);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.LEFT));
        assertThat(loc.getCoordinate(), is(startLocation.toTheLeft()));
    }

    @Test
    public void shouldMoveDownIfFieldIsSpace() {
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.SPACE);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.DOWN);

        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.DOWN));
        assertThat(loc.getCoordinate(), is(startLocation.below()));
    }

    @Test
    public void whatsInFrontShouldReturnWallIfAbove() {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);

        assertThat(explorer.whatsInFront(), is(Optional.of(MazeStructure.WALL)));
    }

    @Test
    public void whatsInFrontShouldReturnWallIfLeft() {
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.WALL);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.LEFT);

        assertThat(explorer.whatsInFront(), is(Optional.of(MazeStructure.WALL)));
    }

    @Test
    public void whatsInFrontShouldReturnWallIfRight() {
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.WALL);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.RIGHT);

        assertThat(explorer.whatsInFront(), is(Optional.of(MazeStructure.WALL)));
    }

    @Test
    public void whatsInFrontShouldReturnWallIfDown() {
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.WALL);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.DOWN);

        assertThat(explorer.whatsInFront(), is(Optional.of(MazeStructure.WALL)));
    }

    @Test
    public void whatsInFrontShouldReturnNoneIfOutOfBounds() {
        when(mazeMock.getStartLocation()).thenReturn(topLeftCorner);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);

        assertThat(explorer.whatsInFront(), is(Optional.empty()));
    }

    @Test
    public void whereAmIReturnTHeCurrentLocationType() {
        when(mazeMock.whatsAt(startLocation)).thenReturn(MazeStructure.EXIT);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);

        assertThat(explorer.whereAmI(), is(MazeStructure.EXIT));
    }

    @Test
    public void getPossibleDirectionsShouldReturnAllTheDirectionsIfExplorerCanMoveThere() {
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.SPACE);
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.SPACE);
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.SPACE);
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.SPACE);

        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);

        assertThat(explorer.getPossibleDirections(), is(Arrays.asList(ClockWiseDirection.values())));
    }

    @Test
    public void getPossibleDirectionsShouldReturnNoTheDirectionsIfExplorerCannotMoveAnywhere() {
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.WALL);
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.WALL);
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.WALL);

        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);

        assertThat(explorer.getPossibleDirections(), is(Arrays.asList(new ClockWiseDirection[]{})));
    }

    @Test
    public void movementShouldBeTracked() {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.SPACE);
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);
        explorer.moveForward();
        assertThat(explorer.getLocation(), is(new ExplorerLocation(new MazeCoordinate(1, 0), ClockWiseDirection.UP)));
        explorer.turnLeft();
        when(mazeMock.whatsAt(new MazeCoordinate(1, 0).toTheLeft())).thenReturn(MazeStructure.SPACE);
        explorer.moveForward();
        assertThat(explorer.getLocation(), is(new ExplorerLocation(new MazeCoordinate(0, 0), ClockWiseDirection.LEFT)));

        assertThat(explorer.getMovement(), is(Arrays.asList(
                new MazeCoordinate[]{
                        new MazeCoordinate(1, 1),
                        new MazeCoordinate(1, 0),
                        new MazeCoordinate(0, 0)
                }
        )));
    }

    @Test
    public void turnToShouldSetDirection() {
        MazeExplorer explorer = new MazeExplorer(mazeMock, ClockWiseDirection.UP);
        assertThat(explorer.getLocation().getDirection(), is(ClockWiseDirection.UP));

        explorer.turnTo(ClockWiseDirection.DOWN);

        assertThat(explorer.getLocation().getDirection(), is(ClockWiseDirection.DOWN));

    }

    private void shouldMoveUpWhenFieldIs(MazeExplorer explorer, MazeStructure field) {
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(field);
        explorer.moveForward();

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(ClockWiseDirection.UP));
        assertThat(loc.getCoordinate(), is(startLocation.above()));
    }


}
