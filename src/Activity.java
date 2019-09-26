public class Activity {
    private String action;
    private int time;

    public Activity(String action, int time){
        this.action = action;
        this.time = time;
    }

    public String getAction(){
        return this.action;
    }

    public int getTime(){
        return this.time;
    }

    public void setAction(String action){
        this.action = action;
    }

    public void setTime(int time){
        this.time = time;
    }
}
