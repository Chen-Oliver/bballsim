public class Match{

    private Team _winner, _loser;
    private int _score;
      public Object[] match(Team a, Team b){
        Object[]ans = new Object[3];
        int A= a.getOverall();
        int B= b.getOverall();
        int overall=Math.abs(A-B);
        int percent=overall + 55;
        a.addGP();
        b.addGP();
        _score = ((int)(Math.random() * 25)) + 90;
        ans[0]=determineWinner(a,b,percent);
          if(a.getNumber()==1 || b.getNumber()==1){
            ans[1]=winnerStats(_score);
            ans[2]=loserStats(_score);
          }
          return ans;
      }
    public Team determineWinner(Team a, Team b, int percent){
        int A= a.getOverall();
        int B= b.getOverall();
        int ran = (int)(Math.random() * (100+(percent%100)));
        if (A >= B){
            if (ran < percent){
                a.addWin();
                _winner = a;
                b.addLoss();
                _loser = b;
                return a;
            }
            if (ran > percent){
                a.addLoss();
                _loser = a;
                b.addWin();
                _winner = b;
                return b;
            }
        }
        if (B > A){
            if (ran < percent){
                a.addLoss();
                _loser = a;
                b.addWin();
                _winner = b;
                return b;
            }
            if (ran > percent){
                a.addWin();
                _winner = a;
                b.addLoss();
                _loser = b;
                return a;
            }
        }
        if (A == B){
          if (ran < 50){
                a.addWin();
                _winner = a;
                b.addLoss();
                _loser = b;
                return a;
            }
            if (ran > 50){
                a.addLoss();
                _loser = a;
                b.addWin();
                _winner = b;
                return b;
            }
        }
        return null;
      }

    public BoxScore winnerStats(int score){
        BoxScore w = new BoxScore(_winner,true,score);
        return w;
    }

    public BoxScore loserStats(int score){
        int s = score - (int)((Math.random() * 19) + 1);
        BoxScore l = new BoxScore(_loser, false,score);
        return l;
    }
    public static boolean getsHealed(Player p){
      if ((Math.random()*100)<=(100-p.getPhysical()))
        return true;
      return false;
    }

    public static void getsHealed(Team a){
      Player[] A=a.getTeam();
      for(int i=0;i<5;i++){
        if(A[i].isInjured()){
          if(getsHealed(A[i])){
            A[i].setInjury(false);
            A[i].healed();
            System.out.println("\t"+A[i].getName()+" is Healed");
          }
        }
      }
    }

    public static boolean isInjured(Player p, Team Opponents){
      Player[] x=Opponents.getTeam();
      double chance=5;
      for (int i=0;i<5;i++)
          chance+=Math.abs((p.getPhysical()-x[i].getPhysical())/50);
      if (Math.random()*100<=chance){
        System.out.println("\t"+p.getName()+ " is injured");
        return true;
      }
      return false;
    }
    public static void getsInjured(Team a,Team b){
      Player[] A= a.getTeam();
      Player[] B= b.getTeam();
      for(int i=0;i<5;i++){
        if(isInjured(A[i],b)){
         A[i].setInjury(true);
         A[i].injury();
        }
      }
    }

}
