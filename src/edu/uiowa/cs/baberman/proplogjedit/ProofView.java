package edu.uiowa.cs.baberman.proplogjedit;

import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EditPane;
import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.textarea.TextArea;
import org.gjt.sp.jedit.textarea.TextAreaExtension;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofView {

    public static final Color INCOMPLETE_RED = new Color(255, 100, 100);
    
    final Buffer buffer;

    Buffer getBuffer() {
        return buffer;
    }

    final Map<TextAreaExtension, JEditTextArea> textAreaExtensionsWithTextAreas;

    private final ProofModel proofModel;

    ProofView(ProofModel proofModel) {
        this.proofModel = proofModel;
//        this.proofModel.addProofView(this);
        this.buffer = jEdit.newFile(jEdit.getActiveView().getEditPane());
        buffer.setReadOnly(true);
        this.textAreaExtensionsWithTextAreas = new HashMap<TextAreaExtension, JEditTextArea>();
    }

    void update() {
        
        if (proofModel == null) {
            throw new RuntimeException("Tried to update view with null model");
        }

        buffer.setReadOnly(false);
        buffer.remove(0, buffer.getLength());
        buffer.insert(0, proofModel.getRoot().getText());
        buffer.setReadOnly(true);

        clearTextAreaExtensions();        
        addTextAreaExtensions();

    }

    private void addTextAreaExtensions() {
        for (JEditTextArea textArea : getTextAreasForBuffer()) {
            addUnderline(textArea);
            addHighlights(textArea);
            addOptionalInsertionPointMarkers(textArea);
        }
    }

    private void addUnderline(JEditTextArea textArea) {
        
        InnerNode parent = proofModel.getSelectedNode().getParent();
        
        if (parent == null)
            return;
        
        Color lineColor;
        if(parent.isComplete()) {
            lineColor = Color.BLACK;
        } else {
            lineColor = INCOMPLETE_RED;
        }
        
        Underline underline = new Underline(textArea, lineColor, 
                parent.getOffset(), 
                parent.getOffset() + parent.getText().length());
        textArea.getPainter().addExtension(underline);
        textAreaExtensionsWithTextAreas.put(underline, textArea);
    }
    
    private void addHighlights(JEditTextArea textArea) {
        addSelectedNodeHighlight(textArea);
        addSelectedNodeSiblingHighlights(textArea);
    }

    private void addSelectedNodeSiblingHighlights(JEditTextArea textArea) {
        List<SelectableNode> selectableNodeSelectableSiblings
                = proofModel.getSelectedNodeSelectableSiblings();

        for (Node node : selectableNodeSelectableSiblings) {
            Highlight selectedNodeHighlight = new Highlight(textArea, new Color(0,0,0,0),
                    Color.BLACK, node.getOffset(), node.getOffset() + node.getText().length());
            textArea.getPainter().addExtension(selectedNodeHighlight);
            textAreaExtensionsWithTextAreas.put(selectedNodeHighlight, textArea);
        }
    }

    private void addSelectedNodeHighlight(JEditTextArea textArea) {
        SelectableNode selectedNode = proofModel.getSelectedNode();
        int selectedNodeOffset = selectedNode.getOffset();

        Paint selectedNodeFill;
        Paint selectedNodeStroke = Color.BLACK;

        if (selectedNode instanceof InnerNode) {
            selectedNodeFill = Color.LIGHT_GRAY;
        } else if (selectedNode instanceof RequiredInsertionPoint) {
            selectedNodeFill = INCOMPLETE_RED;
        } else /*if (selectedNode instanceof OptionalInsertionPoint) */ {
            selectedNodeFill = Color.YELLOW;
        }

        Highlight selectedNodeHighlight = new Highlight(textArea, selectedNodeFill,
                selectedNodeStroke, selectedNodeOffset, selectedNodeOffset + selectedNode.getText().length());
        textArea.getPainter().addExtension(selectedNodeHighlight);
        textAreaExtensionsWithTextAreas.put(selectedNodeHighlight, textArea);
    }

    private void addOptionalInsertionPointMarkers(JEditTextArea textArea) {
        //to do
    }

    public void clearTextAreaExtensions() {
        for (TextAreaExtension textAreaExtension
                : textAreaExtensionsWithTextAreas.keySet()) {
            textAreaExtensionsWithTextAreas
                    .get(textAreaExtension)
                    .getPainter()
                    .removeExtension(textAreaExtension);
        }
        textAreaExtensionsWithTextAreas.clear();
    }

    public List<JEditTextArea> getTextAreasForBuffer() {
        List<JEditTextArea> textAreas = new ArrayList<JEditTextArea>();

        for (View view : jEdit.getViews()) {
            for (EditPane editPane : view.getEditPanes()) {
                if (editPane.getBuffer() == buffer) {
                    textAreas.add(editPane.getTextArea());
                }
            }
        }

        return textAreas;
    }

}
