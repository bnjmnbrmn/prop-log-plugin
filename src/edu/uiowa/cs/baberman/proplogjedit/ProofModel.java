package edu.uiowa.cs.baberman.proplogjedit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.jEdit;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofModel {

    private Proof root;

    private SelectableNode selectedNode = null;

    ProofModel() {
        root = new Proof();
        setSelectedNode(root.getSelectableSubnodes().get(0));
        ProofView proofView = new ProofView(this);
        addProofView(proofView);
    }

    private List<ProofView> proofViews = new ArrayList<ProofView>();

    List<ProofView> getProofViews() {
        return proofViews;
    }

    final void addProofView(ProofView proofView) {
        proofViews.add(proofView);

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

        if (indexOfCurrent == selectableNodes.size() - 1) {
            return;
        }

        setSelectedNode(selectableNodes.get(indexOfCurrent + 1));
    }

    void goToPreviousSelectableSibling() {
        List<SelectableNode> selectableNodes
                = getSelectedNode().getParent().getSelectableSubnodes();
        int indexOfCurrent = selectableNodes.indexOf(getSelectedNode());

        if (indexOfCurrent == 0) {
            return;
        }

        setSelectedNode(selectableNodes.get(indexOfCurrent - 1));
    }

    void appendCharacter(char toAppend) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

class Terminal extends Node {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public Terminal(InnerNode parent, String text) {
        super(parent);
        this.text = text;
    }

    public Terminal(InnerNode parent, String text, int repeats) {
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

    private String text;

    @Override
    public String getText() {
        return text;
    }

    public InsertionPoint(InnerNode parent, Class<? extends SelectableNode> clazz) {
        super(parent);

//        String debug = clazz.getName() + "\n";
//        debug += clazz.getDeclaredFields().length + "\n";
//        
//        for (Field f : clazz.getDeclaredFields())
//            debug += f.getName() + "\n";
//        
//        Macros.message(jEdit.getActiveView(), debug);
        try {
            this.text = "(*" + ((String) clazz.getDeclaredField("placeholderText").get(null)) + "*)";
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (SecurityException ex) {
            Logger.getLogger(InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        }

    }

}

class OptionalInsertionPoint extends InsertionPoint {

    public OptionalInsertionPoint(InnerNode parent, Class<? extends SelectableNode> clazz) {
        super(parent, clazz);
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

    public RequiredInsertionPoint(InnerNode parent, Class<? extends SelectableNode> clazz) {
        super(parent, clazz);
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

//    abstract List<Node> getSubnodes();
    List<Node> subnodes = new ArrayList<Node>();

    List<Node> getSubnodes() {
        return subnodes;
    }

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

    static String placeholderText = "PROOF";

    Proof() {
        super(null);

        getSubnodes().add(new Terminal(this, "Parameters "));
        getSubnodes().add(new RequiredInsertionPoint(this, SpacePropVar.class));
        getSubnodes().add(new Terminal(this, " : Prop.\n\n"));
        getSubnodes().add(new RequiredInsertionPoint(this, ProofItem.class));

    }
}

final class ProofItem extends InnerNode {

    static String placeholderText = "SECTION | PROOF_LINE";

    static String getPlaceholderText() {
        return "SECTION | PROOF_LINE";
    }

    public ProofItem(InnerNode parent) {
        super(parent);
    }

}

final class Section extends InnerNode {

    static String getPlaceholderText() {
        return "SECTION";
    }

    static String placeholderText = "SECTION";

    Section(InnerNode parent) {
        super(parent);
    }

}

final class ProofLine extends InnerNode {

    static String placeholderText = "PROOF_LINE";

    static String getPlaceholderText() {
        return "PROOF_LINE";
    }

    ProofLine(InnerNode parent) {
        super(parent);
    }

}

final class SpacePropVar extends InnerNode {

    static String placeholderText = " PROP_VAR";

    static String getPlaceholderText() {
        return " PROP_VAR";
    }

    public SpacePropVar(InnerNode parent) {
        super(parent);
    }

}

abstract class Identifier extends SelectableNode {

    String text = "";

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Identifier(InnerNode parent) {
        super(parent);
    }

}

final class PropVar extends Identifier {

    static String placeholderText = "PROP_VAR";

    static String getPlaceholderText() {
        return "PROP_VAR";
    }

    PropVar(InnerNode parent) {
        super(parent);
    }

}
