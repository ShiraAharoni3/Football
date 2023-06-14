public class Goal
{
    private int idGoal ;
    private int minute ;
    private Player scorer ;

    public Goal (int idGoal , int minute , Player scorer)
    {
         this.idGoal = idGoal ;
         this.minute = minute ;
         this.scorer = scorer ;
    }

    public int getIdGoal()
    {
        return idGoal;
    }

    public int getMinute() {
        return minute;
    }

    public Player getScorer() {
        return scorer;
    }
}
