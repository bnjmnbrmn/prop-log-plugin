package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMoreSpacePropVars extends OneOrMore<SpacePropVar> {

    public OneOrMoreSpacePropVars() {
        super(new SpacePropVar(true));
        addSubnode(new OneOrMoreSpacePropVars(false));
        addSubnode(new SpacePropVar());
        addSubnode(new OneOrMoreSpacePropVars(false));
    }

    public OneOrMoreSpacePropVars(boolean required) {
        super(required, new SpacePropVar());
    }

}
