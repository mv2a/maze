package excelian.maze.explorer;

public enum ClockWiseDirection {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public ClockWiseDirection turnRight() {
        if (ordinal() == values().length - 1) return values()[0];
        return values()[ordinal() + 1];
    }

    public ClockWiseDirection turnLeft() {
        if (ordinal() == 0) return values()[values().length - 1];
        return values()[ordinal() - 1];
    }

    public ClockWiseDirection opposite() {
        return turnLeft().turnLeft();
    }
}
