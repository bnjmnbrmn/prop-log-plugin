
package edu.uiowa.cs.baberman.proplogjedit;

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

	@Override
	public void start() {
		INSTANCE = this;
                
                 Macros.message(jEdit.getActiveView(),
                        "KCMjEditPlugin start() method called");
	}
	
	@Override
	public void stop() {
               
	}

	public static PropLogPlugin getInstance() {
		return INSTANCE;
	}
    
}
