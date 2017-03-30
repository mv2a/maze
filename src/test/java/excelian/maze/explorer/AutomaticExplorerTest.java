package excelian.maze.explorer;

import excelian.maze.model.Maze;
import excelian.maze.model.MazeCoordinate;
import excelian.maze.model.MazeStructure;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class AutomaticExplorerTest {
    private final int mazeDimension = 10;

    private final MazeCoordinate bottomRightCorner = new MazeCoordinate(mazeDimension - 1, mazeDimension - 1);
    private final MazeCoordinate topLeftCorner = new MazeCoordinate(0, 0);

    private final Maze mazeMock = mock(Maze.class, RETURNS_SMART_NULLS);

    private final MazeCoordinate startLocation = new MazeCoordinate(1, 1);


    @Before
    public void Setup() {
        when(mazeMock.getStartLocation()).thenReturn(startLocation);
        when(mazeMock.getDimensionX()).thenReturn(mazeDimension);
        when(mazeMock.getDimensionY()).thenReturn(mazeDimension);
    }

    @Test
    public void automaticExplorerShouldReturnEmptyIfThereIsNoExitAvailable() {
        AutomaticExplorer explorer = new AutomaticMazeExplorer(mazeMock);
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.WALL);
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.WALL);
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.WALL);

        Optional<List<MazeCoordinate>> movement = explorer.searchWayOut();

        assertThat(movement, is(Optional.empty()));
    }

    @Test
    public void automaticExplorerShouldReturnPathIfExitFound() {
        AutomaticExplorer explorer = new AutomaticMazeExplorer(mazeMock);
        when(mazeMock.whatsAt(startLocation.above())).thenReturn(MazeStructure.WALL);
        when(mazeMock.whatsAt(startLocation.below())).thenReturn(MazeStructure.EXIT);
        when(mazeMock.whatsAt(startLocation.toTheLeft())).thenReturn(MazeStructure.WALL);
        when(mazeMock.whatsAt(startLocation.toTheRight())).thenReturn(MazeStructure.WALL);


        Optional<List<MazeCoordinate>> movement = explorer.searchWayOut();

        assertThat(movement, is(
                Optional.of(Arrays.asList(
                        startLocation,
                        startLocation.below()
                ))
        ));
    }
}
