import java.util.Scanner;

public class Transformer {
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int geometricFigureDefiner = sc.nextInt();
        if (geometricFigureDefiner == 3){
            double centerX = Double.parseDouble(sc.next());
            double centerY = Double.parseDouble(sc.next());
            double radius = Double.parseDouble(sc.next());
            Circle circle = new Circle(radius, new Point(centerX, centerY));
            double moveX = Double.parseDouble(sc.next());
            double moveY = Double.parseDouble(sc.next());
            double rotation = Double.parseDouble(sc.next());
            System.out.println(circle.center.toString());
            System.out.println(circle.areaToString());
            System.out.println(circle);
            circle.move(moveX,moveY);
            circle.rotate(rotation);
            System.out.println(circle);
        }
        else if(geometricFigureDefiner == 4){
            double[] pointsCoord = new double[geometricFigureDefiner];
            for (int i = 0; i != geometricFigureDefiner; i++){
                pointsCoord[i] = Double.parseDouble(sc.next());
            }
            Point[] points = Point.fromDoublesToPoints(pointsCoord);
            LineSegment lineSegment = new LineSegment(points);
            double moveX = Double.parseDouble(sc.next());
            double moveY = Double.parseDouble(sc.next());
            double rotation = Double.parseDouble(sc.next());
            System.out.println(lineSegment.centroid.toString());
            System.out.println(lineSegment.areaToString());
            System.out.println(lineSegment);
            lineSegment.move(moveX,moveY);
            lineSegment.rotate(rotation);
            System.out.println(lineSegment);
        }
        else {
            double[] pointsCoord = new double[geometricFigureDefiner];
            for (int i = 0; i != geometricFigureDefiner; i++){
                pointsCoord[i] = Double.parseDouble(sc.next());
            }
            Point[] points = Point.fromDoublesToPoints(pointsCoord);
            Polygon polygon = new Polygon(points);
            double moveX = Double.parseDouble(sc.next());
            double moveY = Double.parseDouble(sc.next());
            double rotation = Double.parseDouble(sc.next());
            System.out.println(polygon.centroid.toString());
            System.out.println(polygon.areaToString());
            System.out.println(polygon);
            polygon.move(moveX,moveY);
            polygon.rotate(rotation);
            System.out.println(polygon);
        }
        sc.close();
  }
}
