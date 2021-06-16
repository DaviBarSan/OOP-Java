import java.awt.*;

/**
 * Defines the behaviour to paint 2D circles in JFrames
 *
 * @version 20210306136
 * @author hdaniel@ualg.pt
 */
class DrawCircle extends DrawFigure {
    private int lowleftcx, lowleftcy;
    private int width, height;

    /**
     * Forms circle's enclosing box (square)
     *
     * @pre data.size() == 3
     *
     * @param data Defines a circle with the format [centerx, centery, radius]
     */
    private void formBox(double[] data) {
        double x = data[0];
        double y = data[1];
        double r = data[2];

        //No scale, no translation to origin
        /*
        lowleftcx = x - r;
        lowleftcy = y - r;
        width = height = r * 2;
        */

        //translate to origin and scale
        //invert y axis
        lowleftcx = (int) ((x - r + origin.x())*scale);
        lowleftcy = (int) ((-y - r + origin.y())*scale);
        width = height = (int) ((r * 2)*scale);
    }

    /**
     * Creates a new DrawCircle defined by data
     *
     * @pre data.size() == 3
     *
     * @param data Defines a circle with the format [centerx, centery, radius]
     */
    DrawCircle(double[] data) {
        super();
        formBox(data);
    }

    /**
     * Creates a new DrawCircle with axis origin at Point origin
     *
     * @pre data.size() == 3 && origin != null
     *
     * @param data   Defines a circle with the format [centerx, centery, radius]
     * @param origin the origin of axis in the canvas coordinate system
     */
    DrawCircle(double[] data, Point origin) {
        super(origin);
        formBox(data);
    }

    /**
     * Creates a new DrawCircle with axis origin at Point origin
     *
     * @pre data.size() == 3 && origin != null
     *
     * @param data   Defines a circle with the format [centerx, centery, radius]
     * @param origin the origin of axis in the canvas coordinate system
     * @param scale  figure scale factor in JFrame coordinate system
     */
    DrawCircle(double[] data, Point origin, double scale) {
        super(origin, scale);
        formBox(data);
    }

    /**
     * Paint and fill a circle
     *
     * @pre g != null
     *
     * @param g a awt.Graphics descendant
     */
    public void paintFill(Graphics g)    {
        g.fillOval(lowleftcx, lowleftcy, width, height);
    }

    /**
     * Draw the circle outline
     *
     * @pre g != null
     *
     * @param g a awt.Graphics descendant
     */
    public void paintOutline(Graphics g) {
        g.drawOval(lowleftcx, lowleftcy, width, height);
    }
}
