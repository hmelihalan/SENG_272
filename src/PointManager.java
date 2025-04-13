public class PointManager {
    Child child=new Child();

    void ADD_POINTS(int points){
        child.points=child.points + points;
    }
    void PRINT_POINTS(){
        System.out.println(child.points);
    }
    void PRINT_STATUS(){
        System.out.println(child.level);
        System.out.println(child.averagePnts);
    }
}
