package edu.uiowa.cs.baberman.proplogjedit.nodes;

import edu.uiowa.cs.baberman.kcm.KeyboardCard;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import edu.uiowa.cs.baberman.proplogjedit.ProofModel;
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
        String letter;
        letter = "q";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.Q)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "w";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.W)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "e";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.E)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "r";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.R)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "t";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.T)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "y";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.Y)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "u";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.U)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "i";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.I)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "o";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.O)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "p";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.P)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "a";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.A)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "s";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.S)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "d";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.D)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "f";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.F)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "g";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.G)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "h";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.H)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "j";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.J)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "k";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.K)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "l";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.L)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "z";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.Z)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "x";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.X)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "c";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.C)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "v";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.V)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "b";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.B)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "n";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.N)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "m";
        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.M)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));

        defaultRootKeyboardCard.putNewLeaf(ThirtyKey.KeyPosition.COMMA)
                .setMenuItemText("Add Propositional Variable")
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
