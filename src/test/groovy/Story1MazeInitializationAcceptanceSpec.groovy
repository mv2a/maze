import excelian.maze.model.MazeCoordinate
import excelian.maze.model.MazeImpl
import excelian.maze.model.MazeStructure
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
        fileName                | nrOfWalls | nrOfSpaces
        "/ExampleTestMaze1.txt" | 149       | 74
        "/ExampleTestMaze2.txt" | 12        | 2
    }

    // After a maze has been created I should be able to put in a coordinate and know what exists at that point.
    def "At coordinate #coordinate there should be a(n) #whatShouldBeThere"() {

        given: "The example maze in file '#fileName' is created"
        String mazeStr = this.getClass().getResource(fileName).text
        MazeImpl maze = new MazeImpl(mazeStr)

        expect: "Maze created with number of walls '#nrOfWalls' and number of spaces '#nrOfSpaces'"
        maze.whatsAt(coordinate) == whatShouldBeThere

        where:
        fileName                | coordinate                | whatShouldBeThere
        "/ExampleTestMaze1.txt" | new MazeCoordinate(0, 0)  | MazeStructure.WALL
        "/ExampleTestMaze1.txt" | new MazeCoordinate(1, 1)  | MazeStructure.SPACE
        "/ExampleTestMaze1.txt" | new MazeCoordinate(3, 3)  | MazeStructure.START
        "/ExampleTestMaze1.txt" | new MazeCoordinate(1, 14) | MazeStructure.EXIT
    }


}