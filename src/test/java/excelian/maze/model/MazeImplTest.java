package excelian.maze.model;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;

public class MazeImplTest {

    private final String exampleMaze =
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
        MazeImpl maze = new MazeImpl(exampleMaze);

        assertThat(maze.getDimensionX(), is(15));
        assertThat(maze.getDimensionY(), is(15));
    }

    @Test
    public void emptyMazeShouldFailInitialization() {
        String emptyMaze = "";

        assertThatThrownBy(() ->
                new MazeImpl(emptyMaze)
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
                new MazeImpl(mazeWithoutStartingPoint)
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
                new MazeImpl(mazeWithoutExitPoint)
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
                new MazeImpl(mazeWithMultipleStartingPoint)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze should have exactly one starting point!");
    }

    @Test
    public void mazeWithMultipleExitPointShouldFailInitialization() {
        String mazeWithMultipleExitPoint =
                "XXXX\n" +
                "XS X\n" +
                "X  X\n" +
                "XFFX\n";

        assertThatThrownBy(() ->
                new MazeImpl(mazeWithMultipleExitPoint)
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

        MazeImpl maze = new MazeImpl(simpleMaze);

        assertThat(maze.getDimensionX(), is(4));
        assertThat(maze.getDimensionY(), is(4));
        assertThat(maze.getNumberOfWalls(), is(11L));
        assertThat(maze.getNumberOfEmptySpaces(), is(3L));
    }

    @Test
    public void mazeInitializedWithCorrectNumberOfWallsAndSpacesLinebreakWIthCarriageReturn() {
        String simpleMaze =
                "XXXX\r\n" +
                "XS X\r\n" +
                "X  X\r\n" +
                "XXFX";

        MazeImpl maze = new MazeImpl(simpleMaze);

        assertThat(maze.getDimensionX(), is(4));
        assertThat(maze.getDimensionY(), is(4));
        assertThat(maze.getNumberOfWalls(), is(11L));
        assertThat(maze.getNumberOfEmptySpaces(), is(3L));
        assertThat(maze.getStartLocation(),is(new MazeCoordinate(1,1)));
    }

    @Test
    public void exampleMazeShouldReturnCorrectNumberOfWallsAndSpaces() {
        MazeImpl maze = new MazeImpl(exampleMaze);

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

        MazeImpl maze = new MazeImpl(simpleMaze);

        assertThat(maze.whatsAt(new MazeCoordinate(0, 0)), is(MazeStructure.WALL));
        assertThat(maze.whatsAt(new MazeCoordinate(3, 3)), is(MazeStructure.WALL));
        assertThat(maze.whatsAt(new MazeCoordinate(1, 1)), is(MazeStructure.START));
        assertThat(maze.whatsAt(new MazeCoordinate(2, 3)), is(MazeStructure.EXIT));
    }

    @Test
    public void mazeShouldTellWhereStartLocationIs() {
        String simpleMaze =
                "XXXX\n" +
                "X SX\n" +
                "X  X\n" +
                "XXFX\n";

        MazeImpl maze = new MazeImpl(simpleMaze);
        assertThat(maze.getStartLocation(), is(new MazeCoordinate(2,1)));
    }

    @Test
    public void mazeWithDifferentRowLengthShouldFailInitialization() {
        String mazeWithDifferentLenghRows =
                "XXFX\n" +
                "X S X\n" +
                "XXXX\n";

        assertThatThrownBy(() ->
                new MazeImpl(mazeWithDifferentLenghRows)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze rows should consist of the same number of blocks!");
    }

    @Test
    public void mazeShouldTellWhereExitLocationIs() {
        String simpleMaze =
                "XXXX\n" +
                "X SX\n" +
                "X  X\n" +
                "XFXX\n";

        MazeImpl maze = new MazeImpl(simpleMaze);
        assertThat(maze.getExitLocation(), is(new MazeCoordinate(1,3)));
    }


    @Test
    public void mazeShouldTellWhereExitLocationIsIfLast() {
        String simpleMaze =
                "XXXXX\n" +
                "X  SX\n" +
                "X   X\n" +
                "X   X\n" +
                "XXXFX\n";

        MazeImpl maze = new MazeImpl(simpleMaze);
        assertThat(maze.getExitLocation(), is(new MazeCoordinate(3,4)));
    }

}
