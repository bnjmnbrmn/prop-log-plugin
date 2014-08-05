package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class SpacePropVar extends InnerNode {

    public SpacePropVar(boolean required) {
        super(required);
    }
    
    public SpacePropVar() {
        super();
        addSubnode(new Terminal(" "));
        addSubnode(new PropVar(true));
    }

    @Override
    public String getPlaceholderText() {
        return " PROP_VAR";
    }

    public PropVar getPropVar() {
        return (PropVar) getSubnode(1);
    }

    public void setPropVar(PropVar propVar) {
        removeSubnode(1);
        addSubnode(propVar);
    }

    @Override
    public SelectableNode clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
