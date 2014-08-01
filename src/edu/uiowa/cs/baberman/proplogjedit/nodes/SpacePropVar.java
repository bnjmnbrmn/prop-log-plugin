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

    @Override
    public SpacePropVar clone() {
        
    }
    
    

    @Override
    public boolean respondsToLetterPress() {
        return true;
    }

    @Override
    public void respondToLetterPress(String letter) {
        getPropVar().respondToLetterPress(letter);
    }

    public PropVar getPropVar() {
        return propVar;
    }

    public void setPropVar(PropVar propVar) {
        this.propVar = propVar;
    }
}
