public class Schedule{

    private Team[][] _robinArray;
    private Team[] _allTeams;
    private CircleList _Teams;

    //schedule uses a round robin format to match teams against each other 3 times
    public Schedule(League L){
      _robinArray=new Team[8][2];
      _Teams= new CircleList();
      _allTeams=L.getTeams();
      Team x[]=L.getTeams();
      for(int i=0;i<16;i+=2)//creates a Clist with the odd# teams first then even;
        _Teams.add(new Node<Team>(x[i],null));
      for(int i=15;i>=0;i-=2)
        _Teams.add(new Node<Team>(x[i],null));
      _Teams.retreat();
      _robinArray[0][0]=_Teams.remove().getValue();//removes T1 from the Clist
      _Teams.advance();
      _Teams.advance();

      }
      public CircleList getTeams(){
        return _Teams;
      }
    public void playMatches(Team[][] t){
      Healed();
      for (int i=0;i<8;i++){
      Object[]m = new Match().match(t[i][0],t[i][1]);
      t[i][0].setPercentage();
      t[i][1].setPercentage();
      Team w= (Team)m[0];
      if(i==0){System.out.println("Match Results: ");
    System.out.println("=======================");}
      System.out.println("Team " + w.getName()+" won! \n" );
      if(t[i][0].getNumber() == 1 || t[i][1].getNumber() == 1){
     System.out.println((BoxScore)m[1]);
      System.out.println((BoxScore)m[2]);
      if(t[i][0].getNumber()==1){
      System.out.println("Your current record: " +t[i][0].getWins() + "-" + t[i][0].getLosses() + "\n");
    }
      else System.out.println("Your current record: " +t[i][1].getWins() + "-" + t[i][1].getLosses() + "\n");
      }
      }
      Injured();
    }
      public void play(){
        System.out.println("Today's matches:");
        System.out.println("============================");
        playMatches(setMatches(_Teams));
      }
      public void matchesDone(){//the list needs to be Advance in order to get new opponents
        _Teams.advance();
      }

      public Team[][] setMatches(CircleList L){
        for(int i=1; i<8; i++)
          _robinArray[i][0]=L.nextTeam();
        for (int i=7;i>=0;i--)
          _robinArray[i][1]=L.nextTeam();
        matchesDone();
        System.out.println(printRobin(_robinArray));
        return _robinArray;
      }

      public void Healed(){
        for(int i=0;i<16;i++){
          System.out.println("Team "+_allTeams[i].getName()+ " players healed:");
          Match.getsHealed(_allTeams[i]);
        }
      }
      public void Injured(){
        for(int i=0; i<8;i++){
          System.out.println("Team "+_robinArray[i][0].getName()+ " players injured:");
          Match.getsInjured(_robinArray[i][0],_robinArray[i][1]);
          System.out.println("Team "+_robinArray[i][1].getName()+ " players injured:");
          Match.getsInjured(_robinArray[i][1],_robinArray[i][0]);
        }
      }

      public String printRobin(Team[][] x){
        String ans="";
        for (int i=0;i<8;i++)
          ans+=x[i][0].getName()+" vs "+ x[i][1].getName()+"\n";
        return ans;
      }


    //public static void main(String[] args){
      //Team[] t=new Team[16];
      //for(int i=0; i<16;i++)
        //t[i]=new Team((i+1)+"");
      //Schedule test= new Schedule(t);
    //}
}
