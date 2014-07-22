
package edu.uiowa.cs.baberman.proplogjedit.nodes;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class InsertionPoint extends SelectableNode {
    private String text;

    @Override
    public String getText() {
        return text;
    }

    public InsertionPoint(InnerNode parent, Class<? extends SelectableNode> clazz) {
        super(parent);
        //        String debug = clazz.getName() + "\n";
        //        debug += clazz.getDeclaredFields().length + "\n";
        //
        //        for (Field f : clazz.getDeclaredFields())
        //            debug += f.getName() + "\n";
        //
        //        Macros.message(jEdit.getActiveView(), debug);
        try {
            this.text = "(*" + ((String) clazz.getDeclaredField("placeholderText").get(null)) + "*)";
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(edu.uiowa.cs.baberman.proplogjedit.nodes.InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (SecurityException ex) {
            Logger.getLogger(edu.uiowa.cs.baberman.proplogjedit.nodes.InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(edu.uiowa.cs.baberman.proplogjedit.nodes.InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(edu.uiowa.cs.baberman.proplogjedit.nodes.InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        }
    }
    
}
