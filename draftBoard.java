import java.util.ArrayList;
import java.util.List;
import java.io.*; //library to read files

public class draftBoard{

  private Player[]_draftBoard;

  //5 rounds with 16 players per rounds
  //player info is found in players.txt which the user may edit as they please(csv format)
  public draftBoard(){
    _draftBoard = new Player[80]; //80 players to draft(5 players * 16 teams)
    _draftBoard = fillBoard(sortPlayers(importPlayers("Players.txt")),_draftBoard);
  }
  public Player[] getBoard(){
    return _draftBoard;
  }
  //adds sorted players to the draft board
  //prospects is sorted array of the players
  public Player[] fillBoard(ArrayList<Player> prospects,Player[]board){
    for(int i=0;i<getBoard().length;i++){
      board[i]= prospects.get(i);
    }
      return board;
    }
  //sorts the array of players
  //uses an insertion sort in descending order
  public ArrayList<Player> sortPlayers(ArrayList<Player> prospects){
    for(int i=1;i<prospects.size();i++){
      int j = i;
      while(j>0 && prospects.get(j).getOverall()>prospects.get(j-1).getOverall()){
        prospects.set(j-1,prospects.set(j,prospects.get(j-1)));
        j--;
      }
    }
    return prospects;
  }

  //reads players in from a txt file of players(user can write their own file as long
  //as it's in the correct format) and adds to an array(unsorted)
  public ArrayList<Player> importPlayers(String filename){
    ArrayList<Player> draft = new ArrayList<Player>();
    try{
        //creates buffered reader to read from text file
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while(line != null){
          //splits the line by commas to get player data
          String[] data = line.split(",");
          String name = data[0];
          int score = Integer.parseInt(data[1]);
          int rebound = Integer.parseInt(data[2]);
          int pass = Integer.parseInt(data[3]);
          int defense = Integer.parseInt(data[4]);
          int physical = Integer.parseInt(data[5]);
          int position = Integer.parseInt(data[6]);
          draft.add(new Player(name,score,rebound,pass,defense,physical,position));
        line = reader.readLine(); //next line of file
      }
      reader.close();
    }
    //exceptions
      catch(FileNotFoundException error){
      System.out.println("file not found");
      }
      catch (IOException error){
       System.out.println("Unable to load file");
     }
     return draft;
    }
public String toString(){
  String ans = "";
  for(int i=0;i<getBoard().length;i++){
    if (getBoard()[i] == null) ans += "*****Rank: " + (i+1) +  " Drafted*****" + "\n"; 
    else ans += "Rank: " + (i + 1) + " " + getBoard()[i] + "\n";
  }
  return ans;
}
}
