public class Tester {
    public static void main(String[] args) {
        Sphere testMetricsA = new Sphere(new Point3D(1.2, -0.5, 2.0), 2.1);
        PlanetClash testPlanetA = new PlanetClash(testMetricsA);
        System.out.printf("%.2f%n", testMetricsA.sphereVolume());


        Sphere testMetricsB = new Sphere(new Point3D(1.1, 4.2, 8.0), 3.0);
        PlanetClash testPlanetB = new PlanetClash(testMetricsB);
        System.out.printf("%.2f%n", testMetricsB.sphereVolume());
        testPlanetA.collisionSpam(testPlanetB.asPlanet);
        System.out.println(testPlanetA.asPlanet.coord.dist(testPlanetB.asPlanet.coord));

    }
}
//1.2 -0.5 2.0 2.1
//1.1 4.2 8.0 3.0