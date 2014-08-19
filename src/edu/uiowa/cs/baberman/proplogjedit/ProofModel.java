package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.KeyboardCard;
import edu.uiowa.cs.baberman.kcm.SubmenuKey;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import edu.uiowa.cs.baberman.proplogjedit.nodes.OneOrMoreSpacePropVars;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Proof;
import edu.uiowa.cs.baberman.proplogjedit.nodes.PropVar;
import edu.uiowa.cs.baberman.proplogjedit.nodes.SelectableNode;
import edu.uiowa.cs.baberman.proplogjedit.nodes.SlipperyNode;
import edu.uiowa.cs.baberman.proplogjedit.nodes.SpacePropVar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofModel {

    public enum SelectionMode {

        LEAF, BRANCH;
    }

    private Proof root;
    private SelectionMode selectionMode;
    private SelectableNode selectedNode;
    private ProofView proofView;
    private final KeyboardCard navManipKCMRoot;
    private final KeyboardCard propVarKCMRoot;

    //Initialization code (called by constructor): 
    private void initializeNavManipKCMTree() {
        SubmenuKey<ThirtyKey> navKey;
        navKey = getNavManipKCMRoot().putNewSubmenu(KeyEvent.VK_F);
        navKey.setMenuItemText("Navigate");

        ThirtyKey navMenu = navKey.getSubmenu();
        navMenu.putNewLeaf(ThirtyKey.KeyPosition.H)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveLeft();
                    }
                }).setMenuItemText("Left");

        navMenu.putNewLeaf(ThirtyKey.KeyPosition.L)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveRight();
                    }
                }).setMenuItemText("Right");

        navMenu.putNewLeaf(ThirtyKey.KeyPosition.K)
                .addPressAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ProofModel.this.goToParent();
                    }
                }).setMenuItemText("Parent");

        navMenu.putNewLeaf(ThirtyKey.KeyPosition.J)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ProofModel.this.goToSelectedChild();
                    }
                }).setMenuItemText("Selected Child");

    }

    private void initializePropVarKCMTree() {

        //Add letter typing keys
        for (ThirtyKey.KeyPosition kp : ThirtyKey.KeyPosition.values()) {
            final String letter = kp.getKeyLabel().toLowerCase();
            if (Character.isLetter(letter.charAt(0))) {
                propVarKCMRoot.putNewLeaf(kp.getVK_CODE())
                        .setMenuItemText(letter)
                        .addPressAction(new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                PropVar selectedPropVar = (PropVar) getSelectedNode();
                                selectedPropVar.appendToIdentifierString(letter);
                            }
                        });
            }
        }

        //Add Add Prop Var key
        propVarKCMRoot.putNewLeaf(ThirtyKey.KeyPosition.COMMA.getVK_CODE())
                .setMenuItemText("Add Prop Var")
                .addPressAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PropVar selectedPropVar = (PropVar) getSelectedNode();
                        SpacePropVar parent
                        = (SpacePropVar) selectedPropVar.getParent();
                        OneOrMoreSpacePropVars grandparent
                        = (OneOrMoreSpacePropVars) parent.getParent();
                        int i = grandparent.getIndexAmongNonPlaceholdersOf(parent);
                        SpacePropVar newSpacePropVar = new SpacePropVar();
                        grandparent
                        .addAtNonPlaceholderPosition(i + 1,
                                newSpacePropVar);
                        setSelectedNode(newSpacePropVar.getPropVar());
                    }
                });

        //Add Nav-Manip Mode key
        propVarKCMRoot.putNewLeaf(ThirtyKey.KeyPosition.PERIOD.getVK_CODE())
                .setMenuItemText("Nav-Manip Mode")
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PropLogPlugin
                        .getInstance()
                        .getPropLogKCMS()
                        .setCurrentRoot(navManipKCMRoot);
                    }

                });

        //Add Backspace key
        propVarKCMRoot.putNewLeaf(ThirtyKey.KeyPosition.SLASH.getVK_CODE())
                .setMenuItemText("Backspace")
                .addPressAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PropVar selectedPropVar = (PropVar) getSelectedNode();
                        selectedPropVar.applyBackspace();
                    }
                });
    }

    ProofModel() {
        proofView = new ProofView(this);
        root = new Proof(this);
        this.selectionMode = SelectionMode.LEAF;
        this.selectedNode = root.getSelectableLeaves().get(1);

        navManipKCMRoot = ThirtyKey.createRootCard();
        initializeNavManipKCMTree();
        propVarKCMRoot = ThirtyKey.createRootCard();
        initializePropVarKCMTree();

        KCMS propLogKCMS = PropLogPlugin.getInstance().getPropLogKCMS();
        propLogKCMS.addRoot(navManipKCMRoot);
        propLogKCMS.addRoot(propVarKCMRoot);
        propLogKCMS.setCurrentRoot(propVarKCMRoot);

        proofView.update();
    }

    //Getters and setters:
    public KeyboardCard getNavManipKCMRoot() {
        return navManipKCMRoot;
    }

    public KeyboardCard getPropVarKCMRoot() {
        return propVarKCMRoot;
    }

    public Proof getRoot() {
        return root;
    }

    public void setRoot(Proof root) {
        this.root = root;
    }

    public ProofView getProofView() {
        return proofView;
    }

    public void setProofView(ProofView proofView) {
        this.proofView = proofView;
    }

    public SelectionMode getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
        getProofView().update();
    }

    public SelectableNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(SelectableNode selectedNode) {
        this.selectedNode = selectedNode;
        getProofView().update();
    }

    //Convenience methods:
    public List<SelectableNode> getCurrentSelectableNodeListInclusive() {
        if (getSelectionMode().equals(SelectionMode.LEAF)) {
            return root.getSelectableLeaves();
        } else { //if getSelectionMode().equals(SelectionMode.BRANCH)
            return getSelectedNode().getPeerBranchSelectionListInclusive();
        }
    }

    public List<SelectableNode> getCurrentSelectableNodeListExclusive() {
        List<SelectableNode> list = getCurrentSelectableNodeListInclusive();
        list.remove(getSelectedNode());
        return list;
    }

    //Navigation and manipulation actions:
    public void moveLeft() {
        int index;
        index
                = getCurrentSelectableNodeListInclusive().indexOf(getSelectedNode());

        if (index > 0) {
            SelectableNode toSelect;
            toSelect
                    = getCurrentSelectableNodeListInclusive().get(index - 1);
            setSelectedNode(toSelect);
            proofView.update();
        }

    }

    public void moveRight() {
        int index;
        index
                = getCurrentSelectableNodeListInclusive().indexOf(getSelectedNode());

        if (index < getCurrentSelectableNodeListInclusive().size() - 1) {
            SelectableNode toSelect;
            toSelect
                    = getCurrentSelectableNodeListInclusive().get(index + 1);
            setSelectedNode(toSelect);
            proofView.update();
        }
    }

    public void goToParent() {
        if (getSelectionMode() == SelectionMode.LEAF) {
            setSelectionMode(SelectionMode.BRANCH);
        }
        if (getSelectedNode().hasParent()) {
            getSelectedNode().getParent().setMostRecentlySelectedSubnode(getSelectedNode());
            setSelectedNode(getSelectedNode().getParent());
            while (getSelectedNode() instanceof SlipperyNode 
                    && getSelectedNode().hasParent()) {
                getSelectedNode().getParent().setMostRecentlySelectedSubnode(getSelectedNode());
                setSelectedNode(getSelectedNode().getParent());
            }
                
        }
    }
    
    public void goToSelectedChild() {
        if (getSelectionMode() == SelectionMode.LEAF) {
            return;
        }
        
        if (getSelectedNode().hasSelectableSubnode()) {
            setSelectedNode(getSelectedNode().getMostRecentlySelectedChild());
            while (getSelectedNode() instanceof SlipperyNode
                    && getSelectedNode().hasSelectableSubnode()) {
                setSelectedNode(getSelectedNode().getMostRecentlySelectedChild());
            }
        }
    }
}
