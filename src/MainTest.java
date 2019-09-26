import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    Activity testActivity = new Activity("run", 100);
    Activity testActivity2 = new Activity("run", 400);
    Activity testActivity3 = new Activity("jump", 200);
    Activity testActivity4 = new Activity("jump", 500);
    Activity testActivity5 = new Activity("swim", 200);
    Activity testActivity6 = new Activity("swim", 200);

    @AfterEach
    public void resetLists(){
        Main.resetLists();
    }

    @Test
    public void sanityCheck(){
        assertEquals(0, 0);
    }

    @Test
    public void addOneAction(){
        Main.addAction(testActivity);
        String finalJson = Main.getStats();
        assertEquals("{\"action\":\"run\", \"avg\":100} ", finalJson);
    }

    @Test
    public void addTwoSameActionsForAverageTime(){
        Main.addAction(testActivity);
        Main.addAction(testActivity2);
        String finalJson = Main.getStats();
        assertEquals("{\"action\":\"run\", \"avg\":250} ", finalJson);
    }

    @Test
    public void addTwoDifferentActions(){
        Main.addAction(testActivity);
        Main.addAction(testActivity3);
        String finalJson = Main.getStats();
        assertEquals("{\"action\":\"run\", \"avg\":100} {\"action\":\"jump\", \"avg\":200} ", finalJson);
    }

    @Test
    public void twoSameActionsOneDifferent(){
        Main.addAction(testActivity);
        Main.addAction(testActivity2);
        Main.addAction(testActivity3);
        String finalJson = Main.getStats();
        assertEquals("{\"action\":\"run\", \"avg\":250} {\"action\":\"jump\", \"avg\":200} ", finalJson);
    }

    @Test
    public void twoSameAndTwoDifferentActions(){
        Main.addAction(testActivity);
        Main.addAction(testActivity2);
        Main.addAction(testActivity3);
        Main.addAction(testActivity4);
        String finalJson = Main.getStats();
        assertEquals("{\"action\":\"run\", \"avg\":250} {\"action\":\"jump\", \"avg\":350} ", finalJson);
    }

    @Test
    public void threeDifferentActions(){
        Main.addAction(testActivity);
        Main.addAction(testActivity2);
        Main.addAction(testActivity3);
        Main.addAction(testActivity4);
        Main.addAction(testActivity5);
        Main.addAction(testActivity6);
        String finalJson = Main.getStats();
        assertEquals("{\"action\":\"run\", \"avg\":250} {\"action\":\"jump\", " +
                "\"avg\":350} {\"action\":\"swim\", \"avg\":200} ", finalJson);
    }
}