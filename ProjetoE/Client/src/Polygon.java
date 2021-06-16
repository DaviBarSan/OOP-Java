public class Polygon extends GeometricFigure{
    Point centroid;
    Point[] points;
    double area;

    /**
     *
     * @param points Point[]
     */
    public Polygon(Point[] points){
        this.points = points;
        this.area = calculateArea(points);
        this.centroid = centroid();
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
        for (Point point : points){
            point.rotateAround(angle, centroid);
        }
    }

    @Override
    public Point centroid() {
        double xCentroid = xCentroidCoordFinder(points);
        double yCentroid = yCentroidCoordFinder(points);
        Point centroid = new Point(xCentroid, yCentroid);
        return centroid;
    }

    @Override
    public Point[] points() {
        return points;
    }

    /**
     *
     * @param points
     * @return area of polygon drown by the points coordinates;
     */
    private double calculateArea(Point[] points){
        double sum = 0;
        for(int i = 0; i < points.length - 1; i++){
            Point currPoint = points[i];
            Point nextPoint = points[i+1];
            double tmpSum = currPoint.x() * nextPoint.y() - nextPoint.x() * currPoint.y();
            sum += tmpSum;
        }
        int lastIndex = points.length-1;
        double closingPolygonArea = points[lastIndex].x() * points[0].y() - points[0].x() * points[lastIndex].y();
        sum = (sum+closingPolygonArea)/2F;
        return sum;
    }

    /**
     *
     * @param points
     * @return x coordinate for centroid;
     */
    private double xCentroidCoordFinder(Point[] points){
        double sumX = 0;
        for (int i = 0; i < points.length - 1; i++){
            Point currPoint = points[i];
            Point nextPoint = points[i+1];
            double tmpSum = (currPoint.x() + nextPoint.x())*((currPoint.x()*nextPoint.y()) - (nextPoint.x()*currPoint.y()));
            sumX += tmpSum;
        }
        sumX =  sumX * (1f/(6F*area));

        Point firstPoint = points[0];
        Point lastPoint = points[points.length-1];
        double closingPoligon = (1F/(6F * area)) * (lastPoint.x() + firstPoint.x())*(lastPoint.x()*firstPoint.y() - firstPoint.x()*lastPoint.y());
        double finalX = sumX + closingPoligon;
        return finalX;
    }

    /**
     *
     * @param points
     * @return y coordinate for centroid;
     */
    private double yCentroidCoordFinder(Point[] points){
        double sumY = 0;
        for (int i = 0; i < points.length - 1; i++){
            Point currPoint = points[i];
            Point nextPoint = points[i+1];
            double tmpSum = (currPoint.y() + nextPoint.y())*((currPoint.x()*nextPoint.y()) - (nextPoint.x()*currPoint.y()));
            sumY += tmpSum;
        }
        sumY = (1F / (6F*area)) * sumY;

        Point firstPoint = points[0];
        Point lastPoint = points[points.length-1];
        double closingPoligon = (1F/(6F * area)) * (lastPoint.y() + firstPoint.y())*(lastPoint.x()*firstPoint.y() - firstPoint.x()*lastPoint.y());
        double finalY = sumY + closingPoligon;
        return finalY;
    }

    /**
     *
     * @return formatted Polygon coordinates
     */
    public String toString(){
           String polygonToString = "Polygon(";
           for (Point currPoint : points){
               polygonToString += currPoint.toString();
               if(currPoint != points[points.length-1]) polygonToString += ",";
           }
           polygonToString += ")";
           return polygonToString;
      }

    /**
     *
     * @return formatted Polygon area;
     */
    public String areaToString() {
        String areaToString = String.format("%.2f",area).replace(",",".");
        return areaToString;
    }

}
