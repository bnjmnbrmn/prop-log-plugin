package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.proplogjedit.kcmtrees.ManipulationKCMTree;
import edu.uiowa.cs.baberman.proplogjedit.kcmtrees.PropVarEntryKCMTree;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Identifier;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Proof;
import edu.uiowa.cs.baberman.proplogjedit.nodes.PropVar;

import edu.uiowa.cs.baberman.proplogjedit.nodes.SelectableNode;
import java.util.List;

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

    public void moveLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void moveRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void respondToLetterPress(String letter) {
        if (getSelectedNode() instanceof Identifier) {
            ((Identifier) getSelectedNode()).respondToLetterPress(letter);
        }
    }

    public void appendStringToSelectedNode(String str) {
        getSelectedNode().appendString(str);
        
        proofView.update();
    }

    public enum SelectionMode {
        LEAF, BRANCH;
    }

    private Proof root;
    private SelectionMode selectionMode;
    private SelectableNode selectedNode;
    private ProofView proofView;
    
    private final PropVarEntryKCMTree propVarEntryKCMTree 
            = new PropVarEntryKCMTree(this);
    private final ManipulationKCMTree manipulationKCMTree
            = new ManipulationKCMTree(this);

    ProofModel() {
        setRoot(new Proof(this));
        setSelectionMode(SelectionMode.LEAF);
        setSelectedNode(root.getSelectableLeaves().get(1));
        
        KCMS propLogKCMS = PropLogPlugin.getInstance().getPropLogKCMS();
        
        propLogKCMS.addRoot(
                propVarEntryKCMTree.getRoot());
        propLogKCMS.setCurrentRoot(
                propVarEntryKCMTree.getRoot());
        propLogKCMS.addRoot(
            manipulationKCMTree.getRoot());
        
        
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
