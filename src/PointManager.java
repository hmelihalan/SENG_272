public class PointManager {
    Child child;
    public static final int MAX_POINTS = 1000;
    public static final int MAX_LEVEL = 4;

    public PointManager(Child child) {
        this.child = child;
    }

    void addPoints(int points) {
        if (points < 0) {
            System.out.println("Error: Negative points are not allowed.");
            return;
        }
        child.points += points;
        if (child.points > MAX_POINTS) {
            System.out.println("Points exceeded the maximum limit (" + MAX_POINTS + "). Resetting points to 0.");
            child.points = 0;
        }
    }


    void printPoints() {
        System.out.println("Child Points: " + child.points);
    }

    void printStatus() {
        System.out.println("Child Level: " + child.level);
        System.out.println("Child Average Points: " + child.averagePnts);
    }

    // Calculating average points of child for level
    public static void updateLevel(Child child) {
        if (child.averagePnts < 40)
            child.level = 1;
        else if (child.averagePnts < 60)
            child.level = 2;
        else if (child.averagePnts < 80)
            child.level = 3;
        else
            child.level = MAX_LEVEL;
    }

}
