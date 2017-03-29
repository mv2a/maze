package excelian.maze;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;

public class MazeTest {

    private String exampleMaze =
            "XXXXXXXXXXXXXXX\n" +
            "X             X\n" +
            "X XXXXXXXXXXX X\n" +
            "X XS        X X\n" +
            "X XXXXXXXXX X X\n" +
            "X XXXXXXXXX X X\n" +
            "X XXXX      X X\n" +
            "X XXXX XXXX X X\n" +
            "X XXXX XXXX X X\n" +
            "X X    XXXXXX X\n" +
            "X X XXXXXXXXX X\n" +
            "X X XXXXXXXXX X\n" +
            "X X         X X\n" +
            "X XXXXXXXXX   X\n" +
            "XFXXXXXXXXXXXXX";

    @Test
    public void exampleMazeShouldBeInitialized() {
        Maze maze = new Maze(exampleMaze);

        assertThat(maze.getDimensionX(), is(15));
        assertThat(maze.getDimensionY(), is(15));
    }

    @Test
    public void emptyMazeShouldFailInitialization() {
        String emptyMaze = "";

        assertThatThrownBy(() ->
                new Maze(emptyMaze)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze can not be empty!");

    }

    @Test
    public void mazeWithoutStartingPointShouldFailInitialization() {
        String mazeWithoutStartingPoint =
                "XXXX\n" +
                "X  X\n" +
                "X  X\n" +
                "XXFX\n";

        assertThatThrownBy(() ->
                new Maze(mazeWithoutStartingPoint)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze should have exactly one starting point!");
    }

    @Test
    public void mazeWithoutExitPointShouldFailInitialization() {
        String mazeWithoutExitPoint =
                "XXXX\n" +
                "X  X\n" +
                "XS X\n" +
                "XXXX\n";

        assertThatThrownBy(() ->
                new Maze(mazeWithoutExitPoint)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze should have exactly one exit point!");
    }

    @Test
    public void mazeWithMultipleStartingPointShouldFailInitialization() {
        String mazeWithMultipleStartingPoint =
                "XXXX\n" +
                "X  X\n" +
                "XSSX\n" +
                "XXFX\n";

        assertThatThrownBy(() ->
                new Maze(mazeWithMultipleStartingPoint)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze should have exactly one starting point!");
    }

    @Test
    public void mazeWithMultipleExitgPointShouldFailInitialization() {
        String mazeWithMultipleExitPoint =
                "XXXX\n" +
                "XS X\n" +
                "X  X\n" +
                "XFFX\n";

        assertThatThrownBy(() ->
                new Maze(mazeWithMultipleExitPoint)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze should have exactly one exit point!");
    }


    @Test
    public void mazeInitializedWithCorrectNumberOfWallsAndSpaces() {
        String simpleMaze =
                "XXXX\n" +
                "XS X\n" +
                "X  X\n" +
                "XXFX\n";

        Maze maze = new Maze(simpleMaze);

        assertThat(maze.getNumberOfWalls(), is(11L));
        assertThat(maze.getNumberOfEmptySpaces(), is(3L));
    }

    @Test
    public void exampleMazeShouldReturnCorrectNumberOfWallsAndSpaces() {
        Maze maze = new Maze(exampleMaze);

        assertThat(maze.getNumberOfWalls(), is(149L));
        assertThat(maze.getNumberOfEmptySpaces(), is(74L));
    }

    @Test
    public void mazeShouldTellWhatIsAtGivenCoordinate() {
        String simpleMaze =
                "XXXX\n" +
                "XS X\n" +
                "X  X\n" +
                "XXFX\n";

        Maze maze = new Maze(simpleMaze);

        assertThat(maze.whatsAt(new MazeCoordinate(0, 0)), is(MazeStructure.WALL));
        assertThat(maze.whatsAt(new MazeCoordinate(3, 3)), is(MazeStructure.WALL));
        assertThat(maze.whatsAt(new MazeCoordinate(1, 1)), is(MazeStructure.START));
        assertThat(maze.whatsAt(new MazeCoordinate(2, 3)), is(MazeStructure.EXIT));
    }

}
