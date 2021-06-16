public class Point3D {
    double x;
    double y;
    double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float dist(Point3D otherSphere) {
        float quadDistX = (float) ((this.x - otherSphere.x) * (this.x - otherSphere.x));
        float quadDistY = (float) ((this.y - otherSphere.y) * (this.y - otherSphere.y));
        float quadDistZ = (float) ((this.z - otherSphere.z) * (this.z - otherSphere.z));
        float distance = (float) Math.sqrt(Math.abs((quadDistX) + (quadDistY) + (quadDistZ)));
        return distance;
    }


    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
