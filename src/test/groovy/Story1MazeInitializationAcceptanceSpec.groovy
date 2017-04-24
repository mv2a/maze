import org.talangsoft.maze.model.MazeCoordinate
import org.talangsoft.maze.model.MazeImpl
import org.talangsoft.maze.model.MazeStructure
import spock.lang.Unroll

@Unroll
class Story1MazeInitializationAcceptanceSpec extends spock.lang.Specification {

    // A Maze (as illustrated in ExampleMaze.txt) consists of walls 'X', Empty spaces ' ',
    // one and only one Start point 'S' and one and only one exit 'F'.
    def "The example maze in file ExampleMaze.txt should be initialized correctly"() {
        def fileName = "/ExampleMaze.txt"
        given: "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        expect: "The maze initialised with correct startLocation"
        maze.startLocation == new MazeCoordinate(3, 3)
    }

    // After a maze has been created the number of walls and empty spaces should be available to me.
    def "The example maze in file '#fileName' should be initialized correctly"() {

        given: "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        expect: "Maze created with number of walls '#nrOfWalls' and number of spaces '#nrOfSpaces'"
        maze.numberOfWalls == nrOfWalls
        maze.numberOfEmptySpaces == nrOfSpaces

        where:
        fileName         | nrOfWalls | nrOfSpaces
        "/TestMaze1.txt" | 149       | 74
        "/TestMaze2.txt" | 12        | 2
    }

    // After a maze has been created I should be able to put in a coordinate and know what exists at that point.
    def "At coordinate #coordinate there should be a(n) #whatShouldBeThere"() {

        given: "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        expect: "Maze created with number of walls '#nrOfWalls' and number of spaces '#nrOfSpaces'"
        maze.whatsAt(coordinate) == whatShouldBeThere

        where:
        fileName         | coordinate                | whatShouldBeThere
        "/TestMaze1.txt" | new MazeCoordinate(0, 0)  | MazeStructure.WALL
        "/TestMaze1.txt" | new MazeCoordinate(1, 1)  | MazeStructure.SPACE
        "/TestMaze1.txt" | new MazeCoordinate(3, 3)  | MazeStructure.START
        "/TestMaze1.txt" | new MazeCoordinate(1, 14) | MazeStructure.EXIT
    }


}