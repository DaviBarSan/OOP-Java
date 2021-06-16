import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Point> pointsList = new ArrayList<>();
        int numberOfPoints = sc.nextInt();
        addPointsToList(pointsList, numberOfPoints, sc);
        float pathLength = totalLenght(pointsList);
        System.out.printf("%.2f%n", pathLength);

    }

    public static void addPointsToList(List<Point> pointList, int numberOfPoints, Scanner scanner) {
        for (int i = numberOfPoints; i > 0; i--) {
            double xNewPoint = scanner.nextDouble();
            double yNewPoint = scanner.nextDouble();
            pointList.add(new Point(xNewPoint, yNewPoint));
        }
    }

    public static float totalLenght(List<Point> path) {
        float currLenght = 0;
        Iterator<Point> iterator = path.iterator();
        Point currPoint = iterator.next();
        while (true) {
            Point nextPoint = iterator.next();
            currLenght += currPoint.distance(nextPoint);
            currPoint = nextPoint;
            if (path.indexOf(nextPoint) == path.size() - 1) break;
        }
        return currLenght;
    }


}