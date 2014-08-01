package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class SpacePropVar extends InnerNode {

    private PropVar propVar;

    public SpacePropVar(boolean required) {
        super(required);
    }

    @Override
    public String getPlaceholderText() {
        return " PROP_VAR";
    }

    public PropVar getPropVar() {
        return propVar;
    }

    public void setPropVar(PropVar propVar) {
        this.propVar = propVar;
    }

    @Override
    public SelectableNode clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
