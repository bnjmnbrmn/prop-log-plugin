package edu.uiowa.cs.baberman.proplogjedit;

import edu.uiowa.cs.baberman.kcm.KCMS;
import edu.uiowa.cs.baberman.kcm.ThirtyKey;
import javax.swing.JPanel;
import org.gjt.sp.jedit.EBPlugin;
import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.jEdit;

/**
 *
 * @author bnjmnbrmn
 */
public class PropLogPlugin extends EBPlugin {

    public static String NAME = "KCMjEditPlugin";
    public static String AUTHOR = "Benjamin Berman";
    public static String VERSION = "0.0.1";
    public static String OPTION_PREFIX = "options.kcmjeditplugin.";
    private static PropLogPlugin INSTANCE;

    JPanel kcmsHolder;
    
    KCMS propLogKCMS;
    
    ThirtyKey mainRoot;
    
    @Override
    public void start() {
        INSTANCE = this;

        Macros.message(jEdit.getActiveView(),
                "PropLogPlugin start() method called");

        
        mainRoot = ThirtyKey.createRootCard();
        propLogKCMS = new KCMS(mainRoot);
        
        
        kcmsHolder = (JPanel) jEdit.getActiveView()
                .getDockableWindowManager()
                .getDockable("kcmsholder");
        
        kcmsHolder.add(propLogKCMS);
        
    }
    
    @Override
    public void stop() {
        kcmsHolder.remove(propLogKCMS);
    }

    public static PropLogPlugin getInstance() {
        return INSTANCE;
    }

}
