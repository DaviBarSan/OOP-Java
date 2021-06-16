import java.awt.*;

/**
 * Defines the behaviour to paint 2D polygons in JFrames
 *
 *  @version 20210306136
 *  @author hdaniel@ualg.pt
 */
class DrawPolygon extends DrawFigure {
    private Polygon poly;

    /**
     * Creates a awt.Polygon object from coordinates specified in data
     *
     * @pre data.size() % 2 == 0 && points ordered counter clockwise
     *
     * @param data Defines the coordinates of a polygon,
     *             ordered counter clockwise [x0 y0 x1 y1 ...]
     */
    private void formPolygon(double[] data) {
        poly = new Polygon();
        for (int i=0; i<data.length; i+=2)
            //No scale, no translation to origin
            //poly.addPoint((int) data[i], (int) data[i+1]);

            //translate to origin and scale
            //invert y axis
            poly.addPoint((int) (( data[i] + origin.x())*scale),
                    (int) ((-data[i+1] + origin.y())*scale));
    }

    /**
     * Creates a new DrawPolygon with axis origin at Point origin
     *
     * @pre data.size() % 2 == 0 && points ordered counter clockwise
     *
     * @param data Defines the coordinates of a polygon,
     *             ordered counter clockwise [x0 y0 x1 y1 ...]
     */
    DrawPolygon(double[] data) {
        super();
        formPolygon(data);
    }

    /**
     * Creates a new DrawPolygon with axis origin at Point origin
     *
     * @pre data.size() % 2 == 0 && points ordered counter clockwise
     *      && origin != null
     *
     * @param data Defines the coordinates of a polygon,
     *             ordered counter clockwise [x0 y0 x1 y1 ...]
     * @param origin the origin of axis in the canvas coordinate system
     */
    DrawPolygon(double[] data, Point origin) {
        super(origin);
        formPolygon(data);
    }

    /**
     * Creates a new DrawPolygon with axis origin at Point origin
     *
     * @pre data.size() % 2 == 0 && points ordered counter clockwise
     *      origin != null
     *
     * @param data Defines the coordinates of a polygon,
     *             ordered counter clockwise [x0 y0 x1 y1 ...]
     * @param origin the origin of axis in the canvas coordinate system
     * @param scale  figure scale factor in JFrame coordinate system
     */
    DrawPolygon(double[] data, Point origin, double scale) {
        super(origin, scale);
        formPolygon(data);
    }

    /**
     * Paint and fill a polygon
     *
     * @pre g != null
     *
     * @param g a awt.Graphics descendant
     */
    public void paintFill(Graphics g)    { g.fillPolygon(poly); }

    /**
     * Draw the polygon outline
     *
     * @pre g != null
     *
     * @param g a awt.Graphics descendant
     */
    public void paintOutline(Graphics g) {
        g.drawPolygon(poly);
    }
}
