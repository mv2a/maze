package excelian.maze;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MazeExplorerTest {

    private final Maze mazeMock = mock(Maze.class);
    private MazeExplorer explorer;

    private MazeCoordinate startLocation = new MazeCoordinate(0, 0);

    @Before
    public void Setup() {
        explorer = new MazeExplorer();
    }

    @Test
    public void mazeExplorerShouldInitializeInStartLocationTest() {
        when(mazeMock.getStartLocation()).thenReturn(startLocation);
        explorer.startExplore(mazeMock);

        ExplorerLocation loc = explorer.getLocation();
        assertThat(loc.getDirection(), is(Direction.UP));
        assertThat(loc.getLocation(), is(startLocation));
    }
}
