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
public class Highlight extends TextAreaExtension {
    
    private final JEditTextArea textArea;
    private final Paint fillPaint;
    private final Paint strokePaint;
    private final int startOffset; //start offset of to-highlight section
    private final int endOffset; //end offset of to-highlight section

    public Highlight(JEditTextArea textArea, Paint fillPaint, Paint strokePaint,
            int startOffset, int endOffset) {

        this.textArea = textArea;
        this.fillPaint = fillPaint;
        this.strokePaint = strokePaint;
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

//        JEditBuffer buf = jEdit.getActiveView().getTextArea().getBuffer();
        JEditBuffer buf = textArea.getBuffer();
        int bufLength = buf.getLength();

        int lh = textArea.getPainter().getLineHeight();
        gfx.setPaint(fillPaint);
        //gfx.setStroke(new BasicStroke(2));

        Point startPoint;
        startPoint = textArea.offsetToXY(start);
        Point endPoint;
        endPoint = textArea.offsetToXY(end - 1);
        Point sothSectionPoint;
        if (startOffset > bufLength) {
            sothSectionPoint = textArea.offsetToXY(bufLength);
        } else if (startOffset < 0) {
            sothSectionPoint = textArea.offsetToXY(0);
        } else {
            sothSectionPoint = textArea.offsetToXY(startOffset);
        }
        Point eothSectionPoint;
        if (endOffset > bufLength) {
            eothSectionPoint = textArea.offsetToXY(bufLength);
        } else if (endOffset < 0) {
            eothSectionPoint = textArea.offsetToXY(0);
        } else {
            eothSectionPoint = textArea.offsetToXY(endOffset);
        }

        int rectx;
        int recty;
        int rectwidth;
//        int rectheight = lh - 3;
        int rectheight = lh - 1;

        if (startOffset < start && end < endOffset) {
            rectx = startPoint.x;
            recty = startPoint.y;
            rectwidth = endPoint.x - startPoint.x;

            gfx.fill(new Rectangle(
                    rectx,
                    recty,
                    rectwidth,
                    rectheight));
            
            gfx.setPaint(strokePaint);
            
            gfx.drawLine(rectx, recty, rectx + rectwidth, recty);
            gfx.drawLine(rectx, recty + rectheight, rectx + rectwidth, recty + rectheight);
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
            
            gfx.setPaint(strokePaint);
            
            gfx.drawLine(rectx, recty, rectx + rectwidth, recty);
            gfx.drawLine(rectx, recty + rectheight, rectx + rectwidth, recty + rectheight);
            gfx.drawLine(rectx, recty, rectx, recty + rectheight);
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

            gfx.setPaint(strokePaint);
            
            gfx.drawLine(rectx, recty, rectx + rectwidth, recty);
            gfx.drawLine(rectx, recty + rectheight, rectx + rectwidth, recty + rectheight);
            gfx.drawLine(rectx + rectwidth, recty, rectx + rectwidth, recty + rectheight);

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

            gfx.setPaint(strokePaint);

            gfx.drawLine(rectx, recty, rectx + rectwidth, recty);
            gfx.drawLine(rectx, recty + rectheight, rectx + rectwidth, recty + rectheight);
            gfx.drawLine(rectx, recty, rectx, recty + rectheight);
            gfx.drawLine(rectx + rectwidth, recty, rectx + rectwidth, recty + rectheight);
        }

    }

}
