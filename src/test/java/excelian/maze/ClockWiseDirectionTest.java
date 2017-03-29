package excelian.maze;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ClockWiseDirectionTest {

    @Test
    public void turnLeftShouldReturnPreviousEnumElementGoingRound() {
        assertThat(ClockWiseDirection.UP.turnLeft(), is(ClockWiseDirection.LEFT));
        assertThat(ClockWiseDirection.LEFT.turnLeft(), is(ClockWiseDirection.DOWN));
        assertThat(ClockWiseDirection.DOWN.turnLeft(), is(ClockWiseDirection.RIGHT));
        assertThat(ClockWiseDirection.RIGHT.turnLeft(), is(ClockWiseDirection.UP));
    }

    @Test
    public void turnRightShouldReturnNextEnumElementGoingRound() {
        assertThat(ClockWiseDirection.UP.turnRight(), is(ClockWiseDirection.RIGHT));
        assertThat(ClockWiseDirection.RIGHT.turnRight(), is(ClockWiseDirection.DOWN));
        assertThat(ClockWiseDirection.DOWN.turnRight(), is(ClockWiseDirection.LEFT));
        assertThat(ClockWiseDirection.LEFT.turnRight(), is(ClockWiseDirection.UP));
    }
}
