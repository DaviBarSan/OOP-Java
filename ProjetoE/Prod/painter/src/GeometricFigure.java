public abstract class GeometricFigure {
    public abstract void move(double dx, double dy);

    public abstract void rotate (double angle);

    public abstract Point centroid();

    public abstract Point[] points();
}
