package footballTeam;

public class Player {

    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        ValidatorName(name);
        setName(name);
        ValidatorStats("Endurance",endurance);
        setEndurance(endurance);
        ValidatorStats("Sprint",sprint);
        setSprint(sprint);
        ValidatorStats("Dribble",dribble);
        setDribble(dribble);
        ValidatorStats("Passing",passing);
        setPassing(passing);
        ValidatorStats("Shooting",shooting);
        setShooting(shooting);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public double overallSkillLevel(){
        return (this.dribble+this.endurance+this.passing+this.shooting+this.sprint)/5.0;

    }

    public static void ValidatorStats(String name,int number){
        if(number<0 || number>100){
            throw new IllegalArgumentException(String.format("%s should be between 0 and 100.",name));
        }
    }

    public static void ValidatorName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
    }

}
