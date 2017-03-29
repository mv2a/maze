package excelian.maze;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
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

        Assert.assertThat(maze.getDimensionX(), is(15));
        Assert.assertThat(maze.getDimensionY(), is(15));
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
                .hasMessageContaining("Maze should have a starting point!");
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
                .hasMessageContaining("Maze should have an exit point!");
    }

    @Test
    public void mazeWithMultipleStartingPointShouldFailInitialization() {
        String mazeWithoutStartingPoint =
                "XXXX\n" +
                "X  X\n" +
                "XSSX\n" +
                "XXFX\n";

        assertThatThrownBy(() ->
                new Maze(mazeWithoutStartingPoint)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze should have exactly one starting point!");
    }
}
