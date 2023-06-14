import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Team
{
    private int id ;
    private int points ;
    private String Name ;
    private List<Player> players;

    public Team ( int id , String Name , List<Player> players)
    {
        this.id = id ;
        this.Name = Name ;
        this.players = players ;
        this.points = 0 ;
    }

    public int getId()
    {
        return id;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public String getName()
    {
        return Name;
    }
    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void removeAllPlayers() {
        players.clear();
    }

    public int getTotalGoalsScored() {
        int totalGoals = 0;
        for (Player player : players) {
            totalGoals += player.getGoalsScored().size();
        }
        return totalGoals;
    }
    public List<Goal> getGoalsScored() {
        List<Goal> goals = new ArrayList<>();
        for (Player player : players) {
            goals.addAll(player.getGoalsScored());
        }
        return goals;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

}

