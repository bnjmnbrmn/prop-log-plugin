package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.SubmenuKey;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EBPlugin;
import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.DockableWindowManager;
import org.gjt.sp.jedit.jEdit;

/**
 *
 * @author bnjmnbrmn
 */
public class PropLogPlugin extends EBPlugin {

    public static String NAME = "KCMjEditPlugin";
    public static String AUTHOR = "Benjamin Berman";
    public static String VERSION = "0.0.1";
    public static String OPTION_PREFIX = "options.proplogplugin.";
    private static PropLogPlugin INSTANCE;

    KCMS propLogKCMS;

    public KCMS getPropLogKCMS() {
        return propLogKCMS;
    }

    ThirtyKey commandRoot;
    ThirtyKey variableEntryRoot;

    private void setUpKCMS() {
        setUpVariableEntryRoot();
        setUpCommandRoot();

        propLogKCMS = new KCMS(variableEntryRoot);
        propLogKCMS.addRoot(commandRoot);
    }

    private ProofModel getCurrentProofModel() {
        return currentProofModel;
    }

    private void setUpVariableEntryRoot() {
        variableEntryRoot = ThirtyKey.createRootCard();

        String letter;

        letter = "q";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.Q)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "w";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.W)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "e";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.E)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "r";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.R)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "t";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.T)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "y";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.Y)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "u";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.U)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "i";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.I)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "o";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.O)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "p";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.P)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "a";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.A)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "s";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.S)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "d";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.D)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "f";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.F)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "g";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.G)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "h";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.H)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "j";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.J)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "k";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.K)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "l";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.L)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "z";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.Z)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "x";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.X)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "c";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.C)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "v";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.V)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "b";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.B)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "n";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.N)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));
        letter = "m";
        variableEntryRoot.putNewLeaf(ThirtyKey.KeyPosition.M)
                .setMenuItemText(letter)
                .addPressAction(new LetterPressAction(letter));

        //to do...
    }

    class LetterPressAction extends AbstractAction {

        String letter;

        public LetterPressAction(String letter) {
            this.letter = letter;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
//            SelectableNode sn = currentProofModel.getSelectedNode();
//            
//            if (sn instanceof InsertionPoint 
//                    && sn.getText() == 
//                    new InsertionPoint() {
//                        
//                    }(null, 
//                            PropVar.getPlaceholderText()).getText()) {
//                 
//            } else if sn
        }

    }

    private void setUpCommandRoot() {
        commandRoot = ThirtyKey.createRootCard();

        SubmenuKey<ThirtyKey> navKey;
        navKey = commandRoot.putNewSubmenu(KeyEvent.VK_F);
        navKey.setMenuItemText("Navigate");

        ThirtyKey navMenu = navKey.getSubmenu();
        navMenu.putNewLeaf(ThirtyKey.KeyPosition.H)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        goLeft();
                    }
                }).setMenuItemText("Left");

        navMenu.putNewLeaf(ThirtyKey.KeyPosition.L)
                .addPressAction(new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        goRight();
                    }
                }).setMenuItemText("Right");
    }

    @Override
    public void start() {
        INSTANCE = this;

        setUpKCMS();
    }

    @Override
    public void stop() {
//        for (ProofController proofController : proofControllers) {
//            for (ProofView proofView : proofController.getProofModel().getProofViews()) {
//                proofView.clearTextAreaExtensions();
//            }
////        }

        for (ProofView proofView : currentProofModel.getProofViews()) {
            proofView.clearTextAreaExtensions();
        }
    }

    public static PropLogPlugin getInstance() {
        return INSTANCE;
    }

    public void goLeft() {
        currentProofModel.goToPreviousSelectableSibling();
    }

    public void goRight() {
        currentProofModel.goToNextSelectableSibling();
    }

    ProofModel currentProofModel;
    List<ProofModel> proofModels = new ArrayList<ProofModel>();

    public void createNewProof() {

        try {

//            Buffer newFile;
//            newFile = jEdit.newFile(jEdit.getActiveView().getEditPane());
            currentProofModel = new ProofModel();
            proofModels.add(currentProofModel);
//            ProofView proofView = new ProofView(currentProofModel);
//            currentProofModel.addProofView(proofView);

            jEdit.getActiveView().getBuffer().setReadOnly(false);
            jEdit.getActiveView().getBuffer().insert(jEdit.getActiveView().getBuffer().getLength(), "\n\n");
            jEdit.getActiveView().getBuffer().setReadOnly(true);
            jEdit.getActiveView().getTextArea().moveCaretPosition(jEdit.getActiveView().getBuffer().getLength(), false);
            jEdit.getActiveView().getTextArea().setCaretBlinkEnabled(false);

        } catch (Exception e) {
            displayException(e);
        }

    }

    private void displayException(Exception e) {
        String st = "";

        for (int i = 0; i < e.getStackTrace().length; i++) {
            if (i >= 10) {
                st += "...";
                break;
            }
            StackTraceElement ste = e.getStackTrace()[i];
            st += ste.toString() + "\n";

        }

        Macros.message(jEdit.getActiveView(), e + "\n" + st);
    }
}
