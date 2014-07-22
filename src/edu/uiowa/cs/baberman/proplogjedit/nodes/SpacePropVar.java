
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class SpacePropVar extends InnerNode {
    static String placeholderText = " PROP_VAR";

    static String getPlaceholderText() {
        return " PROP_VAR";
    }

    public SpacePropVar(InnerNode parent) {
        super(parent);
        
        getSubnodes().add(new Terminal(this, " "));
        getSubnodes().add(new RequiredInsertionPoint(this, PropVar.class));
    }
    
}
