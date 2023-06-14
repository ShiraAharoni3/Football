import java.util.List;

public class Match
{
    private int id ;
    private Team homeTeam ;
    private Team awayTeam;
    private List<Goal> goals ;

    public Match ( Team homeTeam , Team awayTeam , List<Goal> goals)
    {
        this.homeTeam = homeTeam ;
        this.awayTeam = awayTeam ;
        this.goals = goals ;
    }

    public int getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public List<Goal> getGoals() {
        return goals;
    }
}
