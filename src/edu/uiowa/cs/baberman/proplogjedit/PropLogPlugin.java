package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.SubmenuKey;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
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

//        View view = jEdit.getActiveView();
//        DockableWindowManager dwm = view.getDockableWindowManager();
        
        mainRoot = ThirtyKey.createRootCard();
        propLogKCMS = new KCMS(mainRoot);

        SubmenuKey<ThirtyKey> navKey;
        navKey = mainRoot.putNewSubmenu(KeyEvent.VK_F);
        navKey.setMenuItemText("Navigate");
        
        
//        Buffer newFile;
//        newFile = jEdit.newFile(jEdit.getActiveView().getEditPane());
//        
//        proofModel = new ProofModel();
//        proofView = new ProofView(newFile);
//        proofModel.addProofView(proofView);
        
    }

    @Override
    public void stop() {
        for (ProofController proofController : proofControllers)
            for (ProofView proofView : proofController.getProofModel().getProofViews())
                proofView.clearTextAreaExtensions();
    }

    public static PropLogPlugin getInstance() {
        return INSTANCE;
    }

    public void createNewProof() {
        currentProofController = new ProofController();
        proofControllers.add(currentProofController);
    }
}
