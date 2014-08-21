package edu.uiowa.cs.baberman.proplogjedit.nodes;

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

    @Override
    public void addToRight() {
        if (hasParent() 
                && getParent().hasParent() 
                && getParent().getParent() instanceof OneOrMoreSpacePropVars) {
            getParent().getParent().addToRight();
        }
    }


}
