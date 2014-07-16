package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.SubmenuKey;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
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

    ThirtyKey mainRoot;

    Set<ProofController> proofControllers = new HashSet<ProofController>();
    ProofController currentProofController;

    @Override
    public void start() {

        INSTANCE = this;

        mainRoot = ThirtyKey.createRootCard();
        propLogKCMS = new KCMS(mainRoot);

        SubmenuKey<ThirtyKey> navKey;
        navKey = mainRoot.putNewSubmenu(KeyEvent.VK_F);
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
    public void stop() {
        for (ProofController proofController : proofControllers) {
            for (ProofView proofView : proofController.getProofModel().getProofViews()) {
                proofView.clearTextAreaExtensions();
            }
        }
    }

    public static PropLogPlugin getInstance() {
        return INSTANCE;
    }
    
    public void goLeft() {
        currentProofController.goLeft();
    }
    
    public void goRight() {
        currentProofController.goRight();
    }

    public void createNewProof() {

        try {

            currentProofController = new ProofController();
            proofControllers.add(currentProofController);

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
