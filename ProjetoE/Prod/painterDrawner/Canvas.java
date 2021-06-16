import javax.swing.*;
import java.awt.*;

/**
 * Defines a Canvas to paint IFigures
 *
 *  @version 20210306136
 *  @author hdaniel@ualg.pt
 */
class Canvas {
    private final JFrame frame;
    private final int width;
    private final int height;

    /**
     * Creates a new Canvas
     *
     * @pre w >= 0 && h >= 0
     *
     * @param name  String displayed in the drag bar
     * @param w     Canvas width in pixels
     * @param h     Canvas height in pixels
     */
    public Canvas(String name, int w, int h) {
        width = w;
        height = h;
        frame = new JFrame(name);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(width, height));
    }

    /**
     * Sets this Canvas visible
     */
    public void show() { frame.setVisible(true); }

    /**
     *  Draws cartesian X and Y axis dividing this Canvas in 4 equal sizes quadrants
     *  Draws also guidlines
     */
    public void axis() {
        final int yp = height/2;
        final int xp = width/2;
        final int[] labelx = { xp, xp, xp, xp, xp, xp-200, xp-100, xp+100, xp+200 };
        final int[] labely = { yp+200, yp+100, yp, yp-100, yp-200, yp, yp, yp, yp };
        final String[] label = { "-2", "-1", "0", "1", "2", "-2", "-1", "1", "2"};
        final int[] guides = { -200, -150, -100, -50, 50, 100, 150, 200 };

        int w = width;
        //If not inverted x -axis
        //int h = height;
        int h = -height;
        //main y
        add(new DrawPolygon(new double[]{w/2.0, 0, w/2.0, h}).penThickness(2));
        //main x
        add(new DrawPolygon(new double[]{0, h/2.0, w, h/2.0}).penThickness(2));
        //x y guides
        for (int guide : guides) {
            add(new DrawPolygon(new double[]{w / 2.0 - guide, 0, w / 2.0 - guide, h}).penThickness(1).color(Palette.gray0));
            add(new DrawPolygon(new double[]{0, h / 2.0 - guide, w, h / 2.0 - guide}).penThickness(1).color(Palette.gray0));
        }

        //Labels
        for (int i=0; i<label.length; ++i) {
            JLabel lbl = new JLabel(label[i]);
            Dimension size = lbl.getPreferredSize();
            lbl.setBounds(labelx[i], labely[i], size.width, size.height);
            frame.add(lbl);
        }
    }

    /**
     * Adds a new IDrarFigure to this Canvas
     *
     * @pre f != null
     *
     * @param f an IDrawFigure
     */
    public void add(IDrawFigure f) {
        frame.add((JComponent) f);
        show();
    }

}