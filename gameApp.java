import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class gameApp{
  public static void main(String[]args){
    boolean cont = true; //used to determine if AI should continue drafting
    //boolean canTrade = false;
    int round = 1; //used to determine if in drafting,season or playoff phse
    League nba = new League();
    Team[] teams = nba.getTeams();
    String username = "";
    int POround = 1; //used for playoffs
    Playoffs po = null;
    for(int i=0;i<args.length;i++){
      if(i!= args.length-1)username += args[i] + " ";
      else username += args[i];
    }
    teams[0] = new Team(username); //creates user's team
    Team user = teams[0]; //user team is 1st team by default;
    nba.setUser(user);
    for(int i=1;i<16;i++)teams[i].setNumber(i+1);
    nba.fillSnake(); //creates the snake draft
    nba.setUserNode(nba.getSnake().get(nba.userDpos())); //sets the user node variable in league for use in drafting
    //ascii logo
          System.out.println("     ***********************************************************************");
          System.out.println("       ***************************************  ***************************" );
          System.out.println("         ************************************    ************************"   );
          System.out.println("           *************************** ******* ************************"     );
          System.out.println("             ***********************    **** ***********************"        );
          System.out.println("               **********************   **  **********************"          );
          System.out.println("                 *******************      **********************"            );
          System.out.println("                   ****************      *********************"              );
          System.out.println("                     *************  *    *******************"                );
          System.out.println("                       *********** *     *****************"                  );
          System.out.println("                         ********* *      **************"                    );
          System.out.println("                           ****** *   ***   **********"                      );
          System.out.println("                             ****  ********  *******"                        );
          System.out.println("                               *  ************  **"                          );
          System.out.println("                                 ****************"                           );
          System.out.println("                                   ************"                             );
          System.out.println("                                    *********"                               );
          System.out.println("                                      *****"                                 );
          System.out.println("                                       ***"                                  );
          System.out.println("|"+"\\"+"      |   _______         /\\            /       |    /     /        /     ");
          System.out.println("| \\     |  |       \\       /  \\          /        |  /     /         /      ");
          System.out.println("|  \\    |  |        )     /    \\        /         |/      /         /       ");
          System.out.println("|   \\   |  |_______/     /      \\      |   ___    |      |   ___   |  ___   ");
          System.out.println("|    \\  |  |       \\    /--------\\     | /    \\   |\\     | /    \\  | /    \\ ");
          System.out.println("|     \\ |  |        )  /          \\     \\     /   |  \\    \\     /   \\     / ");
          System.out.println("|      \\|  |_______/  /            \\      ---     |    \\    ---       ---   ");


    System.out.println("Welcome to the text-based Basketball simulation made by Oliver Chen, Suwamik Paul and Dewan Sunnah.");
    System.out.println("To get started you first need to draft a team of 5 players(PG,SG,SF,PF,C) using the 'draft <player rank>' command");
    System.out.println("To see the available players enter 'see draft'.");
    System.out.println("To see players by their positions enter 'seePG','seeSG','seeSF','seePF' or 'seeC'. \n");
    System.out.println("Your draft position:" + (nba.userDpos() +1) + "\n");
    Schedule sched = new Schedule(nba);
    nba.getSnake().addFirst(new Team("Dummy 1")); //add dummy nodes to deal with special cases with drafting
    nba.getSnake().addLast(new Team("Dummy 2"));

    nba.rightToUser(); //this only runs once to get to the user on the first round
    System.out.println();
    System.out.println("Your turn to draft.");
while(!(nba.getSnake().getPrevious(nba.getSnake().getLast()).getValue().teamFull()) || !teams[15].isFinished() || POround != 5){ //check if last team is full
    if(cont ==true && round < 6 && ((round == 3 && user.size() == 2) || (round==5 &&user.size()==4))){
      nba.rightToUser();
      System.out.println();
      System.out.println("Your turn to draft.");
    }

    if(cont ==true && round <6 && ((round == 2 && user.size() == 1) || (round==4 && user.size()==3))){
      nba.leftToUser();
      System.out.println();
      System.out.println("Your turn to draft.");
    }

      Scanner scanner = new Scanner(System.in);
      String entry = scanner.nextLine();
  if(round <= 5){
      if(entry.equals("see draft")){
        System.out.println(nba.getdraftBoard());
        cont = false;
      }

      if(entry.equals("seePG")){
        nba.getPG();
        cont = false;
      }

      if(entry.equals("seeSG")){
        nba.getSG();
        cont = false;
      }

      if(entry.equals("seeSF")){
        nba.getSF();
        cont = false;
      }

      if(entry.equals("seePF")){
        nba.getPF();
        cont = false;
      }

      if(entry.equals("seeC")){
        nba.getC();
        cont = false;
      }

      if(entry.indexOf("draft") == 0){
        try {
         cont = nba.draftPlayer(user,Integer.parseInt((entry.substring(entry.indexOf(" ") + 1))));
         System.out.println();

         if(cont){ //if player draft is successful the AI draft if not then a messgae is printed telling user to try again

           if(round == 1 || round == 3 || round == 5){
             nba.rightToEnd();
           }

           else if(round == 2 || round == 4){
             nba.leftToStart();
           }
           round++;
       }

      else
         System.out.println("Try drafting again." + nba.missingPosNames(user));

       }

        catch(NumberFormatException e) {
          System.out.println("Invalid draft command.");
          cont = false;
      }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid draft rank");

        }
    }
    else cont = false;
  }
  if(round > 5 && round < 7){ //ensures that this only prints once
    System.out.println();
  System.out.println("Drafting complete. Enter 'see teams' to see the teams. Enter 'trade' to trade players.");
    System.out.println("Trading may only be done once after a game.");
    System.out.println("Enter 'play game' to play the next game. Enter 'play season' to play entire season.");
    System.out.println("Enter 'rankings' to see team rankings.");
    cont = false;
    round++;
  }
    else if(round == 7 && !teams[15].isFinished()){  //add while(!finished)
    if(entry.equals("see teams")) for(int i=0;i<16;i++) System.out.println("\n" + teams[i].getName() + "\n" + teams[i]);
    if(entry.equals("trade") && nba.getTrade()){
      System.out.println("Enter the team of the player you wish to trade for and their name: <OtherTeamName>,<OtherPlayerName>,<YourPlayerName> with no spaces between the commas");

      String trade = scanner.nextLine();
      try{
        String[] data = trade.split(",");
        System.out.println(nba.trade(data[0],data[1],data[2]));
      }
      catch(NullPointerException e){ //remember to check if this is the correct exception
        System.out.println("Team and/or player(s) not found. Try trading again.");
      }
      catch(ArrayIndexOutOfBoundsException e){
        System.out.println("You didn't enter a trade!");
      }
      //set canTrade to true after game successfully played
    }
    else if(entry.equals("trade") && nba.getTrade() == false) System.out.println("Cannot make a trade currently. Try after a game has finished.");
    if(entry.equals("play game")){
      sched.play();
      nba.setTrade(true);
    }
    if(entry.equals("play season")){
      for(int i=teams[15].getGP();i<45;i++){
      sched.play();
      nba.setTrade(true);
  }
    }
    if(entry.equals("rankings")){
      nba.printRank(nba.formatRanks(nba.rankSort()));
    }

}
  if(teams[15].isFinished() && round < 8){
    nba.setTrade(false);
    System.out.println("The regular season has concluded. The rankings are as follows: ");
    nba.printRank(nba.formatRanks(nba.rankSort()));
    int userR = nba.userRanking();
    po = new Playoffs(nba.topEight());
    if(userR > 8)System.out.println("Your team was the " + userR + " seed. You will not be competing in the playoffs.");
    else System.out.println("Congratulations! Your team was the " + userR + " seed. You will compete in the playoffs for a chance at a championship.");
    System.out.println("Enter 'play series' to play all the series in a playoff round. Enter 'play all' to play the entire playoffs");
    round++;
  }
  else if(round >=8 && POround < 5){
    if(entry.equals("play series") && POround <4){
      po.play(POround);
      POround++;
    }
    if(entry.equals("play all") && POround <4){
      for(int i=POround;i<4;i++){
        po.play(POround);
        POround++;
      }
    }
    if(POround == 4){
      Team c = po.getChamp();
      if(c.getNumber()==1)System.out.println("Congratulations! You won an NBA Championship!!!");
      else System.out.println("The " + c.getName() + " are the NBA Champions!");
      System.out.println("Thanks for playing!");
      POround++;
    }
  }
    }
  }


  }
