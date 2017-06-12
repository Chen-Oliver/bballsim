public class Player{
    private String _name;
    private int _overall, _score, _rebound, _pass, _defense, _position, _physical, _gamesInj;
    private boolean _isInjured;
    private Team _team;
    public Player(String name, int score, int rebound, int pass, int defense, int physical, int position){
 _name = name;
  _team = null; //team is set after player is drafted
 _score = score;
 _rebound = rebound;
 _pass = pass;
 _defense = defense;
 _position = position;
 _physical = physical;
 _isInjured = false;
 _gamesInj = 0;
 _overall = (int)((score * .3) + (rebound * .25) + (defense * .25) + (pass *.1) + (physical * .1));
    }

    public String getName(){
 return _name;
    }
    public Team getTeam(){
      return _team;
    }
    public int getOverall(){
 return _overall;
    }

    public int getScore(){
 return _score;
    }

    public int getRebound(){
 return _rebound;
    }

    public int getPass(){
 return _pass;
    }

    public int getDefense(){
 return _defense;
    }

    public int getPosition(){
 return _position;
    }

    public int getPhysical(){
 return _physical;
    }

    public boolean isInjured(){
 return _isInjured;
    }

    public int setScore(int newScore){
      int ans = _score;
 _score = newScore;
  return ans;
    }

    public int setRebound(int newRebound){
      int ans = _rebound;
 _rebound = newRebound;
  return ans;
    }
    public Team setTeam(Team newTeam){
      Team ans = getTeam();
      _team = newTeam;
      return ans;
    }
    public int setPass(int newPass){
    int ans = _pass;
 _pass = newPass;
  return ans;
    }

    public int setDefense(int newDefense){
      int ans = _defense;
 _defense = newDefense;
  return ans;
    }

    public int setPhysical(int newPhysical){
      int ans = _physical;
 _physical = newPhysical;
  return ans;
    }
    public boolean setInjury(boolean a){
      _isInjured=a;
      return a;
    }

    public void injury(){
 _isInjured = true;
 _gamesInj = ((int)(Math.random() * 15)) + 1;
 _score = _score - (int)((Math.random() * 15) + 7);
 _rebound = _rebound - (int)((Math.random() * 15) + 7);
 _pass = _pass - (int)((Math.random() * 15) + 7);
 _defense = _defense - (int)((Math.random() * 15) + 7);
 _physical = _physical - (int)((Math.random() * 15) + 7);
    }

    public void healed(){
 _isInjured = false;
 _score = _score + (int)((Math.random() * 15) + 7);
 _rebound = _rebound + (int)((Math.random() * 15) + 7);
 _pass = _pass + (int)((Math.random() * 15) + 7);
 _defense = _defense + (int)((Math.random() * 15) + 7);
 _physical = _physical + (int)((Math.random() * 15) + 7);
    }
  public String position(int pos){
    if(pos == 1) return "PG";
    else if(pos == 2) return "SG";
    else if(pos == 3) return "SF";
    else if(pos == 4) return "PF";
    return "C";
  }
    public String toString(){
      return "Name: " + _name + ", Scoring: " + _score + ", Rebounding: " + _rebound + ", Passing: " + _pass + ", Defense: " + _defense
      +", Physical: " + _physical + ", Position: " + _position + "(" + position(_position) +")" + ", Overall: " + _overall;
    }
}
