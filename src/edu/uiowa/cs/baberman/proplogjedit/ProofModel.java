package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.KeyboardCard;
import edu.uiowa.cs.baberman.kcm.SubmenuKey;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Formula;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Identifier;
import edu.uiowa.cs.baberman.proplogjedit.nodes.LineId;
import edu.uiowa.cs.baberman.proplogjedit.nodes.OneOrMoreSpacePropVars;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Proof;
import edu.uiowa.cs.baberman.proplogjedit.nodes.ProofItem;
import edu.uiowa.cs.baberman.proplogjedit.nodes.ProofLine;
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
    private final ThirtyKey navManipKCMRoot;
    private final ThirtyKey propVarKCMRoot;
    private final ThirtyKey lineIdKCMRoot;

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

        getNavManipKCMRoot().putNewLeaf(ThirtyKey.KeyPosition.PERIOD)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (getSelectionMode() == SelectionMode.BRANCH) {
                            setSelectionMode(SelectionMode.LEAF);
                        } else {
                            setSelectionMode(SelectionMode.BRANCH);
                        }

                    }
                })
                .setMenuItemText("Toggle Branch vs Leaf Selection");

        getNavManipKCMRoot().putNewSubmenu(KeyEvent.VK_L)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getSelectedNode().addToRight();
                    }
                }).setMenuItemText("Add to Right");

        SubmenuKey<ThirtyKey> setToKey;
        setToKey = getNavManipKCMRoot().putNewSubmenu(KeyEvent.VK_J);
        setToKey.addPressAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getSelectedNode().setTo();
            }
        });
        setToKey.setMenuItemText("Set to");

        ThirtyKey setToMenu = setToKey.getSubmenu();
        setToMenu.putNewLeaf(ThirtyKey.KeyPosition.R)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (getSelectedNode() instanceof ProofItem) {
                            ((ProofItem) getSelectedNode()).setToProofLine();
                        }
                    }
                }).setMenuItemText("Proof Line");
    }

    private void initializeIdentifierEditingKCMTreeRootCard(ThirtyKey root) {
        //Add letter typing keys
        for (ThirtyKey.KeyPosition kp : ThirtyKey.KeyPosition.values()) {
            final String letter = kp.getKeyLabel().toLowerCase();
            if (Character.isLetter(letter.charAt(0))) {
                root.putNewLeaf(kp.getVK_CODE())
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

        //Add Nav-Manip Mode key
        root.putNewLeaf(ThirtyKey.KeyPosition.PERIOD.getVK_CODE())
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
        root.putNewLeaf(ThirtyKey.KeyPosition.SLASH.getVK_CODE())
                .setMenuItemText("Backspace")
                .addPressAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Identifier selectedIdentifier = (Identifier) getSelectedNode();
                        selectedIdentifier.applyBackspace();
                    }
                });
    }

    private void initializePropVarKCMTree() {

        initializeIdentifierEditingKCMTreeRootCard(propVarKCMRoot);

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

    }

    private void initializeLineIdKCMTree() {
        initializeIdentifierEditingKCMTreeRootCard(lineIdKCMRoot);

        lineIdKCMRoot.putNewLeaf(ThirtyKey.KeyPosition.COMMA.getVK_CODE())
                .setMenuItemText("Go to Formula")
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LineId selectedLineId = (LineId) getSelectedNode();
                        ProofLine parent = (ProofLine) (getSelectedNode().getParent());
                        Formula formulaForLineformulaForLine;
                        formulaForLineformulaForLine = (Formula) parent.getFormula();
                        setSelectedNode(formulaForLineformulaForLine);
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
        lineIdKCMRoot = ThirtyKey.createRootCard();
        initializeLineIdKCMTree();

        KCMS propLogKCMS = PropLogPlugin.getInstance().getPropLogKCMS();
        propLogKCMS.addRoot(navManipKCMRoot);
        propLogKCMS.addRoot(propVarKCMRoot);
        propLogKCMS.addRoot(lineIdKCMRoot);
        propLogKCMS.setCurrentRoot(propVarKCMRoot);

        proofView.update();
    }

    //Getters and setters:
    public ThirtyKey getNavManipKCMRoot() {
        return navManipKCMRoot;
    }

    public ThirtyKey getPropVarKCMRoot() {
        return propVarKCMRoot;
    }

    public ThirtyKey getLineIdKCMRoot() {
        return lineIdKCMRoot;
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
        if (selectionMode == SelectionMode.LEAF) {
            while (getSelectedNode().hasSelectableSubnode()) {
                setSelectedNode(getSelectedNode().getMostRecentlySelectedChild());
            }
        }
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
