public class Circle extends GeometricFigure{
    double radius;
    Point center;
    double area;

    /**
     *
     * @param r double references to circumference radius
     * @param c Point reference to circumference center coordinates
     */
    public Circle(double r, Point c){
        this.radius = r;
        this.center = c;
        this.area = areaCalculator();
    }

    @Override
    public void move(double dx, double dy) {
        center.move(dx,dy);
    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public Point[] points() {
        Point[] points = {center};
        return points;
    }

    /**
     *
     * @return circumference area;
     */
    private double areaCalculator(){
        double area = Math.PI*(radius*radius);
        return area;
    }

    /**
     *
     * @return formatted circumference coordinates and radius
     */
    public String toString(){
        String circleToString = "Circle(" + center.toString() + "," + radius + ")";
        return circleToString;
    }

    /**
     *
     * @return formatted
     */
    public String areaToString() {
        String areaToString = String.format("%.2f",area).replace(",",".");
        return areaToString;
    }

}
