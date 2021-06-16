import java.awt.*;

/**
 * Defines the interface to paint 2D geometric figures in JFrames
 *
 * @version 20210306136
 * @author hdaniel@ualg.pt
 */
interface IDrawFigure {

    /**
     * Set paint mode to fill figures
     * @return this IDrawFigure
     */
    IDrawFigure fill();

    /**
     * Set paint mode to just outline figures
     * @return this IDrawFigure
     */
    IDrawFigure outline();

    /**
     * Set the draw/paint colour
     *
     * @pre c != null
     *
     * @return this IDrawFigure
     */
    IDrawFigure color(Color c);

    /**
     * Set the draw/paint pen thickness
     *
     * @return this IDrawFigure
     */
    IDrawFigure penThickness(int t);

    /**
     * paint figure using current colour and pen thickness
     *
     * @pre g != null
     *
     * @param g a awt.Graphics descendant
     */
    void paint(Graphics g);
}
