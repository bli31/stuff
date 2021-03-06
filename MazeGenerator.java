import java.util.ArrayList;

public class MazeGenerator {

  private final String SPACE = " ";

 /*
 Advised by students from Mr K's class while writing this class
 */

  String[][] _maze;
  int _rows, _cols;

  public MazeGenerator(int x, int y){
    _maze = new String[x][y];
    for(int i = 0; i <_maze.length; i++){
      for(int e = 0; e <_maze[0].length; e++){
        _maze[i][e] = "#";
      }
    }
    _rows = x;
    _cols = y;
  }

  public void buildMaze(int numRooms){
    for (int i = 0; i < numRooms; i++){
      // choose random dimensions of room
      randBase = randNum(3, 8);
      randHeight = randNum(3, 8)

      // choose random tile
      randRow = randNum(0, _rows);
      randCol = randNum(0, _cols);

      // check if bottom right corner still fits within map
      // if not, resize
      while (randRow + randHeight >= _rows && randCols + randBase >= _cols){
        randBase = randNum(3, 8);
        randHeight = randNum(3, 8)
        randRow = randNum(0, _rows);
        randCol = randNum(0, _cols);
      }

      // passes check, so cleave out the space
      for (int n = 0; n < randBase; n++){
        for (int m = 0; m < randHeight; m++){
          _maze[randRow + m][randCol + n] = SPACE;
        }
      }

    } // end for loop
  } // end method

  // returns [lowerLimit, upperLimit)
  public int randNum(int lowerLimit, int upperLimit){
    return (int)(Math.random() * (upperLimit - lowerLimit) + lowerLimit);
  }

  /*
  While the map hasn’t been filled to the desired amount yet:
    Randomly choose a room size and type.
    Find a random location.
    Check if the room will fit.
    If it fits:
      Draw the room and add it to the list of rooms.
      Add connections from the new room to one or more other rooms.

  Run some a function to add doors.Choose a room to be the start point and add an up staircase there.Find the room furthest from the start point and add a down staircase there.For every room in the list of rooms:
    Add items/monsters/other content as desired!
  */




  public void generate(int startrow, int startcol){
    _maze[startrow][startcol] = "S";
    int endRow = Math.abs(_maze.length - 1 - startrow);
    int endCol = Math.abs(_maze[startrow].length - 1 - startcol);
    _maze[endRow][endCol] = "E";
  }


  private void carve(int row, int col){
      //can carve: not on border, not a space, fewer than 2 neighboring spaces
    if(canCarve(row,col)){
      _maze[row][col] = SPACE;

      //make an arrayList of directions i made the directions: [row offset , col offset]
      ArrayList<int[]>directions = new ArrayList<int[]>();
      //fill up the arrayList Here
      directions.add(new int[]{0, -1});
      directions.add(new int[]{0, 1});
      directions.add(new int[]{-1, 0});
      directions.add(new int[]{1, 0});

      while(directions.size() > 0){
        //choose a direction randomly:
        int randDirection = (int)(Math.random() * (directions.size()));
        int[] direction = directions.remove(randDirection);
        carve(row + direction[0], col + direction[1]);
      }
    }

  }

  private boolean canCarve(int row, int col) {
    if (row <= 0 || col <= 0 || row >= _maze.length - 1 || col >= _maze[row].length - 1) {
      return false;
    }

    int count = 0;
    if (_maze[row+1][col].equals(SPACE))
      count++;

    if (_maze[row-1][col].equals(SPACE))
      count++;

    if (_maze[row][col-1].equals(SPACE))
      count++;

    if (_maze[row][col+1].equals(SPACE))
      count++;


    if (count >= 2) {
      return false;
    }

    if (_maze[row][col].equals(SPACE)) {
      return false;
    }

    return true;
  }

  public String[][] getGeneratedMaze(){
      generate(0,0);
      carve(1,1);
      return _maze;
  }

  /*
  // For testing purposes


  public String toString(){
      String retVal = "";
      for(int i = 0; i<_maze.length; i++){
          for(int e=0; e<_maze[0].length; e++){
            retVal += _maze[i][e];
          }
          retVal += "\n";
      }
      return retVal;
  }
  public static void main(String[] args) {
      MazeGenerator test = new MazeGenerator(17,30);
      System.out.println(test);
      System.out.println("");
      test.carve(1,1);
      System.out.println(test);
  }
  */

}
