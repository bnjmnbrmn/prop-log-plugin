package edu.uiowa.cs.baberman.proplogjedit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import org.gjt.sp.jedit.buffer.JEditBuffer;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.textarea.TextAreaExtension;

/**
 *
 * @author bnjmnbrmn
 */
class Highlight extends TextAreaExtension {

    private final JEditTextArea textArea;
    private Paint highlightPaint;

    private int startOffset = 0; //start offset of to-highlight section
    private int endOffset = 0; //end offset of to-highlight section

    Highlight(JEditTextArea textArea, Paint highlightPaint) {
        this.textArea = textArea;
        this.highlightPaint = highlightPaint;
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
        gfx.setPaint(highlightPaint);

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

        if (startOffset <= start && end - 1 <= endOffset) {
            gfx.fill(new Rectangle(
                    startPoint.x,
                    startPoint.y,
                    endPoint.x - startPoint.x,
                    lh));
        }

        if (start < startOffset
                && startOffset <= end - 1
                && end - 1 <= endOffset) {
            gfx.fill(new Rectangle(
                    sothSectionPoint.x,
                    startPoint.y,
                    endPoint.x - sothSectionPoint.x,
                    lh));
        }

        if (startOffset <= start
                && start < endOffset
                && endOffset < end - 1) {
            gfx.fill(new Rectangle(
                    startPoint.x,
                    startPoint.y,
                    eothSectionPoint.x - startPoint.x,
                    lh));
        }

        if (start < startOffset
                && startOffset <= end - 1
                && start <= endOffset
                && endOffset < end - 1) {
            gfx.fill(new Rectangle(
                    sothSectionPoint.x,
                    startPoint.y,
                    eothSectionPoint.x - sothSectionPoint.x,
                    lh));
        }

    }

    public void highlightSection(int startOffset, int endOffset) {

        this.startOffset = startOffset;
        this.endOffset = endOffset;

        textArea.getPainter().repaint();
    }

    public void setHighlightPaint(Paint paint) {
        this.highlightPaint = paint;

        textArea.getPainter().repaint();
    }
}
