import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Activity> actionsTaken = new ArrayList<>();
    private static ArrayList<String> actionTypes = new ArrayList<>();

    /**
     * The main method that runs the program. Takes in user input for actions taken.
     *  Json inputs must be in one line
     * @param args
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean stopper = false;
        Gson g = new Gson();
        while(stopper == false) {
            System.out.println("Please enter an action JSON, type exit when you are done and want average times.");
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
                catch ( JsonSyntaxException e ) {
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
        if(!actionTypes.contains(action.getAction())){
            actionTypes.add(action.getAction());
        }
    }

    /**
     *  Takes the activities and adds their total time together and averages them based off the activity type
     * @return average times for actions taken
     */
    public static String getStats() {
        int totalTime = 0;
        int actionCount = 0;
        ArrayList<Integer> averageTimes = new ArrayList<Integer>();

        for (int i = 0; i < actionTypes.size(); i++) {
            for (int j = 0; j < actionsTaken.size(); j++) {

                //comparing the action type to make sure we're adding the right times to the right action
                if(actionTypes.get(i).equals(actionsTaken.get(j).getAction())) {
                    totalTime += actionsTaken.get(j).getTime();
                    actionCount++;
                }
            }
            Integer average = totalTime / actionCount;
            averageTimes.add(average);
            totalTime = 0;
            actionCount = 0;
        }
        String finalJson = generateFinalJson(averageTimes);
        return finalJson;
        //{"action" :"jump", "time": 100}
    }

    public static String generateFinalJson(ArrayList<Integer> averageTimes){
        String finishedJson = "";
        for(int i = 0; i < actionTypes.size(); i++){
            finishedJson = finishedJson +  "{ \"action\":\"" + actionTypes.get(i) +
                    "\", \"avg\":" + averageTimes.get(i) + "}";

        }
        return finishedJson;
    }
}
