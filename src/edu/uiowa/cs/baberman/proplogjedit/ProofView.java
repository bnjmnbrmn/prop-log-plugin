package edu.uiowa.cs.baberman.proplogjedit;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EditPane;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.textarea.TextArea;
import org.gjt.sp.jedit.textarea.TextAreaExtension;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofView {

    final Buffer buffer;

    Buffer getBuffer() {
        return buffer;
    }

    final Map<TextAreaExtension, TextArea> textAreaExtensionsWithTextAreas;

    private ProofModel proofModel;

    ProofView(Buffer buffer) {
        this.textAreaExtensionsWithTextAreas = new HashMap<TextAreaExtension, TextArea>();
        this.buffer = buffer;
        buffer.setReadOnly(true);
    }

    void setProofModel(ProofModel proofModel) {
        this.proofModel = proofModel;
    }

    void update() {

        if (proofModel == null) {
            throw new RuntimeException("Tried to update view with null model");
        }

        buffer.setReadOnly(false);
        buffer.insert(0, proofModel.getRoot().getText());
        buffer.setReadOnly(true);

        //remove all textareaextensions from their textareas and 
        //from set of tracked textareaextensions
        for (TextAreaExtension textAreaExtension
                : textAreaExtensionsWithTextAreas.keySet()) {
            textAreaExtensionsWithTextAreas
                    .get(textAreaExtension)
                    .getPainter()
                    .removeExtension(textAreaExtension);
        }
        textAreaExtensionsWithTextAreas.clear();
        
        //to do:  add appropriate TextAreaExtensions (Highlights, Underlines,
        //and OptionalInsertionPointMarkers) to appropriate TextAreas' painters
        //and to mapping of textAreaExtensions

        //test
        Highlight highlight = new Highlight(jEdit.getActiveView().getTextArea(), Color.CYAN, Color.BLACK, 3, 7);
        jEdit.getActiveView().getTextArea().getPainter().addExtension(highlight);
        
//        jEdit.getActiveView().getTextArea().getPainter().removeExtension(highlight);
        
    }
}
