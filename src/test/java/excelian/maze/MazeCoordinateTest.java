package excelian.maze;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.is;

public class MazeCoordinateTest {

    @Test
    public void mazeCoordinateXShouldNotBeNegative() {
        assertThatThrownBy(() ->
                new MazeCoordinate(-1, 0)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Coordinate should not be negative!");
    }

    @Test
    public void mazeCoordinateYShouldNotBeNegative() {
        assertThatThrownBy(() ->
                new MazeCoordinate(0, -1)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Coordinate should not be negative!");
    }

    @Test
    public void validCoordinateOrigoShouldBeCreated() {
        MazeCoordinate coord = new MazeCoordinate(0, 0);
        Assert.assertThat(coord.getX(), is(0));
        Assert.assertThat(coord.getY(), is(0));
    }


    @Test
    public void validCoordinateShouldBeCreated() {
        MazeCoordinate coord = new MazeCoordinate(11, 12);
        Assert.assertThat(coord.getX(), is(11));
        Assert.assertThat(coord.getY(), is(12));
    }
}
