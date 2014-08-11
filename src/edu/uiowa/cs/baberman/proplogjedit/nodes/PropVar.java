package edu.uiowa.cs.baberman.proplogjedit.nodes;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.KeyboardCard;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import edu.uiowa.cs.baberman.proplogjedit.ProofModel;
import edu.uiowa.cs.baberman.proplogjedit.PropLogPlugin;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.jEdit;

/**
 *
 * @author bnjmnbrmn
 */
public final class PropVar extends Identifier {

    private final ThirtyKey defaultRootKeyboardCard;

    private void initializeDefaultRootKeyboardCard() {

        class LetterPressAction extends AbstractAction {

            String letter;

            public LetterPressAction(String letter) {
                this.letter = letter;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                getProofModel().appendStringToSelectedNode(letter);
            }
        }

        for (ThirtyKey.KeyPosition kp : ThirtyKey.KeyPosition.values()) {
            String letter = kp.getKeyLabel().toLowerCase();
            if (Character.isLetter(letter.charAt(0))) {
                defaultRootKeyboardCard.putNewLeaf(kp)
                        .setMenuItemText(letter)
                        .addPressAction(new LetterPressAction(letter));
            }
        }

        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.COMMA)
                .setMenuItemText("Add Prop Var")
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!isPlaceholder()) {
                            OneOrMoreSpacePropVars listOfSpacePropVars
                            = (OneOrMoreSpacePropVars) getParent()
                            .getParent();

                            PropVar newPropVar
                            = listOfSpacePropVars
                            .appendNewPlaceholder();

                            getProofModel()
                            .setSelectionMode(
                                    ProofModel.SelectionMode.LEAF);

                            getProofModel()
                            .setSelectedNode(newPropVar);

                            getProofModel().getProofView().update();
                        }
                    }
                });
        
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.PERIOD)
                .setMenuItemText("Nav-Manip Mode")
                .addPressAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KCMS propLogKCMS = PropLogPlugin.getInstance().getPropLogKCMS();
                propLogKCMS.setCurrentRoot(getProofModel().getNavManipKCMRoot());
            }
                    
                });
                

    }

    public PropVar(boolean required) {
        super(required);

        defaultRootKeyboardCard = ThirtyKey.createRootCard();
        initializeDefaultRootKeyboardCard();
    }

    @Override
    public String getPlaceholderText() {
        return "PROP_VAR";
    }

    @Override
    public SelectableNode clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KeyboardCard getDefaultRootKeyboardCard() {
        return this.defaultRootKeyboardCard;
    }

}
