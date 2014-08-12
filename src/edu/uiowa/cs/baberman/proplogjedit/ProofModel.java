package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.KeyboardCard;
import edu.uiowa.cs.baberman.kcm.SubmenuKey;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Identifier;
import edu.uiowa.cs.baberman.proplogjedit.nodes.Proof;
import edu.uiowa.cs.baberman.proplogjedit.nodes.PropVar;

import edu.uiowa.cs.baberman.proplogjedit.nodes.SelectableNode;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
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
        if (this.selectedNode != null)
            this.selectedNode.setAsSelectedChild(false);
        this.selectedNode = selectedNode;
        this.selectedNode.setAsSelectedChild(true);

//        KCMS propLogKCMS
//                = PropLogPlugin.getInstance().getPropLogKCMS();
//
//        KeyboardCard sndrkc
//                = selectedNode.getDefaultRootKeyboardCard();
//
//        if (!propLogKCMS.getRoots().contains(sndrkc)) {
//            propLogKCMS.addRoot(sndrkc);
//        }
//        propLogKCMS.setCurrentRoot(sndrkc);

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

    void respondToLetterPress(String letter) {
        if (getSelectedNode() instanceof Identifier) {
            ((Identifier) getSelectedNode()).respondToLetterPress(letter);
        }
    }

    public void appendStringToSelectedNode(String str) {
        getSelectedNode().appendString(str);

        proofView.update();
    }

    public KeyboardCard getNavManipKCMRoot() {
        return navManipKCMRoot;
    }

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

    }

    public enum SelectionMode {

        LEAF, BRANCH;
    }

    private Proof root;
    private SelectionMode selectionMode;
    private SelectableNode selectedNode;
    private ProofView proofView;

    private final KeyboardCard navManipKCMRoot;

    ProofModel() {
        setRoot(new Proof(this));
        setSelectionMode(SelectionMode.LEAF);
        setSelectedNode(root.getSelectableLeaves().get(1));

        KCMS propLogKCMS = PropLogPlugin.getInstance().getPropLogKCMS();
        navManipKCMRoot = ThirtyKey.createRootCard();
        initializeNavManipKCMTree();
        propLogKCMS.addRoot(navManipKCMRoot);
        
        propLogKCMS.addRoot(getSelectedNode().getDefaultRootKeyboardCard());
        propLogKCMS.setCurrentRoot(getSelectedNode().getDefaultRootKeyboardCard());

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
