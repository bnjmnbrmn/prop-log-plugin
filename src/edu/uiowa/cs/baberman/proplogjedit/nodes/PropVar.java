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

    public PropVar(boolean required) {
        super(required);

//        defaultRootKeyboardCard = ThirtyKey.createRootCard();
//        initializeDefaultRootKeyboardCard();
    }

    @Override
    public String getPlaceholderText() {
        return "PROP_VAR";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
