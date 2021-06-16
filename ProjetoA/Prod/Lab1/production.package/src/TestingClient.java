import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestingClient {
    public static void main(String[] args) {
        double[] pointsT1 = {0.0, 0.0, -2.0, 2.0};
        double[] pointsT2 = {0.0, 0.0, -2.0, 2.0, 3.0, 1.2};

        List<Point> testList = testerListOfPoints(pointsT1, 2);
        testList.stream().forEach(point -> {
            System.out.println(point.toString());
        });
        System.out.println(totalLength(testList));

        List<Point> test2List = testerListOfPoints(pointsT2, 3);
        test2List.stream().forEach(point -> {
            System.out.println(point.toString());
        });
        System.out.println(totalLength(test2List));


    }

    public static List<Point> testerListOfPoints(double[] testCoordList, int numbOfPoints) {
        List<Point> testPointList = new ArrayList<>();
        for (int i = 0; i < testCoordList.length; i = i + 2) {
            double xNewPoint = testCoordList[i];
            double yNewPoint = testCoordList[i + 1];
            Point currPoint = new Point(xNewPoint, yNewPoint);
            testPointList.add(currPoint);
        }
        return testPointList;
    }

    public static float totalLength(List<Point> path) {
        float currLength = 0.0F;
        Iterator<Point> iterator = path.iterator();
        Point currPoint = iterator.next();
        while (true) {
            Point nextPoint = iterator.next();
            currLength += currPoint.distance(nextPoint);
            currPoint = nextPoint;
            if (path.indexOf(nextPoint) == path.size() - 1) break;
        }
        return currLength;
    }

}
