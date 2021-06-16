/**
 * Duration stored as a fraction in its simplest form:
 *
 * whole note is 1/1
 * half note is 1/2
 * quarter note is 1/4
 * quarter note and dot: (1/4.) is 1/4 + 1/8 = 3/8
 *
 * @author hdaniel@ualg.pt
 * @version 202104011511
 */
public class Duration {
    private byte num;
    private byte den;

    /**
     * Parse a duration in fraction from a string
     *
     * @param s String to be parsed for a duration
     * @return a Note if successful
     */
    static private Duration parse(String s) {
        String[] parts = s.split(CommonDefinitions.fractionSeparator);
        if (parts.length != 2)
            throw new IllegalArgumentException(CommonDefinitions.errorStr21);

        int num = Integer.parseInt(parts[0]);
        int den = Integer.parseInt(parts[1]);

        return new Duration(num, den);
    }


    /**
     * Creates a duration in fraction format num/den
     * fraction is in its simplest form
     *
     * @param num numerator of duration fraction
     * @param den denominator of duration fraction
     *
     * post: num = num/gcd(num,den)
     * post: den = den/gcd(num,den)
     */
    public Duration(int num, int den) {
        if (num <= 0 || den <= 0)
            throw new IllegalArgumentException(CommonDefinitions.errorStr20);

        this.num = (byte) num;
        this.den = (byte) den;

        //reduce to simpler form using gcd
        //If reached here, num and den cannot be <= 0
        //gcd pre fulfilled
        int k = gcd(num, den);
        this.num /= k;
        this.den /= k;
    }

    public Duration(int num) {
        this(num,1);
    }
    public Duration(Duration d) {
        this(d.num, d.den);
    }

    public Duration(String s) {
        this(parse(s));
    }

    public int num() { return num; }
    public int den() { return den; }
    public double ratio() { return (double) num / (double) den; }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Duration)) return false;
        return ((Duration) o).num == num &&
               ((Duration) o).den == den;
    }

    /**
     * Computes the greatest common divisor (gcd) between m and n
     * using Euclides algorithm
     *
     @pre:    (x == (int)x)  &&  (x == (int)y)
     @pre:    m > 0 && n > 0
     @return the gcd between m and n
     */
    static public int gcd( int  m , int  n ) {
        int r;
        while (( r = m % n ) != 0 ) {
            m = n;
            n = r;
        }
        return n;
    }

    @Override
    public String toString() { return "" + num + CommonDefinitions.fractionSeparator + den; }

}