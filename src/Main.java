import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Activity> actionsTaken = new ArrayList<Activity>();

    /**
     * The main method that runs the program. Takes in user input for actions taken.
     *  Json inputs must be in one line
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        boolean stopper = false;

        Gson g = new Gson();

//        Activity activity = g.fromJson("{\"action\": \"Jump\", \"time\": \"100\"}", Activity.class);
//        System.out.println(activity.getAction()); //John
//        System.out.println(activity.getTime()); //John

        //{"action" :"jump", "time": 100}



        while(stopper == false) {
            System.out.println("Please enter an action JSON, type exit when you are done.");
            String input = scanner.nextLine();
            if(input.equals("exit")){
                System.out.println(getStats());
                stopper = true;
                scanner.close();
            }
            else {
                try {
                    Activity activity = g.fromJson(input, Activity.class);
                    addAction(activity);
                }
                catch (  JsonSyntaxException e) {
                    System.out.println("Invalid JSON entered: " + input + " Please try a valid single line json object \n");
                }
            }
        }
    }

    /**
     * Adds an inputted action to the arraylist that stores our inputs from the user
     * @param action
     */
    public static void addAction(Activity action){
        actionsTaken.add(action);
    }

    /**
     *  Takes the activities and adds their total time together and averages them based off the activity type
     * @return average times for actions taken
     */
    public static String getStats(){
        int totalTime = 0;
        for(int i = 0; i < actionsTaken.size(); i++){
            System.out.println(actionsTaken.get(i).getTime());
            totalTime += actionsTaken.get(i).getTime();
        }
        return Integer.toString(totalTime);
    }
}
