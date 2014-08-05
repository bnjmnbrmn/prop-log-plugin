package edu.uiowa.cs.baberman.proplogjedit.kcmtrees;

import edu.uiowa.cs.baberman.kcm.KeyboardCard;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import edu.uiowa.cs.baberman.proplogjedit.ProofModel;
import edu.uiowa.cs.baberman.proplogjedit.PropLogPlugin;
import edu.uiowa.cs.baberman.proplogjedit.nodes.PropVar;
import edu.uiowa.cs.baberman.proplogjedit.nodes.SelectableNode;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author bnjmnbrmn
 */
public class PropVarEntryKCMTree {

    private final ThirtyKey root;
    private final ProofModel proofModel;

    public PropVarEntryKCMTree(ProofModel proofModel) {
        this.proofModel = proofModel;
        
        this.root = ThirtyKey.createRootCard();
        
        String letter;

        letter = "q";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.Q)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "w";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.W)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "e";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.E)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "r";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.R)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "t";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.T)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "y";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.Y)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "u";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.U)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "i";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.I)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "o";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.O)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "p";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.P)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "a";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.A)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "s";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.S)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "d";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.D)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "f";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.F)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "g";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.G)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "h";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.H)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "j";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.J)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "k";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.K)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "l";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.L)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "z";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.Z)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "x";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.X)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "c";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.C)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "v";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.V)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "b";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.B)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "n";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.N)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "m";
        this.root.putNewLeaf(ThirtyKey.KeyPosition.M)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));

        //to do...
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

}
