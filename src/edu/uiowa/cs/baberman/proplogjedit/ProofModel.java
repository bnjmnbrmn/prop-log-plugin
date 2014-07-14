package edu.uiowa.cs.baberman.proplogjedit;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gjt.sp.jedit.Buffer;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofModel {

    Proof root;

    SelectableNode selectedNode = null;

    ProofModel() {
        root = new Proof();

        setSelectedNode(root.getSelectableSubnodes().get(0));
    }

    List<ProofView> proofViews = new ArrayList<ProofView>();
    
    List<ProofView> getProofViews() {
        return proofViews;
    }

    void addProofView(ProofView proofView) {
        proofViews.add(proofView);
        proofView.setProofModel(this);

        updateViews();
    }

    private void updateViews() {
        for (ProofView proofView : proofViews) {
            proofView.update();
        }
    }

    Proof getRoot() {
        return root;
    }

    final void setSelectedNode(SelectableNode toSelect) {
        if (selectedNode != null) {
            selectedNode.setAsSelectedChild(false);
        }
        toSelect.setAsSelectedChild(true);
        selectedNode = toSelect;

        updateViews();
    }

    final SelectableNode getSelectedNode() {
        return selectedNode;
    }

}

abstract class Node {

    private InnerNode parent;

    abstract String getText();

    /**
     * @return the parent
     */
    public InnerNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(InnerNode parent) {
        this.parent = parent;
    }
    
    public boolean hasParent() {
        return parent != null;
    }
    
    public int getOffset() {
        int offset;
        
        if (!hasParent())
            return 0;
        
        InnerNode parent = getParent();
        
        offset = parent.getOffset();
        
        List<Node> subnodes = parent.getSubnodes();
        for (int i = 0; i < subnodes.size(); i++) {
            Node subnode = subnodes.get(i);
            if (subnodes.get(i) == this)
                return offset;
            else
                offset += subnode.getText().length();
        }
        
        //this should never occur
        throw new RuntimeException("Programming error:  selected child not among its parent's children");
        
    }

}

class Leaf extends Node {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public Leaf(String text) {
        this.text = text;
    }

    public Leaf(String text, int repeats) {
        if (repeats < 0) {
            throw new RuntimeException(
                    "repeats parameter must be non-negative");
        }

        String temp = "";
        for (int i = 0; i < repeats; i++) {
            temp += text;
        }

        this.text = temp;
    }

}

abstract class SelectableNode extends Node {

    private boolean selected = false;

    public void setAsSelectedChild(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelectedChild() {
        return selected;
    }

    public boolean isSelectedChildSibling() {
        if (getParent() == null) {
            return false;
        }

        if (isSelectedChild()) {
            return false;
        }

        for (SelectableNode selectableNode : getParent().getSelectableSubnodes()) {
            if (selectableNode.isSelectedChild()) {
                return true;
            }
        }
        return false;
    }

    

}

abstract class InsertionPoint extends SelectableNode {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public InsertionPoint(String text) {
        this.text = "(* " + text + " *)";
    }

}

class OptionalInsertionPoint extends InsertionPoint {

    public OptionalInsertionPoint(String text) {
        super(text);
    }

    @Override
    public String getText() {
        if (isSelectedChild()) {
            return super.getText();
        } else {
            return "";
        }
    }

}

class RequiredInsertionPoint extends InsertionPoint {

    public RequiredInsertionPoint(String text) {
        super(text);
    }

}

abstract class InnerNode extends SelectableNode {

    public boolean hasSelectedChild() {
        for (SelectableNode selectableNode : getSelectableSubnodes()) {
            if (selectableNode.isSelectedChild()) {
                return true;
            }
        }
        return false;
    }

    List<Node> subnodes = new ArrayList<Node>();

    @Override
    String getText() {
        String text = "";
        for (Node subnode : subnodes) {
            text += subnode.getText();
        }
        return text;
    }

    List<SelectableNode> getSelectableSubnodes() {
        List<SelectableNode> selectableSubnodes = new ArrayList<SelectableNode>();

        for (Node subnode : subnodes) {
            if (subnode instanceof SelectableNode) {
                selectableSubnodes.add((SelectableNode) subnode);
            }
        }

        return selectableSubnodes;
    }

    List<Node> getSubnodes() {
        return subnodes;
    }
}

final class Proof extends InnerNode {

    Proof() {
        subnodes.add(new Leaf("Parameters "));
        subnodes.add(new RequiredInsertionPoint(PropVar.getPlaceholderText()));
        subnodes.add(new Leaf(" : Prop.\n\n"));
        subnodes.add(new RequiredInsertionPoint(ProofItem.getPlaceholderText()));
    }

    static String getPlaceholderText() {
        return "PROOF";
    }

}

final class ProofItem extends InnerNode {

    static String getPlaceholderText() {
        return "PROOF_ITEM";
    }

}

final class Section extends InnerNode {

    static String getPlaceholderText() {
        return "SECTION";
    }

}

final class ProofLine extends InnerNode {

    static String getPlaceholderText() {
        return "PROOF_LINE";
    }

}

final class PropVar extends InnerNode {

    static String getPlaceholderText() {
        return "PROP_VAR";
    }

}
