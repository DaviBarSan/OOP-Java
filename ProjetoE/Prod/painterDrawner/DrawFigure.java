import javax.swing.*;
import java.awt.*;

/**
 * Defines the base behaviour to paint 2D geometric figures in JFrames
 *
 * @version 20210306136
 * @author hdaniel@ualg.pt
 */
public abstract class DrawFigure extends JComponent implements IDrawFigure {

    protected boolean fill = false;
    protected Color paintColor = Color.black;
    protected int penThickness = 3;
    protected Point origin = new Point(0,0);
    protected double scale = 1.0;

    /**
     * Creates a new DrawFigure with default settings
     */
    protected DrawFigure() { }

    /**
     * Creates a new DrawFigure with axis origin at Point origin
     *
     * @pre origin != null
     *
     * @param origin the origin of axis in the canvas coordinate system
     */
    protected DrawFigure(Point origin) {
        this();
        this.origin = new Point(origin);
    }

    /**
     * Creates a new DrawFigure with axis origin at Point origin
     * and scaled by scale factor scale
     *
     * @pre origin != null
     *
     * @param origin the origin of axis in JFrame coordinate system
     * @param scale  figure scale factor in JFrame coordinate system
     */
    protected DrawFigure(Point origin, double scale) {
        this(origin);
        this.scale = scale;
    }

    /**
     * Set paint mode to fill figures
     * @return this IDrawFigure
     */
    public IDrawFigure fill() {
        fill = true;
        return this;
    }

    /**
     * Set paint mode to just outline figures
     * @return this IDrawFigure
     */
    public IDrawFigure outline() {
        fill = false;
        return this;
    }

    /**
     * Set the draw/paint colour
     *
     * @pre c != null
     *
     * @return this IDrawFigure
     */
    public IDrawFigure color(Color c) {
        paintColor = c;
        return this;
    }

    /**
     * Set the draw/paint pen thickness
     *
     * @return this IDrawFigure
     */
    public IDrawFigure penThickness(int t) {
        penThickness = t;
        return this;
    }

    /**
     * Draw and fill figure
     *
     * @pre g != null
     *
     * @param g a awt.Graphics descendant
     */
    protected abstract void paintFill(Graphics g);

    /**
     * Draw and fill figure
     *
     * @pre origin != null
     *
     * @param g a awt.Graphics descendant
     */
    protected abstract void paintOutline(Graphics g);

    /**
     * Paint method from JComponent overridden to paint
     * IDrawFigure
     *
     * @pre origin != null
     *
     * @param g a awt.Graphics descendant
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(penThickness));
        g2d.setColor(paintColor);
        if (fill) this.paintFill(g2d);
        else      this.paintOutline(g2d);
    }
}
