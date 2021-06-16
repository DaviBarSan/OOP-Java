import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tester {

    @Test
    public void testMovePointX(){
        Point newPoint = new Point(0,0);
        newPoint.move(15,2);
        assertEquals(newPoint.x(), 15);
        System.out.println(newPoint);
    }
    @Test
    public void testMovePointY(){
        Point newPoint = new Point(0,0);
        newPoint.move(15,2);
        assertEquals(newPoint.y(), 2);
    }

    @Test
    public void testingFromDoubleToPoints(){
        Point[] trianglePoints = Point.fromDoublesToPoints(new double[]{-1.0, -1.0, 1.0, 0.0, 0.0, 1.0});
        Polygon triangle = new Polygon(trianglePoints);
        assertEquals("-1,00", String.format("%.2f",trianglePoints[0].x()));
        assertEquals("-1,00",String.format("%.2f",trianglePoints[0].y()));
        assertEquals("1,00",String.format("%.2f",trianglePoints[1].x()));
        assertEquals("0,00",String.format("%.2f",trianglePoints[1].y()));
        assertEquals("0,00",String.format("%.2f",trianglePoints[2].x()));
        assertEquals("1,00",String.format("%.2f",trianglePoints[2].y()));
        assertEquals("0,00",String.format("%.2f",triangle.centroid.x()));
        System.out.println(triangle);
    }

    @Test
    public void testRotatingATriangle(){
        Point[] trianglePoints = Point.fromDoublesToPoints(new double[]{-1.0, -1.0, 1.0, 0.0, 0.0, 1.0});
        Polygon triangle = new Polygon(trianglePoints);
        triangle.rotate(30);
        assertEquals("-0,37", String.format("%.2f",triangle.points[0].x()));
        assertEquals("-1,37", String.format("%.2f",triangle.points[0].y()));
        assertEquals("0,87", String.format("%.2f",triangle.points[1].x()));
        assertEquals("0,50",String.format("%.2f", triangle.points[1].y()));
        assertEquals("-0,50", String.format("%.2f",triangle.points[2].x()));
        assertEquals("0,87", String.format("%.2f",triangle.points[2].y()));
    }

    @Test
    public void testMovingAndRotatingALineSegment(){
        Point[] lineSegment = Point.fromDoublesToPoints(new double[]{1.0, 1.0, 2.0, 2.0});
        LineSegment lineSegment1 = new LineSegment(lineSegment);
        lineSegment1.move(-0.5,-0.5);
        lineSegment1.rotate(45);
        assertEquals("1,00", String.format("%.2f",lineSegment1.points[0].x()));
        assertEquals("0,29", String.format("%.2f",lineSegment1.points[0].y()));
        assertEquals("1,00", String.format("%.2f",lineSegment1.points[1].x()));
        assertEquals("1,71", String.format("%.2f",lineSegment1.points[1].y()));
        System.out.println(lineSegment1);
        System.out.println(lineSegment1.areaToString());
    }

    @Test
    public void testMovingAndRotatingACircle(){
        Point center = new Point(1.1,0.50);
        double radius = 0.75;
        Circle circle = new Circle(radius, center);
        circle.move(-2,-1);
        circle.rotate(45);
        assertEquals("-0,90",String.format("%.2f",center.x()));
        assertEquals("-0,50",String.format("%.2f",center.y()));
        assertEquals("0,75",String.format("%.2f",radius));
        assertEquals("1,77",String.format("%.2f",circle.area));
        System.out.println(circle);
    }

    @Test
    public void testMovingAndRotatingAPolygon1(){
        double[] pointsCoord = {1,1,1.5,1,1.25,1.5};
        Point[] points = Point.fromDoublesToPoints(pointsCoord);
        Polygon polygon1 = new Polygon(points);
        polygon1.move(-1,-1);
        polygon1.rotate(45);
        assertEquals("0,19",String.format("%.2f",polygon1.points[0].x()));
        assertEquals("-0,13",String.format("%.2f",polygon1.points[0].y()));
        assertEquals("0,54",String.format("%.2f",polygon1.points[1].x()));
        assertEquals("0,23",String.format("%.2f",polygon1.points[1].y()));
        assertEquals("0,01",String.format("%.2f",polygon1.points[2].x()));
        assertEquals("0,40",String.format("%.2f",polygon1.points[2].y()));
    }


    @Test
    public void testMovingAndRotatingAPolygon2(){
        double[] pointsCoord = {-1, -1, 1, -1, 1, 1, -1, 1};
        Point[] points = Point.fromDoublesToPoints(pointsCoord);
        Polygon polygon2 = new Polygon(points);
        assertEquals(0.00, polygon2.centroid.x());
        assertEquals(0.00, polygon2.centroid.y());
        polygon2.move(-1,-1);
        polygon2.rotate(350);
        assertEquals("-2,16", String.format("%.2f",polygon2.points[0].x()));
        assertEquals("-1,81",String.format("%.2f",polygon2.points[0].y()));
        assertEquals("-0,19",String.format("%.2f",polygon2.points[1].x()));
        assertEquals("-2,16",String.format("%.2f",polygon2.points[1].y()));
        assertEquals("0,16",String.format("%.2f",polygon2.points[2].x()));
        assertEquals("-0,19", String.format("%.2f",polygon2.points[2].y()));
        assertEquals("-1,81", String.format("%.2f",polygon2.points[3].x()));
        assertEquals("0,16",String.format("%.2f",polygon2.points[3].y()));
    }

    @Test
    public void testMovingAndRotatingAPolygon3(){
        double[] pointsCoord = {-1.19, 1.4, -0.99, 1.1, -0.89, 0.5, -0.79, 0.4, -0.33, 0.3, -0.11, 0.1};
        Point[] points = Point.fromDoublesToPoints(pointsCoord);
        Polygon polygon3 = new Polygon(points);
        polygon3.move(2,1);
        polygon3.rotate(230);

        assertEquals("2,18",String.format("%.2f",polygon3.points[0].x()));
        assertEquals("1,51",String.format("%.2f",polygon3.points[0].y()));
        assertEquals("1,83",String.format("%.2f",polygon3.points[1].x()));
        assertEquals("1,55",String.format("%.2f",polygon3.points[1].y()));
        assertEquals("1,30",String.format("%.2f",polygon3.points[2].x()));
        assertEquals("1,86",String.format("%.2f",polygon3.points[2].y()));
        assertEquals("1,16",String.format("%.2f",polygon3.points[3].x()));
        assertEquals("1,85",String.format("%.2f",polygon3.points[3].y()));
        assertEquals("0,79",String.format("%.2f",polygon3.points[4].x()));
        assertEquals("1,56",String.format("%.2f",polygon3.points[4].y()));
        assertEquals("0,49",String.format("%.2f",polygon3.points[5].x()));
        assertEquals("1,52",String.format("%.2f",polygon3.points[5].y()));

        System.out.println(polygon3);
    }
}
