public class BoxScore{

    private String[][] _boxScore;
    private Team _team;
    private int Score;

    public static int[] setStats(Player[] team){
        int[] stats = new int[4];
        for(int i = 0;i < 4;i++)
            stats[i] = 0;
        for (int j = 0; j < 5; j++)
            stats[0] += team[j].getScore();
        for (int j = 0; j < 5; j++)
            stats[1] += team[j].getRebound();
        for (int j = 0; j < 5; j++)
            stats[2] += team[j].getPass();
        for (int j = 0; j < 5; j++)
            stats[3] += team[j].getDefense();
        return stats;
    }

    public BoxScore(Team team, boolean isWinner, int Score){
        Player[] _team = team.getTeam();
        _boxScore = new String[7][6];
	    _boxScore[0][0] = "BoxScore";
        _boxScore[0][1] = "Points";
        _boxScore[0][2] = "Rebounds";
        _boxScore[0][3] = "Assists";
        _boxScore[0][4] = "Defensive-Plays";
        _boxScore[0][5] = "Comments";
        _boxScore[1][0] = "Team";
        for (int i = 0;i < 5;i++){
            _boxScore[i+2][0] = _team[i].getName();
        }
        String[] comments = {" had a poor shooting night.", " was great defensively", " had a monster dunk", " airballed a three", " missed a wide-open lay-up", " , Mama, there goes that man", " , Hand Down, Man Down", " Come get in my poster", " When his number is called he's ready", " He's a knockdown shooter"};
        int[] stats = setStats(_team);
        if (isWinner){
            _boxScore[1][1] = Score + "";
	    int r = ((int)(Math.random() *20)) + 20;
	    int a = ((int)(Math.random() *20)) + 20;
	    int d = ((int)(Math.random() *10)) + 1;
            _boxScore[1][2] = r + "";
            _boxScore[1][3] = a + "";
            _boxScore[1][4] = d + "";
            _boxScore[1][5] = "Winner";
            int everything = 0;
	    int ran = 0;
            for (int i = 0; i < 5;i++){
                _boxScore[i+2][1] = (int)((float)((team.getTeam()[i].getScore()*100) / stats[0]) * Score / 100) + "";
                everything += Integer.parseInt(_boxScore[i+2][1]);
            }
	    ran =(int)(Math.random() * 4) + 2;
            _boxScore[ran][1] = Integer.parseInt(_boxScore[ran][1]) + (Score - everything) + "";
            everything = 0;

	    for (int i = 0; i < 5;i++){
                _boxScore[i+2][2] = (int)((float)((team.getTeam()[i].getRebound()*100) / stats[1]) * r / 100) + "";
                everything += Integer.parseInt(_boxScore[i+2][2]);
	    }
	    ran =(int)(Math.random() * 4) + 2;
	    _boxScore[ran][2] = Integer.parseInt(_boxScore[ran][2]) + (r - everything) + "";
	    everything = 0;

            for (int i = 0; i <5;i++){
		_boxScore[i+2][3] = (int)((float)((team.getTeam()[i].getPass()*100) / stats[2]) * a / 100) + "";
                everything += Integer.parseInt(_boxScore[i+2][3]);
	    }
	    ran =(int)(Math.random() * 4) + 2;
	    _boxScore[ran][3] = Integer.parseInt(_boxScore[ran][3]) + (a - everything) + "";
	    everything = 0;

            for (int i = 0; i <5;i++){
		_boxScore[i+2][4] = (int)((float)((team.getTeam()[i].getDefense()*100) / stats[3]) * d / 100) + "";;
		everything += Integer.parseInt(_boxScore[i+2][4]);
	    }
	    ran =(int)(Math.random() * 4) + 2;
	     _boxScore[ran][4] = Integer.parseInt(_boxScore[ran][4]) + (d - everything) + "";


            for (int i =0; i < 5; i++){
                _boxScore[i+2][5] = _team[i].getName() + comments[(int)(Math.random() * 10)];
            }
        }

        else{
	    _boxScore[1][1] = Score + "";
	    int r = ((int)(Math.random() *20)) + 20;
	    int a = ((int)(Math.random() *20)) + 20;
	    int d = ((int)(Math.random() *10)) + 1;
            _boxScore[1][2] = r + "";
            _boxScore[1][3] = a + "";
            _boxScore[1][4] = d + "";
            _boxScore[1][5] = "Loser";
            int everything = 0;
	    int ran = 0;
            for (int i = 0; i < 5;i++){
                _boxScore[i+2][1] = (int)((float)((team.getTeam()[i].getScore()*100) / stats[0]) * Score / 100) + "";
                everything += Integer.parseInt(_boxScore[i+2][1]);
            }
	    ran =(int)(Math.random() * 4) + 2;
            _boxScore[ran][1] = Integer.parseInt(_boxScore[ran][1]) + (Score - everything) + "";
            everything = 0;

	    for (int i = 0; i < 5;i++){
                _boxScore[i+2][2] = (int)((float)((team.getTeam()[i].getRebound()*100) / stats[1]) * r / 100) + "";
                everything += Integer.parseInt(_boxScore[i+2][2]);
	    }
	    ran =(int)(Math.random() * 4) + 2;
	    _boxScore[ran][2] = Integer.parseInt(_boxScore[ran][2]) + (r - everything) + "";
	    everything = 0;

            for (int i = 0; i <5;i++){
		_boxScore[i+2][3] = (int)((float)((team.getTeam()[i].getPass()*100) / stats[2]) * a / 100) + "";
                everything += Integer.parseInt(_boxScore[i+2][3]);
	    }
	    ran =(int)(Math.random() * 4) + 2;
	    _boxScore[ran][3] = Integer.parseInt(_boxScore[ran][3]) + (a - everything) + "";
	    everything = 0;

            for (int i = 0; i <5;i++){
		_boxScore[i+2][4] = (int)((float)((team.getTeam()[i].getDefense()*100) / stats[3]) * d / 100) + "";;
		everything += Integer.parseInt(_boxScore[i+2][4]);
	    }
	    ran =(int)(Math.random() * 4) + 2;
	     _boxScore[ran][4] = Integer.parseInt(_boxScore[ran][4]) + (d - everything) + "";


            for (int i =0; i < 5; i++){
                _boxScore[i+2][5] = _team[i].getName() + comments[(int)(Math.random() * 10)];
            }
        }
    }


    public String toString(){
	String ans = "";
	for(int row = 0; row < 7; row++) {
            for(int col = 0; col < 6; col++) {
		ans +=  _boxScore[row][col] + " ";
            }
	    ans += "\n";
	}
	return ans;
    }
}
