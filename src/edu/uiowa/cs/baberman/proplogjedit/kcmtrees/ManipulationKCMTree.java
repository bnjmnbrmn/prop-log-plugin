package edu.uiowa.cs.baberman.proplogjedit.kcmtrees;

import edu.uiowa.cs.baberman.kcm.SubmenuKey;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import edu.uiowa.cs.baberman.proplogjedit.ProofModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author bnjmnbrmn
 */
public class ManipulationKCMTree {

    private final ThirtyKey root;
    private final ProofModel proofModel;

    public ManipulationKCMTree(ProofModel proofModel) {
        this.proofModel = proofModel;

        this.root = ThirtyKey.createRootCard();

        SubmenuKey<ThirtyKey> navKey;
        navKey = this.root.putNewSubmenu(KeyEvent.VK_F);
        navKey.setMenuItemText("Navigate");

        ThirtyKey navMenu = navKey.getSubmenu();
        navMenu.putNewLeaf(ThirtyKey.KeyPosition.H)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ManipulationKCMTree.this.proofModel.moveLeft();
                    }
                }).setMenuItemText("Left");

        navMenu.putNewLeaf(ThirtyKey.KeyPosition.L)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ManipulationKCMTree.this.proofModel.moveRight();
                    }
                }).setMenuItemText("Right");
    }

    /**
     * @return the root
     */
    public ThirtyKey getRoot() {
        return root;
    }

    /**
     * @return the proofModel
     */
    public ProofModel getProofModel() {
        return proofModel;
    }
}
