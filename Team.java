	public class Team{
		private Player[] _team;
		private int _wins,_losses,_teamRank,_gamesPlayed,_teamNumber;
		private String _name;
		private double _percentage;
	public Team(String name){
			_name=name;
			_wins = _losses = _teamRank = _gamesPlayed=0;
			_team = new Player[5];
		}
	public double getPercentage(){
		return _percentage;
	}
	public void setPercentage(){
		_percentage = (this.getWins()+0.0)/this.getGP();
	}
	public Player[] getTeam(){
		return _team;
	}
	public int getOverall(){
		int ans = 0;
		for (int i = 0;i < 5;i++)
		ans += _team[i].getOverall();
		return (int)(ans / 5);
	}
	public int getNumber(){
		return _teamNumber;
	}
	public void setNumber(int number){
		_teamNumber = number;
	}
	public String getName(){
		return _name;
	}
	public int getGP(){
		return _gamesPlayed;
	}
	public int getWins(){
	      return _wins;
	}
	public int getLosses(){
		return _losses;
	}
	public int getTeamRank(){
		return _teamRank;
	}
	public int setRank(int rank){
		int ans = _teamRank;
		_teamRank = rank;
		return ans;
	}
	public void addGP(){
		_gamesPlayed++;
	}
	public void addWin(){
		 _wins ++;
	}
	public void addLoss(){
		_losses ++;
	}
	public int size(){
		int c = 0;
		for(int i=0;i<5;i++){
			if (_team[i] != null) c++;
		}
		return c;
	}
	public boolean teamFull(){
			return size() == 5;
	}
	public boolean isFinished(){
			return getGP() == 45; //16 teams play each other three times
	}
	public String missingPositions(){
		String ans = "";
		for(int i=0;i<5;i++){
			if(getTeam()[i] == null) ans += (i+1) + " ";
		}
		return ans ;
	}
	public String toString(){
			String ans = "";
			for(int i=0;i<_team.length;i++)ans += _team[i] + "\n";
			return ans;
	}
	}
