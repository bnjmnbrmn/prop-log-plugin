package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMoreSpacePropVars extends OneOrMore<SpacePropVar> {

    public OneOrMoreSpacePropVars() {
        super(new SpacePropVar(true));
        
        addSubnode(new SpacePropVar());
        
//        appendNewPlaceholder();
//        addSubnode(new OneOrMoreSpacePropVars(false));
//        addSubnode(new SpacePropVar());
//        addSubnode(new OneOrMoreSpacePropVars(false));
    }

    public OneOrMoreSpacePropVars(boolean required) {
        super(required, new SpacePropVar());
    }

    PropVar appendNewPlaceholder() {
        SpacePropVar spv = new SpacePropVar();
        if (getSubnodes().size() == 0) {
            addSubnode(new OneOrMoreSpacePropVars(false));
        }

        addSubnode(spv);
        addSubnode(new OneOrMoreSpacePropVars(false));
        return spv.getPropVar();
    }

}
