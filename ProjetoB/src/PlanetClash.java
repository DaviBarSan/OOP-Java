import java.util.Scanner;

public class PlanetClash {
    protected Sphere asPlanet;

    public PlanetClash(Sphere sphereParameters) {
        this.asPlanet = sphereParameters;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double zetaX = sc.nextDouble();
        double zetaY = sc.nextDouble();
        double zetaZ = sc.nextDouble();
        double zetaRadius = sc.nextDouble();

        Point3D pointsZeta = new Point3D(zetaX, zetaY, zetaZ);
        Sphere metricsZeta = new Sphere(pointsZeta, zetaRadius);
        PlanetClash planetZeta = new PlanetClash(metricsZeta);

        double betaX = sc.nextDouble();
        double betaY = sc.nextDouble();
        double betaZ = sc.nextDouble();
        double betaRadius = sc.nextDouble();

        Point3D pointsBeta = new Point3D(betaX, betaY, betaZ);
        Sphere metricsBeta = new Sphere(pointsBeta, betaRadius);
        PlanetClash planetBeta = new PlanetClash(metricsBeta);

        System.out.printf("%.2f%n", metricsZeta.sphereVolume());
        System.out.printf("%.2f%n", metricsBeta.sphereVolume());
        planetZeta.collisionSpam(planetBeta.asPlanet);

        sc.close();
    }

    public void collisionSpam(Sphere planteB) {
        if (this.asPlanet.collisionChecker(planteB)) System.out.println("collision");
        else System.out.println("no collision");

    }
}
