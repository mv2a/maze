package excelian.maze;

import org.junit.Assert;
import org.junit.Test;

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

}
