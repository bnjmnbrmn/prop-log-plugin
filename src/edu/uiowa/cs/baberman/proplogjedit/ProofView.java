package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.proplogjedit.nodes.InnerNode;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Node;
import edu.uiowa.cs.baberman.proplogjedit.nodes.SelectableNode;
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

    public void update() {

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
            addHighlights(textArea);
        }
    }

    private void addHighlights(JEditTextArea textArea) {
        addSelectedNodeHighlight(textArea);
        addOtherHighlights(textArea);
        addOptionalInsertionPointMarkers(textArea);
    }

    private void addOptionalInsertionPointMarkers(JEditTextArea textArea) {
        List<SelectableNode> otherNodesToMark
                = proofModel.getCurrentSelectableNodeListExclusive();
        
        for (SelectableNode node : otherNodesToMark) {
//            Highlight higlight = new Highlight(textArea, fillColor,
//                    strokeColor, node.getOffset(), node.getOffset() + node.getText().length());
            OptionalInsertionPointMarker marker 
                    = new OptionalInsertionPointMarker(textArea, node.getOffset());
            textArea.getPainter().addExtension(marker);
            textAreaExtensionsWithTextAreas.put(marker, textArea);
        }
    }

    private void addOtherHighlights(JEditTextArea textArea) {
        List<SelectableNode> otherNodesToHighlight
                //= proofModel.getSelectedNodeSelectableSiblings();
                = proofModel.getCurrentSelectableNodeListExclusive();

        Color fillColor;
        if (proofModel.getSelectionMode().equals(ProofModel.SelectionMode.LEAF)) {
            fillColor = new Color(200, 255, 200);
        } else {
            fillColor = new Color(222, 184, 135, 150);
        }

        for (SelectableNode node : otherNodesToHighlight) {

            Color strokeColor;
            if (!node.isCompletedSubtreeRoot()) {
                strokeColor = Color.RED;
            } else if (node.isAnOptionalPlaceholder()) {
                strokeColor = new Color(204,102,0);
            } else {
                strokeColor = Color.BLACK;
            }

            Highlight higlight = new Highlight(textArea, fillColor,
                    strokeColor, node.getOffset(), node.getOffset() + node.getText().length());
            textArea.getPainter().addExtension(higlight);
            textAreaExtensionsWithTextAreas.put(higlight, textArea);
        }
    }

    private void addSelectedNodeHighlight(JEditTextArea textArea) {
        SelectableNode selectedNode = proofModel.getSelectedNode();
        int selectedNodeOffset = selectedNode.getOffset();

        Paint selectedNodeFill;
        Paint selectedNodeStroke = Color.BLACK;

        if (proofModel.getSelectionMode().equals(ProofModel.SelectionMode.LEAF)) {
            selectedNodeFill = new Color(143, 188, 143);
        } else {
            selectedNodeFill = new Color(222, 184, 135, 250);
        }

        if (!selectedNode.isCompletedSubtreeRoot()) {
            selectedNodeStroke = Color.RED;
        } else if (selectedNode.isAnOptionalPlaceholder()) {
            selectedNodeStroke = Color.YELLOW;
        } else {
            selectedNodeStroke = Color.BLACK;
        }

        Highlight selectedNodeHighlight = new Highlight(textArea, selectedNodeFill,
                selectedNodeStroke, selectedNodeOffset, selectedNodeOffset + selectedNode.getText().length());
        textArea.getPainter().addExtension(selectedNodeHighlight);
        textAreaExtensionsWithTextAreas.put(selectedNodeHighlight, textArea);
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
