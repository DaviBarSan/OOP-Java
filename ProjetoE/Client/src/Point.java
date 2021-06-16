/**
 * Implements a point in R^2
 *
 *  @version 20210306136
 *  @author hdaniel@ualg.pt
 */
public class Point{
    private double x;
    private double y;

    /**
     * Creates a new point
     * @param x point x coordinate
     * @param y point y coordinate
     *
     * @post   this.x() == x
     * @post   this.y() == y
     */
    public Point(double x, double y) { this.x = x; this.y = y; }

    /**
     * Creates a copy of a Point
     * @param p a Point
     *
     * @post   this.x() == p.x()
     * @post   this.y() == p.y()
     * @post   this != p
     */
    public Point(Point p) { this.x = p.x; this.y = p.y; }

    /**
     * @return the x coordinate
     */
    public double x() { return x;}

    /**
     * @return the y coordinate
     */
    public double y() { return y; }


    /**
     * Move a point by adding x and y to its coordinates
     * @param   dx a double referent to x coordinate
     * @param   dy a double referent to y coordinate
     * @post    Moved point
     */
    public void move(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    /**
     * Rotate a point on 'angles' degrees clockwise, using another point as reference
     *
     * @param angles a double degrees to rotate
     * @param c a reference Point to rotate around
     * @post Point is rotated using inputted reference parameters
     */
    public void rotateAround(double angles, Point c){
        //moving rotation center to origin
        x = x - c.x;
        y = y - c.y;

        double rads = (angles * Math.PI)/180F;

        double Xn = x*Math.cos(rads) - y*Math.sin(rads);
        double Yn = x*Math.sin(rads) + y*Math.cos(rads);

        x = Xn + c.x;
        y = Yn + c.y;
    }

    public static Point[] fromDoublesToPoints (double[] coord){
        Point[] points = new Point[coord.length/2];
        for (int i = 0, j = 0; i < coord.length-1; i = i+2, j++){
            Point currPoint = new Point(coord[i], coord[i+1]);
            points[j] = currPoint;
        }

        return points;
    }

    public String toString(){
        String formatX = String.format("%.2f", x).replace(",",".");
        String  formatY = String.format("%.2f", y).replace(",",".");
        String pointToString = "(" + formatX + "," + formatY + ")";
        return pointToString;
    }
}
