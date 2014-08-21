
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class Formula extends InnerNode {

    public Formula(boolean required) {
        super(required);
    }

    @Override
    public String getPlaceholderText() {
        return "FORMULA";
    }

    @Override
    public void addToRight() {
        //do nothing
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
