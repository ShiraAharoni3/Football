import java.util.ArrayList;
import java.util.List;

public class Player
{
    private int id ;
    private String firstName ;
    private String lastName ;
    private List<Goal> goalsScored;


    public List<Goal> getGoalsScored() {
        return goalsScored;
    }

    public Player (int id , String firstName , String lastName  )
    {
        this.id = id ;
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.goalsScored = new ArrayList<>() ;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addGoal(Goal goal)
    {
        goalsScored.add(goal);
    }

}
