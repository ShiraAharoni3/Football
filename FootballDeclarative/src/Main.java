import java.io.*;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args)
    {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);

        LeagueManager leagueManager = new LeagueManager();
//        leagueManager.readTeamNamesFromFile("C:\\Users\\משתמש\\IdeaProjects\\FootballDeclarative\\src\\footballLeague.csv");
        leagueManager.readTeamNamesFromFile(".\\src\\footballLeague.csv");
        leagueManager.generateMatchSchedule();
        leagueManager.playMatches();
        List<Player> tempPlayer = LeagueManager.generateRandomPlayers();
        /*for (Player player : tempPlayer)
        {
            System.out.println(player.getFirstName() +" "+ player.getLastName());
        }
        */
        List<Match> teamMatches = leagueManager.findMatchesByTeam(1);
        System.out.println("Matches of Team 1:");
        for (Match  match : teamMatches) {
            System.out.println("Match ID: " + match.getId());
            System.out.println("Home Team: " + match.getHomeTeam().getName());
            System.out.println("Away Team: " + match.getAwayTeam().getName());
            System.out.println("Goals: " + match.getGoals().size());
            System.out.println("----------");
        }

        List<Team> topScoringTeams = leagueManager.findTopScoringTeams(3);
        System.out.println("Top Scoring Teams:");
        for (Team team : topScoringTeams) {
            System.out.println("Team ID: " + team.getId());
            System.out.println("Team Name: " + team.getName());
            System.out.println("Goals Scored: " + team.getGoalsScored().size());
            System.out.println("----------");
        }

        List<Player> prolificPlayers = leagueManager.findPlayersWithAtLeastNGoals(2);
        System.out.println("Prolific Players:");
        for (Player player : prolificPlayers) {
            System.out.println("Player ID: " + player.getId());
            System.out.println("Player Name: " + player.getFirstName() + " " + player.getLastName());
            System.out.println("Goals Scored: " + player.getGoalsScored().size());
            System.out.println("----------");
        }

        Team teamInPosition = leagueManager.getTeamByPosition(2);
        System.out.println("Team in Position 2:");
        if (teamInPosition != null) {
            System.out.println("Team ID: " + teamInPosition.getId());
            System.out.println("Team Name: " + teamInPosition.getName());
            System.out.println("Points: " + teamInPosition.getPoints());
            System.out.println("----------");
        } else {
            System.out.println("No team found in the specified position.");
            System.out.println("----------");
        }

        Map<Integer, Integer> topScorers = leagueManager.getTopScorers(5);
        System.out.println("Top Scorers:");
        for (Map.Entry<Integer, Integer> entry : topScorers.entrySet()) {
            int playerId = entry.getKey();
            int goalsScored = entry.getValue();
            System.out.println("Player ID: " + playerId);
            System.out.println("Goals Scored: " + goalsScored);
            System.out.println("----------");
        }
    }
}

//        File file = new File("./footootbalLeague.csv");

/*        try
        {
            File file = new File("src/footballLeague.csv");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
            String line;
            while((line=br.readLine())!=null)
            {
                sb.append(line);      //appends line to string buffer
                sb.append("\n");     //line feed
            }
            fr.close();    //closes the stream and release the resources
            System.out.println("Contents of File: ");
            System.out.println(sb.toString());   //returns a string that textually represents the object
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }*/
