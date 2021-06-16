import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hdaniel@ualg.pt
 * @version 202103101714
 */
class PointTest {
    static final private double expectedx = -2.1;
    static final private double expectedy = 3.31;

    @Test
    void testConstructorAndGetters0() {
        Point p = new Point(expectedx, expectedy);
        assertEquals(expectedx, p.x()); //taken form post conditions
        assertEquals(expectedy, p.y()); //taken form post conditions
    }

    @Test
    void testCopyConstructorAndGetters0() {
        Point p0 = new Point(expectedx, expectedy);
        Point p1 = new Point(p0);
        assertEquals(p0.x(), p1.x()); //taken form post conditions
        assertEquals(p0.y(), p1.y()); //taken form post conditions
        assertTrue(p0 != p1); //taken form post conditions
    }
}