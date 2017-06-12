import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class League{
    private boolean canTrade;
    private Team[] _league;
    private draftBoard _board;
    private Player[] _draftArray;
    private int _matches;
    private DLinkedList _snake;//used to represent snake draft
    private Team _user;
    private DNode userNode;
    public League(){
      _league =new Team[16];//number of teams in the league
      _board=new draftBoard();//draftboard created separately
      _draftArray = _board.getBoard();
      _matches=0;//keeps track of the number of matches
      _user = null;
      _snake = new DLinkedList();
      userNode = null;
      canTrade= false;
      setAI();
    }
    public void setAI(){
      _league[1]=new Team("Swagamicks");
      _league[2]=new Team("Dewannabes");
      _league[3]=new Team("Revilos");
      _league[4]=new Team("Dank Memers");
      _league[5]=new Team("Lakers");
      _league[6]=new Team("Knicks");
      _league[7]=new Team("Lelbrons");
      _league[8]=new Team("Simpsons");
      _league[9]=new Team("Kronbergers");
      _league[10]=new Team("76ers");
      _league[11]=new Team("Boltons");
      _league[12]=new Team("Starks");
      _league[13]=new Team("Lannisters");
      _league[14]=new Team("Tyrells");
      _league[15]=new Team("Greyjoys");
      for(int i=1;i<16;i++)_league[i].setNumber(i+1);
    }
    public void setTrade(boolean a){
      canTrade = a;
    }
    public boolean getTrade(){
      return canTrade;
    }
    public Team[] getTeams(){
      return _league;
    }
    public void setUserNode(DNode a){
      userNode = a;
    }
    public Team getUser(){
      return _user;
    }
    public void setUser(Team user){
      _user = user;
      _user.setNumber(1);
    }
    public DLinkedList getSnake(){
      return _snake;
    }
    public void fillSnake(){
      fillSnake(shuffle(_league));
    }
    public draftBoard getdraftBoard(){
      return _board;
    }
    public Player[] getdraftArray(){
      return _draftArray;
    }

    public int getMatches(){
      return _matches;
    }
    public boolean isFinished(){
      boolean ans = true;
      for(int i=0;i<_league.length;i++){
        if (_league[i].isFinished() == false) ans = false;
      }
      return ans;
    }
    public boolean allFull(){
      boolean ans = true;
      for(int i=0;i<_league.length;i++){
        if (_league[i].teamFull() == false) ans = false;
      }
      return ans;
    }
    public String position(int pos){
      if(pos == 1) return "PG";
      else if(pos == 2) return "SG";
      else if(pos == 3) return "SF";
      else if(pos == 4) return "PF";
      return "C";
    }

///get functions are used in the gameApp class for user to see players in the positions
    public void getPG(){
      System.out.println(getPosPlayers(1));//prints out all the players in the Point Guard position
    }

    public void getSG(){
      System.out.println(getPosPlayers(2));//prints out all the players in the Shooting Guard postion
    }

    public void getSF(){
      System.out.println(getPosPlayers(3));//prints out all the players in the Small Forward postion
    }

    public void getPF(){
      System.out.println(getPosPlayers(4));//prints out all the players in the Power Forward postion
    }

    public void getC(){
      System.out.println(getPosPlayers(5));//prints out all the players in the Center postion
    }
    public String missingPosNames(Team team){
      String ans =" Missing Positions: " ;
      String missing = team.missingPositions();
      for(int i=0;i<missing.length()-1;i+=2){
        ans += position(Integer.parseInt(missing.substring(i,i+1))) + " ";
      }
      if(team.size()<5) return ans;
      else return "";
    }
    //returns all the players in the ___ position
    public String getPosPlayers(int pos){//pos is the pos e.g. 1 is for PG, 2 is for SG, ect...
      String ans="";
      for (int i=0;i<80;i++){
        if(_draftArray[i] == null) ans += "";
        else if (_draftArray[i].getPosition() == pos)//checks all items in the draftboard
          ans+= "Rank: " + (i+1) + ", " + _draftArray[i].toString()+ "\n";//shows the player's name and stats
      }
      return ans;
    }

    //returns an object array of player with highest rank that fits position criteria(used for AI drafting) and its ranking
    public Object[] bestPlayer(Team team){
      Object[]ans = new Object[2];
      int c = 0;
      while(c<80){
        Player p = _draftArray[c];
        if(p!=null && team.getTeam()[p.getPosition()-1] == null){ //null means available spot for player of position
            ans[0] = p;
            _draftArray[c] = null;
            break;
        }
        else c++;
        }
        ans[1] = c+1;
      return ans;
  }

    public void AIdraft(Team team){
      if(team.teamFull())System.out.print("");
      else{
      Object[] a = bestPlayer(team);
      Player p = (Player)a[0];
      int rank = (int)a[1];
      p.setTeam(team);
      team.getTeam()[p.getPosition()-1]= p; ////PG in index 0,SG in index 1 and so on ...
      System.out.println(team.getName() + " drafted " + p.getName() + "(" + rank + ")"); //need way to get player rank
    }
}
    public boolean draftPlayer(Team team,int prospectRank){
        int size = team.size();
        Player p = _draftArray[prospectRank-1];
          if (p == null)System.out.println("Player already drafted");
          else{
          if(team.getTeam()[p.getPosition()-1] != null)System.out.println("Already have a " + position(p.getPosition()) + missingPosNames(team));
          else{
            team.getTeam()[p.getPosition()-1]= p; ////PG in index 0,SG in index 1 and so on ...
            _draftArray[prospectRank-1]=null;
            p.setTeam(team);
            System.out.print(team.getName() + " drafted " + p.getName() + "(" + prospectRank + ").");
            System.out.println(missingPosNames(team));
          }
        }
        return team.size() == size + 1;
      }
      //helper for shuffle function
      public void swap(Team[] league, int i, int j) {
              Team t = league[i];
              league[i] = league[j];
              league[j] = t;
          }

      // takes an array of teams and shuffles it
      public Team[] shuffle(Team[] league){
        Team[]aux = new Team[16]; //create aux array so league isnt changed
        for(int i = 0;i<16;i++){
          aux[i] = league[i];
        }
              int N = aux.length;
              for (int i = 0; i < N; i++) {
                  int j = i + (int) (Math.random() * (N-i));   // between i and N-1
                  swap(aux, i, j);
              }
      return aux;
          }
//adds shuffled team[] to snake draft dlinkedlist
public void fillSnake(Team[] teams){
   for(int i=0;i<teams.length;i++) _snake.addLast(teams[i]);
}
//returns draft position of the user(initially)
  public int userDpos(){
  DNode current = _snake.getFirst();
  int pos = 0;
  while(current.getValue()!= _user){
    current = _snake.getNext(current);
    pos++;
  }
  return pos;
  }
  public boolean leagueDraft(){
    boolean ans = true;
    return ans;
  }

  public String teamPlayers(Team team){
    return team.toString();
  }
//AI snake draft functions
public void rightToUser(){
  DNode current = _snake.getFirst();
  while(current.getValue() != _user){
    if(current.getValue().getName().equals("Dummy 1"))System.out.print("");
    else AIdraft(current.getValue());
    current = _snake.getNext(current);
  }

}
public void rightToEnd(){
  DNode current = _snake.getNext(userNode);
  while(!current.getValue().getName().equals("Dummy 2")){
    AIdraft(current.getValue());
    current = _snake.getNext(current);
  }
}
public void leftToUser(){
  DNode current = _snake.getLast();
  while(current.getValue() != _user){
    if(current.getValue().getName().equals("Dummy 2"))System.out.print("");
    else AIdraft(current.getValue());
    current = _snake.getPrevious(current);
  }
}
public void leftToStart(){
  DNode current = _snake.getPrevious(userNode);
  while(!current.getValue().getName().equals("Dummy 1")){
    AIdraft(current.getValue());
    current = _snake.getPrevious(current);
  }
}
public String toString(){ //prints team names
    String ans ="";
    for(int i=0;i<16;i++) ans += getTeams()[i].getName();
    return ans;
  }

public Team findTeam(String teamname){
 for(int i=0;i<16;i++){
   Team t = getTeams()[i];
   if(t.getName().equals(teamname)) return t;
}
return null;
}
public Player findPlayer(Team team,String name){
  for(int i=0;i<5;i++){
  Player p = team.getTeam()[i];
  if(p.getName().equals(name)) return p;
  }
  return null;
}
public void trade(Team otherT,Player otherP, Player userP){
  otherT.getTeam()[otherP.getPosition()-1] = userP;
  _user.getTeam()[userP.getPosition()-1]=otherP;
  userP.setTeam(otherT);
  otherP.setTeam(_user);
}
public String trade(String other, String otherPlayer, String userPlayer){
    Team otherT = findTeam(other);
    Player otherP =  findPlayer(otherT, otherPlayer);
    Player userP = findPlayer(_user,userPlayer);
    if(otherT == null)return(other + " does not exist.");
    else if(userP == null)return(userPlayer + " is not on your team!");
    else if(otherP == null)return(otherPlayer + " does not exist in team " + other + ".");
    else if(otherT != null && otherP != null && userP != null){
      if(otherP.getPosition() == userP.getPosition()){
        if(Math.abs(otherP.getOverall()-userP.getOverall())<= 1){
          trade(otherT,otherP,userP);
          canTrade = false;
            return("Trade Successful");
          }
        else if(Math.abs(otherP.getOverall() - userP.getOverall()) <= 5 && (int)Math.random()*100 < 8){
          trade(otherT,otherP,userP);
          canTrade = false;
          return("Trade Successful");
          }
        else if((Math.abs(otherP.getOverall() - userP.getOverall()) < 5 && (Math.random()*100 < 1.5))){
          trade(otherT,otherP,userP);
          canTrade = false;
          return("Trade Successful");
          }
          else{
            canTrade = false;
            return (other + " rejected your trade.");
          }
    }
    else return("Player positions don't match. Try again");
  }

return "Trade failed.";
}

public ArrayList<Team> rankSort(){ //sorts teams by win percentage
  ArrayList<Team> aux = new ArrayList<Team>();
  for(int h=0;h<16;h++)aux.add(_league[h]);
  for(int i=1;i<aux.size();i++){
    int j=i;
    while(j>0&&aux.get(j).getPercentage()>aux.get(j-1).getPercentage()){
      aux.set(j-1,aux.set(j,aux.get(j-1)));
      j--;
    }
  }
  return aux;
}
public String[] formatRanks(ArrayList<Team> r){ //format rankings to show team name,record and win percentage
  String[] rank = new String[16];
  for(int i=0;i<16;i++){
    Team a = r.get(i);
    rank[i]= i+1 + ") " + a.getName() + " " + a.getWins() + "-" + a.getLosses() + "(" + (float)a.getPercentage() + ")";
  }
  return rank;
}
public Team[] topEight(){
  ArrayList<Team> r = rankSort();
  Team[] top8 = new Team[8];
  for(int i=0;i<8;i++) top8[i] = r.get(i);
  return top8;
}
public void printRank(String[]rank){
  for(int i=0;i<16;i++)System.out.println(rank[i] + "\n");
}
public int userRanking(){
  ArrayList<Team> r = rankSort();
  for(int i=0;i<r.size();i++){
    if(r.get(i).getNumber() == 1)return i+1;
  }
  return -1;
}
}
