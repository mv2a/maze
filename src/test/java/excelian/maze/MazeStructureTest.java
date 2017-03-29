package excelian.maze;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;


public class MazeStructureTest {
    @Test
    public void mazeStructureCharXShouldBeRecognizesAsWall() {
        Assert.assertThat(MazeStructure.from('X'), is(MazeStructure.WALL));
    }

    @Test
    public void mazeStructureCharSShouldBeRecognizesAsStartingPoint() {
        Assert.assertThat(MazeStructure.from('S'), is(MazeStructure.START));
    }

    @Test
    public void mazeStructureCharFShouldBeRecognizesAsExitPoint() {
        Assert.assertThat(MazeStructure.from('F'), is(MazeStructure.EXIT));
    }

    @Test
    public void mazeStructureCharSpaceShouldBeRecognizesAsSpace() {
        Assert.assertThat(MazeStructure.from(' '), is(MazeStructure.SPACE));
    }

    @Test
    public void mazeStructureCharLinebreakShouldBeRecognizesAsNewRow() {
        Assert.assertThat(MazeStructure.from('\n'), is(MazeStructure.NEWROW));
    }

    @Test
    public void unknownStructureCharShouldThrowException() {
        assertThatThrownBy(() -> MazeStructure.from('?')
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Maze structure not recognised from '?'!");
    }

}
