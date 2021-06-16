import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hdaniel@ualg.pt
 * @version 202104011514
 */
class DurationTest {
    static int num0 = 1;
    static int den0 = 1;
    static String s0 = ""+num0+"/"+den0;

    static int num1 = 2;
    static int den1 = 3;
    static String s1 = ""+num1+"/"+den1;

    static Duration d0  = new Duration(1,1);
    static Duration d0c = new Duration(1,1);
    static Duration d1  = new Duration(2,3);

    @Test
    void num01() {
        assertEquals(num0, d0.num());
        assertEquals(num1, d1.num());
    }

    @Test
    void den01() {
        assertEquals(den0, d0.den());
        assertEquals(den1, d1.den());
    }

    @Test
    void reduce01() {
        Duration d = new Duration(2);
        assertEquals(2, d.num());
        assertEquals(1, d.den());

        d = new Duration(4,2);
        assertEquals(2, d.num());
        assertEquals(1, d.den());

        d = new Duration(2,4);
        assertEquals(1, d.num());
        assertEquals(2, d.den());

        d = new Duration(3,8);
        assertEquals(3, d.num());
        assertEquals(8, d.den());
    }

    @Test
    void exceptions01() {

        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> new Duration(1,0)
        );
        assertEquals(CommonDefinitions.errorStr20, e.getMessage());

        e = assertThrows(
                IllegalArgumentException.class,
                () -> new Duration(0,1)
        );
        assertEquals(CommonDefinitions.errorStr20, e.getMessage());

        e = assertThrows(
                IllegalArgumentException.class,
                () -> new Duration(-1,2)
        );
        assertEquals(CommonDefinitions.errorStr20, e.getMessage());

        e = assertThrows(
                IllegalArgumentException.class,
                () -> new Duration(1,-2)
        );
        assertEquals(CommonDefinitions.errorStr20, e.getMessage());
    }

    @Test
    void ratio01() {
        assertEquals(1, d0.ratio());
        assertEquals(2.0 / 3.0, d1.ratio());
    }

    @Test
    void equals01() {
        assertEquals(d0, d0);
        assertEquals(d1, d1);
        assertNotEquals(d0, d1);
        assertNotEquals(d0, 1);
        assertEquals(d0, d0c);
    }

    @Test
    void parse01() {
        String s0 = "1/2";
        String s1 = "-1/2";
        String s2 = "1\2";
        assertEquals(s0, new Duration(s0).toString());

        //exceptions
        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> new Duration(s1)
        );
        assertEquals(CommonDefinitions.errorStr20, e.getMessage());


        e = assertThrows(
                IllegalArgumentException.class,
                () -> new Duration(s2)
        );
        assertEquals(CommonDefinitions.errorStr21, e.getMessage());
    }


    @Test
    void testToString() {
        assertEquals(s0, d0.toString());
        assertEquals(s1, d1.toString());
    }
}
