package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.SubmenuKey;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import java.awt.event.KeyEvent;
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

    JPanel kcmsHolder;

    KCMS propLogKCMS;
    
    public KCMS getPropLogKCMS() {
        return propLogKCMS;
    }

    ThirtyKey mainRoot;

    @Override
    public void start() {
        
        
        INSTANCE = this;

        View view = jEdit.getActiveView();
        DockableWindowManager dwm = view.getDockableWindowManager();
        
        
        Buffer newFile;
        newFile = jEdit.newFile(view.getEditPane());
        
        newFile.setReadOnly(false);
        
        newFile.insert(0, "Hello");
        
        newFile.setReadOnly(true);
        
        mainRoot = ThirtyKey.createRootCard();
        propLogKCMS = new KCMS(mainRoot);

        SubmenuKey<ThirtyKey> navKey;
        navKey = mainRoot.putNewSubmenu(KeyEvent.VK_F);
        navKey.setMenuItemText("Navigate");
    }

    @Override
    public void stop() {
        kcmsHolder.remove(propLogKCMS);
    }

    public static PropLogPlugin getInstance() {
        return INSTANCE;
    }

}
