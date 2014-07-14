package edu.uiowa.cs.baberman.proplogjedit;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import org.gjt.sp.jedit.buffer.JEditBuffer;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.textarea.TextAreaExtension;

/**
 *
 * @author bnjmnbrmn
 */
public class Underline extends TextAreaExtension {

    private final JEditTextArea textArea;
    private final Paint paint;
    private final int startOffset;
    private final int endOffset;

    Underline(JEditTextArea textArea, Paint paint,
            int startOffset, int endOffset) {

        this.textArea = textArea;
        this.paint = paint;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }
    
    @Override
    public synchronized void paintValidLine(Graphics2D gfx,
            int screenLine,
            int physicalLine,
            int start,
            int end,
            int y) {
        
        
        JEditBuffer buf = textArea.getBuffer();
        int bufLength = buf.getLength();

        int lh = textArea.getPainter().getLineHeight();
        gfx.setPaint(paint);
        
        int lineThickness = 3;
        
        Point startPoint;
        startPoint = textArea.offsetToXY(start);
        startPoint.y += (lh - lineThickness);
        Point endPoint;
        endPoint = textArea.offsetToXY(end - 1);
        endPoint.y += (lh - lineThickness);
        Point sothSectionPoint;
        if (startOffset > bufLength) {
            sothSectionPoint = textArea.offsetToXY(bufLength);
        } else if (startOffset < 0) {
            sothSectionPoint = textArea.offsetToXY(0);
        } else {
            sothSectionPoint = textArea.offsetToXY(startOffset);
        }
        sothSectionPoint.y += (lh - lineThickness);
        Point eothSectionPoint;
        if (endOffset > bufLength) {
            eothSectionPoint = textArea.offsetToXY(bufLength);
        } else if (endOffset < 0) {
            eothSectionPoint = textArea.offsetToXY(0);
        } else {
            eothSectionPoint = textArea.offsetToXY(endOffset);
        }
        eothSectionPoint.y += (lh - lineThickness);
        
        
        int rectx;
        int recty;
        int rectwidth;
        int rectheight = lineThickness;

        if (startOffset < start && end < endOffset) {
            rectx = startPoint.x;
            recty = startPoint.y;
            rectwidth = endPoint.x - startPoint.x;

            gfx.fill(new Rectangle(
                    rectx,
                    recty,
                    rectwidth,
                    rectheight));
        }

        if (start <= startOffset
                && startOffset < end - 1
                && end < endOffset) {

            rectx = sothSectionPoint.x;
            recty = startPoint.y;
            rectwidth = endPoint.x - sothSectionPoint.x;

            gfx.fill(new Rectangle(
                    rectx,
                    recty,
                    rectwidth,
                    rectheight));
        }

        if (startOffset < start
                && start < endOffset
                && endOffset <= end - 1) {

            rectx = startPoint.x;
            recty = startPoint.y;
            rectwidth = eothSectionPoint.x - startPoint.x;

            gfx.fill(new Rectangle(
                    rectx,
                    recty,
                    rectwidth,
                    rectheight));
        }

        if (start <= startOffset
                && startOffset <= end - 1
                && start <= endOffset
                && endOffset <= end) {
            
            rectx = sothSectionPoint.x;
            recty = startPoint.y;
            rectwidth = eothSectionPoint.x - sothSectionPoint.x;
            
            gfx.fill(new Rectangle(
                    rectx,
                    recty,
                    rectwidth,
                    rectheight));
        }
    }

}
