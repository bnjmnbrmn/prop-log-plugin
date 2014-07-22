package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.proplogjedit.nodes.Identifier;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Proof;
import edu.uiowa.cs.baberman.proplogjedit.nodes.InnerNode;
import edu.uiowa.cs.baberman.proplogjedit.nodes.InsertionPoint;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Node;
import edu.uiowa.cs.baberman.proplogjedit.nodes.RequiredInsertionPoint;
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

    private Proof root;

    private SelectableNode selectedNode = null;

    ProofModel() {
        root = new Proof();
        setSelectedNode(root.getFirstRequiredInsertionPointDescendant());
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














