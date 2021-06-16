public class LineSegment extends GeometricFigure{
    Point centroid;
    Point[] points;
    double area;

    public LineSegment(Point[] pointsArray){
        this.points = pointsArray;
        this.centroid = centroid();
        this.area = 0.00;
    }
    @Override
    public void move(double dx, double dy) {
        for (Point point: points())
            point.move(dx, dy);
        this.centroid = centroid();
    }

    @Override
    public void rotate(double angle) {
        if(angle<0) angle = 360+angle;
        for(Point point : points){
            point.rotateAround(angle, centroid);
        }
    }

    @Override
    public Point centroid() {
        double sumX = 0;
        double sumY = 0;
        for (Point point : points){
            sumX += point.x();
            sumY += point.y();
        }
        sumX = sumX/2f;
        sumY = sumY/2f;
        Point centroid = new Point(sumX, sumY);
        return centroid;
    }

    @Override
    public Point[] points() {
        return points;
    }

    /**
     *
     * @return formated LineSegment coordinates
     */
    public String toString(){
        String lineSegment = "Segment(";
        for (Point currPoint : points){
            lineSegment += currPoint.toString();
            if (currPoint != points[points().length-1]) lineSegment += ",";
        }
        lineSegment += ")";
        return lineSegment;
    }

    /**
     *
     * @return formated LineSegment area;
     */
    public String areaToString(){
        String areaStr = String.format("%.2f",area).replace(",", ".");
        return areaStr;
    }
}
