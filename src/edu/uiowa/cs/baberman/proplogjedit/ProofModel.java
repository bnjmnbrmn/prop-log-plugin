package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.proplogjedit.nodes.Identifier;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Proof;
import edu.uiowa.cs.baberman.proplogjedit.nodes.InnerNode;

import edu.uiowa.cs.baberman.proplogjedit.nodes.Node;

import edu.uiowa.cs.baberman.proplogjedit.nodes.SelectableNode;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Terminal;
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

    /**
     * @return the root
     */
    public Proof getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public final void setRoot(Proof root) {
        this.root = root;
    }

    /**
     * @return the selectionMode
     */
    public SelectionMode getSelectionMode() {
        return selectionMode;
    }

    /**
     * @param selectionMode the selectionMode to set
     */
    public final void setSelectionMode(SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
    }

    /**
     * @return the selectedNode
     */
    public SelectableNode getSelectedNode() {
        return selectedNode;
    }

    /**
     * @param selectedNode the selectedNode to set
     */
    public final void setSelectedNode(SelectableNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    /**
     * @return the proofView
     */
    public final ProofView getProofView() {
        return proofView;
    }

    /**
     * @param proofView the proofView to set
     */
    public final void setProofView(ProofView proofView) {
        this.proofView = proofView;
    }

    void goLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void goRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void respondToLetterPress(String letter) {
        if (getSelectedNode() instanceof Identifier) {
            ((Identifier) getSelectedNode()).respondToLetterPress(letter);
        }
    }

    public enum SelectionMode {
        LEAF, BRANCH;
    }

    private Proof root;
    private SelectionMode selectionMode;
    private SelectableNode selectedNode;
    private ProofView proofView;

    ProofModel() {
        setRoot(new Proof());
        setSelectionMode(SelectionMode.LEAF);

        setSelectedNode(root.getSelectableLeaves().get(0));
        setProofView(new ProofView(this));
        getProofView().update();
    }

    public List<SelectableNode> getCurrentSelectableNodeListInclusive() {
        if (getSelectionMode().equals(SelectionMode.LEAF)) {
            return root.getSelectableLeaves();
        } else { //if getSelectionMode().equals(SelectionMode.BRANCH)
            return getSelectedNode().getSelectableSiblingsInclusive();
        }
    }

    public List<SelectableNode> getCurrentSelectableNodeListExclusive() {
        List<SelectableNode> list = getCurrentSelectableNodeListInclusive();
        list.remove(getSelectedNode());
        return list;
    }

}
