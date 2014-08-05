package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class PropVar extends Identifier {

    public PropVar(boolean required) {
        super(required);        
    }

    @Override
    public String getPlaceholderText() {
        return "PROP_VAR";
    }

    @Override
    public SelectableNode clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
