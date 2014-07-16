package edu.uiowa.cs.baberman.proplogjedit;

import java.util.ArrayList;
import java.util.List;

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

    List<SelectableNode> getSelectedNodeSelectableSiblings() {
        List<SelectableNode> selectedNodeSelectableSiblings
                = new ArrayList<SelectableNode>();
        for (Node subnode : selectedNode.getParent().getSubnodes()) {
            if (subnode instanceof SelectableNode && subnode != selectedNode) {
                selectedNodeSelectableSiblings.add((SelectableNode) subnode);
            }
        }
        return selectedNodeSelectableSiblings;
    }
    
    void goToNextSelectableSibling() {
        List<SelectableNode> selectableNodes
                = getSelectedNode().getParent().getSelectableSubnodes();
        int indexOfCurrent = selectableNodes.indexOf(getSelectedNode());
        
        if (indexOfCurrent == selectableNodes.size() - 1)
            return;
        
        setSelectedNode(selectableNodes.get(indexOfCurrent+1));
    }
    
    void goToPreviousSelectableSibling() {
        List<SelectableNode> selectableNodes
                = getSelectedNode().getParent().getSelectableSubnodes();
        int indexOfCurrent = selectableNodes.indexOf(getSelectedNode());
        
        if (indexOfCurrent == 0)
            return;
        
        setSelectedNode(selectableNodes.get(indexOfCurrent-1));
    }

}

abstract class Node {

    private InnerNode parent;

    abstract String getText();

    Node(InnerNode parent) {
        this.parent = parent;
    }

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

        if (!hasParent()) {
            return 0;
        }

        InnerNode parent = getParent();

        offset = parent.getOffset();

        List<Node> subnodes = parent.getSubnodes();
        for (int i = 0; i < subnodes.size(); i++) {
            Node subnode = subnodes.get(i);
            if (subnodes.get(i) == this) {
                return offset;
            } else {
                offset += subnode.getText().length();
            }
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

    public Leaf(InnerNode parent, String text) {
        super(parent);
        this.text = text;
    }

    public Leaf(InnerNode parent, String text, int repeats) {
        super(parent);

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

    public SelectableNode(InnerNode parent) {
        super(parent);
    }

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

    public InsertionPoint(InnerNode parent, String text) {
        super(parent);
        this.text = "(*" + text + "*)";
    }

}

class OptionalInsertionPoint extends InsertionPoint {

    public OptionalInsertionPoint(InnerNode parent, String text) {
        super(parent, text);

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

    public RequiredInsertionPoint(InnerNode parent, String text) {
        super(parent, text);
    }

}

abstract class InnerNode extends SelectableNode {

    InnerNode(InnerNode parent) {
        super(parent);
    }

    protected void setSubnodeParentsToThis() {
        for (Node subnode : getSubnodes()) {
            subnode.setParent(this);
        }
    }

    public boolean hasSelectedChild() {
        for (SelectableNode selectableNode : getSelectableSubnodes()) {
            if (selectableNode.isSelectedChild()) {
                return true;
            }
        }
        return false;
    }

//    List<Node> subnodes = new ArrayList<Node>();

    @Override
    String getText() {
        String text = "";
        for (Node subnode : getSubnodes()) {
            text += subnode.getText();
        }
        return text;
    }

    List<SelectableNode> getSelectableSubnodes() {
        List<SelectableNode> selectableSubnodes = new ArrayList<SelectableNode>();

        for (Node subnode : getSubnodes()) {
            if (subnode instanceof SelectableNode) {
                selectableSubnodes.add((SelectableNode) subnode);
            }
        }

        return selectableSubnodes;
    }

    abstract List<Node> getSubnodes();
    
//    List<Node> getSubnodes() {
//        return subnodes;
//    }

    boolean hasAllRequiredSubnodes() {
        for (Node subnode : getSubnodes()) {
            if (subnode instanceof RequiredInsertionPoint) {
                return false;
            }
        }
        return true;
    }

    boolean isComplete() {
        if (!hasAllRequiredSubnodes()) {
            return false;
        }

        for (Node subnode : getSubnodes()) {
            if (subnode instanceof InnerNode) {
                InnerNode subInnerNode = (InnerNode) subnode;
                if (!subInnerNode.isComplete()) {
                    return false;
                }
            }
        }

        return true;
    }
}

final class Proof extends InnerNode {

    final String spacePropVarPlaceholder = " PROP_VAR";
    final String sectionOrProofLinePlaceholder = "SECTION | PROOF_LINE";

    private final Leaf leafA = new Leaf(this, "Parameters ");
    private final List<Node> oneOrMoreSpacePropVars = new ArrayList<Node>();
    private final Leaf leafB = new Leaf(this, " : Prop.\n\n");
    private final List<Node> oneorMoreSectionOrProofLines
            = new ArrayList<Node>();

    @Override
    List<Node> getSubnodes() {
        List<Node> toReturn = new ArrayList<Node>();

        toReturn.add(leafA);
        for (Node node : oneOrMoreSpacePropVars) {
            toReturn.add(node);
        }
        toReturn.add(leafB);
        for (Node node : oneorMoreSectionOrProofLines) {
            toReturn.add(node);
        }

        return toReturn;
    }

    Proof() {
        super(null);

        oneOrMoreSpacePropVars
                .add(new RequiredInsertionPoint(this, spacePropVarPlaceholder));
        oneorMoreSectionOrProofLines
                .add(new RequiredInsertionPoint(this, sectionOrProofLinePlaceholder));

    }
}

final class ProofItem extends InnerNode {

    public ProofItem(InnerNode parent) {
        super(parent);
    }

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

final class Section extends InnerNode {

    Section(InnerNode parent) {
        super(parent);
    }

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

final class ProofLine extends InnerNode {

    ProofLine(InnerNode parent) {
        super(parent);
    }

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

final class PropVar extends InnerNode {

    PropVar(InnerNode parent) {
        super(parent);
    }

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
