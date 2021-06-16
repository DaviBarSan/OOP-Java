public class Sphere {
    Point3D coord;
    double radius;

    public Sphere(Point3D point3d, double r) {
        if (r < 0) {
            System.out.println("iv: C(" + point3d.x + "," + point3d.y + "," + point3d.z + ") r=" + r);
            System.exit(0);
        }
        this.radius = r;
        this.coord = point3d;
    }

    public float sphereVolume() {
        var pi = Math.PI;
        float volume = (float) ((4.0F / 3.0F) * pi * (radius * radius * radius));
        return volume;
    }

    public boolean collisionChecker(Sphere otherSphere) {
        return ((this.coord.dist(otherSphere.coord)) < (this.radius + otherSphere.radius));
    }

}
