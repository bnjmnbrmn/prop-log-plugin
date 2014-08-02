
package edu.uiowa.cs.baberman.proplogjedit;

import java.awt.Color;
import java.awt.Graphics2D;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.textarea.TextAreaExtension;

/**
 *
 * @author bnjmnbrmn
 */
class OptionalInsertionPointMarker extends TextAreaExtension {

    private final int offset;
    private final Color color = new Color(204, 102, 0);
    private final JEditTextArea textArea;
    
    OptionalInsertionPointMarker(JEditTextArea textArea, int offset) {
        this.offset = offset;
        this.textArea = textArea;
    }
    
    @Override
    public synchronized void paintValidLine(Graphics2D gfx,
            int screenLine,
            int physicalLine,
            int start,
            int end,
            int y) {
        
        int lh = textArea.getPainter().getLineHeight();
        gfx.setPaint(color);
        
        int offsetX = textArea.offsetToXY(offset).x;
        int offsetY = textArea.offsetToXY(offset).y;
        
        if (start <= offset && offset <= end - 1) {
            int[] xVals = {offsetX, offsetX+4, offsetX-4};
            int[] yVals = {offsetY+lh, offsetY+lh+4, offsetY+lh+4};
            gfx.fillPolygon(xVals, yVals, 3);
        }
        
        if (offset == start) {
            //to do
        }
        
        if (offset == end - 1) {
            //to do
        }
        
    }
}
