public class Playoffs{
    private Stack<Team> s1,s2;
    private Team[] top8;

      public Playoffs(Team[] t){
        top8 = t;
        s1=new NodeStack<Team>();
        s2=new NodeStack<Team>();
        s1.push(top8[0]);
        s1.push(top8[7]);
        s1.push(top8[3]);
        s1.push(top8[4]);
        s1.push(top8[2]);
        s1.push(top8[5]);
        s1.push(top8[1]);
        s1.push(top8[6]);
        }
    public void play(int round){
        if(round == 1){
            for(int i=0;i<4;i++)
            s2.push(bestOfFive(s1.pop(),s1.pop()));
      }
        else if(round == 2){
            for(int j=0;j<2;j++)s1.push(bestOfFive(s2.pop(),s2.pop()));
        }
        else s2.push(bestOfFive(s1.pop(),s1.pop()));
    }
    public Team getChamp(){
  return s2.top();
  }
    public Team bestOfFive(Team a,Team b){
        int aWins= 0;
        int bWins = 0;
        System.out.println(a.getName() + " vs. " + b.getName());
        System.out.println("====================");
        for(int i=0;i<5;i++){
        if(aWins < 3 && bWins < 3){
            Object[] m = new Match().match(a,b);
            Team winner = (Team)m[0];
            if(winner.getName().equals(a.getName())){
                aWins ++;
                System.out.println(a.getName() + " won" + " game " + (i+1));
            }
            else{
                bWins++;
            System.out.println(b.getName() + " won" + " game " + (i+1));
            }
        }
      }
        if(aWins == 3){
            System.out.println(a.getName() + " won the series " + aWins + "-" + bWins);
            System.out.println();
            return a;
        }
        else{
        System.out.println(b.getName() + " won the series " + bWins + "-" + aWins);
        System.out.println();
        return b;
        }
    }

}
